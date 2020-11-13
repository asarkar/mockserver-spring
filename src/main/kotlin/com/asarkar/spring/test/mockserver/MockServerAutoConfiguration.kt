package com.asarkar.spring.test.mockserver

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(
    name = [
        "org.mockserver.netty.MockServer"
    ]
)
class MockServerAutoConfiguration {
    @Bean
    fun mockserverLifecycle(): MockServerLifecycle {
        return MockServerLifecycle()
    }
}
