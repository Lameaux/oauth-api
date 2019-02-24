package com.euromoby.oauth.dtos;

import com.euromoby.oauth.entities.User;

public class GetUser extends GetEntity {
    private String email;
    private String firstName;
    private String lastName;

    public GetUser() {
        super();
    }

    public GetUser(User user) {
        super(user);

        email = user.getEmail();
        firstName = user.getFirstName();
        lastName = user.getLastName();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
