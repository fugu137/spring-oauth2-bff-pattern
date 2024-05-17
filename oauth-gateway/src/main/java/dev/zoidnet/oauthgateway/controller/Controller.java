package dev.zoidnet.oauthgateway.controller;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping("/oidc/logout")
    public String logout() {
        return "logout.html";
    }

}
