package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetDsID {
    private static GetDsID instance;
	static final String DB_URL = "jdbc:sqlite:src/main/java/model/wareHouseDatabase.db";

    private GetDsID() {
//        try {
//            Class.forName("org.sqlite.JDBC");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//            throw new RuntimeException("Failed to load SQLite JDBC driver", e);
//        }
    }
    
    public static  GetDsID getInstance() {
        if (instance == null) {
            instance = new GetDsID();
        }
        return instance;
    }

    public int getDsID(int productId) {
        String sql = "SELECT dsID FROM product WHERE pID = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, productId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("dsID");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
        }
        // Return a default value or throw an exception based on your use case.
        return 0;
    }

  
}
