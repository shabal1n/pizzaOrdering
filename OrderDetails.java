import java.util.*;
import java.time.*;

public class OrderDetails{

  static boolean isBirthday = false;
  static int id;
  static LocalDate date;
  static LocalTime time;
  static int pizzaSize;
  static String orderDetails;
  static int tempTotal;
  static int finalTotal;
  static String orderSorting;
  static ArrayList<String> orderSortingList = new ArrayList<String>();
  static ArrayList<String> ordersFinal = new ArrayList<String>();

  OrderDetails(){}

  public static void birthdayCondition(boolean bday){
    isBirthday = bday;
  }




  public static void addDataToOrder(String append){
    if(orderDetails == null){
      orderDetails = append;
    } else{
      orderDetails += append;
    }
  }

  public static void addDataToOrderSorting(String append){
    if(orderSorting == null){
      orderSorting = append;
    } else{
      orderSorting += append;
    }
  }

  public static void clearOrder(){
    orderDetails = "";
  }

  public static void addData(){
    orderSortingList.add(orderSorting);
  }

  public static void manageData(){
    ordersFinal.add(orderDetails);
  }


}
