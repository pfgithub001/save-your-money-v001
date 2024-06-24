#!/bin/bash

toLowerCase() {
    local className=$1
    echo "${className}" | awk '{print tolower(substr($0, 1, 1)) substr($0, 2)}'
}

pluralize() {
    local singular=$1
    local plural=""
    
    # Check the end of the word and apply pluralization rules
    if [[ $singular == *s || $singular == *sh || $singular == *ch || $singular == *x || $singular == *z ]]; then
        plural="${singular}es"
    elif [[ $singular == *y && ! $singular =~ [aeiou]y ]]; then
        plural="${singular%y}ies"
    elif [[ $singular == *f ]]; then
        plural="${singular%f}ves"
    elif [[ $singular == *fe ]]; then
        plural="${singular%fe}ves"
    else
        plural="${singular}s"
    fi
    
    echo $plural
}

createDirectories() {

    # Define the directory name
    local dirname1="exception"
    local dirname2="repository"
    local dirname3="service"
    local dirname4="controller"


    # Check if the directory exists
    if [ ! -d "$dirname1" ]; then
        # Create the directory
        mkdir "$dirname1"
        echo "Directory '$dirname1' created."
    fi

    # Check if the directory exists
    if [ ! -d "$dirname2" ]; then
        # Create the directory
        mkdir "$dirname2"
        echo "Directory '$dirname2' created."
    fi

    # Check if the directory exists
    if [ ! -d "$dirname3" ]; then
        # Create the directory
        mkdir "$dirname3"
        echo "Directory '$dirname3' created."
    fi

    # Check if the directory exists
    if [ ! -d "$dirname4" ]; then
        # Create the directory
        mkdir "$dirname4"
        echo "Directory '$dirname4' created."
    fi
}

# Function to get the Java package name
getPackageName() {
    local current_dir="$PWD"
    local package_name=""

    # Loop until we find the 'java' folder
    while [[ "${current_dir##*/}" != "java" ]]; do
        package_name="${current_dir##*/}${package_name:+.$package_name}"  # Concatenate directory names
        current_dir=$(dirname "$current_dir")  # Move up to the parent directory
    done

    # Remove leading dot if present
    echo "${package_name#.}"
}

createExceptionFile() {

    local entity=$1
    local package=$(getPackageName)

    cat <<EOF > "exception/${entity}NotFoundException.java"
package $package.exception;

public class ${entity}NotFoundException extends RuntimeException {
    public ${entity}NotFoundException(String message){
        super(message);
    }
}
EOF

    echo "Exception created for entity '$entity'."
}

createRepositoryFile() {

    local entity=$1
    local package=$(getPackageName)

    cat <<EOF > "repository/${entity}Repo.java"
package $package.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import sbapp.save_your.money.entity.${entity};

public interface ${entity}Repo extends JpaRepository<${entity}, Long> {
    
    Optional<${entity}> find${entity}ById(Long id);
    void delete${entity}ById(Long id);
}
EOF

    echo "Repository created for entity '$entity'."
}

createServiceFile() {
    local entity=$1
    local package=$(getPackageName)

    cat <<EOF > "service/${entity}Service.java"
package $package.service;

import jakarta.transaction.Transactional;
import ${package}.dto.${entity}DTO;
import ${package}.entity.${entity};
import ${package}.exception.${entity}NotFoundException;
import ${package}.repository.${entity}Repo;
import ${package}.mapper.${entity}Mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ${entity}Service {

    private final ${entity}Repo $(toLowerCase "${entity}")Repo;

    @Autowired
    public ${entity}Service(${entity}Repo $(toLowerCase "${entity}")Repo) { this.$(toLowerCase "${entity}")Repo = $(toLowerCase "${entity}")Repo; }

    public List<${entity}DTO> findAll$(pluralize "${entity}")() { 
        List<${entity}> $(toLowerCase "$(pluralize "${entity}")") = $(toLowerCase "${entity}")Repo.findAll();
        return $(toLowerCase "$(pluralize "${entity}")").stream()
                .map(${entity}Mapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    public ${entity}DTO find${entity}ById(Long id) {
        ${entity} $(toLowerCase "${entity}") = $(toLowerCase "${entity}")Repo.find${entity}ById(id)
                .orElseThrow(() -> new ${entity}NotFoundException("${entity} by id " + id + " was not found"));
        return ${entity}Mapper.INSTANCE.toDto($(toLowerCase "${entity}"));
    }

    public ${entity}DTO add${entity}(${entity}DTO $(toLowerCase "${entity}")DTO) { 
        ${entity} $(toLowerCase "${entity}") = ${entity}Mapper.INSTANCE.toEntity($(toLowerCase "${entity}")DTO);
        ${entity} saved${entity} = $(toLowerCase "${entity}")Repo.save($(toLowerCase "${entity}"));
        return ${entity}Mapper.INSTANCE.toDto(saved${entity});
    }

    public ${entity}DTO update${entity}(${entity}DTO $(toLowerCase "${entity}")DTO) { 
        ${entity} $(toLowerCase "${entity}") = ${entity}Mapper.INSTANCE.toEntity($(toLowerCase "${entity}")DTO);
        ${entity} updated${entity} = $(toLowerCase "${entity}")Repo.save($(toLowerCase "${entity}"));
        return ${entity}Mapper.INSTANCE.toDto(updated${entity});
    }

    public void delete${entity}(Long id) { 
        $(toLowerCase "${entity}")Repo.delete${entity}ById(id); 
    }

}
EOF

    echo "Service created for entity '$entity'."
}

createControllerFile() {
    local entity=$1
    local package=$(getPackageName)

    cat <<EOF > "controller/${entity}Controller.java"
package $package.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import sbapp.save_your.money.dto.${entity}DTO;
import sbapp.save_your.money.service.${entity}Service;

@RestController
@RequestMapping("/$(toLowerCase "${entity}")")
@CrossOrigin
public class ${entity}Controller {

    private final ${entity}Service $(toLowerCase "${entity}")Service;

    public ${entity}Controller(${entity}Service $(toLowerCase "${entity}")Service) { 
        this.$(toLowerCase "${entity}")Service = $(toLowerCase "${entity}")Service; 
    }

    @GetMapping("/all")
    public ResponseEntity<List<${entity}DTO>> getAll$(pluralize "${entity}")() {
        List<${entity}DTO> $(toLowerCase "$(pluralize "${entity}")")DTO = $(toLowerCase "${entity}")Service.findAll$(pluralize "${entity}")();
        return new ResponseEntity<>($(toLowerCase "$(pluralize "${entity}")")DTO, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<${entity}DTO> get${entity}ById(@PathVariable("id") Long id) {
        ${entity}DTO $(toLowerCase "${entity}")DTO = $(toLowerCase "${entity}")Service.find${entity}ById(id);
        return new ResponseEntity<>($(toLowerCase "${entity}")DTO, HttpStatus.OK);
    }       

    @PostMapping("/add")
    public ResponseEntity<${entity}DTO> add${entity}(@RequestBody ${entity}DTO $(toLowerCase "${entity}")DTO) {
        ${entity}DTO new${entity}DTO = $(toLowerCase "${entity}")Service.add${entity}($(toLowerCase "${entity}")DTO);
        return new ResponseEntity<>(new${entity}DTO, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<${entity}DTO> update${entity}(@RequestBody ${entity}DTO $(toLowerCase "${entity}")DTO) {
        ${entity}DTO updated${entity}DTO = $(toLowerCase "${entity}")Service.update${entity}($(toLowerCase "${entity}")DTO);
        return new ResponseEntity<>(updated${entity}DTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete${entity}(@PathVariable("id") Long id) {
        $(toLowerCase "${entity}")Service.delete${entity}(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

EOF

    echo "Controller created for entity '$entity'."
}

iterateAndCreateFiles() {
    # Define the path to the entity folder
    entity_folder="entity"

    # Iterate over all files in the entity folder
    for file in "$entity_folder"/*; do
        # Check if it is a file
        if [[ -f "$file" ]]; then
            # Check if the file contains the @Entity annotation
            if grep -q "@Entity" "$file"; then
                # Extract and print the class name
                class_name=$(grep -E 'public\s+class\s+\w+' "$file" | awk '{print $3}')
                createExceptionFile "${class_name}"
                createRepositoryFile "${class_name}"
                createServiceFile "${class_name}"
                createControllerFile "${class_name}"
            fi
        fi
    done
}

main() {
    createDirectories
    iterateAndCreateFiles
}

main