package com.foogaro.data;

import com.foogaro.data.models.Movie;
import com.foogaro.data.repositories.MovieRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@ExtendWith(SpringExtension.class)
//@WebMvcTest
//@SpringBootTest(classes = RedisDatabaseConfigurationTests.class)
class RedisDatabaseControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieRepository repository;

   // @Test
    void movieList_shouldReturnNoneFound_whenNoDataExists() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/movies"))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }

   // @Test
    void saveMovie_shouldReturnCreatedId() throws Exception {
        Mockito.when(repository.save(any(Movie.class))).thenReturn(new Movie(1l,"Friday",5,2022));
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/movies")
                        .param("id", "1")
                        .param("title", "Friday")
                        .param("rating", "5")
                        .param("year", "2022"))
                .andExpect(status().isOk())
                .andReturn();
        Mockito.verify(repository).save(any(Movie.class));
        Assert.assertTrue(mvcResult.getResponse().getContentAsString().equals("1"));
    }
}
