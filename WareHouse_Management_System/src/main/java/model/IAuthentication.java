package model;

public interface IAuthentication {
    boolean authenticate(String username, String password);
}