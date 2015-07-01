package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.PropertyPlaceholderAutoConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestCommonConfig.class, PropertyPlaceholderAutoConfiguration.class })
@ActiveProfiles("dev")
public class AppTest {

	static {
		System.setProperty("spring.profiles.active", "dev");
	}
	
//	@Autowired
//	private JedisConnectionFactory jedisConnectionFactory;
	
//	@Autowired
//	private UserService userService;
	
	@Test
	public void testJedisSpring() throws Exception {
//		List<User> usersList=userService.selectPoBySo(new UserSearchSo());
//		System.out.println(usersList);
	}

	
}