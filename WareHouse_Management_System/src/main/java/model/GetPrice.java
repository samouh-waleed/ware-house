package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetPrice {
    private static GetPrice instance;
	static final String DB_URL = "jdbc:sqlite:src/main/java/model/wareHouseDatabase.db";
    
    private GetPrice() {
    }
    
    public static GetPrice getInstance() {
        if (instance == null) {
            instance = new GetPrice();
        }
        return instance;
    }

    public float getProductPrice(int productId) {
        String sql = "SELECT unitPrice FROM product WHERE pID = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, productId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getFloat("unitPrice");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
        }
        // Return a default value or throw an exception based on your use case.
        return 0.0f;
    }
}
