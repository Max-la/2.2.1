package hiber;

import hiber.config.AppConfig;
import hiber.dao.UserDaoImp;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car car = new Car("Porshe",3);
      User user1 = new User("Ivan","Ivanov","brnvjer@mail.ru");
      user1.setCar(car);
      car.setUser(user1);
      userService.add(user1);
      Car car1 = new Car("Toyota",6);
      User user2 = new User("Vasyliy","Petrov","vervemail.ru");
      user2.setCar(car1);
      car1.setUser(user2);
      userService.add(user2);

      User userCar = userService.getUser("Porshe",3);
      System.out.println("Id = "+userCar.getId());
      System.out.println("First Name = "+userCar.getFirstName());
      System.out.println("Last Name = "+userCar.getLastName());
      System.out.println("Email = "+userCar.getEmail());

      List<User> users = userService.listUsers();
      for (User user : users) {

         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      context.close();
   }
}
