#!/bin/bash

POM_APP_VERSION=$(./mvnw -q help:evaluate -Dexpression=project.version -DforceStdout=true)

echo "POM Project version: ${POM_APP_VERSION}"

docker compose build --build-arg APP_VERSION="$POM_APP_VERSION" --no-cache

docker compose up -d