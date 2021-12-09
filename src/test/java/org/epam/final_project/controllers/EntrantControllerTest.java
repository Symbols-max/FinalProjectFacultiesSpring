//package org.epam.final_project.controllers;
//
//import org.epam.final_project.FinalProjectFacultiesSpringApplication;
//import org.epam.final_project.service.UserService;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
//import org.junit.runners.BlockJUnit4ClassRunner;
//import org.mockito.Mockito;
//import org.mockito.runners.MockitoJUnitRunner;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@RunWith(MockitoJUnitRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//@TestPropertySource("/application-test.properties")
//public class EntrantControllerTest {
//    @Autowired
//    private WebApplicationContext webApplicationContext;
//        private UserService userService;
//    private MockMvc mockMvc;
//    @Before
//    public void setup() throws Exception {
//
//        System.out.println(userService.toString());
//        System.out.println(webApplicationContext.toString());
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
//        System.out.println("mock: "+mockMvc.toString());
//    }
//
//    @Test
//    public void contextLoads() throws Exception {
////        assertThrows(NullPointerException.class,
////                ()->{
////                    this.mockMvc.perform(get("/")).andDo(print())
////                .andExpect(view().name("index"));
////                });
////
//        this.mockMvc.perform(get("/")).andDo(print())
//                .andExpect(view().name("index"));
//    }
//
////    @Test
////    public void accessDeniedTest() throws Exception {
////        this.mockMvc.perform(get("/main"))
////                .andDo(print())
////                .andExpect(status().is3xxRedirection())
////                .andExpect(redirectedUrl("http://localhost/login"));
////    }
////
////    @Test
////    @Sql(value = {"/create-user-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
////    public void correctLoginTest() throws Exception {
////        this.mockMvc.perform(formLogin().user("dru").password("1"))
////                .andExpect(status().is3xxRedirection())
////                .andExpect(redirectedUrl("/"));
////    }
////
////    @Test
////    public void badCredentials() throws Exception {
////        this.mockMvc.perform(post("/login").param("username", "jonh"))
////                .andDo(print())
////                .andExpect(status().isForbidden());
////    }
//}