/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 *
 * @author mahmoudelboghdadi
 */
public class PasswordValidator {
 
    private int minLength;
    private boolean hasSpecialChar;
 
    /**
     * Creates a PasswordValidator with default rules:
     * length > 7 and must contain at least one special character.
     */
    public PasswordValidator() {
        this.minLength = 7;
        this.hasSpecialChar = true;
    }
 
    /**
     * Validates a password against the rules.
     *
     * @param password the password to check
     * @return true if the password meets all requirements
     */
    public boolean validate(String password) {
        if (password == null || password.length() <= minLength) {
            return false;
        }
        if (hasSpecialChar) {
            for (char c : password.toCharArray()) {
                if (!Character.isLetterOrDigit(c)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }
}
