//KODY BUSS
//5-2-2016
//COURSE PROJECT

package com.example.kody.contactmanager;

/**
 * Created by Kody on 5/2/2016.
 */

//BASIC CLASS THAT HOLDS A PERSON'S CONTACT INFORMATION
public class Contact {

    private String _name, _phone, _email, _address;

    //INITIALIZES VARIABLE TO WHAT IS PASSED TO IT
    public Contact(String name, String phone, String email, String address){
        _name = name;
        _phone = phone;
        _email = email;
        _address = address;
    }

    //SET OF METHODS THAT RETRIVES PRIVATE VARIABLES IN THE CLASS
    public String getName(){
        return _name;
    }

    public String getPhone(){
        return _phone;
    }

    public String getEmail(){
        return _email;
    }

    public String getAddress(){
        return _address;
    }

}
