package com.foogaro.data;

import com.foogaro.data.models.Movie;
import com.foogaro.data.repositories.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = RedisDatabaseConfigurationTests.class)
class RedisDatabaseApplicationTests {

    @Autowired
    private MovieRepository repository;

    @Test
    public void shouldSaveUser_toRedis() {
        Movie movie = new Movie(System.currentTimeMillis(),"Friday",5,2022);
        Movie saved = repository.save(movie);

        assertNotNull(saved);
    }

}
