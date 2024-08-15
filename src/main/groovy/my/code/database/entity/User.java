package my.code.database.entity;

import java.util.Set;

public class User {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String username;

    private String password;

    private Gender gender;

    private double balance;

    private int age;

    private Set<Role> roles;

}
