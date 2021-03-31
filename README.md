# mockserver-spring

Starts a `MockServer` and makes the port available as Spring Boot environment property. Stops the server when the Spring
context is destroyed.

Requires Java 8 or later. Uses [mockserver](https://github.com/mock-server/mockserver)
and [Spring Boot](https://spring.io/projects/spring-boot).

## Installation

You can find the latest version
on [Maven Central](https://search.maven.org/search?q=g:com.asarkar.spring%20AND%20a:mockserver-spring).

## Usage

The only thing you need is the `AutoConfigureMockServer` annotation:

```
@SpringBootTest
@AutoConfigureMockServer
public class AutoConfigureWithKnownPortsTest {
    @BeforeEach
    // create expectation using port 1080

    @Test
    public void testConnection() {
        // GET "http://localhost:1080/"
    }
}
```

To use random port:

```
@SpringBootTest
@AutoConfigureMockServer(port = 0)
public class AutoConfigureWithRandomPortsTest {
    @Value("${mockserver.port:-1}")
    private int port;

    @BeforeEach
    // create expectation using port

    @Test
    public void testConnection() {
        // GET "http://localhost:port/"
    }
}
```

If you want to configure the `MockServer` before it's started, provide an implementation for
[MockServerConfigurationProvider](src/main/kotlin/com/asarkar/spring/test/mockserver/MockServerConfigurationProvider.kt)
, as a Spring bean.

See KDoc for more details.

### Gotchas

1. Do not create expectations in the static `beforeAll()` method; it runs before the MockServer starts. If you want to
   create commons expectations, either do it in the `beforeEach()` method, which runs before each test method, or do it
   in a non static `beforeAll()` method. In the second case, you'll have to also annotate your test class with
   `@TestInstance(Lifecycle.PER_CLASS)`.

2. In order for the server to be started, the class given to `@SpringBootTest` must be annotated with
   `@SpringBootApplication`. Such a class can be specified in the `classes` attribute of `@SpringBootTest` annotation,
   or found implicitly by component scan.

## Contribute

This project is a volunteer effort. You are welcome to send pull requests, ask questions, or create issues. If you like
it, you can help by spreading the word and "Starring" the GitHub repo!

## License

Copyright 2021 Abhijit Sarkar - Released under [Apache License v2.0](LICENSE).
