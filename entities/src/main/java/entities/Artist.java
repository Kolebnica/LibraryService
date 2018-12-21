package entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import java.util.List;

@Entity(name = "artists")
@NamedQueries(value = {
        @NamedQuery(name = "Artist.getArtists", query = "SELECT a FROM artists a"),
        @NamedQuery(name = "Artist.getArtistsByUser", query = "SELECT a FROM artists a WHERE a.userId = :id")
})
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlID
    @XmlElement
    private int id;

    @Column(name = "user_id", nullable = false)
    private int userId;

    private String creativeName;

    private String fullName;

    @OneToMany(mappedBy = "artist", fetch = FetchType.LAZY)
    private List<Album> album;

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

    public List<Album> getAlbum() {
        return album;
    }

    public void setAlbum(List<Album> album) {
        this.album = album;
    }
}
