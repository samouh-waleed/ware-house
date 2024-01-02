package model;

public class AdminAuthAdapter implements IAuthentication {
    private final AdminAuthentication adminAuth;

    public AdminAuthAdapter() {
        adminAuth = AdminAuthentication.getInstance();
    }

    @Override
    public boolean authenticate(String username, String password) {
        return adminAuth.verifyCredentials(username, password);
    }
}