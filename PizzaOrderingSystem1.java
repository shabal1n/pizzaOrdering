import java.util.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.*;

public class PizzaOrderingSystem1 {

  public static void main(String[] args) {

    Scanner scan = new Scanner(System.in);
    OrderDetails orders = new OrderDetails();
    boolean bdayMessage = true;
    boolean runtime = true;

    //repeating
    while(runtime) {

      orders.tempTotal = 0;

      //welcome and birthday
      if(bdayMessage){

        printWelcome();
        System.out.println("Today is: " + printDate());
        System.out.print("> Is it your BIRTHDAY? (10% discount available on presenting ID) (Y/N): ");
        String input = scan.nextLine();
        if(input.toLowerCase().equals("y")) {
          orders.birthdayCondition(true);
        }
        bdayMessage = false;
      }
      System.out.println("-----------------------------");

      orders.id = (int)(Math.random() * 8999 + 1000);
      System.out.println(orders.id);
      orders.addDataToOrderSorting(Integer.toString(orders.id) + " ");
      orders.addDataToOrderSorting(printDate() + " ");
      orders.addDataToOrderSorting(printTime() + " ");

      //pizza size
      orders.pizzaSize = pizzaSize();
      if(orders.pizzaSize == 20){

        orders.tempTotal += 1000;
        orders.addDataToOrder("20cm pizza, Cheese");

      } else if(orders.pizzaSize == 30) {

        orders.tempTotal += 1500;
        orders.addDataToOrder("30cm pizza, Cheese");

      } else if(orders.pizzaSize == 40) {

        orders.tempTotal += 2000;
        orders.addDataToOrder("40cm pizza, Cheese");

      }
      System.out.println("-----------------------------");

      //adding toppings
      toppings();
      System.out.print(">Do you want meat? (Y/N): ");
      String meat = scan.next();
      if(meat.toLowerCase().equals("y")){
        orders.tempTotal += 200;
        orders.addDataToOrder(" + Meat");
      }
      System.out.print("\n>Do you want sausage? (Y/N): ");
      String sausage = scan.next();
      if(sausage.toLowerCase().equals("y")){
        orders.tempTotal += 200;
        orders.addDataToOrder(" + Sausage");
      }
      System.out.print("\n>Do you want vegetables? (Y/N): ");
      String vegetables = scan.next();
      if(vegetables.toLowerCase().equals("y")){
        orders.tempTotal += 200;
        orders.addDataToOrder(" + Vegetables");
      }
      System.out.print("\n>Do you want mushrooms? (Y/N): ");
      String mushrooms = scan.next();
      if(mushrooms.toLowerCase().equals("y")){
        orders.tempTotal += 200;
        orders.addDataToOrder(" + Mushrooms");
      }
      orders.addDataToOrder(":");
      orders.addDataToOrder(Integer.toString(orders.tempTotal));
      orders.manageData();
      orders.clearOrder();
      orders.finalTotal += orders.tempTotal;
      System.out.println("-----------------------------");
      orders.addDataToOrderSorting(Integer.toString(orders.tempTotal) + " ");
      if(orders.isBirthday){
        orders.addDataToOrderSorting("Yes ");
      } else {
        orders.addDataToOrderSorting("No ");
      }
      orders.addDataToOrderSorting(Integer.toString(orders.pizzaSize) + " ");
      orders.addDataToOrderSorting("Yes ");
      if(meat.toLowerCase().equals("y")){
        orders.addDataToOrderSorting("Yes ");
      } else {
        orders.addDataToOrderSorting("No ");
      }
      if(sausage.toLowerCase().equals("y")){
        orders.addDataToOrderSorting("Yes ");
      } else {
        orders.addDataToOrderSorting("No ");
      }

      if(vegetables.toLowerCase().equals("y")){
        orders.addDataToOrderSorting("Yes ");
      } else {
        orders.addDataToOrderSorting("No ");
      }
      orders.addData();
      orders.orderSorting = "";

      //printing ordered pizza
      printOrder(orders.ordersFinal);
      System.out.println("Total: " + orders.finalTotal + " T");
      printMenu();
      int runCondition  = scan.nextInt();
      if(runCondition == 1){

        runtime = false;
        if(orders.isBirthday == true){

          discountComplete(orders.finalTotal);

        }

        completeOrder(orders.id);
      } else if(runCondition == 3){

        runtime = false;
        System.out.println("Good bye!");

      } else if(runCondition == 4){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm", Locale.ENGLISH);
        int operation = scan.nextInt();
        int idSearch = 0;
        String dateSearch = "";
        LocalDate searchDate = LocalDate.now();
        if(operation == 2){
          idSearch = scan.nextInt();
        } else if(operation == 3){
          dateSearch = scan.next();
          searchDate = LocalDate.parse(dateSearch, formatter);
        }
        int numberOfOrders = orders.orderSortingList.size();

        String[] ordersNew = new String[numberOfOrders];

        for(int i = 0; i < numberOfOrders; i++){
          ordersNew[i] = orders.orderSortingList.get(i);
        }
        int[] id = new int[numberOfOrders];
        LocalDate[] date = new LocalDate[numberOfOrders];
        LocalTime[] dateTime = new LocalTime[numberOfOrders];
        int[] cost = new int[numberOfOrders];
        boolean[] isBirthday = new boolean[numberOfOrders];
        int[] size = new int[numberOfOrders];
        boolean[] topping1 = new boolean[numberOfOrders];
        boolean[] topping2 = new boolean[numberOfOrders];
        boolean[] topping3 = new boolean[numberOfOrders];
        boolean[] topping4 = new boolean[numberOfOrders];


        for(int i = 0; i < ordersNew.length; i++){
          int j = 0;
          String tempId = "";
          String dateTemp = "";
          String dateTimeTemp = "";
          String costTemp = "";
          String isBirthdayTemp = "";
          String sizeTemp = "";
          String topping1Temp = "";
          String topping2Temp = "";
          String topping3Temp = "";
          String topping4Temp = "";

          while(ordersNew[i].charAt(j) != ' '){
            tempId += ordersNew[i].charAt(j);
            j++;
          }
          //System.out.print(tempId + " ");
          id[i] = Integer.parseInt(tempId);
          j++;

          while(ordersNew[i].charAt(j) != ' '){
            dateTemp += ordersNew[i].charAt(j);
            j++;
          }
          LocalDate dateNew = LocalDate.parse(dateTemp, formatter);
          date[i] = dateNew.parse(dateTemp, formatter);
          //format(DateTimeFormatter.ofPattern("dd.MM.yyyy")
          j++;

          while(ordersNew[i].charAt(j) != ' '){
            dateTimeTemp += ordersNew[i].charAt(j);
            j++;
          }
          LocalTime timeNew = LocalTime.parse(dateTimeTemp, timeFormatter);
          dateTime[i] = timeNew.parse(dateTimeTemp, timeFormatter);
          j++;

          while(ordersNew[i].charAt(j) != ' '){
            costTemp += ordersNew[i].charAt(j);
            j++;
          }
          //System.out.print(costTemp + " ");
          cost[i] = Integer.parseInt(costTemp);
          j++;

          while(ordersNew[i].charAt(j) != ' '){
            isBirthdayTemp += ordersNew[i].charAt(j);
            j++;
          }
          //System.out.print(isBirthdayTemp + " ");
          if(isBirthdayTemp.equals("Yes")){
            isBirthday[i] = true;
          }
          j++;


          while(ordersNew[i].charAt(j) != ' '){
            sizeTemp += ordersNew[i].charAt(j);
            j++;
          }
          //System.out.print(sizeTemp + " ");
          size[i] = Integer.parseInt(sizeTemp);
          j++;

          while(ordersNew[i].charAt(j) != ' '){
            topping1Temp += ordersNew[i].charAt(j);
            j++;
          }
          //System.out.print(topping1Temp + " ");
          if(topping1Temp.equals("Yes")){
            topping1[i] = true;
          }
          j++;

          while(ordersNew[i].charAt(j) != ' '){
            topping2Temp += ordersNew[i].charAt(j);
            j++;
          }
          //System.out.print(topping2Temp + " ");
          if(topping2Temp.equals("Yes")){
            topping2[i] = true;
          }
          j++;

          while(ordersNew[i].charAt(j) != ' '){
            topping3Temp += ordersNew[i].charAt(j);
            j++;
          }
          //System.out.print(topping3Temp + " ");
          if(topping3Temp.equals("Yes")){
            topping3[i] = true;
          }

          j++;
          while(j < ordersNew[i].length()){
            topping4Temp += ordersNew[i].charAt(j);
            j++;
          }
          //System.out.print(topping4Temp + " ");
          if(topping4Temp.equals("Yes")){
            topping4[i] = true;
          }
          //System.out.println();
        }

          if(operation == 1){
            System.out.println("Total Price:\n" + getTotalPrice(cost));
          } else if(operation == 2){
            System.out.println("Search by ID: " + idSearch);
            searchByID(id, ordersNew, idSearch);
          } else if(operation == 3){
            System.out.println("Search by date: " + dateSearch);
            searchByDate(date, searchDate, ordersNew);
          } else if(operation == 4){
            System.out.println("Sorted by ID: ");
            sortByID(id, ordersNew);
          } else if(operation == 5){
            System.out.println("Sorted by date and time: ");
            sortByDateAndTime(date, dateTime, ordersNew);
          } else if(operation == 6){
            System.out.println("The most popular pizza size: ");
            mostPopularSize(size);
          } else if(operation == 7){
            System.out.println("The most popular pizza type: ");
            mostPopularPizzaType(topping1, topping2, topping3, topping4, numberOfOrders);
          }
          runtime = false;

      }

    }

  }


  public static void printWelcome() {
    System.out.println("====================================");
		System.out.println("Welcome to \"Eat&Chat\" Pizza Order!");
		System.out.println("====================================");
  }


  public static String printDate() {
    DateTimeFormatter d = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    LocalDateTime date = LocalDateTime.now();
    String s = d.format(date);
    return s;
  }

  public static String printTime(){
    DateTimeFormatter mask = DateTimeFormatter.ofPattern("HH:mm");
    LocalDateTime now = LocalDateTime.now();
    String time = mask.format(now);
    return time;
  }

  public static int pizzaSize() {
    Scanner scan = new Scanner(System.in);
    System.out.println("Pizza Size(cm)\t Cost");
    System.out.println("\t20 \t1000 T");
    System.out.println("\t30 \t1500 T");
    System.out.println("\t40 \t2000 T");
    System.out.println("What size pizza would you like?");
    System.out.print("> 20, 30, or 40 (enter the number only): ");

    int size = scan.nextInt();

    return size;

  }

  public static void toppings() {
    System.out.println("All pizzas come with cheese.");
    System.out.println("Additional toppings are 200T each, choose from: \n- Meat, Sausage, Vegetables, Mushroom");
  }


  public static void printOrder(ArrayList<String> order) {

    System.out.println("Your order: ");
    for(int i = 0; i < order.size(); i++){

      if(order.get(i) != null){

        System.out.println((i + 1) + ") " + order.get(i));
      }
    }
  }

  public static void completeOrder(int id){
    System.out.println("-----------------------------");
    System.out.println("Your order will be ready to pick up in 30 minutes.");
    System.out.println("Date: " + printDate() + "\tTime: " + printTime());
    System.out.println("Order ID: " + id);
  }

  public static void printMenu(){
		System.out.println("------------MENU-------------");
		System.out.println("1 - Complete");
		System.out.println("2 - Add another order");
		System.out.println("3 - Exit");
    System.out.println("4 - Additional options");
		System.out.print("> Choose one of the above (Enter a number): ");
	}

  public static void discountComplete(int total){
    System.out.println("-----------------------------");
    System.out.println("TOTAL with DISCOUNT (on presenting ID only!):");
    System.out.println((total - (total / 10)) + " T");
  }



  public static int getTotalPrice(int[] cost){
    int result = 0;
    for(int i = 0; i < cost.length; i++){
      result += cost[i];
    }
    return result;
  }

  public static void searchByID(int[] id, String[] orders, int idSearch){
    boolean condition = false;
    for(int i = 0; i < id.length; i++){
      if(id[i] == idSearch){
        condition = true;
        System.out.println(orders[i]);
      }
    }
    if(!condition){
      System.out.println("No result");
    }
  }

  public static void searchByDate(LocalDate[] date, LocalDate searchDate, String[] orders){
    boolean condition = false;
    for(int i = 0; i < date.length; i++){
      if(date[i].isEqual(searchDate)){
        condition = true;
        System.out.println(orders[i]);
      }
    }
    if(!condition){
      System.out.println("No result");
    }
  }

  public static void sortByID(int[] id, String[] orders){
    String[] result = orders;
    int i, j, temp;
    for(i = 0; i < id.length - 1; i++){
      for(j = 0; j < id.length - i - 1; j++){
        if(id[j] > id[j + 1]){
           temp = id[j];
           id[j] = id[j + 1];
           id[j + 1] = temp;
           String tempOrder = result[j];
           result[j] = result[j + 1];
           result[j + 1] = tempOrder;
        }
      }
    }
    for(int f = 0; f < result.length; f++){
      System.out.println(result[f]);
    }
  }

  public static void sortByDateAndTime(LocalDate[] date, LocalTime[] time, String[] orders){
    String[] result = orders;
    int i, j;
    LocalDate temp;
    for(int k = 0; k < 2; k++){
      for(i = 0; i < date.length - 1; i++){
        for(j = 0; j < date.length - i - 1; j++){
          if(date[j].isAfter(date[j + 1])){
             temp = date[j];
             date[j] = date[j + 1];
             date[j + 1] = temp;
             String tempData = result[j];
             result[j] = result[j + 1];
             result[j + 1] = tempData;
          } else if(date[j].isEqual(date[j + 1])){
            if(time[j].isAfter(time[j + 1])){
              LocalTime tempTime = time[j];
              time[j] = time[j + 1];
              time[j + 1] = tempTime;
              temp = date[j];
              date[j] = date[j + 1];
              date[j + 1] = temp;
              String tempData = result[j];
              result[j] = result[j + 1];
              result[j + 1] = tempData;
            }
          }
        }
      }
    }
    for(int y = 0; y < result.length; y++){
      System.out.println(result[y]);
    }
  }

  public static void mostPopularSize(int[] size){
    int[] counter = new int[3];
    for(int i = 0; i < size.length; i++){
      if(size[i] == 20){
        counter[0]++;
      } else if(size[i] == 30){
        counter[1]++;
      } else if(size[i] == 40){
        counter[2]++;
      }
    }

    int max = counter[0];
    for(int j = 1; j < counter.length; j++){
      if(counter[j] > max){
        max = counter[j];
      }
    }

    for(int i = 0; i < counter.length; i++){
      if(counter[i] == max && i == 0){
        System.out.println(20);
      } else if(counter[i] == max && i == 1){
        System.out.println(30);
      } else if(counter[i] == max && i == 2){
        System.out.println(40);
      }
    }
  }

  public static void mostPopularPizzaType(boolean[] topping1, boolean[] topping2, boolean[] topping3, boolean[] topping4, int numberOfOrders){
    int[] pizzaType = new int[numberOfOrders];
    int[] pizzaTypeCounter = new int[numberOfOrders];
    for(int i = 0; i < numberOfOrders; i++){
      if(topping1[i]){
        pizzaType[i] += 1000;
      }

      if(topping2[i]){
        pizzaType[i] += 200;
      }

      if(topping3[i]){
        pizzaType[i] += 30;
      }

      if(topping4[i]){
        pizzaType[i] += 4;
      }
    }

    int[] commonElements = new int[pizzaType.length];
    for (int i = 0; i < (pizzaType.length - 1); i++){
      for (int j = i + 1; j < pizzaType.length; j++){
          if(pizzaType[i] == pizzaType[j]){
            commonElements[i]++;
          }
        }
      }

      int max = commonElements[0];
      for(int i = 0; i < commonElements.length; i++){
        if(max < commonElements[i]){
          max = commonElements[i];
        }
      }

      int[] finalPizzaType = new int[pizzaType.length];
      for(int i = 0; i < commonElements.length; i++){
        if(commonElements[i] == max){
          finalPizzaType[i] = pizzaType[i];
        }
      }

      for(int i = 0; i < finalPizzaType.length; i++){
        if(finalPizzaType[i] / 1000 == 1){
          System.out.print("Cheese+");
        }
        if((finalPizzaType[i] % 1000) / 100 == 2){
          System.out.print("Meet+");
        }
        if(((finalPizzaType[i] % 1000) % 100) / 10 == 3){
          System.out.print("Sausage+");
        }
        if(((finalPizzaType[i] % 1000) % 100) % 10 == 4){
          System.out.print("Vegetables");
        }
        if(finalPizzaType[i] != 0){
          System.out.println();
        }
      }
}
}
