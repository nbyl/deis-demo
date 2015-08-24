package de.codecentric.nbyl.demo.deis.web;

import de.codecentric.nbyl.demo.deis.web.dto.Greeting;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@Controller
@RequestMapping("/hello")
public class HelloWorldController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Value("${app.defaultGreeter}")
    private String defaultGreeter;

    @RequestMapping(method = RequestMethod.GET)
    public
    @ResponseBody
    Greeting sayHello() {
        return createGreeting(defaultGreeter);
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public
    @ResponseBody
    Greeting sayHello(@PathVariable String name) {
        return createGreeting(name);
    }

    private Greeting createGreeting(String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

}