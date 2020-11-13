package com.asarkar.spring.test.mockserver

/**
 * Class that gets called before the MockServer is started giving the user a chance to provide configuration options
 * in a [MockServerConfiguration].
 *
 * @author Abhijit Sarkar
 * @since 1.0.0
 */
interface MockServerConfigurationProvider {
    fun configuration(): MockServerConfiguration
}
