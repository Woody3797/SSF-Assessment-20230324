package ibf2022.batch2.ssf.frontcontroller.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import ibf2022.batch2.ssf.frontcontroller.model.User;
import ibf2022.batch2.ssf.frontcontroller.respositories.AuthenticationRepository;
import jakarta.json.Json;
import jakarta.json.JsonObject;

@Service
public class AuthenticationService {

	@Autowired
	AuthenticationRepository repo;

	// TODO: Task 2
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write the authentication method in here
	public ResponseEntity<String> authenticate(String username, String password) throws Exception {
	
		JsonObject jo = Json.createObjectBuilder()
		.add("username", username)
		.add("password", password).build();

		String url = "https://auth.chuklee.com/api/authenticate";
		RequestEntity<String> request = RequestEntity.post(url)
		.contentType(MediaType.APPLICATION_JSON)
		.header("Accept", MediaType.APPLICATION_JSON_VALUE)
		.body(jo.toString(), String.class);

		RestTemplate template = new RestTemplate();
		ResponseEntity<String> response = template.exchange(request, String.class);
		return response;
	}

	// TODO: Task 4
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write an implementation to disable a user account for 30 mins
	public boolean disableUser(String username) {
		String value = repo.get(username);
		if (Integer.parseInt(value) > 3) {
			return true;
		}
		return false;
	}

	// TODO: Task 5
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write an implementation to check if a given user's login has been disabled
	public boolean isLocked(String username) {
		return false;
	}
}
