package domain.product;

public class ShopComponent {

    private final String ID;
    private final String name;
    private final String[] description;

    public ShopComponent(String ID, String name, String[] description) {
        this.ID = ID;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String[] getDescription() {
        return description;
    }

}
