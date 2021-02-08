package de.zettsystems.netzfilm.movie.application;

import de.zettsystems.netzfilm.movie.domain.CopyRepository;
import de.zettsystems.netzfilm.movie.domain.Movie;
import de.zettsystems.netzfilm.movie.domain.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CopyServiceImpl implements CopyService {
    private final CopyRepository copyRepository;
    private final MovieRepository movieRepository;

    public CopyServiceImpl(CopyRepository copyRepository, MovieRepository movieRepository) {
        this.copyRepository = copyRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Movie> findAllMoviesWithoutFreeCopies() {
        return movieRepository.findAll().stream()
                .filter(movie -> copyRepository.countAllByMovieAndLentFalse(movie) == 0L)
                .collect(Collectors.toUnmodifiableList());
    }

}
