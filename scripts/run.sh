#!/bin/sh
set -e
PROJECT_ROOT="$(cd "$(dirname "$0")/.." && pwd)"
cd "$PROJECT_ROOT"
mkdir -p build
javac -d build $(find src/main/java -name "*.java")
java -cp build dev.vdokit.VdoKitApp
