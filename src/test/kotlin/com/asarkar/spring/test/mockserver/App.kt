package com.asarkar.spring.test.mockserver

import org.mockserver.client.MockServerClient
import org.mockserver.model.HttpRequest
import org.mockserver.model.HttpResponse
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.net.HttpURLConnection
import java.net.URL

@SpringBootApplication
open class App

fun main(args: Array<String>) {
    runApplication<App>(*args)
}

fun String.getUrlText(): String {
    return URL(this).run {
        openConnection().run {
            this as HttpURLConnection
            inputStream.bufferedReader().use { readText() }
        }
    }
}

fun setExpectations(port: Int = 1080) {
    MockServerClient("localhost", port)
        .`when`(
            HttpRequest.request()
                .withPath("/mockserver")
        )
        .respond(
            HttpResponse.response()
                .withBody("ok")
        )
}
