package com.example.SpringMangoProject.Controller

import com.example.SpringMangoProject.Entity.Login
import com.example.SpringMangoProject.Service.LoginServices
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers


@WebMvcTest(LoginController)
class LoginControllerTests {

    @Autowired
    private MockMvc mvc

    @MockBean
    private LoginServices loginServices

    @BeforeEach
    void setUp() {
        Mockito.reset(loginServices)
    }

    @Test
    void testPostLogin() throws Exception {
        // Arrange
        Login login = new Login("1", "user1", "user1@example.com", "password1")
        Mockito.when(loginServices.saveorUpdate(Mockito.any(Login.class))).thenReturn(login)

        // Act and Assert
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/login/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"_id\":\"1\",\"username\":\"user1\",\"email\":\"user1@example.com\",\"password\":\"password1\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("1"))
    }


    @Test
    void testGetLogins() throws Exception {
        // Arrange
        def logins = [
                new Login('1', 'user1', 'user1@example.com', 'password1'),
                new Login('2', 'user2', 'user2@example.com', 'password2')
        ]
        Mockito.when(loginServices.listAll()).thenReturn(logins)

        // Act
        mvc.perform(MockMvcRequestBuilders.get('/api/v1/login/getAll'))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json('[{"_id":"1","username":"user1","email":"user1@example.com","password":"password1"},{"_id":"2","username":"user2","email":"user2@example.com","password":"password2"}]'))

        // Assert
        Mockito.verify(loginServices).listAll()
    }

    @Test
    void testUpdate() throws Exception {
        // Arrange
        def login = new Login('1', 'user1', 'user1@example.com', 'password1')
        Mockito.when(loginServices.getLoginByID('1')).thenReturn(login)
        Mockito.when(loginServices.saveorUpdate(login)).thenReturn(login)

        // Act
        mvc.perform(MockMvcRequestBuilders.put('/api/v1/login/edit/1')
                .contentType(MediaType.APPLICATION_JSON)
                .content('{"_id":"1","username":"user1","email":"user1@example.com","password":"password1"}'))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json('{"_id":"1","username":"user1","email":"user1@example.com","password":"password1"}'))

        // Assert
        Mockito.when(loginServices.saveorUpdate(login)).thenReturn(login)
    }
    @Test
    void testDeleteLogin() throws Exception {
        // Arrange
        String loginId = "1";

        // Act
        mvc.perform(MockMvcRequestBuilders.delete("/api/v1/login/delete/{id}", loginId))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Assert
        Mockito.verify(loginServices).deleteLogin(loginId);
    }

}