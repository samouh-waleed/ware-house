package controller;

import model.*;       

public class Login {
    private IAuthentication loginAuthenticator;  

    public Login() {
        loginAuthenticator = new AdminAuthAdapter();  
    }

    public boolean authenticateUser(String username, String password) {
        return loginAuthenticator.authenticate(username, password);  
    }
}