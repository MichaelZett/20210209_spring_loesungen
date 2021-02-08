package de.zettsystems.netzfilm.rent.domain;

import de.zettsystems.netzfilm.movie.domain.Movie;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class CopyRepositoryImpl implements CopyRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Copy> findByUuid(String uuid) {
        return Optional.ofNullable(entityManager.createQuery("SELECT m FROM Copy m where m.uuid =:uuid", Copy.class)
                .setParameter("uuid", uuid)
                .getSingleResult());
    }

    @Override
    public Optional<Copy> findByMovie(Movie movie) {
        return Optional.ofNullable(entityManager.createQuery("SELECT c FROM Copy c where c.movie =:movie", Copy.class)
                .setParameter("movie", movie)
                .getSingleResult());
    }

    @Override
    public void save(Copy copy) {
        this.entityManager.persist(copy);
    }

    @Transactional
    @Override
    public List<Copy> findAll() {
        return entityManager.createQuery("SELECT m FROM Copy m", Copy.class).getResultList();
    }
}
