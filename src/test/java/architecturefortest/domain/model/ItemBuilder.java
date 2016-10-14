package architecturefortest.domain.model;

import java.math.BigDecimal;

public class ItemBuilder {

    private String name;
    private BigDecimal price;
    private BigDecimal amount;

    public ItemBuilder() {
        this.name = "Item for testing";
        this.price = BigDecimal.TEN;
        this.amount = BigDecimal.ONE;
    }

    public ItemBuilder withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Item create() {
        return new Item(name, price, amount);
    }

}
