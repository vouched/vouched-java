# Vouched Java SDK Example

This project demonstrates how to use Vouched Java SDK with Maven.

### Prerequisites

- Java8 +
- Maven

### Local env

Before development, create file `.env` in project root with your private Vouched key:

```
PRIVATE_KEY=<your private Vouched key>
```

### Running

Example code is in `src/main/java/io/github/vouched/example/Example.java`. To run it, use

```
mvn compile exec:java
```

Vouched Java SDK dependency is expressed in `pom.xml`.
