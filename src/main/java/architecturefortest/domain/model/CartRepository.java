package architecturefortest.domain.model;

import architecturefortest.domain.model.base.GenericRepository;

public interface CartRepository extends GenericRepository<Cart> {
    Cart get(Shopper shopper);
    void add(Cart cart);
}
