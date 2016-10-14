package architecturefortest.domain.model;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CartTest {

    private String name;
    private BigDecimal price;
    private BigDecimal amount;

    @Before
    public void init() {
        name = "Name of item";
        price = BigDecimal.TEN;
        amount = BigDecimal.ONE;
    }

    @Test
    public void should_add_an_item_inside_to_cart() {
        Cart cart = new CartBuilder().create();

        cart.update(name, amount, price);

        Item itemAdded = cart.getItems().get(0);
        assertTrue(cart.getItems().size() == 1);
        assertEquals(name, itemAdded.getName());
        assertEquals(price, itemAdded.getPrice());
        assertEquals(amount, itemAdded.getAmount());
    }

    @Test
    public void should_update_an_item_inside_to_cart() {
        BigDecimal newAmount = BigDecimal.TEN;
        Cart cart = new CartBuilder().create();
        cart.update(name, amount, price);

        cart.update(name, newAmount, price);

        Item itemUpdated = cart.getItems().get(0);
        assertTrue(cart.getItems().size() == 1);
        assertEquals(name, itemUpdated.getName());
        assertEquals(price, itemUpdated.getPrice());
        assertEquals(newAmount, itemUpdated.getAmount());
    }

    @Test
    public void should_remove_an_item_when_amount_is_zero() {
        BigDecimal newAmount = BigDecimal.ZERO;
        Cart cart = new CartBuilder().create();
        cart.update(name, amount, price);

        cart.update(name, newAmount, price);

        assertTrue(cart.getItems().isEmpty());

    }
}
