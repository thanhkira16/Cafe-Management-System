/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Bill;
/**
 *
 * @author Admin
 */
public class BillDao {
       public static String getId(){
           int id = 1;
           try {
               ResultSet rs = DbOperation.getData("select max(id) from bill");
               if(rs.next()){
                   id = rs.getInt(1);
                   id ++;
               }
           } catch (Exception e) {
               JOptionPane.showMessageDialog(null, e);
           }
           return String.valueOf(id);
       }
       public static void save(Bill bill){
           String query = "insert into bill values('"+bill.getId()+"', '"+bill.getName()+"', '"+bill.getEmail()+"','"+bill.getMobileNumber()+"', '"+bill.getDate()+"','"+bill.getTotal()+"','"+bill.getCreateBy()+"')";
           DbOperation.setDataOrDelete(query, "Bill Details added successfully");
           
       }
       
       public static ArrayList<Bill> getAllRecordsByAsc(String date){
           return getAllRecordsByDateAndOrder(date, "ASC");
       }
       
       public static ArrayList<Bill> getAllRecordsByDesc(String date){
           return getAllRecordsByDateAndOrder(date, "DESC");
       }

       private static ArrayList<Bill> getAllRecordsByDateAndOrder(String date, String order){
           ArrayList<Bill> list = new ArrayList<>();
           // Keep ORDER BY restricted to the two directions exposed by this DAO.
           String sortDirection = "ASC".equalsIgnoreCase(order) ? "ASC" : "DESC";
           String query = "SELECT id, name, email, mobileNumber, date, total, createdBy "
                   + "FROM bill WHERE date LIKE ? ORDER BY id " + sortDirection;
           try (Connection connection = ConnectionProvider.getCon();
                PreparedStatement statement = connection.prepareStatement(query)) {
               statement.setString(1, "%" + (date == null ? "" : date.trim()) + "%");
               try (ResultSet rs = statement.executeQuery()) {
                   while (rs.next()) {
                       Bill bill = new Bill();
                       bill.setId(rs.getInt("id"));
                       bill.setName(rs.getString("name"));
                       bill.setEmail(rs.getString("email"));
                       bill.setMobileNumber(rs.getString("mobileNumber"));
                       bill.setDate(rs.getString("date"));
                       bill.setTotal(rs.getString("total"));
                       bill.setCreateBy(rs.getString("createdBy"));
                       list.add(bill);
                   }
               }
           } catch (Exception e) {
               JOptionPane.showMessageDialog(null, e);
           }
           return list;
       }
}
