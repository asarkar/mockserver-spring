package com.asarkar.spring.test.mockserver

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class NonMockServerTest {
    @Value("\${mockserver.port:-1}")
    private var port: Int = -1

    @Test
    fun testConnection() {
        assertThat(port).isEqualTo(-1)
    }
}
