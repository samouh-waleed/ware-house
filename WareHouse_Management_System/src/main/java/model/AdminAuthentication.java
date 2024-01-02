package model;

import java.sql.*;
import java.util.Scanner;
public class AdminAuthentication {
	private static AdminAuthentication instance;
	static final String DB_URL = "jdbc:sqlite:src/main/java/model/wareHouseDatabase.db";
	static final String QUERY = "SELECT * FROM user";

    private AdminAuthentication() {
    }
    public static synchronized AdminAuthentication getInstance() {
        if (instance == null) {
            instance = new AdminAuthentication();
        }
        return instance;
    }
	 
	
    public boolean verifyCredentials(String username, String password) {
        String sql = "SELECT * FROM User WHERE username = ? AND password = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
        }
        return false;
    }

}