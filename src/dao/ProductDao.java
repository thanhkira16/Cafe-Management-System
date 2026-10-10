/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import model.Product;
import java.util.ArrayList;
import java.sql.*;
import javax.swing.JOptionPane;
       
/**
 *
 * @author Admin
 */
public class ProductDao {
    public static void save(Product product){
        String query = "insert into product(name, category, price) values ('"+product.getName()+"','"+product.getCategory()+"','"+product.getPrice()+"')";
        DbOperation.setDataOrDelete(query, "Product added  successfully!");
    }
    
    public static ArrayList getAllRecords(){
        ArrayList<Product> arrayList = new ArrayList<>();
            try {
                ResultSet rs = DbOperation.getData("select *from product");
                while(rs.next()){
                    Product product = new Product();
                    product.setId(rs.getInt("id"));
                    product.setName(rs.getString("name"));
                    product.setCategory(
                            
         rs.getString("category"));
                    product.setPrice(rs.getString("price"));
                    arrayList.add(product);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            
        return arrayList;
    }
    public static void update(Product product){
        String query = "update product set name='"+product.getName()+"', category='"+product.getCategory()+"', price = '"+product.getPrice()+"' where id='"+product.getId()+"'";
        DbOperation.setDataOrDelete(query, "Product updated successfully");
    }
    public static void delete(String id){
        String query = "delete from product where id='"+id+"'";
        DbOperation.setDataOrDelete(query, "Product deleted successfully");
    }
    
    public static ArrayList getAllRecordsByCategory(String category){
        ArrayList<Product> list = new ArrayList<>();
        try {
            ResultSet rs = DbOperation.getData("select *from product where category='"+category+"'");
            while(rs.next()){
                Product product = new Product();
                product.setName(rs.getString("name"));
                list.add(product);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return list;
    }
    
    public static ArrayList filterProductByName(String name, String category){
        ArrayList<Product> list = new ArrayList<>();
        try {
            ResultSet rs = DbOperation.getData("selcect *from product where name like'%"+name+"%' and category='"+category+"'");
            while(rs.next()){
                Product product  = new Product();
                product.setName(rs.getString("name"));
                list.add(product);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public static Product getProductByName(String name){
        Product product = new Product();
        try {
            ResultSet rs = DbOperation.getData("select *from product where name ='"+name+"'");
            while(rs.next()){
                product.setName(rs.getString(2));
                product.setCategory(rs.getString(3));
                product.setPrice(rs.getString(4));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, name);
        }
        return product;
    }
}
