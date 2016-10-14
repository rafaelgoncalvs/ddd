package architecturefortest.application;

import architecturefortest.application.base.Persist;
import architecturefortest.domain.model.ShopperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ShopperPersist extends Persist {

    @Autowired
    public ShopperPersist(ShopperRepository shopperRepository) {
        super(shopperRepository);
    }

}
