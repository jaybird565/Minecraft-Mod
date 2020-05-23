#!/bin/sh

INSTALL_DIR="/home/zach/Games/minecraft/instances/1.12.2-forge/mods"
BUILD_DIR="build/libs"
JAR="*.jar"

main() {
  cp -rf $BUILD_DIR/$JAR $INSTALL_DIR
}
main
