#!/bin/bash

# Check if a component name was provided
if [ -z "$1" ]; then
  echo "Please provide a component name."
  exit 1
fi

COMPONENT_NAME=$1
COMPONENT_DIR="src/$COMPONENT_NAME"

# Create the component directory
mkdir -p $COMPONENT_DIR

# Create the component file
COMPONENT_FILE="$COMPONENT_DIR/$COMPONENT_NAME.js"
touch $COMPONENT_FILE

# Add content to the component file
cat > $COMPONENT_FILE <<EOL
import React from 'react';
import './$COMPONENT_NAME.scss';

function $COMPONENT_NAME() {
  return (
    <div className="$COMPONENT_NAME">
      <h2>Welcome to the $COMPONENT_NAME Component!</h2>
      <p>This is a simple $COMPONENT_NAME component.</p>
    </div>
  );
}

export default $COMPONENT_NAME;
EOL

# Create the SCSS file
SCSS_FILE="$COMPONENT_DIR/$COMPONENT_NAME.scss"
touch $SCSS_FILE

# Import and use the new component in App.js
APP_FILE="src/App.js"

# Add import statement
IMPORT_STATEMENT="import $COMPONENT_NAME from './$COMPONENT_NAME/$COMPONENT_NAME';"
if ! grep -q "$IMPORT_STATEMENT" "$APP_FILE"; then
  sed -i "1 a\\$IMPORT_STATEMENT" "$APP_FILE"
fi

# Add component usage in the App component
USAGE_STATEMENT="          <$COMPONENT_NAME />"
if ! grep -q "$USAGE_STATEMENT" "$APP_FILE"; then
  sed -i "/<header className=\"App-header\">/a\\$USAGE_STATEMENT" "$APP_FILE"
fi

echo "$COMPONENT_NAME component has been created and added to App.js"
