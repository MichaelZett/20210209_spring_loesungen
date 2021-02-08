package de.zettsystems.netzfilm.movie.domain;

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
public class MovieRepositoryImpl implements MovieRepository {
    private static final String FIND_BY_UUID = "SELECT * FROM MOVIE WHERE UUID = ?";
    private static final String SELECT_BY_ID = "SELECT * FROM MOVIE WHERE TITLE = :title";
    private static final String INSERT = "INSERT INTO MOVIE VALUES (?, ?, ?, ?)";
    private static final String FIND_ALL = "SELECT * FROM MOVIE";

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsertMovie;

    public MovieRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate, SimpleJdbcInsert simpleJdbcInsertMovie) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.simpleJdbcInsertMovie = simpleJdbcInsertMovie;
    }

    @Override
    public List<Movie> findAll() {
        return jdbcTemplate.query(FIND_ALL, getMovieRowMapper());
    }

    @Override
    public Optional<Movie> findByUuid(String uuid) {
        Movie movie = jdbcTemplate.queryForObject(FIND_BY_UUID, getMovieRowMapper(), uuid);
        return Optional.ofNullable(movie);
    }

    @Override
    public Optional<Movie> findByTitle(String title) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("title", title);
        return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(SELECT_BY_ID, namedParameters, getMovieRowMapper()));
    }

    @Override
    public boolean save(Movie movie) {
        if (movie.getId() % 2 == 0) {
            SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(movie);
            return 1 == simpleJdbcInsertMovie.execute(parameterSource);
        } else {
            return 1 == jdbcTemplate.update(INSERT, movie.getId(), movie.getUuid(),
                    movie.getTitle(), movie.getReleaseDate());
        }
    }

    private RowMapper<Movie> getMovieRowMapper() {
        return (rs, rowNum) ->
                new Movie(
                        rs.getLong("id"),
                        rs.getString("uuid"),
                        rs.getString("title"),
                        rs.getDate("release_date").toLocalDate()
                );
    }
}
