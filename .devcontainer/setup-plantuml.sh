#!/bin/bash

echo "Setting up PlantUML Environment..."

# Update package lists
sudo apt-get update

# Install Graphviz (if not already installed by main setup)
sudo apt-get install -y graphviz

# Install additional dependencies for PlantUML
sudo apt-get install -y \
	default-jre \
	wget \
	curl

# Create PlantUML directory
PLANTUML_DIR="/opt/plantuml"
sudo mkdir -p $PLANTUML_DIR

# Download the latest PlantUML JAR
PLANTUML_VERSION="1.2025.7"
PLANTUML_URL="https://github.com/plantuml/plantuml/releases/download/v${PLANTUML_VERSION}/plantuml-${PLANTUML_VERSION}.jar"

echo "Downloading PlantUML ${PLANTUML_VERSION}..."
sudo wget -O ${PLANTUML_DIR}/plantuml.jar "${PLANTUML_URL}"

# Set permissions
sudo chown -R vscode:vscode $PLANTUML_DIR

# Create wrapper script for easy execution
sudo tee /usr/local/bin/plantuml >/dev/null <<'EOF'
#!/bin/bash
java -jar /opt/plantuml/plantuml.jar "$@"
EOF

sudo chmod +x /usr/local/bin/plantuml

# Add PlantUML environment variables to bashrc
cat >>~/.bashrc <<'EOF'

# PlantUML Environment Variables
export PLANTUML_JAR=/opt/plantuml/plantuml.jar
export PLANTUML_LIMIT_SIZE=8192

# Alias for PlantUML
alias plantuml='java -jar $PLANTUML_JAR'

EOF

# Source the bashrc to make variables available immediately
source ~/.bashrc

# Test PlantUML installation
echo ""
echo "Testing PlantUML installation..."
if command -v plantuml &>/dev/null; then
	plantuml -version
	echo ""
	echo "✓ PlantUML installation successful!"
else
	echo "✗ PlantUML installation failed!"
	exit 1
fi

echo ""
echo "PlantUML setup complete!"
echo ""
echo "Usage examples:"
echo "1. Generate PNG: plantuml diagram.puml"
echo "2. Generate SVG: plantuml -tsvg diagram.puml"
echo "3. Generate PDF: plantuml -tpdf diagram.puml"
echo "4. Watch mode: plantuml -gui diagram.puml"
echo ""
echo "VS Code PlantUML extension is already configured."
echo "Simply create .puml files and preview them with Alt+D or Ctrl+Shift+V"
echo ""
echo "PlantUML JAR location: $PLANTUML_JAR"
