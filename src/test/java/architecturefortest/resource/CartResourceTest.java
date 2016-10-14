package architecturefortest.resource;

import architecturefortest.application.CartApplicationService;
import architecturefortest.application.CartDto;
import architecturefortest.application.ItemDto;
import architecturefortest.resource.base.ResourceTestBase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import javax.ws.rs.client.Entity;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class CartResourceTest extends ResourceTestBase {

    private CartApplicationService cartApplicationService;
    private Long shopperId;
    private BigDecimal amount;
    private BigDecimal price;
    private String name;

    @Override
    protected List<Object> resourcesParaSeremRegistrados() {
        cartApplicationService = mock(CartApplicationService.class);
        return Arrays.asList(new CartResource(cartApplicationService));
    }

    @Before
    public void init() {
        shopperId = 2L;
        amount = BigDecimal.ONE;
        price = BigDecimal.TEN;
        name = "Job";
    }

    @Test
    public void should_get_the_cart() {
        CartDto cartDto = new CartDto();
        cartDto.setShopperId(shopperId);
        ItemDto itemDto = new ItemDto();
        itemDto.setAmount(amount);
        itemDto.setPrice(price);
        itemDto.setName(name);
        cartDto.setItemDtos(Arrays.asList(itemDto));
        Mockito.when(cartApplicationService.get(shopperId)).thenReturn(cartDto);

        CartResourceDto cartResourceDto = target("carts").path(shopperId.toString()).request().get(CartResourceDto.class);
        ItemResourceDto itemResourceDto = cartResourceDto.getItemsResponce().get(0);

        assertTrue(cartResourceDto.getItemsResponce().size() == 1);
        assertEquals(shopperId, cartResourceDto.getShopperId());
        assertEquals(name, itemResourceDto.getName());
        assertEquals(price, itemResourceDto.getPrice());
        assertEquals(amount, itemResourceDto.getAmount());
    }

    @Test
    public void should_update_the_cart() {
        ItemResourceDto itemResourceDto = new ItemResourceDto();
        itemResourceDto.setName(name);
        itemResourceDto.setAmount(amount);
        itemResourceDto.setPrice(price);
        CartResourceDto cartResourceDto = new CartResourceDto();
        cartResourceDto.setShopperId(shopperId);
        cartResourceDto.setItemsResponce(Arrays.asList(itemResourceDto));

        target("carts").path(shopperId.toString()).request().put(Entity.json(cartResourceDto));

        ArgumentCaptor<CartDto> cartDtoArgument = ArgumentCaptor.forClass(CartDto.class);
        Mockito.verify(cartApplicationService, Mockito.times(1)).update(cartDtoArgument.capture());
        CartDto cartDto = cartDtoArgument.getValue();
        ItemDto itemDto = cartDto.getItemDtos().get(0);

        assertTrue(cartDto.getItemDtos().size() == 1);
        assertEquals(shopperId, cartDto.getShopperId());
        assertEquals(name, itemDto.getName());
        assertEquals(amount, itemDto.getAmount());
        assertEquals(price, itemDto.getPrice());

    }
}
