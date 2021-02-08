package de.zettsystems.netzfilm.rent.domain;

import de.zettsystems.netzfilm.movie.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CopyRepository extends JpaRepository<Copy, Long> {
    Optional<Copy> findByUuid(String uuid);

    Optional<Copy> findByMovie(Movie movie);
}
