package dev.zoidnet.resourceserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class ResourceController {

    @GetMapping("/articles")
    public String[] getAll(Principal principal) {
        return new String[]{
                "Article 1",
                "Article 2"
        };
    }
}
