package com.egen.clearSky;

import com.egen.clearSky.config.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { Application.class })
@SpringBootTest
public class ProjectApplicationTests {

	@Test
	public void contextLoads() {
	}

}
