package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    // 1. Basic Hello
    @GetMapping("/hello")
    public String hello() {
        logger.info("Called hello");
        return "Hello, World!";
    }

    // 2. Path Variable
    @GetMapping("/greet/{name}")
    public String greet(@PathVariable String name) {
        logger.info("Called greet with name: {}", name);
        return "Hello, " + name + "!";
    }

    // 3. Request Parameters
    @GetMapping("/sum")
    public String sum(@RequestParam int a, @RequestParam int b) {
        logger.info("Called sum with a: {}, b: {}", a, b);
        return "Sum: " + (a + b);
    }

    // 4. POST with Body
    @PostMapping("/echo")
    public String echo(@RequestBody String message) {
        logger.info("Called echo with message: {}", message);
        return "Echo: " + message;
    }

    // 5. Random Number
    @GetMapping("/random")
    public int random() {
        logger.info("Called random");
        return new Random().nextInt(100);
    }

    // 6. JSON Response (Map)
    @GetMapping("/status")
    public Map<String, Object> status() {
        logger.info("Called status");
        Map<String, Object> status = new HashMap<>();
        status.put("status", "UP");
        status.put("uptime", System.currentTimeMillis());
        return status;
    }

    // 7. PUT Request
    @PutMapping("/update")
    public String update(@RequestBody String data) {
        logger.info("Called update with data: {}", data);
        return "Updated: " + data;
    }

    // 8. DELETE Request
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        logger.info("Called delete with id: {}", id);
        return "Deleted item with ID: " + id;
    }

    // 9. Request Header
    @GetMapping("/headers")
    public String headers(@RequestHeader("User-Agent") String userAgent) {
        logger.info("Called headers with User-Agent: {}", userAgent);
        return "Your User-Agent is: " + userAgent;
    }

    // 10. Current Time
    @GetMapping("/time")
    public String time() {
        logger.info("Called time");
        return "Current Time: " + LocalDateTime.now();
    }

    @GetMapping("/time")
    public String logger() {
        logger.info("logger test");
        return "logger test";
    }
}
