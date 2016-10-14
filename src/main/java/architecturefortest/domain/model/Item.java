package architecturefortest.domain.model;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class Item {

    private String name;

    private BigDecimal price;

    private BigDecimal amount;

    private Item() {}

    public Item(String name, BigDecimal price, BigDecimal amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public void updateAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Item) {
            if(this == object) {
                return true;
            }
            Item other = (Item) object;
            return other.getName() != null && this.name.equals(other.getName());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.name);
    }

}
