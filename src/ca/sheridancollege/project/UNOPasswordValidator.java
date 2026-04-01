/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 *
 * @author mahmoudelboghdadi
 */
public class UNOPasswordValidator extends PasswordValidator {
 
    /**
     * Validates the password using UNO's registration rules.
     * Password must be longer than 7 characters and contain at least one special character.
     *
     * @param password the password to validate
     * @return true if the password is valid
     */
    @Override
    public boolean validate(String password) {
        return super.validate(password);
    }
}
