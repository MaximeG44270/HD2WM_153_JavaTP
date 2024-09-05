package eni.tp.app.eni_app.bo;

public class Category {
    private String name;
    private Long id;

    public Category(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public Category() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
