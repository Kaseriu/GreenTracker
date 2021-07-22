package org.greentracker;

import org.greentracker.builders.SessionBuilder;
import org.greentracker.builders.UserBuilder;
import org.greentracker.requests.SessionRequest;
import org.greentracker.requests.UserRequest;

import java.util.Scanner;

public class App {
    public static final String API_URI = "http://localhost:5678/";

    public static void main(String[] args) throws Exception {

        boolean exit = false;
        Scanner scanner = new Scanner(System.in);

        while (!exit) {

            System.out.println("1.Connexion");
            System.out.println("2.Inscription");
            System.out.println("3.Quitter");
            System.out.print("Choisissez une action : ");
            String input = scanner.nextLine();

            switch (input.toLowerCase()) {
                case "1":
                    System.out.print("Email : ");
                    String mail = scanner.nextLine();
                    System.out.print("Mot de passe : ");
                    String password = scanner.nextLine();

                    String sessionInfo = SessionRequest.SessionConnection(mail, password);
                    if (sessionInfo != null) {
                        SessionBuilder sessionBuilder = new SessionBuilder(sessionInfo);
                        String userInfo = UserRequest.GetUserById(sessionBuilder.getSession());
                        UserBuilder userBuilder = new UserBuilder(userInfo);
                        Menu.menuDisplay(sessionBuilder.getSession(), userBuilder.getUser());
                    }
                    break;
                case "2":
                    String[] userInfo = new String[3];
                    System.out.print("Nom : ");
                    userInfo[0] = scanner.nextLine();
                    System.out.print("Email : ");
                    userInfo[1] = scanner.nextLine();
                    System.out.print("Mot de Passe : ");
                    userInfo[2] = scanner.nextLine();

                    String userSubscription = SessionRequest.UserSubscription(userInfo);
                    if (userSubscription != null) {
                        String[] userAndSessionInfo = userSubscription.split("},");
                        UserBuilder userBuilder = new UserBuilder(userAndSessionInfo[0].replace("{\"user\":{", ""));
                        SessionBuilder sessionBuilder = new SessionBuilder(userAndSessionInfo[1].replace("\"session\":{", ""));
                        Menu.menuDisplay(sessionBuilder.getSession(), userBuilder.getUser());
                    } else {
                        System.out.print("Appuyer sur entrer pour continuer");
                        scanner.nextLine();
                    }
                    break;
                case "3":
                    exit = true;
                    break;
                default:
                    System.out.println("Mauvaise saisie");
                    System.out.print("Appuyer sur entrer pour continuer");
                    scanner.nextLine();
            }
        }
    }
}
