FROM openjdk:26-slim-trixie

# Install common tools
RUN apt-get update && \
    apt-get install -y --no-install-recommends bash git \
    && rm -rf /var/lib/apt/lists/*

# Setup default user
ARG USERNAME=vscode
ARG USER_UID=1000
ARG USER_GID=$USER_UID

RUN groupadd --gid $USER_GID $USERNAME && \
    useradd --uid $USER_UID --gid $USER_GID -s /bin/bash -m $USERNAME

# Switch to the default user
USER $USERNAME
