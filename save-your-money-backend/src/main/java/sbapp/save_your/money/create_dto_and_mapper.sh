#!/bin/bash

toLowerCase() {
    local className=$1
    echo "${className}" | awk '{print tolower(substr($0, 1, 1)) substr($0, 2)}'
}

# Function to create directories if they don't exist
createDirectories() {
    dirname1="dto"
    dirname2="mapper"

    # Check and create dto directory
    if [ ! -d "$dirname1" ]; then
        mkdir "$dirname1"
        echo "Directory '$dirname1' created."
    fi

    # Check and create mapper directory
    if [ ! -d "$dirname2" ]; then
        mkdir "$dirname2"
        echo "Directory '$dirname2' created."
    fi
}

# Function to check if the entity file exists
entityExists() {
    local entity=$1
    local entity_folder="entity"

    # Iterate through files in entity directory
    for file in "$entity_folder"/*; do
        # Check if it is a file and if the file name matches the entity name
        if [[ -f "$file" ]]; then
            class_name=$(grep -E 'public\s+class\s+\w+' "$file" | awk '{print $3}')
            if [[ "$class_name" == "$entity" ]]; then
                return 0  # Entity exists
            fi
        fi
    done
    return 1  # Entity does not exist
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

# Function to create DTO file for the entity
createDTOFile() {
    local entity=$1
    local package=$(getPackageName)

    cat <<EOF > "dto/${entity}DTO.java"
package $package.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ${entity}DTO {

    private long id;

}
EOF
}

createMapperFile() {
    local entity=$1
    local package=$(getPackageName)

    cat <<EOF > "mapper/${entity}Mapper.java"
package $package.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import sbapp.save_your.money.dto.${entity}DTO;
import sbapp.save_your.money.entity.${entity};

@Mapper
public interface ${entity}Mapper {
    
    ${entity}Mapper INSTANCE = Mappers.getMapper(${entity}Mapper.class);

    //@Mapping(source = "entityAttribute", target = "dtoAttribute")
    ${entity}DTO toDto(${entity} $(toLowerCase "${entity}"));

    //@Mapping(source = "dtoAttribute", target = "entityAttribute")
    ${entity} toEntity(${entity}DTO $(toLowerCase "${entity}")DTO);
}
EOF
}

# Main function to orchestrate the script
main() {
    local entity=$1
    echo "Checking for entity: $entity"

    # Create necessary directories
    createDirectories

    # Check if entity exists and create DTO file if it does
    if entityExists "$entity"; then
        createDTOFile "$entity"
        echo "DTO created for entity '$entity'."
        createMapperFile "$entity"
        echo "Mapper created for entity '$entity'."
    else
        echo "Entity '$entity' does not exist."
    fi
}

# Call main function with first argument passed to the script
main "$1"
