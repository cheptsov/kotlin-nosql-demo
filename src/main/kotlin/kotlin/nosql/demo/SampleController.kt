package kotlin.nosql.demo

import org.springframework.stereotype.*
import org.springframework.ui.Model

import org.springframework.web.bind.annotation.*;

import kotlin.nosql.dynamodb.DynamoDB
import kotlin.nosql.*

import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

object Users: Table() {
    val email = string("email").key()
    val fullName = string("full_name")

    val all = email + fullName
}

Controller
public class SampleController {
    val db = DynamoDB(System.getenv("AWS_KEY")!!, System.getenv("AWS_SECRET")!!)

    PostConstruct
    fun onStart() {
        Thread {
            db {
                Users.create()

                Users attrs { all } set { values("andrey.cheptsov@gmail.com", "Andrey Cheptsov") }
            }
        }.start()
    }

    PreDestroy
    fun onDestroy() {
        db {
            Users.drop()
        }
    }

    RequestMapping(value = array("/add"), method = array(RequestMethod.POST))
    public fun add(RequestParam("email", required = true) emailParam: String,
                   RequestParam("fullName") fullNameParam: String): String {
        db {
            Users attrs { all } set { values(emailParam, fullNameParam) }
        }
        // save
        return "redirect:/";
    }

    RequestMapping(array("delete"), method = array(RequestMethod.POST))
    fun delete(RequestParam("email") emailParam: String): String {
        db {
            Users filter { email eq emailParam } delete { }
        }
        return "redirect:/";
    }

    RequestMapping(array("/"))
    fun index(model: Model): String {
        db {
            model.addAttribute("users", Users attrs { all } map { email, fullName -> Pair(email, fullName) });
        }
        return "index"
    }
}