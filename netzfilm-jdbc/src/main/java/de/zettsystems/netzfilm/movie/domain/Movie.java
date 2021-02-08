package de.zettsystems.netzfilm.movie.domain;

import java.time.LocalDate;
import java.util.UUID;

public class Movie {

    private long id;
    private String uuid;
    private String title;
    private LocalDate releaseDate;

    public Movie(long id, String title, LocalDate releaseDate) {
        this(id, UUID.randomUUID().toString(), title, releaseDate);
    }

    public Movie(long id, String uuid, String title, LocalDate releaseDate) {
        super();
        this.id = id;
        this.uuid = uuid;
        this.title = title;
        this.releaseDate = releaseDate;
    }

    public Long getId() {
        return id;
    }

    public String getUuid() {
        return uuid;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
