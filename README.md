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

### Development

SDK code is test-covered. To run tests, use

```
mvn test
```

Tests are executed against production server.

### Publishing to repository

SDK is published to Maven repository as JAR file.