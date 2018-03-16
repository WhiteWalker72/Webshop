package domain.product;

public class ShopComponent {

    private final String id;
    private final String name;
    private final String description;

    public ShopComponent(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}
