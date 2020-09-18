# Getting Started

### Prerequisites

- Java8 +
- Maven
- GPG for publishing to Maven repo

### Local env

Before development, create file `.env` in project root with your private Vouched key:

```
PRIVATE_KEY=<your private Vouched key>
```

### Development

SDK code is test-covered. To run tests, use

```
mvn test
```

Tests are executed against production server.

### Publishing to repository

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
Java SDK uses Sonatype Central Repo to publish JAR to Maven.
Guide available at https://central.sonatype.org/pages/ossrh-guide.html

Publishing consists of initial one-time steps, and recurrent steps to publish a new version.

#### Initial steps

1. Create Jira Account https://issues.sonatype.org/secure/Signup!default.jspa
1. Create new project ticket. https://issues.sonatype.org/secure/CreateIssue.jspa?issuetype=21&pid=10134 .
   Use values from pom.xml for ticket fields. They will require to validate group id by either creating a Git repo or
   DNS record, depending on group id value. After ticket resolution Sonatype will be ready to accept JARs for publising
1. Configure PGP, is not already, guide https://central.sonatype.org/pages/working-with-pgp-signatures.html. Do not forget to push GPG keys to remote servers.
1. Configure Maven `settings.xml`. to add Jira account from step 1 and GPG passphrase. File should look like this:

```
<settings>
  <servers>
    <server>
      <id>ossrh</id>
      <username>your-jira-id</username>
      <password>your-jira-pwd</password>
    </server>
  </servers>

  <profiles>
    <profile>
      <activation>
        <activeByDefault>true</activeByDefault>
      </activation>
      <properties>
        <gpg.executable>/usr/local/bin/gpg</gpg.executable>
        <gpg.passphrase>XXXXXXXXXXXXXXXXXX</gpg.passphrase>
      </properties>
    </profile>
  </profiles>
</settings>
```

#### Publishing a new version

1. Update version in `pom.xml`
1. Run `mvn clean deploy`
