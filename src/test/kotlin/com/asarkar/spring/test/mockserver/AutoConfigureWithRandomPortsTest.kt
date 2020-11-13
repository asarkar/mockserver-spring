package com.asarkar.spring.test.mockserver

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@AutoConfigureMockServer(
    port = 0
)
class AutoConfigureWithRandomPortsTest {
    @Value("\${mockserver.port:-1}")
    private var port: Int = -1

    @BeforeEach
    fun beforeEach() {
        setExpectations(port)
    }

    @Test
    fun testConnection() {
        assertThat("http://localhost:$port/mockserver".getUrlText()).isEqualTo("ok")
    }
}
