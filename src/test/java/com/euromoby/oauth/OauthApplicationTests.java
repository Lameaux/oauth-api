package com.euromoby.oauth;

import com.euromoby.oauth.controllers.UsersController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OauthApplicationTests {

	@Autowired
	private UsersController usersController;

	@Test
	public void contextLoads() {
		assertThat(usersController).isNotNull();
	}

}
