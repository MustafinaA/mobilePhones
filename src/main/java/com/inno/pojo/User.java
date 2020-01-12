package com.inno.pojo;

public class User {
    private final Integer id;
    private final String login;
    private final String email;
    private final String phone;
    private final String password;

    private User(UserBuilder builder) {
        this.id = builder.id;
        this.login = builder.login;
        this.email = builder.email;
        this.phone = builder.phone;
        this.password = builder.password;
    }

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
    public static class UserBuilder {
        private final String login;
        private final String password;
        private Integer id;
        private String email;
        private String phone;

        public UserBuilder(String login, String password){
            this.login = login;
            this.password = password;
        }

        public UserBuilder withId(int id) {
            this.id = id;
            return this;
        }

        public UserBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder withPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public User build() {
            return new User(this);
        }
//
//    public User(Integer id) {
//        this.id = id;
//    }
//
//    public User(String login, String password) {
//        this.login = login;
//        this.password = password;
//    }
//
//    public User(Integer id, String login, String email, String phone, String password) {
//        this.id = id;
//        this.login = login;
//        this.email = email;
//        this.phone = phone;
//        this.password = password;
//    }
    }
}
