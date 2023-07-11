/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.User;

/**
 *
 * @author Admin
 */
public class SocketHandle implements Runnable {

    private BufferedWriter os;
    private BufferedReader is;
    private Socket socketOfClient;
    private int ID_Server;

    public SocketHandle() {
    }

//    public List<User> getListUser(String[] message){
//        List<User> friend = new ArrayList<>();
//        for(int i=1; i<message.length; i=i+4){
//            friend.add(new User(Integer.parseInt(message[i]),
//                    message[i+1],
//                    message[i+2].equals("1"),
//                    message[i+3].equals("1")));
//        }
//        return friend;
//    }
    
    public User getUserFromString(int start, String[] message){
        return new User(
                message[start],
                message[start+1],
                message[start+2],
                message[start+3],
                message[start+4],
                message[start+5],
                message[start+6],
                message[start+7]
                );
    }
    @Override
    public void run() {
        try {
            // Gửi yêu cầu kết nối tới Server đang lắng nghe
            socketOfClient = new Socket("127.0.0.1", 7777);
            System.out.println("Kết nối thành công!");
            // Tạo luồng đầu ra tại client (Gửi dữ liệu tới server)
            os = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));
            // Luồng đầu vào tại Client (Nhận dữ liệu từ server).
            is = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));
            String message;
            while (true) {
                message = is.readLine();
                if (message == null) {
                    break;
                }

                String[] messageSplit = message.split(",");
                if (messageSplit[0].equals("server-send-id")) {
                    ID_Server = Integer.parseInt(messageSplit[1]);
                }

                //Đăng nhập thành công
                if (messageSplit[0].equals("login-success")) {
                    System.out.println("login-success");
                    User user = getUserFromString(1, messageSplit);
                    Client.user = user;
                    Client.openView(Client.View.HOME, user.getStatus());
                }

                //Thông tin tài khoản sai
                if (messageSplit[0].equals("wrong-user")) {
                    System.out.println("wrong-user");
                    Client.openView(Client.View.LOGIN);
                    Client.loginFrm.showError("Incorrect email or password");
                }
                //Tài khoản đã đăng nhập ở nơi khác
                if (messageSplit[0].equals("dupplicate-login")) {
                    System.out.println("dupplicate-login");
                    Client.openView(Client.View.LOGIN);
                    Client.loginFrm.showError("Tài khoản đã đăng nhập ở nơi khác");
                }
                //Xử lý register trùng tên
                if (messageSplit[0].equals("duplicate-email")) {
                    System.out.println("duplicate-email");
                    Client.closeAllViews();
                    Client.openView(Client.View.SIGNUP);
                    JOptionPane.showMessageDialog(Client.registerFrm, "Email is already registed");
                }
                
                //Xử lý nhận thông tin, chat từ toàn server
                if(messageSplit[0].equals("chat-server")){
                    if(Client.homePageFrm!=null){
                        Client.homePageFrm.addMessage(messageSplit[1]);
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    public void write(String message) throws IOException {
        os.write(message);
        os.newLine();
        os.flush();
    }

    public Socket getSocketOfClient() {
        return socketOfClient;
    }
}
