#!/bin/bash

# Define environment variables
DOCKER_CONTAINER_NAME="sym-db"
DOCKER_POSTGRES_USER="root"
DOCKER_POSTGRES_PASSWORD="root"
DOCKER_POSTGRES_DATABASE="postgres"
HOST_SQL_SCRIPT_PATH="src/main/java/sbapp/save_your/money/database/test_datasets/test_dataset_23062024.sql"
CONTAINER_SQL_SCRIPT_PATH="/tmp/test_dataset_23062024.sql"
PROJECT_ROOT="/Users/pabloferreras/Documents/v/developement/personal/save-your-money-v001/save-your-money-backend"

# Delete all database data
docker exec -i "$DOCKER_CONTAINER_NAME" psql -U "$DOCKER_POSTGRES_USER" -d "$DOCKER_POSTGRES_DATABASE" <<EOF
DROP TABLE IF EXISTS public.amount_date_ranges CASCADE;
DROP TABLE IF EXISTS public.amount_frequencies CASCADE;
DROP TABLE IF EXISTS public.expected_movements CASCADE;
DROP TABLE IF EXISTS public.fixed_movements CASCADE;
DROP TABLE IF EXISTS public.movements CASCADE;
DROP TABLE IF EXISTS public.profiles CASCADE;
EOF

# Check if the deletion was successful
if [ $? -ne 0 ]; then
    echo "Error: Failed to delete existing database tables."
    exit 1
fi

# Build the project
cd "$PROJECT_ROOT"
mvn clean install

# Check if the project build was successful
if [ $? -ne 0 ]; then
    echo "Error: Failed to build the project."
    exit 1
fi

# Start the Spring Boot application
mvn spring-boot:run &

# Give the Spring Boot application some time to start
sleep 15

# Check if the Spring Boot application started successfully
if [ $? -ne 0 ]; then
    echo "Error: Failed to start the Spring Boot application."
    exit 1
fi

# Copy SQL script to the Docker container
docker cp "$HOST_SQL_SCRIPT_PATH" "$DOCKER_CONTAINER_NAME":"$CONTAINER_SQL_SCRIPT_PATH"

# Check if the file copy was successful
if [ $? -ne 0 ]; then
    echo "Error: Failed to copy the SQL script to the Docker container."
    exit 1
fi

# Execute SQL script within Docker container
docker exec -i "$DOCKER_CONTAINER_NAME" psql -U "$DOCKER_POSTGRES_USER" -d "$DOCKER_POSTGRES_DATABASE" -f "$CONTAINER_SQL_SCRIPT_PATH"

# Check if the execution was successful
if [ $? -eq 0 ]; then
    echo "Script executed successfully."
else
    echo "Error: Failed to execute the script."
    exit 1
fi
