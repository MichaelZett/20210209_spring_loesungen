package de.zettsystems.netzfilm.customer.domain;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
    private static final String FIND_BY_UUID = "SELECT * FROM CUSTOMER WHERE UUID = ?";
    private static final String SELECT_BY_LASTNAME = "SELECT * FROM CUSTOMER WHERE LAST_NAME = :lastName";
    private static final String INSERT = "INSERT INTO CUSTOMER VALUES (?, ?, ?, ?,?)";
    private static final String FIND_ALL = "SELECT * FROM CUSTOMER";

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsertCustomer;

    public CustomerRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate, SimpleJdbcInsert simpleJdbcInsertCustomer) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.simpleJdbcInsertCustomer = simpleJdbcInsertCustomer;
    }

    @Override
    public List<Customer> findAll() {
        return jdbcTemplate.query(FIND_ALL, getCustomerRowMapper());
    }

    @Override
    public Optional<Customer> findByUuid(String uuid) {
        Customer customer = jdbcTemplate.queryForObject(FIND_BY_UUID, getCustomerRowMapper(), uuid);
        return Optional.ofNullable(customer);
    }

    @Override
    public Optional<Customer> findByLastName(String lastName) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("lastName", lastName);
        return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(SELECT_BY_LASTNAME, namedParameters, getCustomerRowMapper()));
    }

    @Override
    public boolean save(Customer customer) {
        if (customer.getId() % 2 == 0) {
            SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(customer);
            return 1 == simpleJdbcInsertCustomer.execute(parameterSource);
        } else {
            return 1 == jdbcTemplate.update(INSERT, customer.getId(), customer.getUuid(),
                    customer.getName(), customer.getLastName(), customer.getBirthdate());
        }
    }

    private RowMapper<Customer> getCustomerRowMapper() {
        return (rs, rowNum) ->
                new Customer(
                        rs.getLong("id"),
                        rs.getString("uuid"),
                        rs.getString("name"),
                        rs.getString("last_name"),
                        rs.getDate("birthdate").toLocalDate()
                );
    }
}
