package kotlin.nosql.demo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan

ComponentScan
EnableAutoConfiguration
class Application {
}

fun main(args: Array<String>) {
    System.setProperty("server.port", System.getenv("PORT")!!)
    SpringApplication.run(array(javaClass<Application>()), args)
}

