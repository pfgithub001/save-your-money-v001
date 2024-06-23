#!/bin/bash

# Function to pluralize a word
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

# DEFINE VARIABLES

# Define the directory name
dirname1="exception"
dirname2="repository"
dirname3="service"
dirname4="controller"

# Cretate directories

# Check if the directory exists
if [ ! -d "$dirname1" ]; then
  # Create the directory
  mkdir "$dirname1"
  echo "Directory '$dirname1' created."
else
  echo "Directory '$dirname1' already exists."
fi

# Check if the directory exists
if [ ! -d "$dirname2" ]; then
  # Create the directory
  mkdir "$dirname2"
  echo "Directory '$dirname2' created."
else
  echo "Directory '$dirname2' already exists."
fi

# Check if the directory exists
if [ ! -d "$dirname3" ]; then
  # Create the directory
  mkdir "$dirname3"
  echo "Directory '$dirname3' created."
else
  echo "Directory '$dirname3' already exists."
fi

# Check if the directory exists
if [ ! -d "$dirname4" ]; then
  # Create the directory
  mkdir "$dirname2"
  echo "Directory '$dirname4' created."
else
  echo "Directory '$dirname4' already exists."
fi

# Starting directory
current_dir=$(pwd)
package_name=""

# Loop until we find the 'java' folder
while [[ "${current_dir##*/}" != "java" ]]; do
    package_name="${current_dir##*/}${package_name:+.$package_name}"  # Concatenate directory names
    current_dir=$(dirname "$current_dir")  # Move up to the parent directory
done

# Define the path to the entity folder
entity_folder="entity"

# Check if the entity folder exists
if [[ ! -d "$entity_folder" ]]; then
    echo "Entity folder does not exist."
    exit 1
fi

# Iterate over all files in the entity folder
for file in "$entity_folder"/*; do
    # Check if it is a file
    if [[ -f "$file" ]]; then
        # Check if the file contains the @Entity annotation
        if grep -q "@Entity" "$file"; then
            # Extract and print the class name
            class_name=$(grep -E 'public\s+class\s+\w+' "$file" | awk '{print $3}')
            word_singular=$class_name
            word_singular_lc="$(echo "${word_singular}" | awk '{print tolower(substr($0, 1, 1)) substr($0, 2)}')"
            word_plural=$(pluralize "$word_singular")
            word_plural_lc="$(echo "${word_plural}" | awk '{print tolower(substr($0, 1, 1)) substr($0, 2)}')"
# CREATE EXCEPTION

# Define the file name
file="${dirname1}/${word_singular}NotFoundException.java"

# Create the file with a template content
cat <<EOF > "$file"
package ${package_name}.exception;

public class ${word_singular}NotFoundException extends RuntimeException {
    public ${word_singular}NotFoundException(String message){
        super(message);
    }
}
EOF

echo "File '$file' created."

# CREATE REPOSITORY


# Define the file name
file="${dirname2}/${word_singular}Repo.java"

# Create the file with a template content
cat <<EOF > "$file"
package ${package_name}.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import sbapp.save_your.money.entity.${word_singular};

public interface ${word_singular}Repo extends JpaRepository<${word_singular}, Long> {
    
    Optional<${word_singular}> find${word_singular}ById(Long id);
    void delete${word_singular}ById(Long id);
}
EOF

echo "File '$file' created."

# CREATE SERVICE

# Define the file name
file="${dirname3}/${word_singular}Service.java"

# Create the file with a template content
cat <<EOF > "$file"
package ${package_name}.service;

import jakarta.transaction.Transactional;
import ${package_name}.entity.${word_singular};
import ${package_name}.exception.${word_singular}NotFoundException;
import ${package_name}.repository.${word_singular}Repo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ${word_singular}Service {

    private final ${word_singular}Repo ${word_singular_lc}Repo;

    @Autowired
    public ${word_singular}Service(${word_singular}Repo ${word_singular_lc}Repo) { this.${word_singular_lc}Repo = ${word_singular_lc}Repo; }

    public List<${word_singular}> findAll${word_plural}() { return ${word_singular_lc}Repo.findAll(); }

    public ${word_singular} find${word_singular}ById(Long id) {
        return ${word_singular_lc}Repo.find${word_singular}ById(id)
                .orElseThrow(()-> new ${word_singular}NotFoundException("${word_singular} by id" + id + "was not found"));
    }

    public ${word_singular} add${word_singular}(${word_singular} ${word_singular_lc}) { return ${word_singular_lc}Repo.save(${word_singular_lc}); }

    public ${word_singular} update${word_singular}(${word_singular} ${word_singular_lc}) { return ${word_singular_lc}Repo.save(${word_singular_lc}); }

    public void delete${word_singular}(Long id) { ${word_singular_lc}Repo.delete${word_singular}ById(id); }

}
EOF

echo "File '$file' created."

# CREATE CONTROLLER

# Define the file name
file="${dirname4}/${word_singular}Controller.java"

# Create the file with a template content
cat <<EOF > "$file"
package ${package_name}.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import ${package_name}.entity.${word_singular};
import ${package_name}.service.${word_singular}Service;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/${word_singular_lc}")
@CrossOrigin

public class ${word_singular}Controller {

    public final ${word_singular}Service ${word_singular_lc}Service;

    public ${word_singular}Controller(${word_singular}Service ${word_singular_lc}Service) { this.${word_singular_lc}Service = ${word_singular_lc}Service; }

    @GetMapping("/all")
    public ResponseEntity<List<${word_singular}>> getAll${word_plural}() {
        List<${word_singular}> ${word_plural_lc} = ${word_singular_lc}Service.findAll${word_plural}();
        return new ResponseEntity<>(${word_plural_lc}, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<${word_singular}> get${word_singular}ById(@PathVariable("id") Long id) {
        ${word_singular} ${word_singular_lc} = ${word_singular_lc}Service.find${word_singular}ById(id);
        return new ResponseEntity<>(${word_singular_lc}, HttpStatus.OK);
    }       

    @PostMapping("/add")
    public ResponseEntity<${word_singular}> add${word_singular}(@RequestBody ${word_singular} ${word_singular_lc}) {
        ${word_singular} new${word_singular} = ${word_singular_lc}Service.add${word_singular}(${word_singular_lc});
        return new ResponseEntity<>(new${word_singular}, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<${word_singular}> update${word_singular}(@RequestBody ${word_singular} ${word_singular_lc}) {
        ${word_singular} update${word_singular} = ${word_singular_lc}Service.update${word_singular}(${word_singular_lc});
        return new ResponseEntity<>(update${word_singular}, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete${word_singular}(@PathVariable("id") Long id) {
        ${word_singular_lc}Service.delete${word_singular}(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
EOF

echo "File '$file' created."
        fi
    fi
done