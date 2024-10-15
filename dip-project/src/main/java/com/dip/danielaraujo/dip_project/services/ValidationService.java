package com.dip.danielaraujo.dip_project.services;

import com.dip.danielaraujo.dip_project.dtos.ClientDTO;
import com.dip.danielaraujo.dip_project.dtos.DipDTO;
import com.dip.danielaraujo.dip_project.exceptions.InvalidDataException;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {
    private final String cannotBeEmpty = " cannot be empty.";
    private final String acceptJustLetters = " accepts just letters";
    private final String isInvalid = " is invalid";

    public ValidationService(){

    }

    public ValidationService(ClientDTO data){
        String str = "cannot be empty.";
        this.validateClientFirstName(data.firstName(), "The first name");
        this.validateClientLastName(data.lastName(), "The last name");
        this.validadeClientEmail(data.email(), "The email");
        this.validadeClientPhoneNumber(data.phoneNumber(), "The phone number");
        this.validadeClientPassword(data.password(), "The password");
    }

    public ValidationService(DipDTO data){
        String str = "cannot be empty.";
        this.validateDipName(data.name(), "The name");
    }

    private void validateClientFirstName(String name, String expression){
        if (name.isBlank()){
            throw new InvalidDataException(expression + this.cannotBeEmpty);
        }else if (!(this.cotainOnlyLetters(name))){
            throw new InvalidDataException(expression + this.acceptJustLetters);
        }
    }

    private void validateClientLastName(String lastName, String expression){
        this.validateClientFirstName(lastName, expression);
    }

    private void validadeClientEmail(String email, String expression){
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        if (email.isBlank()){
            throw new InvalidDataException(expression + this.cannotBeEmpty);
        }else if (!email.matches(emailRegex)){
            throw new InvalidDataException(expression + this.isInvalid);
        }
    }

    private void validadeClientPhoneNumber(String phoneNumber, String expression){
        String phoneNumberRegex = "^[0-9]{11}$";
        if (phoneNumber.isBlank()){
            throw new InvalidDataException(expression + this.cannotBeEmpty);
        }else if (!phoneNumber.matches(phoneNumberRegex)){
            throw new InvalidDataException(expression + this.isInvalid);
        }
    }

    private void validadeClientPassword(String password, String expression){
        String passWordRegex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+=-]).{8,12}$";
        if (password.isBlank()){
            throw new InvalidDataException(expression + this.cannotBeEmpty);
        }else if (!password.matches(passWordRegex)){
            throw new InvalidDataException(expression + this.isInvalid);
        }
    }

    private boolean cotainOnlyLetters(String data){
        return data.matches("^[\\p{L} ]+$");
    }

    private void validateDipName(String dipName, String expression){
        this.validateClientFirstName(dipName, expression);
    }

}
