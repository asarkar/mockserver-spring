package com.asarkar.spring.test.mockserver

import org.mockserver.netty.MockServer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.SmartLifecycle

open class MockServerLifecycle : SmartLifecycle {
    @Value("\${mockserver.port:-1}")
    private var localPort: Int = -1

    @Autowired(required = false)
    private var configurationProvider: MockServerConfigurationProvider? = null

    private lateinit var mockServer: MockServer

    override fun start() {
        if (isRunning || localPort <= 0) return

        mockServer = if (configurationProvider == null) {
            MockServer(localPort)
        } else {
            val config = configurationProvider!!.configuration()
            if (config.remotePort > 0 && config.proxyConfiguration != null) {
                MockServer(config.proxyConfiguration, config.remoteHost, config.remotePort, localPort)
            } else if (config.remotePort > 0) {
                MockServer(config.remotePort, config.remoteHost, localPort)
            } else if (config.proxyConfiguration != null) {
                MockServer(config.proxyConfiguration, localPort)
            } else {
                MockServer(localPort)
            }
        }
    }

    override fun stop() {
        if (isRunning) mockServer.stop()
    }

    override fun isRunning() = this::mockServer.isInitialized && mockServer.isRunning
}
