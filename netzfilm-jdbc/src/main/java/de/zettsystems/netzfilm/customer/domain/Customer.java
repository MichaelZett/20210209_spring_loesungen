package de.zettsystems.netzfilm.customer.domain;

import java.time.LocalDate;
import java.util.UUID;

public class Customer {

    private long id;
    private String uuid;
    private String name;
    private String lastName;
    private LocalDate birthdate;

    public Customer(long id, String name, String lastName, LocalDate birthdate) {
        this(id, UUID.randomUUID().toString(), name, lastName, birthdate);
    }

    public Customer(long id, String uuid, String name, String lastName, LocalDate birthdate) {
        super();
        this.id = id;
        this.uuid = uuid;
        this.name = name;
        this.lastName = lastName;
        this.birthdate = birthdate;
    }

    public Long getId() {
        return id;
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthdate=" + birthdate +
                '}';
    }
}
