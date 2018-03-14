package domain.product;

public class ShopComponent {

    private final String name;
    private final String description;
    private final String imageName;

    public ShopComponent(String name, String description, String imageName) {
        this.name = name;
        this.description = description;
        this.imageName = imageName;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}
