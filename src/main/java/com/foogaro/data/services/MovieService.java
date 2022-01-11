package com.foogaro.data.services;

import com.foogaro.data.models.Movie;
import com.foogaro.data.repositories.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository repository;

    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }

    public Optional<Movie> findById(Long id) {
        return repository.findById(id);
    }

    public List<Movie> findAll() {
        return (List<Movie>) repository.findAll();
    }

    public Movie save(Movie movie) { return repository.save(movie); }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}
