package org.prepare;

public class Gift {

    private String name;
    private String description;
    private String storeLink;
    private String price;
    private String imageLink;

    public Gift(String name, String description, String storeLink, String price, String imageLink) {
        this.name = name;
        this.description = description;
        this.storeLink = storeLink;
        this.price = price;
        this.imageLink = imageLink;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getStoreLink() {
        return storeLink;
    }

    public String getPrice() {
        return price;
    }

    public String getImageLink() {
        return imageLink;
    }
}
