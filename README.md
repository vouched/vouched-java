# Getting Started

### Prerequisites

- Java8 +
- Maven

### Local env

Before development, create file `.env` in project root with your private Vouched key:
```
PRIVATE_KEY=<your private Vouched key>
TEST_JOB_ID=<ID of one of existing jobs, will be removed during test execution>
```

Also create file `~/.m2/settings.xml` with a key to deploy SDK JAR to Maven repository:

```
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                      https://maven.apache.org/xsd/settings-1.0.0.xsd">
    <servers>
        <server>
            <id>bintray-vouched1-vouched-java</id>
            <username>vouched1</username>
            <password>API_KEY_HERE</password>
        </server>
    </servers>
</settings>
```

API key could be obtained by logged in into Bintray.com

### Development

SDK code is test-covered. To run tests, use

```
mvn test
```

Tests are executed against production server.

### Publishing to repository

SDK is published to Maven repository as JAR file. Vouched-Java uses JFrog Bintray. Vouched username is `vouched1`.
To publish a new version to repository, use 
```
mvn deploy
```

Successful build results indicates a new version has been deployed to repository. 