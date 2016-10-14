package architecturefortest.application;

import architecturefortest.application.base.Persist;
import architecturefortest.domain.model.Cart;
import architecturefortest.domain.model.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CartPersist extends Persist<Cart> {

    private ShopperPersist shopperPersist;

    @Autowired
    public CartPersist(CartRepository cartRepository, ShopperPersist shopperPersist) {
        super(cartRepository);
        this.shopperPersist = shopperPersist;
    }

    @Override
    protected void addOtherEntities(Cart cart) {
        shopperPersist.persist(cart.getShopper());
    }
}
