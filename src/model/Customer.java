/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package model;

import java.time.LocalDate;

import org.apache.poi.ss.usermodel.Row;

/**
 * @author PMI7HC
 */
public class Customer {

  private String ID;
  private String Name;
  private LocalDate DateOfBirth;
  private String Address;
  
  /**
   * @param row
   */
  public void assignCustomerFromExcel (Row row) {
    this.ID = row.getCell(0).getStringCellValue();
    this.Name = row.getCell(1).getStringCellValue();
    this.DateOfBirth = convertToLocalDateViaSqlDate(row.getCell(2).getDateCellValue());
    this.Address = row.getCell(3).getStringCellValue();
  }
  
  private LocalDate convertToLocalDateViaSqlDate(java.util.Date date) {
    return new java.sql.Date(date.getTime()).toLocalDate();
  }

  /**
   * 
   */
  public Customer() {}

  /**
   * @param iD
   * @param name
   * @param dateOfBirth
   * @param address
   */
  public Customer(String iD, String name, LocalDate dateOfBirth, String address) {
    ID = iD;
    Name = name;
    DateOfBirth = dateOfBirth;
    Address = address;
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
   * @return the dateOfBirth
   */
  public LocalDate getDateOfBirth() {
    return DateOfBirth;
  }

  /**
   * @param dateOfBirth the dateOfBirth to set
   */
  public void setDateOfBirth(LocalDate dateOfBirth) {
    DateOfBirth = dateOfBirth;
  }

  /**
   * @return the address
   */
  public String getAddress() {
    return Address;
  }

  /**
   * @param address the address to set
   */
  public void setAddress(String address) {
    Address = address;
  }

  /** 
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "Customer [ID=" + ID + ", Name=" + Name + ", DateOfBirth=" + DateOfBirth + ", Address=" + Address + "]";
  }
}
