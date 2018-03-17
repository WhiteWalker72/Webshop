package domain.component;

public class ShopComponent {

    private final int id;
    private final String name;
    private final String description;
    private final String imageName;

    ShopComponent(int id, String name, String description, String imageName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageName = imageName;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageName() {
        return imageName;
    }

}
