package dev.zoidnet.resourceserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.zoidnet.resourceserver.model.dto.Profile;
import dev.zoidnet.resourceserver.service.ResourceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ResourceController.class)
public class ResourceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ResourceService resourceService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "Michael")
    void shouldReturnProfileByUsername() throws Exception {
        Profile userProfile = new Profile("Michael", List.of("Programming", "Movies"));

        when(this.resourceService.getProfile("Michael")).thenReturn(userProfile);

        String expectedContent = objectMapper.writeValueAsString(userProfile);

        this.mockMvc.perform(get("/profile"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedContent));
    }

    @Test
    @WithMockUser(username = "Unknown User")
    void shouldReturn404IfNoProfileExists() throws Exception {
        this.mockMvc.perform(get("/profile"))
                .andExpect(status().isNotFound());
    }
}

