#!/bin/bash

echo "Setting up JavaFX Development Environment..."

# Update package lists
sudo apt-get update

# Install essential packages for JavaFX and GUI applications
sudo apt-get install -y \
    graphviz \
    libgl1-mesa-glx \
    libgtk-3-0 \
    libxtst6 \
    libxrender1 \
    libxi6 \
    libxrandr2 \
    libxcursor1 \
    libxinerama1 \
    libxss1 \
    libasound2-dev \
    x11-apps \
    xvfb

# Download and install JavaFX SDK
JAVAFX_VERSION="21.0.2"
JAVAFX_URL="https://download2.gluonhq.com/openjfx/${JAVAFX_VERSION}/openjfx-${JAVAFX_VERSION}_linux-x64_bin-sdk.zip"

# Create JavaFX directory
sudo mkdir -p /opt/javafx
cd /tmp

# Download JavaFX SDK
wget -O javafx-sdk.zip "${JAVAFX_URL}"
sudo unzip -q javafx-sdk.zip -d /opt/javafx
sudo mv /opt/javafx/javafx-sdk-${JAVAFX_VERSION}/* /opt/javafx/
sudo rmdir /opt/javafx/javafx-sdk-${JAVAFX_VERSION}

# Set permissions
sudo chown -R vscode:vscode /opt/javafx

# Clean up
rm javafx-sdk.zip

# Create environment variables file
cat >> ~/.bashrc << 'EOF'

# JavaFX Environment Variables
export JAVAFX_HOME=/opt/javafx
export PATH_TO_FX=$JAVAFX_HOME/lib
export JAVAFX_VERSION=21.0.2

# Aliases for common JavaFX commands
alias javafx-compile='javac --module-path $PATH_TO_FX --add-modules javafx.controls,javafx.fxml'
alias javafx-run='java --module-path $PATH_TO_FX --add-modules javafx.controls,javafx.fxml'

EOF

# Source the bashrc to make variables available immediately
source ~/.bashrc

echo "JavaFX DevContainer setup complete!"
echo ""
echo "To run your JavaFX application:"
echo "1. Open the desktop environment at http://localhost:6080 (password: vscode)"
echo "2. Use 'mvn javafx:run' for Maven projects"
echo "3. Or compile manually with: javafx-compile YourApp.java"
echo "4. And run with: javafx-run YourApp"
echo ""
echo "JavaFX SDK is installed at: /opt/javafx"
