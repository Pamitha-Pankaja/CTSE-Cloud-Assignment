package com.ctse.microservices.order.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails {
    private String email;
    private String firstName;
    private String lastName;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail(){
        return email;
    }
}


