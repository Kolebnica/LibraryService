package entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;

@Entity(name = "albums")
@NamedQueries(value = {
        @NamedQuery(name = "Album.getAlbums", query = "SELECT a FROM albums a"),
        @NamedQuery(name = "Album.getAlbumsByArtist", query = "SELECT a FROM albums a WHERE a.artist.id = :id"),
        @NamedQuery(name = "Album.getAlbumsByUser", query = "SELECT a FROM albums a WHERE a.userId = :id")
})
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlID
    @XmlElement
    private int id;

    @Column(name = "user_id", nullable = false)
    private int userId;

    private String title;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @Column(name = "release_year")
    private int releaseYear;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

}
