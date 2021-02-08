package de.zettsystems.netzfilm.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;

@Configuration
public class PersistenceConfiguration {
    @Bean
    public SimpleJdbcInsert simpleJdbcInsertMovie(DataSource dataSource) {
        return new SimpleJdbcInsert(dataSource).withTableName("MOVIE");
    }

    @Bean
    public SimpleJdbcInsert simpleJdbcInsertCustomer(DataSource dataSource) {
        return new SimpleJdbcInsert(dataSource).withTableName("CUSTOMER");
    }
}
