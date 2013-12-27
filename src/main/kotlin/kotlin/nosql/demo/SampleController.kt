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
    val db = DynamoDB()

    PostConstruct
    fun onStart() {
        db {
            Users.create()

            Users columns { all } insert { values("andrey.cheptsov@gmail.com", "Andrey Cheptsov") }
        }
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
            Users columns { all } insert { values(emailParam, fullNameParam) }
        }
        // save
        return "redirect:/";
    }

    RequestMapping(array("delete"), method = array(RequestMethod.POST))
    fun deleteUser(RequestParam("email") emailParam: String): String {
        db {
            Users delete { email eq emailParam }
        }
        return "redirect:/";
    }

    RequestMapping(array("/"))
    fun index(model: Model): String {
        db {
            model.addAttribute("users", Users columns { all } map { email, fullName -> Pair(email, fullName)});
        }
        return "index"
    }
}