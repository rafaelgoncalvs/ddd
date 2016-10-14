package architecturefortest.application.base;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@ContextConfiguration(classes = ApplicationContextForTest.class)
public class ServiceApplicationTestBase {

    protected Session session;

    @Autowired
    protected SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Before
    public void setUp() throws Exception {
        openSessionFactory();
        cleanDatabase(getSession());
    }

    @After
    public void cleanUp() throws Exception {
        closeSessionFactory();
    }

    private void openSessionFactory() {
        if (session == null) {
            try {
                session = sessionFactory.getCurrentSession();
            } catch (HibernateException he) {
                session = sessionFactory.openSession();
            } finally {
                TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(session));
            }
        }
    }

    private void closeSessionFactory() {
        SessionHolder sessionHolder = (SessionHolder) TransactionSynchronizationManager.unbindResource(session.getSessionFactory());
        SessionFactoryUtils.closeSession(sessionHolder.getSession());
        session = null;
    }

    protected void cleanDatabase(Session session) throws Exception {
        session.createSQLQuery("TRUNCATE SCHEMA PUBLIC RESTART IDENTITY AND COMMIT NO CHECK").executeUpdate();
        session.clear();
        session.flush();
    }

}
