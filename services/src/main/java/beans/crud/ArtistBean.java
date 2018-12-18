package beans.crud;

import entities.Artist;
import org.eclipse.microprofile.metrics.annotation.Counted;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ArtistBean {

    @PersistenceContext(unitName = "sr-jpa")
    private EntityManager em;

    @Counted(name = "ArtistBeanCall", monotonic = true)
    public Artist getArtist(int id) {
        return em.find(Artist.class, id);
    }

    @Counted(name = "ArtistBeanCall", monotonic = true)
    public List<Artist> getArtists() {
        TypedQuery<Artist> q = em.createNamedQuery("Artist.getArtists", Artist.class);
        return q.getResultList();
    }

    @Transactional
    @Counted(name = "ArtistBeanCall", monotonic = true)
    public Artist insertArtist(Artist a) {
        try {
            em.persist(a);
            em.flush();
            return a;
        } catch (EntityExistsException exists) {
            return null;
        }
    }

    @Transactional
    @Counted(name = "ArtistBeanCall", monotonic = true)
    public Artist updateArtist(int id, Artist a) {
        Artist existing = em.find(Artist.class, id);
        if (existing == null) {
            return null;
        }

        a.setId(existing.getId());
        a = em.merge(a);
        em.flush();
        return a;
    }

    @Transactional
    @Counted(name = "ArtistBeanCall", monotonic = true)
    public boolean deleteArtist(int id) {
        Artist a = em.find(Artist.class, id);
        if (a == null) {
            return false;
        }

        em.remove(a);
        return true;
    }

}
