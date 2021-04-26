package com.nanox.w2m;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.nanox.w2m.controller.requests.UpdateSuperHeroRequest;
import com.nanox.w2m.controller.requests.UserLoginInput;
import com.nanox.w2m.domain.UserLogin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class W2MIntegrationTest {

    private static final String NEW_SUPERHERO_NAME = "NEW SUPER HERO";
    private static final String AUTHORIZATION = "Authorization";
    private static final String LOGIN_ENDPOINT = "/login";
    private static final String GET_ALL_SUPERHEROES = "/api/superheroes";
    private static final String GET_SUPERHERO_BY_ID = "/api/superhero/";
    private static final String SEARCH_SUPERHERO = "/api/superheroes/search";
    private static final String UPDATE_SUPERHERO_BY_ID = "/api/superhero/";
    private static final String DELETE_SUPERHERO_BY_ID = "/api/superhero/";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void isForbiddenWithoutAuthorizationToken() throws Exception {
        this.mockMvc.perform(get(GET_ALL_SUPERHEROES)).andExpect(status().isForbidden());
    }

    @Test
    public void isUserAuthenticatedWithUsername() throws Exception {
        String userLoginRequest = createUserLoginInput();
        this.mockMvc.perform(post(LOGIN_ENDPOINT).contentType(MediaType.APPLICATION_JSON).content(userLoginRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").isNotEmpty())
                .andExpect(content().string(containsString("Bearer")));
    }

    @Test
    public void userAuthenticatedWithoutUsernameThrowException() throws Exception {
        this.mockMvc.perform(post(LOGIN_ENDPOINT).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void givenUserAuthenticatedThenReturnSuperHeroes() throws Exception {
        UserLogin userLogin = userAuthenticated();
        this.mockMvc.perform(get(GET_ALL_SUPERHEROES)
                .contentType(MediaType.APPLICATION_JSON)
                .header(AUTHORIZATION, userLogin.getToken()))
                .andDo(print())
                .andExpect(jsonPath("$[*].id").isNotEmpty())
                .andExpect(status().isOk());
    }

    @Test
    public void givenUserAuthenticatedThenReturnSuperHeroesById() throws Exception {
        UserLogin userLogin = userAuthenticated();
        String superHeroId = "d4758b3d-f25c-47cc-8aa1-9872cb10de25";
        this.mockMvc.perform(get(GET_SUPERHERO_BY_ID + superHeroId)
                .contentType(MediaType.APPLICATION_JSON)
                .header(AUTHORIZATION, userLogin.getToken()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").isNotEmpty());
    }

    @Test
    public void givenUserAuthenticatedThenReturnSuperHeroesByName() throws Exception {
        UserLogin userLogin = userAuthenticated();
        this.mockMvc.perform(get(SEARCH_SUPERHERO)
                .contentType(MediaType.APPLICATION_JSON)
                .param("name", "man")
                .header(AUTHORIZATION, userLogin.getToken()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id").isNotEmpty())
                .andExpect(jsonPath("$[*].name").isNotEmpty());
    }

    @Test
    public void givenUserAuthenticatedThenUpdateSuperHero() throws Exception {
        UserLogin userLogin = userAuthenticated();
        String newSuperHero = createUpdateSuperHeroRequest();
        String superHeroId = "30875b39-371f-4f43-bf46-7bebcb2f0f14";
        this.mockMvc.perform(put(UPDATE_SUPERHERO_BY_ID + superHeroId)
                .content(newSuperHero)
                .contentType(MediaType.APPLICATION_JSON)
                .header(AUTHORIZATION, userLogin.getToken()))
                .andExpect(status().isOk());

        this.mockMvc.perform(get(GET_SUPERHERO_BY_ID + superHeroId)
                .contentType(MediaType.APPLICATION_JSON)
                .header(AUTHORIZATION, userLogin.getToken()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(superHeroId))
                .andExpect(jsonPath("$.name").value(NEW_SUPERHERO_NAME));
    }

    @Test
    public void givenUserAuthenticatedThenDeleteSuperHero() throws Exception {
        UserLogin userLogin = userAuthenticated();
        String superHeroId = "a6630afd-e292-41b1-b6c0-698563046157";
        this.mockMvc.perform(delete(DELETE_SUPERHERO_BY_ID + superHeroId)
                .param("name", superHeroId)
                .contentType(MediaType.APPLICATION_JSON)
                .header(AUTHORIZATION, userLogin.getToken()))
                .andExpect(status().isOk());

        this.mockMvc.perform(get(GET_SUPERHERO_BY_ID + superHeroId)
                .contentType(MediaType.APPLICATION_JSON)
                .header(AUTHORIZATION, userLogin.getToken()))
                .andExpect(status().is4xxClientError());
    }

    private String createUpdateSuperHeroRequest() {
        UpdateSuperHeroRequest updateSuperHeroRequest = new UpdateSuperHeroRequest();
        updateSuperHeroRequest.setName(NEW_SUPERHERO_NAME);
        return new Gson().toJson(updateSuperHeroRequest);
    }


    private String createUserLoginInput() throws Exception {
        UserLoginInput userLoginInput = new UserLoginInput();
        userLoginInput.setUsername("nanox");
        return new ObjectMapper().writeValueAsString(userLoginInput);
    }

    private UserLogin userAuthenticated() throws Exception {
        String userLoginRequest = createUserLoginInput();
        String response = this.mockMvc.perform(post(LOGIN_ENDPOINT).contentType(MediaType.APPLICATION_JSON).content(userLoginRequest))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        return new Gson().fromJson(response, UserLogin.class);
    }

}