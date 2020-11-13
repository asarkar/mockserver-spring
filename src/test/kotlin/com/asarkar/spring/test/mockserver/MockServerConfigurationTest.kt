package com.asarkar.spring.test.mockserver

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import

@SpringBootTest
@AutoConfigureMockServer(port = 0)
@Import(MockServerConfigurationTestConfiguration::class)
class MockServerConfigurationTest {
    @Value("\${mockserver.port:-1}")
    private var port: Int = -1

    @Autowired
    private lateinit var configurationProvider: MockServerConfigurationProviderImpl

    @BeforeEach
    fun beforeEach() {
        setExpectations(port)
    }

    @Test
    fun testConfiguration() {
        Assertions.assertThat("http://localhost:$port/mockserver".getUrlText()).isEqualTo("ok")
        Assertions.assertThat(configurationProvider.called).isTrue
    }
}

@TestConfiguration
open class MockServerConfigurationTestConfiguration {
    @Bean
    open fun configurationProvider() = MockServerConfigurationProviderImpl()
}

class MockServerConfigurationProviderImpl : MockServerConfigurationProvider {
    var called: Boolean = false
    override fun configuration(): MockServerConfiguration {
        called = true
        return MockServerConfiguration()
    }
}
