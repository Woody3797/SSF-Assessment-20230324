package ibf2022.batch2.ssf.frontcontroller.respositories;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.batch2.ssf.frontcontroller.model.User;

@Repository
public class AuthenticationRepository {

	// TODO Task 5
	// Use this class to implement CRUD operations on Redis
	@Autowired
	@Qualifier("user")
	RedisTemplate<String, String> template;



	public void save(User user) {
		template.opsForValue().setIfPresent(user.getUsername(), String.valueOf(user.getLoginAttempts()), 1800, TimeUnit.SECONDS);
	}

	public String get(String username) {
		return template.opsForValue().get(username);
	}

}
