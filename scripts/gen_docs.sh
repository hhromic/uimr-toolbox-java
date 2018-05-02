#!/bin/bash

MODULES=("toolbox" "toolbox-as" "toolbox-concurrent" "toolbox-mysql" "toolbox-servlet" "toolbox-stats" "toolbox-twitter")
TARGET="docs"

function build_docs() {
    local target=$1
    local module=$2
    echo "Generating apidocs for module '$module' ..."
    cd "$module" &&
    rm -rf target/site/apidocs &&
    mvn javadoc:javadoc &&
    cp -rf target/site/apidocs "../$target/$module" && return 0
    return 1
}

function build_index() {
    local target=$1; shift
    local module
    echo "Building index for modules '$*'"
    cat >"$target/index.html" <<'__EOF__'
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>UIMR Java Toolbox</title>
</head>
<body>
  <h1>UIMR Java Toolbox</h1>
  <ul>
__EOF__
    for module in "$@"; do
        echo "    <li><a href=\"$module\">$module</a></li>" >>"$target/index.html"
    done
    cat >>"$target/index.html" <<'__EOF__'
  </ul>
</body>
</html>
__EOF__
    return 0
}

rm -rf "$TARGET" &&
mkdir -p "$TARGET" &&
for module in "${MODULES[@]}"; do
    (build_docs "$TARGET" "$module") || break
done
build_index "$TARGET" "${MODULES[@]}"
