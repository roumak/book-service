package com.epam.jpop.book;

import com.epam.jpop.bookmicroservice.controller.BookRestController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookMicroserviceApplicationTests {

	@Test
	public void contextLoads() {
	}

	@RunWith(SpringJUnit4ClassRunner.class)
    public static class ControllerJunitTests {

        @Autowired
		BookRestController theBookRestController;




    }
}
