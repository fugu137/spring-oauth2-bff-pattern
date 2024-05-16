package dev.zoidnet.resourceserver.service;

import dev.zoidnet.resourceserver.model.dto.Profile;
import io.micrometer.common.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ResourceService {

    private final Map<String, Profile> profiles = Map.of(
            "Michael", new Profile("Michael", List.of("Programming", "Movies")),
            "Admin", new Profile("Admin", List.of("Cooking", "Washing")),
            "fugu137", new Profile("GitHub User", List.of("Rust", "Gleam", "Kotlin"))
    );

    public @Nullable Profile getProfile(String username) {
        return profiles.get(username);
    }
}
