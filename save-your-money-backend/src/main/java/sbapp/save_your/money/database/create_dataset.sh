#!/bin/bash

# Define environment variables
DOCKER_CONTAINER_NAME="sym-db"
DOCKER_POSTGRES_USER="root"
DOCKER_POSTGRES_PASSWORD="root"
DOCKER_POSTGRES_DATABASE="postgres"
HOST_SQL_SCRIPT_PATH="test_datasets/test_dataset_23062024.sql"
CONTAINER_SQL_SCRIPT_PATH="/tmp/test_dataset_23062024.sql"

# Delete all database data
docker exec -i "$DOCKER_CONTAINER_NAME" psql -U "$DOCKER_POSTGRES_USER" -d "$DOCKER_POSTGRES_DATABASE" -c"
DROP TABLE public.amount_date_ranges CASCADE;

DROP TABLE public.amount_frequencies CASCADE;

DROP TABLE public.expected_movements CASCADE;

DROP TABLE public.fixed_movements CASCADE;

DROP TABLE public.movements CASCADE;
"

# Create tables

cd /Users/pabloferreras/Documents/v/developement/personal/save-your-money-v001/save-your-money-backend ; 
/usr/bin/env /Library/Java/JavaVirtualMachines/zulu-17.jdk/Contents/Home/bin/java @/var/folders/yh/vqqsmyxn1m5c8_v0nhs9_l480000gn/T/cp_7fnjk8mulhx2dzp7dpme5ktc0.argfile sbapp.save_your.money.SaveYourMoneyApplication

cd /Users/pabloferreras/Documents/v/developement/personal/save-your-money-v001/save-your-money-backend/src/main/java/sbapp/save_your/money/database
# Copy SQL script to the Docker container
docker cp "$HOST_SQL_SCRIPT_PATH" "$DOCKER_CONTAINER_NAME":"$CONTAINER_SQL_SCRIPT_PATH"

# Execute SQL script within Docker container
docker exec -i "$DOCKER_CONTAINER_NAME" psql -U "$DOCKER_POSTGRES_USER" -d "$DOCKER_POSTGRES_DATABASE" -f "$CONTAINER_SQL_SCRIPT_PATH"

# Check if the execution was successful
if [ $? -eq 0 ]; then
    echo "Script executed successfully."
else
    echo "Error: Failed to execute the script."
fi
