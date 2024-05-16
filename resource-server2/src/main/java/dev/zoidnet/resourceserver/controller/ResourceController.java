package dev.zoidnet.resourceserver.controller;

import dev.zoidnet.resourceserver.model.dto.Profile;
import dev.zoidnet.resourceserver.service.ResourceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class ResourceController {

    private final ResourceService resourceService;

    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @GetMapping(value = "/profile")
    public ResponseEntity<Profile> getProfile(Principal principal) {
        System.out.println("Principal:");
        System.out.println(principal);
        System.out.println(principal.getName());

        String username = principal.getName();
        Profile profile = resourceService.getProfile(username);

        if (profile != null) {
            return ResponseEntity.ok(profile);
        }

        return ResponseEntity.notFound().build();
    }
}
