
package ua.com.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.com.controller.ItemController;
import ua.com.entity.Item;

import java.util.List;
import java.util.UUID;

@Transactional
public class ItemDAO {
    @Autowired
    SessionFactory sessionFactory;
    final static Logger log = Logger.getLogger(ItemController.class);

    Session session = null;
    Transaction transaction = null;

    private void begin() {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
    }

    private void end() {
        transaction.commit();
        session.close();
    }


    public void saveItem(Item item) {
        begin();
        session.save(item);
        end();
    }

    public void saveItemList(Item[] items) {

        for (Item item : items) {
            begin();
            try {
                session.save(item);
            }
            catch (Exception e){
                log.error(e.getMessage());
            }
            end();
        }

    }


    public List getAll() {
        begin();
        log.error(session.createCriteria(Item.class).list());
        List<Item> userList = session.createCriteria(Item.class).list();
        end();
        return userList;
    }

    public Item getbyUUID(UUID uuid) {
        begin();
        Item item = (Item) session.byId(Item.class).load(uuid);
        end();
        return item;
    }

    public Item getbyId(Integer id) {
        begin();
        Item item = (Item)session.createCriteria(Item.class).add(Restrictions.eq("id", id)).list().get(0);
        end();
        return item;
    }


    public List<Item> getbyDate(String date, Integer offset, Integer maxResults) {
        begin();
        List<Item> items = session
                .createCriteria(Item.class)
                .add(Restrictions.eq("airdate", date) )
                .setFirstResult(offset != null ? offset : 0)
                .setMaxResults(maxResults != null ? maxResults : 10)
                .list();
        //List<Item> items = session.createQuery("from Item i where i.airdate= :date").setParameter("date",date).list();
        end();
        return items;
    }
}
