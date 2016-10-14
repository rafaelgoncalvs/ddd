package architecturefortest.application;

import java.util.ArrayList;
import java.util.List;

public class CartDto {

	private Long shopperId;

	private List<ItemDto> itemDtos = new ArrayList<>();

    public Long getShopperId() {
        return shopperId;
    }

    public void setShopperId(Long shopperId) {
        this.shopperId = shopperId;
    }

    public List<ItemDto> getItemDtos() {
        return itemDtos;
    }

    public void setItemDtos(List<ItemDto> itemDtos) {
        this.itemDtos = itemDtos;
    }
}
