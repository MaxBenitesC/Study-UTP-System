/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package builder;

/**
 *
 * @author Administrador
 */
public record Address(String street,String city,String country) {

    public Address{
        if(street==null || street.trim().isEmpty()){
         throw new IllegalArgumentException("Address cannot be null o empty");
        }
        if(city==null || city.trim().isEmpty()){
            throw new IllegalArgumentException("City cannot be null o empty");
        }
        if(country==null || country.trim().isEmpty()){
            throw new IllegalArgumentException("City cannot be null o empty");
        }}
    
    public static Address of(String street,String city,String country){
       return new Address(street,city,country);
    }
    
}
