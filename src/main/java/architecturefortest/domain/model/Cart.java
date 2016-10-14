package architecturefortest.domain.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import architecturefortest.domain.model.base.DomainEntity;

@Entity
public class Cart extends DomainEntity {

    @ManyToOne
    private Shopper shopper;

    @ElementCollection
    private List<Item> items = new ArrayList<>();

    private Cart() {}

    public Cart(Shopper shopper) {
        this.shopper = shopper;
    }

    public void update(String name, BigDecimal amount, BigDecimal price) {
        Optional<Item> itemForUpdating = items.stream().filter(item -> item.getName().equals(name)).findAny();
        if(itemForUpdating.isPresent()) {
            if(amountMinusOrZero(amount)) {
                items.remove(itemForUpdating.get());
            } else {
                itemForUpdating.get().updateAmount(amount);
            }
        } else {
            items.add(new Item(name, price, amount));
        }
    }

    private boolean amountMinusOrZero(BigDecimal amount) {
        return amount.compareTo(BigDecimal.ZERO) <= 0;
    }

    public Shopper getShopper() {
        return shopper;
    }

    public List<Item> getItems() {
        return items;
    }
}
