package com.asarkar.spring.test.mockserver

import org.mockserver.proxyconfiguration.ProxyConfiguration

/**
 * This class gives the user a chance to configure the MockServer before it's started. This class is to be returned
 * from an implementation of [MockServerConfigurationProvider].
 *
 * @author Abhijit Sarkar
 * @since 1.0.0
 */
data class MockServerConfiguration(
    val remoteHost: String? = null,
    val remotePort: Int = -1,
    val proxyConfiguration: ProxyConfiguration? = null
)
