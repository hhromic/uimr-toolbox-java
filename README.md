# UIMR Java Toolbox

## Installation

Follow these steps to install the toolbox in Linux machines:

    git clone --depth 1 https://github.com/hhromic/uimr-toolbox-java
    cd uimr-toolbox-java
    mvn clean install archetype:update-local-catalog

## Documentation

The Java apidocs for each module of this toolbox can be found here:

    http://hhromic.github.io/uimr-toolbox-java/

## Skipping Tests

By default, the parent POM of this toolbox skips tests during installation/packaging.

If you want to enable your tests use `-Dmaven.test.skip=false`. For example:

    mvn install -Dmaven.test.skip=false
    mvn package -Dmaven.test.skip=false

See: <http://maven.apache.org/surefire/maven-surefire-plugin/examples/skipping-tests.html>

## Archetype

This toolbox includes a Maven archetype to get you started quickly.

To create a new project using the included archetype:

    mvn archetype:generate \
        -DarchetypeGroupId=io.github.hhromic.uimr \
        -DarchetypeArtifactId=archetype

Then, follow the interactive instructions to create your project based on the archetype.

Afterwards, you can compile the example "Hello World" project from the archetype:

    cd <YOUR_ARTIFACT_ID>
    mvn clean package
    java -jar target/<YOUR_ARTIFACT_ID>-<YOUR_ARTIFACT_VERSION>-shaded.jar

See: <https://maven.apache.org/guides/introduction/introduction-to-archetypes.html>

## License

This software is under the **Apache License 2.0**.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

