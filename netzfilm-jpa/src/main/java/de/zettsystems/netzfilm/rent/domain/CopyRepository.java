package de.zettsystems.netzfilm.rent.domain;

import de.zettsystems.netzfilm.movie.domain.Movie;

import java.util.List;
import java.util.Optional;

public interface CopyRepository {
    Optional<Copy> findByUuid(String uuid);

    Optional<Copy> findByMovie(Movie movie);

    void save(Copy movie);

    List<Copy> findAll();
}
