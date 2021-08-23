package mySpring.service;

import lombok.Getter;

@Getter
public class Product {

    private int id;
    private String title;
    private int cost;

    public Product(int id, String title, int cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
    }

    public String allInfo(){
        return ("id: "+ id +
                " title: " + title +
                " cost: " + cost);
    }
}
