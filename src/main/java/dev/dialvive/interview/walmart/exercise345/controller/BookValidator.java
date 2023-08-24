package dev.dialvive.interview.walmart.exercise345.controller;

import dev.dialvive.interview.walmart.exercise345.model.Book;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component("beforeSaveBookValidator")
public class BookValidator implements Validator {

    private static final Pattern ISBN_REGEX_PATTERN =
            Pattern.compile("^(?=(?:\\D*\\d){10}(?:(?:\\D*\\d){3})?$)[\\d-]+$");

    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book book = (Book) target;
        if (checkInputString(book.getId())) {
            errors.rejectValue("id", "id.empty");
        }

        if (checkISBN(book.getId())) {
            errors.rejectValue("id", "id.notValidISBN");
        }
    }

    private boolean checkInputString(String input) {
        return (input == null || input.trim().length() == 0);
    }

    private boolean checkISBN(String input) {
        return ISBN_REGEX_PATTERN.matcher(input).matches();
    }


}
