/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.io.*;
import java.net.*;
import java.util.*;

public class PasswordAuthenticationExample {
    private static Map<String, String> users = new HashMap<>();

    public static void main(String[] args) {
        // Perform your network operations here
        // For the sake of example, let's simulate user interactions
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Sign up");
            System.out.println("2. Login");
            System.out.println("3. Forgot password");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    signUp(scanner);
                    break;
                case 2:
                    login(scanner);
                    break;
                case 3:
                    forgotPassword(scanner);
                    break;
                case 4:
                    System.exit(0);
            }
        }
    }

    private static void signUp(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        if (users.containsKey(username)) {
            System.out.println("Username already exists!");
            return;
        }
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        if (users.containsValue(email)) {
            System.out.println("Email already exists!");
            return;
        }
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        users.put(username, password);
        users.put(email, username);
        System.out.println("Sign up successful!");
    }

    private static void login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        if (!users.containsKey(username)) {
            System.out.println("Invalid username!");
            return;
        }
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        if (!users.get(username).equals(password)) {
            System.out.println("Invalid password!");
            return;
        }
        System.out.println("Login successful!");
    }

    private static void forgotPassword(Scanner scanner) {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        if (!users.containsValue(email)) {
            System.out.println("Invalid email!");
            return;
        }

        // Generate a new random password
        String newPassword = generateRandomPassword();

        // Update the password in the map
        String username = getUsernameByEmail(email);
        users.put(username, newPassword);

        // Send password reset email
        sendPasswordResetEmail(email, newPassword);

        System.out.println("Password reset successful! Please check your email for the new password.");
    }

    private static String generateRandomPassword() {
        // Generate a random password using your preferred method
        // Here, we'll just generate a random string of 8 characters
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            int index = (int) (Math.random() * characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }

    private static String getUsernameByEmail(String email) {
        for (Map.Entry<String, String> entry : users.entrySet()) {
            if (entry.getValue().equals(email)) {
                return entry.getKey();
            }
        }
        return null;
    }

    private static void sendPasswordResetEmail(String email, String newPassword) {
        // Configure your SMTP server settings
        String smtpHost = "mail.example.com";
        int smtpPort = 25;

        // Create a socket connection to the SMTP server
        try (Socket socket = new Socket(smtpHost, smtpPort)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            // Read the server's greeting message
            String response = reader.readLine();
            System.out.println("Server: " + response);

            // Send the EHLO command
            writer.write("EHLO localhost\r\n");
            writer.flush();
            response = reader.readLine();
            System.out.println("Server: " + response);

            // Send the MAIL FROM command
            writer.write("MAIL FROM:<hthanh16092004@gmail.com>\r\n");
            writer.flush();
            response = reader.readLine();
            System.out.println("Server: " + response);

            // Send the RCPT TO command
            writer.write("RCPT TO:<" + email + ">\r\n");
            writer.flush();
            response = reader.readLine();
            System.out.println("Server: " + response);

            // Send the DATA command
            writer.write("DATA\r\n");
            writer.flush();
            response = reader.readLine();
            System.out.println("Server: " + response);

            // Send the email content
            writer.write("Subject: Password Reset\r\n");
            writer.write("From: Your Name <hthanh16092004@gmail.com>\r\n");
            writer.write("To: " + email + "\r\n");
            writer.write("Content-Type: text/plain\r\n");
            writer.write("\r\n");
            writer.write("Your new password is: " + newPassword + "\r\n");
            writer.write(".\r\n");
            writer.flush();
            response = reader.readLine();
            System.out.println("Server: " + response);

            // Send the QUIT command
            writer.write("QUIT\r\n");
            writer.flush();
            response = reader.readLine();
            System.out.println("Server: " + response);

            System.out.println("Password reset email sent!");
        } catch (IOException e) {
            System.out.println("Error sending password reset email: " + e.getMessage());
        }
    }
}
