/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 *
 * @author YuvrajSinghSahi
 */
public abstract class PasswordValidator {

    private final int minLength;
    private final boolean hasSpecialChar;

    /**
     * creates a new PasswordValidator with the given rules: length > 7 and must
     * contain at least one special character.
     */
    public PasswordValidator() {
        this.minLength = 7;
        this.hasSpecialChar = true;
    }

    protected int getMinLength() {
        return minLength;
    }

    protected boolean isHasSpecialChar() {
        return hasSpecialChar;
    }

    /*
    * validates a password against the rules defined by  validator
    * returns true if the password meets all requirements, otherwise false 
     */
    public abstract boolean validate(String password);
}
