package model;

import java.sql.*;

public class OrderData {
    
	static final String DB_URL = "jdbc:sqlite:src/main/java/model/wareHouseDatabase.db";

    
    // user quantity < available quantity 
    public boolean isOrderQuantityAvailable(int productID, int orderQuantity) {
        String sql = "SELECT * FROM product WHERE pID = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, productID);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int availableQuantity = rs.getInt("quantity");
                    return orderQuantity <= availableQuantity;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // user quantity > max quantity 
    public boolean isOrderExceedsMaxQuantity(int productID, int orderQuantity) {
        String sql = "SELECT * FROM product WHERE pID = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, productID);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int maxQuantity = rs.getInt("maxQuantity");
                    return orderQuantity > maxQuantity;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // Method to restock the product

    public boolean updateProductQuantityAfterOrder(int productID, int orderQuantity) {
        boolean updatedSuccessfully = false;
        String sql = "UPDATE product SET quantity = quantity - ? WHERE pID = ? AND quantity >= ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, orderQuantity);
            pstmt.setInt(2, productID);
            pstmt.setInt(3, orderQuantity);

            int rowsAffected = pstmt.executeUpdate();
            updatedSuccessfully = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println("Order was updated");
        return updatedSuccessfully;
    }

    
    public boolean restockProduct(int productID) {
        boolean restockedSuccessfully = false;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL);
            conn.setAutoCommit(false); // Start transaction

            String query = "SELECT quantity, restockSchedule, maxQuantity FROM product WHERE pID = ?";
            try (PreparedStatement pstmtQuery = conn.prepareStatement(query)) {
                pstmtQuery.setInt(1, productID);
                ResultSet rs = pstmtQuery.executeQuery();

                if (rs.next()) {
                    int currentQuantity = rs.getInt("quantity");
                    int restockSchedule = rs.getInt("restockSchedule");
                    int maxQuantity = rs.getInt("maxQuantity");

                    while (currentQuantity <= maxQuantity) {
                        int potentialNewQuantity = currentQuantity + restockSchedule;
                        if (potentialNewQuantity > maxQuantity) {
                            currentQuantity = maxQuantity; // Adjust to exactly match maxQuantity
                        } else {
                            currentQuantity = potentialNewQuantity; // Increment by restock schedule
                        }

                        String update = "UPDATE product SET quantity = ? WHERE pID = ?";
                        try (PreparedStatement pstmtUpdate = conn.prepareStatement(update)) {
                            pstmtUpdate.setInt(1, currentQuantity);
                            pstmtUpdate.setInt(2, productID);
                            pstmtUpdate.executeUpdate();
                        }

                        if (currentQuantity >= maxQuantity) {
                            break;
                        }
                    }

                    conn.commit(); // Commit transaction
                    restockedSuccessfully = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback(); // Rollback transaction on error
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true); // Re-enable auto-commit
                    conn.close(); // Close the connection
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return restockedSuccessfully;
    }
    //TO BE IMPLEMENTED AND POLISHED.
  //THIS ONE HAS THE QUANTITY = MAX - USER QUANTITY AND SAVES IT IN THE DB
//    public boolean restockProduct(int productID) {
//        boolean restockedSuccessfully = false;
//        Connection conn = null;
//        try {
//            conn = DriverManager.getConnection(DB_URL, USER, PASS);
//            conn.setAutoCommit(false); // Start transaction
//
//            String query = "SELECT * FROM product WHERE pID = ?";
//            try (PreparedStatement pstmtQuery = conn.prepareStatement(query)) {
//                pstmtQuery.setInt(1, productID);
//                ResultSet rs = pstmtQuery.executeQuery();
//
//                if (rs.next()) {
//                    int currentQuantity = rs.getInt("quantity");
//                    int restockSchedule = rs.getInt("restockSchedule");
//                    int maxQuantity = rs.getInt("maxQuantity");
//
//                    while (currentQuantity < maxQuantity) {
//                        int potentialNewQuantity = currentQuantity + restockSchedule;
//                        currentQuantity = (potentialNewQuantity > maxQuantity) ? maxQuantity : potentialNewQuantity;
//
//                        String update = "UPDATE product SET quantity = ? WHERE pID = ?";
//                        try (PreparedStatement pstmtUpdate = conn.prepareStatement(update)) {
//                            pstmtUpdate.setInt(1, currentQuantity);
//                            pstmtUpdate.setInt(2, productID);
//                            pstmtUpdate.executeUpdate();
//                        }
//
//                        if (currentQuantity >= maxQuantity) {
//                            break;
//                        }
//                    }
//
//                    conn.commit(); // Commit transaction
//                    restockedSuccessfully = true;
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            if (conn != null) {
//                try {
//                    conn.rollback(); // Rollback transaction on error
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                }
//            }
//        } finally {
//            if (conn != null) {
//                try {
//                    conn.setAutoCommit(true); // Re-enable auto-commit
//                    conn.close(); // Close the connection
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return restockedSuccessfully;
//    }
}
