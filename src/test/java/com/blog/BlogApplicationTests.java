package com.blog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.Date;

@SpringBootTest
class BlogApplicationTests {

	@Test
	void contextLoads() {
		System.out.println(new Date());
		System.out.println(new Timestamp(new Date().getTime()));
	}

}
