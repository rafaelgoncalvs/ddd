package architecturefortest.infra.orm.base;

import architecturefortest.domain.model.base.GenericRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.lang.reflect.ParameterizedType;

public abstract class GenericRepositoryHibernate<T> implements GenericRepository<T> {

    private SessionFactory sessionFactory;

    private final Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public GenericRepositoryHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void add(T entidade) {
        getSession().save(entidade);
    }

    public void remove(T entidade) {
        getSession().delete(entidade);
    }

    public T get(Long id) {
        return (T) getSession().get(persistentClass, id);
    }
}
