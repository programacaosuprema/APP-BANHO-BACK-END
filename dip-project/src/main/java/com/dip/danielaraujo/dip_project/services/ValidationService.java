package com.dip.danielaraujo.dip_project.services;

import com.dip.danielaraujo.dip_project.dtos.ClientDTO;
import com.dip.danielaraujo.dip_project.dtos.DipDTO;
import com.dip.danielaraujo.dip_project.exceptions.InvalidDataException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

@Service
public class ValidationService {
    private static final String CANNOT_BE_EMPTY = " cannot be empty.";
    private static final String IS_INVALID = " is invalid.";
    private static final String ACCEPTS_JUST_LETTERS = " accepts just letters.";

    private static final Pattern EMAIL_REGEX = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    private static final Pattern PHONE_NUMBER_REGEX = Pattern.compile("^[0-9]{11}$");
    private static final Pattern PASSWORD_REGEX = Pattern.compile("^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+=-]).{8,12}$");
    private static final Pattern LETTERS_ONLY_REGEX = Pattern.compile("^[\\p{L} ]+$");

    private static final Set<String> VALID_STATES = new HashSet<>(Arrays.asList(
            "AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "MS", "MT",
            "PA", "PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO"
    ));

    public ValidationService() {
    }

    public ValidationService(ClientDTO data) {
        validateClient(data);
    }

    public ValidationService(DipDTO data) {
        validateDip(data);
    }

    public void validateClient(ClientDTO data) {
        validateNotEmpty(data.firstName(), "The first name");

        validateOnlyLetters(data.firstName(), "The first name");

        validateNotEmpty(data.lastName(), "The last name");
        validateOnlyLetters(data.lastName(), "The last name");

        validateNotEmpty(data.email(), "The email");
        validateEmail(data.email());

        validateNotEmpty(data.phoneNumber(), "The phone number");
        validatePhoneNumber(data.phoneNumber());

        validateNotEmpty(data.password(), "The password");
        validatePassword(data.password());
    }
    public void validateDip(DipDTO data) {
        validateNotEmpty(data.name(), "The name");
        validateOnlyLetters(data.name(), "The name");

        validateState(data.state());
    }

    private void validateNotEmpty(String value, String field) {
        if (value == null || value.isEmpty()) {
            throw new InvalidDataException(field + CANNOT_BE_EMPTY);
        }
    }

    private void validateOnlyLetters(String value, String field) {
        if (!LETTERS_ONLY_REGEX.matcher(value).matches()) {
            throw new InvalidDataException(field + ACCEPTS_JUST_LETTERS);
        }
    }

    private void validateEmail(String email) {
        if (!EMAIL_REGEX.matcher(email).matches()) {
            throw new InvalidDataException("The email" + IS_INVALID);
        }
    }

    private void validatePhoneNumber(String phoneNumber) {
        if (!PHONE_NUMBER_REGEX.matcher(phoneNumber).matches()) {
            throw new InvalidDataException("The phone number" + IS_INVALID);
        }
    }

    private void validatePassword(String password) {
        if (!PASSWORD_REGEX.matcher(password).matches()) {
            throw new InvalidDataException("The password" + IS_INVALID);
        }
    }

    private void validateState(String state) {
        if (!VALID_STATES.contains(state.toUpperCase())) {
            throw new InvalidDataException("The state" + IS_INVALID);
        }
    }
}
