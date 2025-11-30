package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "forward:/index.html";
    }
    
    @GetMapping("/contenidos")
    public String contenidos() {
        return "forward:/contenidos.html";
    }
}
