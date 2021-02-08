package de.zettsystems.netzfilm.movie.domain;

import java.util.List;
import java.util.Optional;

public interface MovieRepository {
    Optional<Movie> findByUuid(String uuid);

    Optional<Movie> findByTitle(String title);

    void save(Movie movie);

    List<Movie> findAll();
}
