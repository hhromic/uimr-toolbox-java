#!/bin/bash

echo "**** Dependencies Updates ****"
echo
mvn versions:display-dependency-updates

echo "**** Plugins Updates ****"
echo
mvn versions:display-plugin-updates
