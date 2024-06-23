package org.example.ibmskillsbuildapp;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    void whenAuthenticated_thenSuccessLogin() throws Exception {
        mockMvc.perform(get("/success-login"))
            .andExpect(status().is3xxRedirection());
    }

    @Test
    void whenNotAuthenticated_thenLoginForm() throws Exception {
        mockMvc.perform(get("/login-form"))
            .andExpect(status().isOk());
    }

    @Test
    void whenNotAuthenticated_thenAccessDenied() throws Exception {
        mockMvc.perform(get("/accessDenied"))
            .andExpect(status().is3xxRedirection());
    }

    @Test
    void whenNotAuthenticated_thenRegisterForm() throws Exception {
        mockMvc.perform(get("/register"))
            .andExpect(status().isOk());
    }
}
