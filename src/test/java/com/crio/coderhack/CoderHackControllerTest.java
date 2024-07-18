package com.crio.coderhack;

import com.crio.coderhack.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.crio.coderhack.dto.UserRegistrationRequestDto;
import com.crio.coderhack.repository.CoderHackRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashSet;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CoderHackControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private CoderHackRepository coderHackRepository;

	@BeforeEach
	public void setUp() {
		User user = new User();
		user.setUserId("999");
		user.setUserName("testUser");
		user.setScore(0);
		user.setBadges(new HashSet<>());
		coderHackRepository.save(user);
	}

	@Test
	public void testPostUser() throws Exception {
		UserRegistrationRequestDto userRegistrationRequestDto = new UserRegistrationRequestDto("10000", "testUser2");

		mockMvc.perform(MockMvcRequestBuilders.post("/users")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(userRegistrationRequestDto)))
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.userId").value("10000"))
				.andExpect(jsonPath("$.userName").value("testUser2"))
				.andExpect(jsonPath("$.score").value(0))
				.andExpect(jsonPath("$.badges").isEmpty());

		// Cleanup
		coderHackRepository.deleteById("10000");
	}

	@Test
	public void testGetUser() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/users/999")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.userId").value("999"))
				.andExpect(jsonPath("$.userName").value("testUser"))
				.andExpect(jsonPath("$.score").value(0))
				.andExpect(jsonPath("$.badges").isEmpty());

		// Cleanup
		coderHackRepository.deleteById("999");
	}

}

