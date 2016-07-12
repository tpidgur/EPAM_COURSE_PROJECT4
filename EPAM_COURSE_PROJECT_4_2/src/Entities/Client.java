package Entities;

/**
 * Created by Зая on 05.07.2016.
 */
public class Client {
    private int id;
    // private static int counter=1;
    private String firstName;
    private String lastName;
    private String address;
    private String telephone;
    private String email;

//    public Client(String firstName, String lastName, String address, String telephone, String email) {
//      //  id = counter++;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.address = address;
//        this.telephone = telephone;
//        this.email = email;
//    }

    public Client(String firstName, String lastName, String address, String telephone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.telephone = telephone;
        this.email = email;
    }

    public Client() {

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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

    @Override
    public String toString() {
        return "\nClient{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
