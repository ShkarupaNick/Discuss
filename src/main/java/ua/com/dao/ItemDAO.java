
package ua.com.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
        begin();
        for (Item item : items) {
            log.trace("Save item: " + item);
            session.save(item);
        }
        end();
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
        Item item = session.bySimpleNaturalId(Item.class).load(id);
        end();
        return item;
    }


    public List<Item> getbyDate(String date, Integer offset, Integer maxResults) {
        begin();
        List<Item> items = session
                .createCriteria(Item.class)
                .setFirstResult(offset != null ? offset : 0)
                .setMaxResults(maxResults != null ? maxResults : 10)
                .list();
        //List<Item> items = session.createQuery("from Item i where i.airdate= :date").setParameter("date",date).list();
        end();
        return items;
    }
}
