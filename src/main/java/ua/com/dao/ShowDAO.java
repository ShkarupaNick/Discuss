
package ua.com.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.controller.ShowController;
import ua.com.entity.Show;
import java.util.List;
import java.util.UUID;

@Transactional
@Service
public class ShowDAO {
    @Autowired
    private SessionFactory sessionFactory;
    final static Logger log = Logger.getLogger(ShowController.class);

    Session session = null;
   Transaction transaction = null;

    private void begin(){
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
    }

    private void end(){
        transaction.commit();
        session.close();
    }



    public List getAll() {
        begin();
        log.error(session.createCriteria(Show.class).list());
        List showList = session.createCriteria(Show.class).list();
        end();
        return showList;
    }

    public Show getbyUUID(UUID uuid){
        begin();
        Show show = session.byId(Show.class).load(uuid);
        end();
        return show;
    }

    public Show getbyId(Integer id){
        begin();
        Show show = session.bySimpleNaturalId(Show.class).load(id);
        end();
        return show;
    }


    public List<Show> getPaginated(Integer offset, Integer maxResults){
        begin();
        List shows= session
                .createCriteria(Show.class)
                .setFirstResult(offset!=null?offset:0)
               // .add(Restrictions.eq("date", date) )
                .setMaxResults(maxResults!=null?maxResults:10)
                .list();
        //List<Show> shows = session.createQuery("from Show i where i.airdate= :date").setParameter("date",date).list();
        end();
        return shows;
    }


    public void saveShow(Show show) {
        begin();
        session.save(show);
        end();
    }
}
