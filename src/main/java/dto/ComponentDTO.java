package dto;

public class ComponentDTO {

    private Integer id;
    private String name;
    private String description;
    private String image;

    // Only for REST
    @Deprecated
    public ComponentDTO() {

    }

    public ComponentDTO(Integer id, String name, String description, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

}
