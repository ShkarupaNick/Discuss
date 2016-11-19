
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

    public void saveItem(Item item) {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.save(item);
        transaction.commit();
        session.close();
    }


    public List getAll() {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();

        log.error(session.createCriteria(Item.class).list());
        List<Item> userList = session.createCriteria(Item.class).list();
        transaction.commit();
        session.close();
        return userList;
    }

    public List<Item> getbyId(UUID id){
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        List<Item> items = session.createQuery("from Item i where i.uuid = :id").setParameter("id",id).list();
        transaction.commit();
        session.close();
        return items;
    }


    public List<Item> getbyDate(String date){
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        List<Item> items = session.createQuery("from Item i where i.airdate= :date").setParameter("date",date).list();
        transaction.commit();
        session.close();
        return items;
    }
}
