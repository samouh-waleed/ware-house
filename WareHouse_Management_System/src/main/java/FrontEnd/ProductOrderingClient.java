package FrontEnd;

import javax.swing.*;

import controller.NewOrderState;
import controller.OrderContext;
import model.OrderData;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ProductOrderingClient {

    private JFrame frame;
    private JComboBox<String> productDropdown;
    private JComboBox<Integer> quantityDropdown;
    private JTextArea orderDetails;
    private JButton chooseProductButton;
    private JButton chooseQuantityButton;
    private JButton backButton;
    private String selectedProduct;
    private int selectedQuantity;
    private JButton submitOrder;

    public ProductOrderingClient() {
        // Initialize main frame
        frame = new JFrame("Product Ordering Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Back Button
        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onBackButtonPressed();
            }
        });

        // Top panel for choosing product and quantity
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        productDropdown = new JComboBox<>(new String[]{"Product1", "Product2", "Product3", "Product4", "Product5"});
        chooseProductButton = new JButton("Choose");
        chooseProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedProduct = (String) productDropdown.getSelectedItem();
                updateOrderDetails();
                System.out.println(selectedProduct);
            }
        });
        

        quantityDropdown = new JComboBox<>(new Integer[]{1, 50, 100, 150, 200, 250, 300, 350});
        chooseQuantityButton = new JButton("Choose");
        chooseQuantityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedQuantity = (Integer) quantityDropdown.getSelectedItem();
                updateOrderDetails();
                System.out.println(selectedQuantity);
            }
        });
        
        
        submitOrder = new JButton("Submit");
        submitOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	OrderData orderData = new OrderData();
    			OrderContext context = new OrderContext(orderData);
    			Character selectedProductNum = selectedProduct.charAt(selectedProduct.length()-1);
    			NewOrderState obj = new NewOrderState();
//    			String result = context.handleOrder(Integer.parseInt(selectedProductNum.toString()), selectedQuantity);
    			String result = obj.handleOrder(context, Integer.parseInt(selectedProductNum.toString()), selectedQuantity);
    			System.out.println(result);
    			orderDetails.setText(orderDetails.getText() + "\n" + result);
            }
        });
        

        topPanel.add(backButton); // Add back button to the top panel
        topPanel.add(new JLabel("Step1 Choose Product:"));
        topPanel.add(productDropdown);
        topPanel.add(chooseProductButton);
        topPanel.add(new JLabel("Step2 Choose Quantity:"));
        topPanel.add(quantityDropdown);
        topPanel.add(chooseQuantityButton);
        topPanel.add(new JLabel("Step3 Submit:"));
        topPanel.add(submitOrder);
  
        
        // Center panel for order details
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(new JLabel("Order Details:"), BorderLayout.NORTH);
        orderDetails = new JTextArea(5, 30);
        orderDetails.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(orderDetails);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        // Add panels to the frame
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);

        // Display the window.
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void onBackButtonPressed() {
        HomePage homePage = new HomePage();
        frame.dispose();
    }

    private void updateOrderDetails() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = now.format(formatter);

        orderDetails.setText("Product: " + selectedProduct + "\nQuantity: " + selectedQuantity + "\nClient Time Stamp: " + timestamp);
    }

}
