package wanglijie.model;

/**
 * Created by $Jason.Zhang on 12/29/15.
 */
public class User {
    int id;
    int idNumber;
    String userName;
    String email;
    String password;
    String gender;
    String role;
    int age;

    public User(){}

    public User(String idNumber, String userName, String email,String password,String gender,String role,String age){
        this.idNumber = Integer.valueOf(idNumber);
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.role = role;
        this.age = Integer.valueOf(age);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
