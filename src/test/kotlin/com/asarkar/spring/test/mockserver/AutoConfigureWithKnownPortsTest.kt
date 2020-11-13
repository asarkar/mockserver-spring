package com.asarkar.spring.test.mockserver

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@AutoConfigureMockServer
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AutoConfigureWithKnownPortsTest {
    @BeforeAll
    fun beforeEach() {
        setExpectations()
    }

    @Test
    fun testConnection() {
        assertThat("http://localhost:1080/mockserver".getUrlText()).isEqualTo("ok")
    }
}
