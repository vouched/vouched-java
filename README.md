# Getting Started

### Prerequisites

- Java 8+
- Maven or Gradle

### Use SDK
The SDK is available on [JCenter](https://bintray.com/bintray/jcenter)
1. Configure build system to use JCenter repository
2. Add `id.vouched.java:vouched-sdk` as a dependency

##### Gradle
in your build.gradle
```
repositories {
    jcenter()
}

dependencies {
    implementation 'id.vouched.java:vouched-sdk:VERSION'
}
```

##### Maven
in your settings.xml, add/append to *profiles* and *activeProfiles* tag
```
    <profiles>
        <profile>
            <repositories>
                <repository>
                    <snapshots>
                        <enabled>false</enabled>
                    </snapshots>
                    <id>central</id>
                    <name>jcenter</name>
                    <url>https://jcenter.bintray.com</url>
                </repository>
            </repositories>
            <id>jcenter</id>
        </profile>
    </profiles>
    <activeProfiles>
        <activeProfile>jcenter</activeProfile>
    </activeProfiles>
```

in your pom.xml
```
  <dependencies>
    <dependency>
      <groupId>id.vouched.java</groupId>
      <artifactId>vouched-sdk</artifactId>
      <version>VERSION</version>
    </dependency>
  </dependencies>
```

### Run Example
##### Environment Variables

Before development, create file `.env` in project root with your private Vouched key:

```
PRIVATE_KEY=<your private Vouched key>
```

##### Testing

SDK code is test-covered. To run tests, use

```
mvn test
```

Tests are executed against production server.