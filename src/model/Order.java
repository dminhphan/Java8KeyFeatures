/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package model;

import java.time.LocalDate;

import org.apache.poi.ss.usermodel.Row;

/**
 * @author PMI7HC
 */
public class Order {

  private int ID;
  private String Status;
  private LocalDate OrderDate;
  private LocalDate DeliveryDate;
  private String ProductId;
  private String CustomerId;

  /**
   * @param row
   */
  public void assignOrderFromExcel (Row row) {
    this.ID = (int) row.getCell(0).getNumericCellValue();
    this.Status = row.getCell(1).getStringCellValue();
    this.OrderDate = convertToLocalDateViaSqlDate(row.getCell(2).getDateCellValue());
    this.DeliveryDate = convertToLocalDateViaSqlDate(row.getCell(3).getDateCellValue());
    this.ProductId = row.getCell(4).getStringCellValue();
    this.CustomerId = row.getCell(5).getStringCellValue();
  }

  private LocalDate convertToLocalDateViaSqlDate(java.util.Date date) {
    return new java.sql.Date(date.getTime()).toLocalDate();
  }

  /**
   * 
   */
  public Order() {}


  /**
   * @param iD
   * @param status
   * @param orderDate
   * @param deliveryDate
   * @param productId
   * @param customerId
   */
  public Order(int iD, String status, LocalDate orderDate, LocalDate deliveryDate, String productId,
      String customerId) {
    ID = iD;
    Status = status;
    OrderDate = orderDate;
    DeliveryDate = deliveryDate;
    ProductId = productId;
    CustomerId = customerId;
  }

  /**
   * @return the iD
   */
  public int getID() {
    return ID;
  }

  /**
   * @param iD the iD to set
   */
  public void setID(int iD) {
    ID = iD;
  }

  /**
   * @return the status
   */
  public String getStatus() {
    return Status;
  }

  /**
   * @param status the status to set
   */
  public void setStatus(String status) {
    Status = status;
  }

  /**
   * @return the orderDate
   */
  public LocalDate getOrderDate() {
    return OrderDate;
  }

  /**
   * @param orderDate the orderDate to set
   */
  public void setOrderDate(LocalDate orderDate) {
    OrderDate = orderDate;
  }

  /**
   * @return the deliveryDate
   */
  public LocalDate getDeliveryDate() {
    return DeliveryDate;
  }

  /**
   * @param deliveryDate the deliveryDate to set
   */
  public void setDeliveryDate(LocalDate deliveryDate) {
    DeliveryDate = deliveryDate;
  }


  /**
   * @return the productId
   */
  public String getProductId() {
    return ProductId;
  }


  /**
   * @param productId the productId to set
   */
  public void setProductId(String productId) {
    ProductId = productId;
  }


  /**
   * @return the customerId
   */
  public String getCustomerId() {
    return CustomerId;
  }


  /**
   * @param customerId the customerId to set
   */
  public void setCustomerId(String customerId) {
    CustomerId = customerId;
  }

  /** 
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "Order [ID=" + ID + ", Status=" + Status + ", OrderDate=" + OrderDate + ", DeliveryDate=" + DeliveryDate +
        ", ProductId=" + ProductId + ", CustomerId=" + CustomerId + "]";
  }

}
