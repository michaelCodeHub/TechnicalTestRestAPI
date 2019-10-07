package com.technical.test;

import com.technical.test.model.Person;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplicationTests {


	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void testGetAllUsers() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/users",
				HttpMethod.GET, entity, String.class);

		Assert.assertNotNull(response.getBody());
	}

	@Test
	public void testGetUserById() {
		Person person = restTemplate.getForObject(getRootUrl() + "/users/1", Person.class);
		System.out.println(person.getFirstName());
		Assert.assertNotNull(person);
	}

	@Test
	public void testCreateUser() {
		Person user = new Person();
		user.setFirstName("admin");
		user.setLastName("admin");

		ResponseEntity<Person> postResponse = restTemplate.postForEntity(getRootUrl() + "/users", user, Person.class);
		Assert.assertNotNull(postResponse);
		Assert.assertNotNull(postResponse.getBody());
	}

}
