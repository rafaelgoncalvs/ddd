package architecturefortest.application;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import architecturefortest.domain.model.Cart;
import architecturefortest.domain.model.CartRepository;
import architecturefortest.domain.model.Item;
import architecturefortest.domain.model.Shopper;
import architecturefortest.domain.model.ShopperRepository;

@Service
public class CartApplicationService {

    private CartRepository cartRepository;

    private ShopperRepository shopperRepository;

    @Autowired
    public CartApplicationService(CartRepository cartRepository, ShopperRepository shopperRepository) {
        this.cartRepository = cartRepository;
        this.shopperRepository = shopperRepository;
    }

    @Transactional
    public CartDto get(long shopperId) {
        Shopper shopper = shopperRepository.get(shopperId);
		Cart cart = cartRepository.get(shopper);
		if(cart == null) {
			cart = newCart(shopper);
		}

        List<ItemDto> itemDtos = cart.getItems().stream().map(item -> mapperItemDto(item)).collect(toList());

        CartDto cartDto = new CartDto();
        cartDto.setShopperId(cart.getShopper().getId());
        cartDto.setItemDtos(itemDtos);
        return cartDto;
	}

    @Transactional
    public void update(CartDto cartDto) {
        Shopper shopper = shopperRepository.get(cartDto.getShopperId());
        Cart cart = cartRepository.get(shopper);
        for(ItemDto itemDto : cartDto.getItemDtos()) {
            cart.update(itemDto.getName(), itemDto.getAmount(), itemDto.getPrice());
        }
        cartRepository.add(cart);
    }
    
    private Cart newCart(Shopper shopper) {
		Cart cart = new Cart(shopper);
		cartRepository.add(cart);
		return cart;
	}

    private ItemDto mapperItemDto(Item item) {
        ItemDto itemDto = new ItemDto();
        itemDto.setName(item.getName());
        itemDto.setAmount(item.getAmount());
        itemDto.setPrice(item.getPrice());
        return itemDto;
    }

}
