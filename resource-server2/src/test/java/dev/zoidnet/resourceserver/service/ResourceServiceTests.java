package dev.zoidnet.resourceserver.service;

import dev.zoidnet.resourceserver.model.dto.Profile;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;


@ExtendWith(SpringExtension.class)
public class ResourceServiceTests {

    private ResourceService resourceService;

    @BeforeEach
    void setup() {
        this.resourceService = new ResourceService();
    }

    @AfterEach
    void tearDown() {
        this.resourceService = null;
    }

    @Test
    void shouldReturnProfileByUsername() {
        Profile profile = resourceService.getProfile("Michael");

        assertThat(profile, notNullValue());
        assertThat(profile.username(), is("Michael"));
    }
}
