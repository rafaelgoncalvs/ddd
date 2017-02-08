package architecturefortest.application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import architecturefortest.application.base.ServiceApplicationTestBase;
import architecturefortest.domain.model.Cart;
import architecturefortest.domain.model.CartBuilder;
import architecturefortest.domain.model.CartRepository;
import architecturefortest.domain.model.Item;
import architecturefortest.domain.model.Shopper;
import architecturefortest.domain.model.ShopperBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
public class CartApplicationServiceTest extends ServiceApplicationTestBase {

    @Autowired
    private CartApplicationService cartApplicationService;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ShopperPersist shopperPersist;

    @Autowired
    private CartPersist cartPersist;

    private Shopper shopper;

    private String name;

    private BigDecimal price;

    private BigDecimal amount;

    @Before
    public void init() {
        shopper = new ShopperBuilder().create();
        shopperPersist.persist(shopper);

        name = "Mary";
        price = BigDecimal.TEN;
        amount = BigDecimal.ONE;
    }

    @Test
    public void should_get_a_cart() {
		Cart cart = new CartBuilder().withShopper(shopper).create();
		cart.update(name, amount, price);
		cartPersist.persist(cart);
    	
        cartApplicationService.get(shopper.getId());

        Cart cartAdded = cartRepository.get(shopper);
        Item itemAdded = cartAdded.getItems().get(0);

        assertTrue(cartAdded.getItems().size() == 1);
        assertEquals(name, itemAdded.getName());
        assertEquals(price, itemAdded.getPrice());
        assertEquals(amount, itemAdded.getAmount());
    }
    
    @Test
    public void should_add_a_cart_when_it_not_exist() {
        cartApplicationService.get(shopper.getId());

        Cart cartAdded = cartRepository.get(shopper);
        assertNotNull(cartAdded);
    }

    @Test
    public void should_add_an_item_inside_to_cart() {
        Cart cart = new CartBuilder().create();
        cartPersist.persist(cart);
        ItemDto itemDto = new ItemDto();
        itemDto.setName(name);
        itemDto.setPrice(price);
        itemDto.setAmount(amount);
        CartDto cartDto = new CartDto();
        cartDto.setShopperId(cart.getShopper().getId());
        cartDto.setItemDtos(Arrays.asList(itemDto));

        cartApplicationService.update(cartDto);

        Cart cartAdded = cartRepository.get(shopper);
        Item itemAdded = cartAdded.getItems().get(0);

        assertTrue(cartAdded.getItems().size() == 1);
        assertEquals(name, itemAdded.getName());
        assertEquals(price, itemAdded.getPrice());
        assertEquals(amount, itemAdded.getAmount());
    }

    @Test
    public void should_update_an_item_inside_to_cart() {
        BigDecimal newAmount = BigDecimal.valueOf(3.67);
        Cart cart = new CartBuilder().create();
        cart.update(name, amount, price);
        cartPersist.persist(cart);
        ItemDto itemDto = new ItemDto();
        itemDto.setName(name);
        itemDto.setAmount(newAmount);
        CartDto cartDto = new CartDto();
        cartDto.setShopperId(cart.getShopper().getId());
        cartDto.setItemDtos(Arrays.asList(itemDto));

        cartApplicationService.update(cartDto);

        Cart cartAdded = cartRepository.get(shopper);
        Item itemUpdated = cartAdded.getItems().get(0);

        assertEquals(name, itemUpdated.getName());
        assertEquals(price, itemUpdated.getPrice());
        assertEquals(newAmount, itemUpdated.getAmount());
    }

    @Test
    public void should_remove_an_item_when_amount_is_zero() {
        Cart cart = new CartBuilder().create();
        cart.update(name, amount, price);
        cartPersist.persist(cart);
        ItemDto itemDto = new ItemDto();
        itemDto.setName(name);
        itemDto.setAmount(BigDecimal.ZERO);
        CartDto cartDto = new CartDto();
        cartDto.setShopperId(cart.getShopper().getId());
        cartDto.setItemDtos(Arrays.asList(itemDto));

        cartApplicationService.update(cartDto);

        Cart cartAdded = cartRepository.get(shopper);
        assertTrue(cartAdded.getItems().isEmpty());
    }
}
