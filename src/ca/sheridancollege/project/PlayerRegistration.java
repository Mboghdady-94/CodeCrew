/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;


import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles player registration for the UNO game.
 *
 * @author MahmoudElboghdady
 */
public class PlayerRegistration {

    private GameView view;                          // used to display registration prompts
    private UNOPasswordValidator passwordValidator; // validates each player's password

    // creates a new PlayerRegistration with the shared view
    public PlayerRegistration(GameView view) {
        this.view = view;
        this.passwordValidator = new UNOPasswordValidator();
    }

    // registers the given number of players and returns the list of UNOPlayer objects
    public ArrayList<Player> register(int numPlayers, Scanner scanner) {
        ArrayList<Player> registered = new ArrayList<>();

        for (int i = 0; i < numPlayers; i++) {
            view.promptRegisterPlayer(i + 1);
            String name = scanner.nextLine().trim();
            String password = collectValidPassword(scanner);

            registered.add(new UNOPlayer(name, password));
            view.showRegistrationSuccess(name);
            view.showRegisteredPlayers(registered);
        }

        return registered;
    }

    // repeatedly prompts for a password until a valid one is entered
    // delegates validation to UNOPasswordValidator
    private String collectValidPassword(Scanner scanner) {
        while (true) {
            view.promptPassword();
            String password = scanner.nextLine().trim();
            if (passwordValidator.validate(password)) {
                return password;
            }
            view.showInvalidPasswordError();
        }
    }
}
