package com.mkkim.myspringboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@TestPropertySource(locations="classpath:/test.properties")
@SpringBootTest
public class MyspringbootApplicationTests {
	
//	@Autowired
//	Environment environment;
	
	@Test
	public void contextLoads() {
		//System.out.println(environment.getProperty("mkkim.name"));
//		System.out.println(assertThat(environment.getProperty("mkkim.name")).isEqualTo("test1"));
	}

}
