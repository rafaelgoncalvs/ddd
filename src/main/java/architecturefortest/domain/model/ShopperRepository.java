package architecturefortest.domain.model;

import architecturefortest.domain.model.base.GenericRepository;

public interface ShopperRepository extends GenericRepository<Shopper> {
    Shopper get(Long shopperId);
}
