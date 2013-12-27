package kotlin.nosql.demo

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

Controller
EnableAutoConfiguration
public class SampleController {

    RequestMapping(array("/"))
    ResponseBody
    fun home(): String {
        return "Hello World!";
    }
}

fun main(args: Array<String>) {
    System.setProperty("server.port", System.getenv("PORT")!!)
    SpringApplication.run(array(javaClass<SampleController>()), args);
}
