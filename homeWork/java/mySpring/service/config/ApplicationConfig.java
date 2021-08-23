package mySpring.service.config;


import mySpring.service.MyCart;
import mySpring.service.ProductRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "mySpring")
public class ApplicationConfig {

    @Bean
    public ProductRepo productRepo(){
    return new ProductRepo();
    }

    @Bean
    public MyCart myCart(){
        return new MyCart();
    }
}
