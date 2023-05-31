 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class tables {
    public static void main(String[] args) {
        try {
            String userTable = "create table user (id int AUTO_INCREMENT primary key, name varchar(200), email varchar(200), mobileNumber varchar(10), address varchar(200), password varchar(200), securityQuestion varchar(200), answer varchar(200), status varchar(200), UNIQUE(email))";
            String admin = "insert into user (name, email, mobileNumber, address, password, securityQuestion, answer, status) values ('Truong Thanh', 'admindeptrai@gmail.com', '0705929292', 'England', 'What country do you intend to live?', 'England', 'true')";
            String categoryTable = "create table category (id int AUTO_INCREMENT primary key, name varchar(200))";
            String productTable = "create table product (id int AUTO_INCREMENT primary key,name varchar(200), category varchar(200), price varchar(200))";
            String billTable = "create table bill (id int AUTO_INCREMENT primary key, name varchar(200), email varchar(200), mobileNumber varchar(10), date varchar(50), total varchar(200), createdBy varchar(200))";
//            DbOperation.setDataOrDelete(userTable, "User table Created Successfully");
//            DbOperation.setDataOrDelete(admin, "Add addmin sucessfully!");
//            DbOperation.setDataOrDelete(categoryTable, "Category table Created Successfully");
//            DbOperation.setDataOrDelete(productTable, "Product table Created Successfully");
            DbOperation.setDataOrDelete(billTable, "Bill table Created Successfully");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
