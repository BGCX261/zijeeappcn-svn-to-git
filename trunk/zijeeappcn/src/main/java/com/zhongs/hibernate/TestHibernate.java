package com.zhongs.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestHibernate  {

    private static SessionFactory sessionFactory;

    private static String id ="4028e7f34389b4b7014389b4b8210000";
    
    public static void main(String[] args) throws Exception {
        setUp();
//        test1LevelCache();
        test2LevelCache();
        test2LevelCache_2();
        tearDown();        
    }
    
    /**
     * 2 level cachel
     */
    public static void test2LevelCache() {
        Session session = sessionFactory.openSession();
        List<Event> result = session.createQuery("from Event").list();
        Event eInCache = null;

        for (Event event : (List<Event>) result) {
//            System.out.println("Event (" + event.getDate() + ") : " + event.getTitle());
            if (eInCache == null) {
                eInCache = event;
                System.out.println("in for:" + eInCache);
            }
        }
        
        // ----------------------------------------
        id = eInCache.getId();
        System.out.println("eIn2LevelCache:" + sessionFactory.getCache().containsEntity(Event.class, id));
        Event eLoad = (Event) session.load(Event.class, id);
        System.out.println("eIn2LevelCache load:" + eLoad);
        session.close();
    }
    public static void test2LevelCache_2() {
        System.out.println("eIn2LevelCache——2:" + sessionFactory.getCache().containsEntity(Event.class, id));
        Session session = sessionFactory.openSession();
        Event eLoad = (Event) session.load(Event.class, id);
        System.out.println("eIn2LevelCache--2 load:" + eLoad);
        session.close();
    }
    

    public static void test1LevelCache() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Event> result = session.createQuery("from Event").list();
        Event eInCache = null;

        for (Event event : (List<Event>) result) {
            System.out.println("Event (" + event.getDate() + ") : " + event.getTitle());
            if (eInCache == null) {
                eInCache = event;
                System.out.println("in for:" + eInCache);
            }
        }

        // session.createQuery("from Event").list();

        // ----------------------------------------
        // Event e = new Event();
        // e.setId("4028e7f34389b4b7014389b4b8210000");
        // System.out.println(session.contains(e));

        // ----------------------------------------
        System.out.println("eInCache:" + session.contains(eInCache));
        Event eLoad = (Event) session.load(Event.class, eInCache.getId());
        System.out.println("eInCache load:" + eLoad);

        // ----------------------------------------
        session.evict(eInCache);
        Event eLoad2 = (Event) session.load(Event.class, eInCache.getId());
        System.out.println("eInCache evict:" + eLoad2);

        System.out.println(session.getCacheMode());

        session.getTransaction().commit();
        session.close();
        
      
    }
    
    
    

    protected static void setUp() throws Exception {
        // A SessionFactory is set up once for an application
        // configures settings from hibernate.cfg.xml
        if(sessionFactory == null){
            sessionFactory = new Configuration().configure().buildSessionFactory();
            System.out.println("setUp");
        }
    }
    
    

    protected static void tearDown() throws Exception {
        System.out.println(sessionFactory.getStatistics());
        
        sessionFactory.close();
        System.out.println("tearDown");
    }
    

    
    // public void testSaveEntities() {
    // Session session = sessionFactory.openSession();
    // session.beginTransaction();
    // session.save(new Event("Our second eventww!", new Date()));
    // session.save(new Event("only a simple", new Date()));
    // session.getTransaction().commit();
    // session.close();
    // }

}
