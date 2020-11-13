package com.asarkar.spring.test.mockserver

import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.springframework.boot.test.autoconfigure.properties.PropertyMapping
import java.lang.annotation.Inherited

/**
 * Annotation for test classes that want to start a MockServer as part of the Spring application Context.
 *
 * @property port MockServer local port. Defaults to 1080. Set 0 to use a random port.
 *
 * @author Abhijit Sarkar
 * @since 1.0.0
 */
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@Tags(
    Tag("spring"),
    Tag("spring-boot"),
    Tag("mockserver"),
    Tag("http-client"),
    Tag("http-server"),
    Tag("test"),
    Tag("integration-test")
)
@Inherited
@PropertyMapping("mockserver")
annotation class AutoConfigureMockServer(
    val port: Int = 1080
)
