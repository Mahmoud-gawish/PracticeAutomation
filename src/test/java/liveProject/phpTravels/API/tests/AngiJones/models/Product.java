package liveProject.phpTravels.API.tests.AngiJones.models;

public class Product {

    private int  id;
    private String name;
    private String description;
    private double price;
    private int category_id;

    public Product(){}

    // use for post request
    public Product(String name, String description,double price ,int category_id){
        setName(name);
        setDescription(description);
        setPrice(price);
        setCategory_id(category_id);

    }

    // used for PUT request
    public Product(int id,String name, String description,double price ,int category_id){
        setId(id);
        setName(name);
        setDescription(description);
        setPrice(price);
        setCategory_id(category_id);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
}
