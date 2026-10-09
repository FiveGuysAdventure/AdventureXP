package com.adventurexp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontendController {

    @GetMapping({
            "/login",
            "/booking",
            "/reservationer",
            "/inventar",
            "/employees",
            "/employeeShop",
            "/booking-overview",
            "/customerShop"
    })
    public String forwardToIndex() {
        return "forward:/index.html";
    }
}