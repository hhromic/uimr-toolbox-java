#!/bin/bash

TARGET="docs"
MODULES="toolbox toolbox-as toolbox-concurrent toolbox-mysql toolbox-servlet toolbox-stats toolbox-twitter"

function gen_docs() {
    local module=$1
    echo "Generating apidocs for '$module' ..."
    cd "$module" &&
    rm -rf target/site/apidocs &&
    mvn javadoc:javadoc &&
    cp -rf target/site/apidocs "../$TARGET/$module"
}

rm -rf "$TARGET" &&
mkdir -p "$TARGET" &&
for module in $MODULES; do
    (gen_docs "$module") || break
done
