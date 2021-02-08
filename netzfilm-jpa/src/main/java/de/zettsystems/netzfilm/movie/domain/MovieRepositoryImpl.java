package de.zettsystems.netzfilm.movie.domain;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class MovieRepositoryImpl implements MovieRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Movie> findAll() {
        return entityManager.createQuery("SELECT m FROM Movie m", Movie.class).getResultList();
    }

    @Override
    public Optional<Movie> findByUuid(String uuid) {
        return Optional.ofNullable(entityManager.createQuery("SELECT m FROM Movie m where m.uuid =:uuid", Movie.class)
                .setParameter("uuid", uuid)
                .getSingleResult());
    }

    @Override
    public Optional<Movie> findByTitle(String title) {
        return Optional.ofNullable(entityManager.createQuery("SELECT m FROM Movie m where m.title =:title", Movie.class)
                .setParameter("title", title)
                .getSingleResult());
    }


    @Override
    @Transactional
    public void save(Movie movie) {
        this.entityManager.persist(movie);
    }

}
