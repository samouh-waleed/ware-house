package FrontEnd;

import controller.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrontLogin extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public FrontLogin() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250); 

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onBackButtonPressed();
            }
        });

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel loginLabel = new JLabel("Login:");
        JLabel passwordLabel = new JLabel("Password:");

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(Color.BLACK);
        loginButton.setOpaque(true);
        loginButton.setBorderPainted(false);

        int preferredWidth = 280; 
        Dimension fieldDimension = new Dimension(preferredWidth, 25);
        usernameField.setPreferredSize(fieldDimension);
        passwordField.setPreferredSize(fieldDimension);
        loginButton.setPreferredSize(fieldDimension);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(loginLabel, gbc);

        gbc.gridy = 1;
        panel.add(usernameField, gbc);

        gbc.gridy = 2;
        panel.add(passwordLabel, gbc);

        gbc.gridy = 3;
        panel.add(passwordField, gbc);

        gbc.gridy = 4;
        panel.add(loginButton, gbc);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(backButton, BorderLayout.WEST);

        add(topPanel, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);

        loginButton.addActionListener(new ActionListener() {
        	
        	Login loginController = new Login();
        	boolean contLoop = false;
        	
            public void actionPerformed(ActionEvent e) {
            	
            	boolean isAuthenticated = loginController.authenticateUser(usernameField.getText(), passwordField.getText());
        		if (isAuthenticated) {
        			contLoop =false;
        			System.out.println("Login Successful!");
                    AdminView admin = new AdminView();
                    onBackButtonHide();
        			
        		}else {
        			System.out.println("Login Failed");
        			contLoop =true;
        		}
                
                
        		
            }
        });
    }

	private void onBackButtonPressed() {
        HomePage homePage = new HomePage();
        this.setVisible(false);
    }
	
	private void onBackButtonHide() {
        this.setVisible(false);
    }
	
}
