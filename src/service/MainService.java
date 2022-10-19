/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.Customer;
import model.Order;
import model.Product;
import model.Report;

/**
 * @author PMI7HC
 *
 */
public class MainService {
  /**
   * @param args
   */
  public static void main(String[] args) {
    try (XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream("data.xlsx"))) {
      
      // Sheet 0
      XSSFSheet sheet = wb.getSheetAt(0); // creating a Sheet object to retrieve object
      Iterator<Row> itr = sheet.iterator(); // iterating over the rows in sheet 0
      itr.next(); // jump to row 1 in the sheet and start to scan from there 
      List<Product> productList = new ArrayList<>();      
      while (itr.hasNext()) {
        Product product = new Product();
        Row row = itr.next();
        product.assignProductFromExcel(row);
        productList.add(product);
      }
      
      // Sheet 1
      XSSFSheet sheet1 = wb.getSheetAt(1); // creating a Sheet object to retrieve object
      Iterator<Row> itr1 = sheet1.iterator(); // iterating over the rows in sheet 1
      itr1.next(); // jump to row 1 in the sheet and start to scan from there 
      List<Customer> customerList = new ArrayList<>();
      while (itr1.hasNext()) {
        Customer customer = new Customer();
        Row row = itr1.next();
        customer.assignCustomerFromExcel(row);
        customerList.add(customer);
      }
      
      // Sheet 2
      XSSFSheet sheet2 = wb.getSheetAt(2); // creating a Sheet object to retrieve object
      Iterator<Row> itr2 = sheet2.iterator(); // iterating over the rows in sheet 2
      itr2.next(); // jump to row 1 in the sheet and start to scan from there
      List<Order> orderList = new ArrayList<>();
      while (itr2.hasNext()) {
        Order order = new Order();
        Row row = itr2.next();
        order.assignOrderFromExcel(row);
        orderList.add(order);
      }
      
      // Mapping the name of the only Customers in the Order Sheet
      // Extracting the CustomerIds[] from orderList i.e. cus01
      List<String> customerIds = orderList
          .stream()
          .map(Order::getCustomerId)
          .distinct()
          .collect(Collectors.toList());
      customerIds.forEach(System.out::println);
      System.out.println();
      // Mapping to a list of CustomerName by customerIds[] i.e. Nguyen Van A
      List<String> customerNames = customerIds.stream()
        .map(idCust -> { // idCust is one of customerIds[]
          Customer cust = customerList.stream() // choosing the cust which has matching idCust in customerList[]
              .filter(customer -> customer.getID().equalsIgnoreCase(idCust)) // customer is originally from customerList[]
              .findAny()
              .orElse(null);
          if (cust != null) {
            return cust.getName(); // after getting the cust, get the Name of that cust
          }
          return "";
        }).collect(Collectors.toList());  
      customerNames.forEach(System.out::println);  
      System.out.println();
      // Create a Map with customerId = CustomerName i.e. cus01=Nguyen Van A
      Map<String, String> customerNameMap = new HashMap<>();
      for (int i = 0; i < customerIds.size(); i++) {
        customerNameMap.put(customerIds.get(i), customerNames.get(i));
      }
      customerNameMap.entrySet().stream().forEach(System.out::println);
      System.out.println();
      
      // Refer to the Customer DOB
      // Mapping to a list of CustomerDOBs[] by customerIds[]
      List<LocalDate> customerDOBs = customerIds.stream()
          .map(idCust -> { // idCust is one of customerIds[]
            Customer cust = customerList // get all the cust in customerList[] object that match the condition
                .stream()
                .filter(customer -> customer.getID() //compared with all the elements in customerList[] 
                    .equalsIgnoreCase(idCust))
                .findAny()
                .orElse(null);
            if (cust != null) { // if there cust matches with customer in customerList[]
              return cust.getDateOfBirth(); // get the DOB of that cust 
            }
            return null;
          })
          .collect(Collectors.toList());
      customerDOBs.forEach(System.out::println);
      System.out.println();
      // Create a Map with customerId = CustomerDOB i.e. cus01=1992-03-11
      Map<String, LocalDate> customerDOBMap = new HashMap<>();
      for (int i = 0; i < customerIds.size(); i++) {
        customerDOBMap.put(customerIds.get(i), customerDOBs.get(i));
      }
      customerDOBMap.entrySet().stream().forEach(System.out::println);
      System.out.println();
      
      // FlatMap - Purchased Products for 1 Customers i.e. [Computer-Lenovo]
      // CustomerId=List<Order> i.e. cus02=[Order [ID=4, Status=Delivered, OrderDate=2022-05-19, DeliveryDate=2022-05-22, ProductId=p01, CustomerId=cus02]]
      Map<String, List<Order>> customerPurchasedProd = orderList // group order in orderList[] by customerId
          .stream()
          .collect(Collectors.groupingBy(Order::getCustomerId)); // order -> order.getCustomerId()
      customerPurchasedProd.entrySet().stream().forEach(System.out::println);
      System.out.println();
      // CustomerId=List<OrderId> i.e. cus01=[p02, p03, p05]
      Map<String, List<String>> purchasedProductIdCustomer = customerPurchasedProd // get the productId of each product in the purchased[]
        .entrySet()
        .stream()
        .collect(
            Collectors
            .toMap( // turn the old Map to the new Map
                Entry::getKey, entry -> entry.getValue() // keep the key[], turn the value[] into a stream 
                .stream()
                .map(Order::getProductId) // order -> order.getProductId()
                .collect(Collectors.toList())));     
      purchasedProductIdCustomer.entrySet().stream().forEach(System.out::println);
      System.out.println();
      // cus01=[Computer-Lenovo, Mobile phone-Iphone 6, Mobile phone-Iphone 8]
      Map<String, List<String>> purchasedProductNameCustomer = purchasedProductIdCustomer // get the productName of each product in the productId[]
          .entrySet()
          .stream()
          .collect(
              Collectors
              .toMap(
                  Entry::getKey, entry -> entry.getValue() // turn the value[] into a stream
                  .stream()
                  .map(productId -> productList.stream()
                      .filter(
                          product -> productId.equals(product.getID()))
                      .findAny()
                      .get()
                      .toString())
                  .collect(Collectors.toList())));
      purchasedProductNameCustomer.entrySet().stream().forEach(System.out::println);
      System.out.println();
      
      List<Report> reportList = new ArrayList<>();      
   // Reduce calculate the Total Price each customer bought i.e. cus02=15000.0
      Map<String, Double> totalPrice = customerPurchasedProd
          .entrySet()
          .stream()
          .collect(
              Collectors.toMap(Entry::getKey, entry->entry.getValue()
                  .stream()
                  .collect(
                      Collectors.summingDouble(order-> productList
                          .stream()
                          .filter(product->product.getID()
                              .equals(order.getProductId()))
                          .findAny()
                          .get()
                          .getPrice()))));
      totalPrice.entrySet().stream().forEach(System.out::println);
      System.out.println();
      
      // Reduce Calculate Total Delivery Day i.e. cus01=16
      Map<String, Long> deliveryDay = customerPurchasedProd
          .entrySet()
          .stream()
          .collect(
              Collectors
              .toMap(Entry::getKey, entry -> entry.getValue()
                  .stream()
                  .collect(
                      Collectors
                      .summingLong(
                          order -> ChronoUnit.DAYS.between(
                              order.getOrderDate(), order.getDeliveryDate())))));
      deliveryDay.entrySet().stream().forEach(System.out::println);
      System.out.println();
      
      purchasedProductNameCustomer.entrySet().stream().forEach(entry->{
        Report report = new Report();
        report.setCustomerId(entry.getKey());
        report.setPurchasedProducts(entry.getValue());
        reportList.add(report);
      });      
      reportList.stream().forEach(report -> {
        report.setTotalPrice(totalPrice.get(report.getCustomerId()));
        report.setDeliveryDays(deliveryDay.get(report.getCustomerId()));
        report.setCustomerName(customerNameMap.get(report.getCustomerId()));
        report.setDOB(customerDOBMap.get(report.getCustomerId()));
      });
      // sorted purchasedProductNameCustomer[] with descending order of TotalPrice
      List<Report> reportListSorted = reportList
          .stream()
          .sorted((a, b) -> -Double
              .compare(a.getTotalPrice(), b.getTotalPrice()))
          .collect(Collectors.toList());
      
      reportListSorted.stream().forEach(System.out::println);
      System.out.println();

      List<Entry<String, List<String>>> newList = new LinkedList<>(purchasedProductIdCustomer.entrySet());
      newList.stream().forEach(System.out::println);
      System.out.println(newList.size());
      
      List<List<String>> values = purchasedProductIdCustomer.values() // from a Map, get values only
          .stream().collect(Collectors.toList()); 
      System.out.println(values);

      exportReport(reportListSorted);
    }
    catch (IOException e) {
      e.printStackTrace();
    }

  }
  
  /**
   * export List<Report> to excel file
   * 
   * @param reportList list of Report object to be written to the excel file
   */
  private static void exportReport(List<Report> reportList) {
    try (OutputStream fileOut = new FileOutputStream("Report.xlsx"); 
        XSSFWorkbook workbook = new XSSFWorkbook()) {
      XSSFSheet outSheet = workbook.createSheet("Report");

      List<String> header = new ArrayList<>();
      header.add("Customer");
      header.add("Date of birth");
      header.add("Purchased products");
      header.add("Total Price");
      header.add("Total days of delivery");
      
      Row row = outSheet.createRow(0);
      AtomicReference<Integer> index = new AtomicReference<>(0);
      header.stream().forEach(item -> {
        Cell cell = row.createCell(index.get());
        cell.setCellValue(item);   
        index.set(index.get() + 1);
      });
      
      for (int i = 0; i < reportList.size(); i++) {
        Row rowData = outSheet.createRow(i + 1);
        Cell cell0 = rowData.createCell(0);
        cell0.setCellValue(reportList.get(i).getCustomerName());
        Cell cell1 = rowData.createCell(1);
        cell1.setCellValue(reportList.get(i).getDOB().toString());
        Cell cell2 = rowData.createCell(2);
        cell2.setCellValue(reportList.get(i).getPurchasedProducts().toString());
        Cell cell3 = rowData.createCell(3);
        cell3.setCellValue(reportList.get(i).getTotalPrice());
        Cell cell4 = rowData.createCell(4);
        cell4.setCellValue(reportList.get(i).getDeliveryDays());
      }
      
      workbook.write(fileOut);
      System.out.println("Your excel file has been generated!");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
