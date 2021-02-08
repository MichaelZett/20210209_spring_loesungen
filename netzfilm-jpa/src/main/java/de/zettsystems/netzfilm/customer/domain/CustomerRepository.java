package de.zettsystems.netzfilm.customer.domain;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    Optional<Customer> findByUuid(String uuid);

    Optional<Customer> findByLastName(String lastName);

    void save(Customer customer);

    List<Customer> findAll();
}
