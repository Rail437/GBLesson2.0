package mySpring.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MyCart implements Cart{

    private final Map<Product, Integer> myProducts;

    public MyCart(){
        this.myProducts = new ConcurrentHashMap<>();
    }

    @Override
    public void add(Product product) {
        try {
            if (myProducts.get(product) == null) {
                myProducts.put(product, 1);
            } else {
                myProducts.replace(product, myProducts.get(product) + 1);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Product product) {
        try {
            if (myProducts.get(product) > 1) {
                myProducts.replace(product, myProducts.get(product) - 1);
            } else {
                myProducts.remove(product);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void checkCart(){
        for (var entry : myProducts.entrySet()) {

            System.out.println(entry.getKey().allInfo() + " amount:" + entry.getValue());
        }
    }
}
