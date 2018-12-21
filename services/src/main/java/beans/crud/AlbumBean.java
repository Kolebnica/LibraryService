package beans.crud;

import entities.Album;
import org.eclipse.microprofile.metrics.annotation.Counted;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class AlbumBean {

    @PersistenceContext(unitName = "sr-jpa")
    private EntityManager em;

    @Counted(name = "AlbumBeanCall", monotonic = true)
    public Album getAlbum(int id) {
        return em.find(Album.class, id);
    }

    @Counted(name = "AlbumBeanCall", monotonic = true)
    public List<Album> getAlbums() {
        TypedQuery<Album> q = em.createNamedQuery("Album.getAlbums", Album.class);
        return q.getResultList();
    }

    @Counted(name = "AlbumBeanCall", monotonic = true)
    public List<Album> getAlbumsByArtist(int artistId) {
        TypedQuery<Album> q = em.createNamedQuery("Album.getAlbumsByArtist", Album.class).setParameter("id", artistId);
        return q.getResultList();
    }

    @Counted(name = "AlbumBeanCall", monotonic = true)
    public List<Album> getAlbumsByUser(int userId) {
        TypedQuery<Album> q = em.createNamedQuery("Album.getAlbumsByUser", Album.class).setParameter("id", userId);
        return q.getResultList();
    }

    @Transactional
    @Counted(name = "AlbumBeanCall", monotonic = true)
    public Album insertAlbum(Album a) {
        try {
            em.persist(a);
            em.flush();
            return a;
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    @Counted(name = "AlbumBeanCall", monotonic = true)
    public Album updateAlbum(int id, Album a) {
        Album existing = em.find(Album.class, id);
        if (existing == null) {
            return null;
        }

        a.setId(existing.getId());
        a = em.merge(a);
        em.flush();
        return a;
    }

    @Transactional
    @Counted(name = "AlbumBeanCall", monotonic = true)
    public boolean deleteAlbum(int id) {
        Album a = em.find(Album.class, id);
        if (a == null) {
            return false;
        }

        em.remove(a);
        return true;
    }

}