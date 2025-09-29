#!/bin/bash

echo "Setting up JavaFX Development Environment..."

# Update package lists
sudo apt-get update

# Install essential packages for JavaFX and GUI applications
sudo apt-get install -y \
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
cat >>~/.bashrc <<'EOF'

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

# Create a sample JavaFX project structure if it doesn't exist
if [ ! -f "pom.xml" ] && [ ! -f "build.gradle" ]; then
	echo "Creating sample JavaFX project structure..."

	# Create Maven project structure
	mkdir -p src/main/java/com/example/javafx
	mkdir -p src/main/resources

	# Create sample Main.java
	cat >src/main/java/com/example/javafx/Main.java <<'EOF'
package com.example.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("Hello, JavaFX in DevContainer!");
        StackPane root = new StackPane(label);

        Scene scene = new Scene(root, 400, 300);

        primaryStage.setTitle("JavaFX DevContainer Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
EOF

	# Create sample pom.xml for Maven
	cat >pom.xml <<'EOF'
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         http://maven.apache.org/xsd/maven-4.0.0.xsd">

    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example</groupId>
    <artifactId>javafx-devcontainer-demo</artifactId>
    <version>1.0.0</version>

    <properties>
        <maven.compiler.source>21</maven.compiler.source>
        <maven.compiler.target>21</maven.compiler.target>
        <javafx.version>21.0.2</javafx.version>
        <javafx.maven.plugin.version>0.0.8</javafx.maven.plugin.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-controls</artifactId>
            <version>${javafx.version}</version>
        </dependency>

        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-fxml</artifactId>
            <version>${javafx.version}</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.11.0</version>
                <configuration>
                    <source>21</source>
                    <target>21</target>
                </configuration>
            </plugin>

            <plugin>
                <groupId>org.openjfx</groupId>
                <artifactId>javafx-maven-plugin</artifactId>
                <version>${javafx.maven.plugin.version}</version>
                <configuration>
                    <mainClass>com.example.javafx.Main</mainClass>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
EOF

	echo "Sample JavaFX project created!"
fi

echo "JavaFX DevContainer setup complete!"
echo ""
echo "To run your JavaFX application:"
echo "1. Open the desktop environment at http://localhost:6080 (password: vscode)"
echo "2. Use 'mvn javafx:run' for Maven projects"
echo "3. Or compile manually with: javafx-compile YourApp.java"
echo "4. And run with: javafx-run YourApp"
echo ""
echo "JavaFX SDK is installed at: /opt/javafx"
