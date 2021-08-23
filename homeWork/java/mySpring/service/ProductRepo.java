package mySpring.service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class ProductRepo {
    private List<Product> productList;

    public ProductRepo() {
        this.productList = new CopyOnWriteArrayList<>();
    }

    @PostConstruct
    public void init() {
        for (int i = 0; i < 5; i++) {
            int random = new Random().nextInt(1000);
            productList.add(new Product(i + 1,"Product " + (i + 1), random + 100));
        }
    }

    public List<Product> findAll(){
        return productList;
    }

    public Product findById(int id){
        for (int i = 0; i < productList.size(); i++) {
            if(id == productList.get(i).getId()){
                return productList.get(i);
            }
        }
        return null;
    }
    public void printList(){
        for (int i = 0; i < productList.size(); i++) {
            System.out.println(productList.get(i).allInfo());
        }
    }
}
