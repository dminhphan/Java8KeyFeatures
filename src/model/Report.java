/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package model;

import java.time.LocalDate;
import java.util.List;

/**
 * Object of to write the report excel file
 * 
 * @author PMI7HC
 */
public class Report {

  private String CustomerId;
  private String CustomerName;
  private LocalDate DOB;
  private List<String> purchasedProducts;
  private double totalPrice;
  private long deliveryDays;

  /**
   * 
   */
  public Report() {}

  /**
   * @param customerId
   * @param customerName
   * @param dOB
   * @param purchasedProducts
   * @param totalPrice
   * @param deliveryDays
   */
  public Report(String customerId, String customerName, LocalDate dOB, List<String> purchasedProducts, double totalPrice,
      long deliveryDays) {
    CustomerId = customerId;
    CustomerName = customerName;
    DOB = dOB;
    this.purchasedProducts = purchasedProducts;
    this.totalPrice = totalPrice;
    this.deliveryDays = deliveryDays;
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
   * @return the customerName
   */
  public String getCustomerName() {
    return CustomerName;
  }

  
  /**
   * @param customerName the customerName to set
   */
  public void setCustomerName(String customerName) {
    CustomerName = customerName;
  }

  /**
   * @return the dOB
   */
  public LocalDate getDOB() {
    return DOB;
  }

  /**
   * @param dOB the dOB to set
   */
  public void setDOB(LocalDate dOB) {
    DOB = dOB;
  }

  /**
   * @return the purchasedProducts
   */
  public List<String> getPurchasedProducts() {
    return purchasedProducts;
  }

  /**
   * @param purchasedProducts the purchasedProducts to set
   */
  public void setPurchasedProducts(List<String> purchasedProducts) {
    this.purchasedProducts = purchasedProducts;
  }

  /**
   * @return the totalPrice
   */
  public double getTotalPrice() {
    return totalPrice;
  }

  /**
   * @param totalPrice the totalPrice to set
   */
  public void setTotalPrice(double totalPrice) {
    this.totalPrice = totalPrice;
  }

  /**
   * @return the deliveryDays
   */
  public long getDeliveryDays() {
    return deliveryDays;
  }

  /**
   * @param deliveryDays the deliveryDays to set
   */
  public void setDeliveryDays(long deliveryDays) {
    this.deliveryDays = deliveryDays;
  }

  /** 
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "Report [CustomerId=" + CustomerId + ", CustomerName=" + CustomerName + ", DOB=" + DOB +
        ", purchasedProducts=" + purchasedProducts + ", totalPrice=" + totalPrice + ", deliveryDays=" + deliveryDays +
        "]";
  }
}
