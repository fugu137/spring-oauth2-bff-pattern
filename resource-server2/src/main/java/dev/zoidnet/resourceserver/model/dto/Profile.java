package dev.zoidnet.resourceserver.model.dto;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public record Profile(String username, List<String> hobbies) {
}
