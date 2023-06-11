package fr.orionbs.user_manager;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(profiles = "application-test.yml")
class UserManagerApplicationTests {

	@Test
	void contextLoads() {
	}

}
