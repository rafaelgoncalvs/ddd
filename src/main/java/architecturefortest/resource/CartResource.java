package architecturefortest.resource;

import static java.util.stream.Collectors.toList;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import architecturefortest.application.CartApplicationService;
import architecturefortest.application.CartDto;
import architecturefortest.application.ItemDto;

@Produces(MediaType.APPLICATION_JSON)
@Path("carts")
public class CartResource {

	private CartApplicationService cartApplicationService;

	@Autowired
	public CartResource(CartApplicationService cartApplicationService) {
		this.cartApplicationService = cartApplicationService;
	}

	@GET
	@Path("/{shopper}")
	public CartResourceDto get(@PathParam("shopper") Long shopperId) {
		CartDto cartDto = cartApplicationService.get(shopperId);
        List<ItemResourceDto> itemsResponce = cartDto.getItemDtos().stream().map(itemDto -> mapperItemResourceDto(itemDto)).collect(toList());
        CartResourceDto cartResourceDto = new CartResourceDto();
        cartResourceDto.setShopperId(cartDto.getShopperId());
        cartResourceDto.setItemsResponce(itemsResponce);
        return cartResourceDto;
	}

    @PUT
    @Path("/{shopper}")
	public void put(@PathParam("shopper") Long shopperId, CartResourceDto cartResourceDto) {
        List<ItemDto> itemDtos = cartResourceDto.getItemsResponce().stream().map(itemResponceDto -> mapperItemDto(itemResponceDto)).collect(toList());
        CartDto cartDto = new CartDto();
        cartDto.setShopperId(shopperId);
        cartDto.setItemDtos(itemDtos);
        cartApplicationService.update(cartDto);
	}

    private ItemResourceDto mapperItemResourceDto(ItemDto itemDto) {
        ItemResourceDto itemResourceDto = new ItemResourceDto();
        itemResourceDto.setName(itemDto.getName());
        itemResourceDto.setAmount(itemDto.getAmount());
        itemResourceDto.setPrice(itemDto.getPrice());
        return itemResourceDto;
    }

    private ItemDto mapperItemDto(ItemResourceDto itemResponceDto) {
        ItemDto itemDto = new ItemDto();
        itemDto.setAmount(itemResponceDto.getAmount());
        itemDto.setName(itemResponceDto.getName());
        itemDto.setPrice(itemResponceDto.getPrice());
        return  itemDto;
    }
}
