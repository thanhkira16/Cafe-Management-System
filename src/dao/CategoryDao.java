/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Category;
       
/**
 *
 * @author Admin
 */
public class CategoryDao {
    public static void save(Category category){
        String query = "insert into category(name) values ('"+category.getName()+"' )";
        DbOperation.setDataOrDelete(query, "Added Category Sucessfully");
        
    }
    
    public static ArrayList getAllRecords(){
        ArrayList<Category> arr = new ArrayList<>();
        try {
            
            ResultSet rs = DbOperation.getData("select *from category");
            while(rs.next()){
                Category ca = new Category();
                ca.setId(rs.getString("id"));
                ca.setName(rs.getString("name"));
                arr.add(ca);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return arr;
    }
    
    public static void delete(String id){
        String query = "delete from category where id='"+id+"'";
        DbOperation.setDataOrDelete(query, "Deleted Sucessfully");
    }
}
