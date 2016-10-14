package architecturefortest.domain.model;

public class ShopperBuilder {

    private String name;

    public ShopperBuilder() {
        this.name = "Jon";
    }

    public Shopper create() {
        return new Shopper(name);
    }

}
