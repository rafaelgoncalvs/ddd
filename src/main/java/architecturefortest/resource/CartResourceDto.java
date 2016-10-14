package architecturefortest.resource;

import java.util.ArrayList;
import java.util.List;

public class CartResourceDto {

    private Long shopperId;

    private List<ItemResourceDto> itemsResponce = new ArrayList<>();

    public Long getShopperId() {
        return shopperId;
    }

    public void setShopperId(Long shopperId) {
        this.shopperId = shopperId;
    }

    public List<ItemResourceDto> getItemsResponce() {
        return itemsResponce;
    }

    public void setItemsResponce(List<ItemResourceDto> itemsResponce) {
        this.itemsResponce = itemsResponce;
    }
}
