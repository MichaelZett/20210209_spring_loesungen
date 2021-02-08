package de.zettsystems.netzfilm.movie.application;

import de.zettsystems.netzfilm.movie.domain.Movie;

import java.util.List;

public interface CopyService {
    List<Movie> findAllMoviesWithoutFreeCopies();
}
