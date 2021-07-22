package org.greentracker;

import org.greentracker.builders.AsciiTableBuilder;
import org.greentracker.builders.StateBuilder;
import org.greentracker.builders.TicketBuilder;
import org.greentracker.models.Session;
import org.greentracker.models.User;
import org.greentracker.requests.SessionRequest;
import org.greentracker.requests.StateRequest;
import org.greentracker.requests.TicketRequest;

import java.util.Scanner;

public class Menu {
    static public void menuDisplay(Session session, User user) throws Exception {
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bonjour " + user.getName());

        while (!exit) {
            System.out.println("1.Tickets");
            System.out.println("2.Mes Ticket");
            System.out.println("3.Info Ticket");
            System.out.println("4.Créer Ticket");
            System.out.println("5.Modifier Ticket");
            System.out.println("6.Supprimer Ticket");
            System.out.println("7.Créer State");
            System.out.println("8.Modifier State");
            System.out.println("9.Supprimer State");
            System.out.println("10.Déconnexion");
            System.out.print("Choisissez une action : ");
            String input = scanner.nextLine();

            switch (input.toLowerCase()) {
                case "1":
                    StateBuilder stateBuilder = new StateBuilder(StateRequest.getAllStates(session));
                    TicketBuilder ticketBuilder = new TicketBuilder(TicketRequest.getAllTickets(session));

                    AsciiTableBuilder builder = new AsciiTableBuilder(ticketBuilder.getTicketList(), stateBuilder.getStateList());
                    builder.displayTable();

                    System.out.print("Appuyer sur entrer pour continuer");
                    scanner.nextLine();
                    break;
                case "2":
                    StateBuilder stateBuilder1 = new StateBuilder(StateRequest.getAllStates(session));
                    TicketBuilder userTicketBuilder = new TicketBuilder(TicketRequest.getUserTickets(session, user));

                    AsciiTableBuilder userBuilder = new AsciiTableBuilder(userTicketBuilder.getTicketList(), stateBuilder1.getStateList());
                    userBuilder.displayTable();

                    System.out.print("Appuyer sur entrer pour continuer");
                    scanner.nextLine();
                    break;
                case "3":
                    System.out.print("Nom du ticket : ");
                    String ticketName = scanner.nextLine();

                    String ticketInfo = TicketRequest.getTicketByName(session, ticketName);
                    if (ticketInfo != null) {
                        TicketBuilder ticketByNameBuilder = new TicketBuilder(ticketInfo);
                        System.out.println(ticketByNameBuilder.getTicketList().get(0).toString(session));
                    }

                    System.out.print("Appuyer sur entrer pour continuer");
                    scanner.nextLine();
                    break;
                case "4":
                    String[] newTicketInfo = new String[3];
                    System.out.print("Nom du ticket : ");
                    newTicketInfo[0] = scanner.nextLine();
                    System.out.print("Description : ");
                    newTicketInfo[1] = scanner.nextLine();
                    System.out.print("Assigner à : ");
                    newTicketInfo[2] = scanner.nextLine();

                    TicketRequest.createTicket(session, user, newTicketInfo);

                    System.out.print("Appuyer sur entrer pour continuer");
                    scanner.nextLine();
                    break;
                case "5":
                    System.out.print("Nom du ticket à modifier : ");
                    String ticketToUpdateName = scanner.nextLine();

                    String ticketToUpdateInfo = TicketRequest.getTicketByName(session, ticketToUpdateName);
                    if (ticketToUpdateInfo != null) {
                        TicketBuilder ticketToUpdateBuilder = new TicketBuilder(ticketToUpdateInfo);
                        String[] updateTicketInfo = new String[4];
                        System.out.println("Si vous ne voulez pas modifié un éléments appuyer sur Entrée");
                        System.out.print("Nom du ticket : ");
                        updateTicketInfo[0] = scanner.nextLine();
                        System.out.print("Description : ");
                        updateTicketInfo[1] = scanner.nextLine();
                        System.out.print("Assigner à : ");
                        updateTicketInfo[2] = scanner.nextLine();
                        System.out.print("Etat : ");
                        updateTicketInfo[3] = scanner.nextLine();

                        TicketRequest.updateTicket(
                                session, ticketToUpdateBuilder.getTicketList().get(0).getName(), updateTicketInfo);
                    }

                    System.out.print("Appuyer sur entrer pour continuer");
                    scanner.nextLine();
                    break;
                case "6":
                    System.out.print("Nom du ticket à supprimer : ");
                    String ticketToDeleteName = scanner.nextLine();

                    if (TicketRequest.getTicketByName(session, ticketToDeleteName) != null) {
                        TicketRequest.deleteTicket(session, ticketToDeleteName);
                        System.out.println("Ticket supprimer");
                    }

                    System.out.print("Appuyer sur entrer pour continuer");
                    scanner.nextLine();
                    break;
                case "7":
                    System.out.print("Nom de la State : ");
                    String stateName = scanner.nextLine();

                    StateRequest.createState(session, stateName);

                    System.out.print("Appuyer sur entrer pour continuer");
                    scanner.nextLine();
                    break;
                case "8":
                    System.out.print("Nom de la state à modifier : ");
                    String stateToUpdateName = scanner.nextLine();

                    String stateToUpdateInfo = StateRequest.getStateByName(session, stateToUpdateName);
                    if (stateToUpdateInfo != null) {
                        StateBuilder stateToUpdateBuilder = new StateBuilder(stateToUpdateInfo);
                        System.out.print("Nouveau nom : ");
                        String stateNewName = scanner.nextLine();

                        StateRequest.updateState(
                                session, stateToUpdateBuilder.getStateList().get(0).getName(), stateNewName);
                    }

                    System.out.print("Appuyer sur entrer pour continuer");
                    scanner.nextLine();
                    break;
                case "9":
                    System.out.print("Nom de la state à supprimer : ");
                    String stateToDeleteName = scanner.nextLine();

                    if (StateRequest.getStateByName(session, stateToDeleteName) != null) {
                        StateRequest.deleteState(session, stateToDeleteName);
                        System.out.println("State supprimer");
                    }

                    System.out.print("Appuyer sur entrer pour continuer");
                    scanner.nextLine();
                    break;
                case "10":
                    SessionRequest.SessionDisconnection(session);
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
