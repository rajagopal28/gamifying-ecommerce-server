package com.dxtrs.hack.gamify.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String username;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    private String lastName;

    @Column(nullable = false)
    private Date dob;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private long wallet;

    @Column(nullable = false)
    private String currencyType;

    @JsonIgnore
    @Transient
    private String dateOfBirth;
    @Column()
    private Date createdTS;

    @Column()
    private Date lastUpdatedTS;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", wallet=" + wallet +
                ", currencyType='" + currencyType + '\'' +
                ", createdTS=" + createdTS +
                ", lastUpdatedTS=" + lastUpdatedTS +
                '}';
    }
}
