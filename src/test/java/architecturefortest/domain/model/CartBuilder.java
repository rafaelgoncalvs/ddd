package architecturefortest.domain.model;

public class CartBuilder {

    private Shopper shopper;

    public CartBuilder() {
        this.shopper = new ShopperBuilder().create();
    }

    public CartBuilder withShopper(Shopper shopper) {
		this.shopper = shopper;
		return this;
	}
    
    public Cart create() {
        return new Cart(shopper);
    }

}
