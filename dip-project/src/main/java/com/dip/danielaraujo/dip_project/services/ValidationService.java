package com.dip.danielaraujo.dip_project.services;

import com.dip.danielaraujo.dip_project.dtos.ClientDTO;
import com.dip.danielaraujo.dip_project.exceptions.InvalidDataFromClientException;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {
    private final String cannotBeEmpty = " cannot be empty.";
    private final String acceptJustLetters = " accepts just letters";
    private final String isInvalid = " is invalid";

    public void validateClient(ClientDTO data){
        String str = "cannot be empty.";
        this.validateFirstName(data.firstName(), "The first name");
        this.validateLastName(data.lastName(), "The last name");
        this.validadeEmail(data.email(), "The email");
        this.validadePhoneNumber(data.phoneNumber(), "The phone number");
        this.validadePassword(data.password(), "The password");
    }

    private void validateFirstName(String name, String expression){
        if (name.isBlank()){
            throw new InvalidDataFromClientException(expression + this.cannotBeEmpty);
        }else if (!(this.cotainOnlyLetters(name))){
            throw new InvalidDataFromClientException(expression + this.acceptJustLetters);
        }
    }

    private void validateLastName(String lastName, String expression){
        this.validateFirstName(lastName, expression);
    }

    private void validadeEmail(String email, String expression){
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        if (email.isBlank()){
            throw new InvalidDataFromClientException(expression + this.cannotBeEmpty);
        }else if (!email.matches(emailRegex)){
            throw new InvalidDataFromClientException(expression + this.isInvalid);
        }
    }

    private void validadePhoneNumber(String phoneNumber, String expression){
        String phoneNumberRegex = "^[0-9]{11}$";
        if (phoneNumber.isBlank()){
            throw new InvalidDataFromClientException(expression + this.cannotBeEmpty);
        }else if (!phoneNumber.matches(phoneNumberRegex)){
            throw new InvalidDataFromClientException(expression + this.isInvalid);
        }
    }

    private void validadePassword(String password, String expression){
        String passWordRegex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+=-]).{8,12}$";
        if (password.isBlank()){
            throw new InvalidDataFromClientException(expression + this.cannotBeEmpty);
        }else if (!password.matches(passWordRegex)){
            throw new InvalidDataFromClientException(expression + this.isInvalid);
        }
    }

    private boolean cotainOnlyLetters(String data){
        return data.matches("^[\\p{L} ]+$");
    }

}
