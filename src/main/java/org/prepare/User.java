package org.prepare;

import com.github.javafaker.Faker;

public class User {

    private Faker faker = new Faker();

    private String userName;
    private String password;
    private String email;

    public User(){
        userName = faker.name().username().replace(".", "").substring(0, 9);
        password = faker.internet().password(9, 10);
        email = faker.internet().emailAddress();
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
