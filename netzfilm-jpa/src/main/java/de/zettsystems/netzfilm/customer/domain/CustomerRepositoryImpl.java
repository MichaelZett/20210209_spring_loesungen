package de.zettsystems.netzfilm.customer.domain;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Customer> findAll() {
        return entityManager.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
    }

    @Override
    public Optional<Customer> findByUuid(String uuid) {
        return Optional.ofNullable(entityManager.createQuery("SELECT c FROM Customer c where c.uuid =:uuid", Customer.class)
                .setParameter("uuid", uuid)
                .getSingleResult());
    }

    @Override
    public Optional<Customer> findByLastName(String lastName) {
        return Optional.ofNullable(entityManager.createQuery("SELECT c FROM Customer c where c.lastName =:lastName", Customer.class)
                .setParameter("lastName", lastName)
                .getSingleResult());
    }

    @Override
    @Transactional
    public void save(Customer customer) {
        this.entityManager.persist(customer);
    }

}
