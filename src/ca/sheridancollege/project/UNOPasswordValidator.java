/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 *password validator: A valid password must be longer than 7 characters
 * and contain at least one special character.
 * @author YuvrajSinghSahi
 */
public class UNOPasswordValidator extends PasswordValidator {
 
     // the set of accepted special characters
    private static final String SPECIAL_CHARS = "!@#$%^&*()-_=+[]{}|;:',.<>?/`~";
    
// creates a UNOPasswordValidator with a minimum length of 8 and special character required
    public UNOPasswordValidator() {
        super();
    }
 
    // validates that the password is longer than 7 characters
    // and contains at least one special character
    // returns true if both rules pass, false otherwise
    @Override
    public boolean validate(String password) {
        if (password.length() <= 7) {
            return false;
        }
        return containsSpecialCharacter(password);
    }
 
    // returns true if the password contains at least one special character
    private boolean containsSpecialCharacter(String password) {
        for (char c : password.toCharArray()) {
            if (SPECIAL_CHARS.indexOf(c) >= 0) {
                return true;
            }
        }
        return false;
    }
}
