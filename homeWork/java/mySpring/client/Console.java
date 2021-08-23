package mySpring.client;

import mySpring.service.MyCart;
import mySpring.service.ProductRepo;
import mySpring.service.config.ApplicationConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;
import java.util.stream.Stream;

public class Console {
    public static void main(String[] args) {

        final ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        final Scanner scanner = new Scanner(System.in);
        helpCommand(context);
        while (true) {
            final String str = scanner.nextLine();
            if (str.contains("ADD")) {
                int[] id = Stream.of(str.split("\\s+")).skip(1).limit(1).mapToInt(Integer::parseInt).toArray();
                addCommand(context, id[0]);
            }
            if (str.contains("Delete")){
                int[] id = Stream.of(str.split("\\s+")).skip(1).limit(1).mapToInt(Integer::parseInt).toArray();
                deleteCommand(context, id[0]);
            }
            if ("help".equalsIgnoreCase(str)) {
                helpCommand(context);
            }
            if ("EXIT".equalsIgnoreCase(str)) {
                return;
            }
        }
    }
    private static void addCommand(ApplicationContext context, int id){
        MyCart cart = context.getBean(MyCart.class);
        ProductRepo repo = context.getBean(ProductRepo.class);
        cart.add(repo.findById(id));
        System.out.println("В корзину добавлен: " + repo.findById(id).getTitle());
        System.out.println("В вашей корзине сейчас: ");
        cart.checkCart();
    }

    private static void deleteCommand(ApplicationContext context, int id){
        MyCart cart = context.getBean(MyCart.class);
        ProductRepo repo = context.getBean(ProductRepo.class);
        cart.delete(repo.findById(id));
        System.out.println("Из корзины удален: " + repo.findById(id).getTitle());
        System.out.println("В вашей корзине сейчас: ");
        cart.checkCart();
    }


    private static void helpCommand(ApplicationContext context) {
        System.out.println("""
                Введите одну из комманд команд:
                Cart - моя корзина.
                ADD № - Добавить продукт c номером № в корзину
                Delete № - Удалить продукт с номером № из корзины
                Help - Повторить команды и доступные продукты
                Доступные продукты:\s""");
        context.getBean(ProductRepo.class).printList();
    }
}
