package architecturefortest.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import architecturefortest.domain.model.Shopper;
import architecturefortest.domain.model.ShopperRepository;

@Service
public class ShopperApplicationContext {

    private ShopperRepository shopperRepository;

    @Autowired
    public ShopperApplicationContext(ShopperRepository shopperRepository) {
        this.shopperRepository = shopperRepository;
    }

    @Transactional
    public void add(ShopperDto shopperDto) {
        Shopper shopper = new Shopper(shopperDto.getName());
        shopperRepository.add(shopper);
    }

}
