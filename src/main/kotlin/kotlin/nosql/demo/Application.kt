package kotlin.nosql.demo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan

ComponentScan
EnableAutoConfiguration
class Application {
}

fun main(args: Array<String>) {
    val port = System.getenv("PORT")
    if (port != null) {
        System.setProperty("server.port", port)
    }
    SpringApplication.run(array(javaClass<Application>()), args)
}

