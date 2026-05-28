/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package builder;

/**
 *
 * @author Administrador
 */
public class Customer {

    private Integer id;
    private String name;
    private String lastName;
    private String email;
    private Address address;

    private Customer() {
    }

    public static class Builder {

        private Customer customer;

        public Builder() {
            this.customer = new Customer();
        }

        public Builder id(Integer id) {
            if (id == null) {
                throw new IllegalArgumentException("Id cannot be null");
            }
            this.customer.id = id;
            return this;
        }

        public Builder name(String name) {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Id cannot be null or empty");
            }
            this.customer.name = name;
            return this;
        }

        public Builder lastName(String lastName) {
            if (lastName == null || lastName.trim().isEmpty()) {
                throw new IllegalArgumentException("Id cannot be null or empty");
            }
            this.customer.lastName = lastName;
            return this;
        }

        public Builder email(String email) {
            if (email == null || email.trim().isEmpty()) {
                throw new IllegalArgumentException("Id cannot be null or empty");
            }
            if (!email.matches("^[uU][0-9]{8}@(utp.edu.pe)$")) {
                throw new IllegalArgumentException("Email no cumple con el formato");
            }
            this.customer.email = email;
            return this;
        }

        public Builder address(Address address) {
            this.customer.address = Address.of(address.street(),address.city(),address.country());
            return this;
        }

        public Customer build() {
            return this.customer;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", name=" + name + ", lastName=" + lastName + ", email=" + email + ", address=" + address + '}';
    }

}
