package architecturefortest.infra.orm;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import architecturefortest.domain.model.Cart;
import architecturefortest.domain.model.CartRepository;
import architecturefortest.domain.model.Shopper;
import architecturefortest.infra.orm.base.GenericRepositoryHibernate;

@Repository
public class CartRepositoryHibernate extends GenericRepositoryHibernate<Cart> implements CartRepository {

    @Autowired
    public CartRepositoryHibernate(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Cart get(Shopper shopper) {
        //TODO: it is not returning a single result
        Query query = getSession().createQuery("SELECT c FROM Cart c");
        List<Cart> carts = query.list();
        return carts.isEmpty() ? null : carts.get(0);
    }

}
