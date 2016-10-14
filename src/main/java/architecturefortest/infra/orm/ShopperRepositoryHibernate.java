package architecturefortest.infra.orm;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import architecturefortest.domain.model.Shopper;
import architecturefortest.domain.model.ShopperRepository;
import architecturefortest.infra.orm.base.GenericRepositoryHibernate;

@Repository
public class ShopperRepositoryHibernate extends GenericRepositoryHibernate<Shopper> implements ShopperRepository {

    @Autowired
    public ShopperRepositoryHibernate(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

}
