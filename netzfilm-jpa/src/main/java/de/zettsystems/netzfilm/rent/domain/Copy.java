package de.zettsystems.netzfilm.rent.domain;

import de.zettsystems.netzfilm.movie.domain.Movie;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

@Entity
public class Copy {
    @Id
    @GeneratedValue
    private long id;
    @Version
    private long version;
    private String uuid;
    @Enumerated(EnumType.STRING)
    private CopyType type;
    @ManyToOne
    private Movie movie;

    //needed for jpa
    protected Copy() {

    }

    public Copy(String uuid, CopyType type, Movie movie) {
        this.uuid = uuid;
        this.type = type;
        this.movie = movie;
    }

    public long getId() {
        return id;
    }

    public long getVersion() {
        return version;
    }

    public String getUuid() {
        return uuid;
    }

    public CopyType getType() {
        return type;
    }

    public Movie getMovie() {
        return movie;
    }
}
