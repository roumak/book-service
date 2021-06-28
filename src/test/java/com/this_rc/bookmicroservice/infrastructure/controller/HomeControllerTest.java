package com.this_rc.bookmicroservice.infrastructure.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.ServletContext;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= ControllerConfig.class)
@WebAppConfiguration(
        value = "src/main/java/com/this_rc/bookmicroservice/infrastructure/controller"
)
public class HomeControllerTest {

    @Autowired
    private WebApplicationContext wac;

//    @Autowired
//    private AnnotationConfigWebApplicationContext config;



    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void givenWac_whenServletContext_thenItProvidesGreetController() {
        ServletContext servletContext = wac.getServletContext();

        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Stream.of(wac.getBeanDefinitionNames()).parallel().forEach(System.out::println);
        Assert.assertNotNull(wac.getBean("homeController"));
    }
    




}