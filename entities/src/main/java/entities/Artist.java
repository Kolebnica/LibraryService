package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity(name = "artists")
@NamedQueries(value = {
        @NamedQuery(name = "Artist.getArtists", query = "SELECT a FROM artists a"),
        @NamedQuery(name = "Artist.getArtistsByUser", query = "SELECT a FROM artists a WHERE a.userId = :id")
})

public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id", nullable = false)
    private int userId;

    private String creativeName;

    private String fullName;

    @OneToMany(mappedBy = "artist", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"artist"})
    private List<Album> albums;

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

    public String getCreativeName() {
        return creativeName;
    }

    public void setCreativeName(String creativeName) {
        this.creativeName = creativeName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<Album> getAlbums() {
        return albums;
    }
}
