/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package model;

import org.apache.poi.ss.usermodel.Row;

/**
 * @author PMI7HC
 */
public class Product {

  private String ID;
  private String Name;
  private String Category;
  private double Price;
  
  /**
   * @param row
   */
  public void assignProductFromExcel(Row row) {
    this.ID = row.getCell(0).getStringCellValue();
    this.Name = row.getCell(1).getStringCellValue();
    this.Category = row.getCell(2).getStringCellValue();
    this.Price = row.getCell(3).getNumericCellValue();
  }

  /**
   * 
   */
  public Product() {
  }

  /**
   * @param iD
   * @param name
   * @param category
   * @param price
   */
  public Product(String iD, String name, String category, double price) {
    ID = iD;
    Name = name;
    Category = category;
    Price = price;
  }

  /**
   * @return the iD
   */
  public String getID() {
    return ID;
  }

  /**
   * @param iD the iD to set
   */
  public void setID(String iD) {
    ID = iD;
  }

  /**
   * @return the name
   */
  public String getName() {
    return Name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    Name = name;
  }

  /**
   * @return the category
   */
  public String getCategory() {
    return Category;
  }

  /**
   * @param category the category to set
   */
  public void setCategory(String category) {
    Category = category;
  }

  /**
   * @return the price
   */
  public double getPrice() {
    return Price;
  }

  /**
   * @param price the price to set
   */
  public void setPrice(double price) {
    Price = price;
  }

  /** 
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return Category + "-" + Name;
  }

}
