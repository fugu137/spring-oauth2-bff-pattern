import dev.zoidnet.resourceserver.ResourceServerApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.blankOrNullString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ResourceServerApplication.class)
@AutoConfigureMockMvc
public class ResourceIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldGetUserProfileByUsernameWhenAuthorized() throws Exception {
        String username = "Michael";
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("SCOPE_profile.read"));

        this.mockMvc.perform(get("/profile")
                        .with(jwt().authorities(authorities).jwt(jwt -> jwt.subject(username))))
                .andExpect(status().isOk())
                .andExpect(content().json("{ 'username': 'Michael', 'hobbies': [ 'Programming', 'Movies' ] }"));
    }

    @Test
    void shouldNotGetUserProfileByUsernameWhenNotAuthorized() throws Exception {
        String username = "Michael";
        List<GrantedAuthority> authorities = Collections.emptyList();

        this.mockMvc.perform(get("/profile")
                        .with(jwt().authorities(authorities).jwt(jwt -> jwt.subject(username))))
                .andExpect(status().isForbidden())
                .andExpect(content().string(blankOrNullString()));
    }
}
