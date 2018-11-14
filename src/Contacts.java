import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

// Elapsed time 1 hour
public class Contacts {

    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to contacts!");
        System.out.println("Available commands: Add 'name', Find 'name', Exit");

        HashSet<String> contacts = new HashSet<>();

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String command;
        while (!(command = in.readLine()).equals("Exit")) {
            String[] commandParts = command.split(" ");
            String commandKey = commandParts[0];
            switch (commandKey) {
                case "Add":
                    if (isValidCommand(commandParts)) {
                        addContact(contacts, commandParts[1]);
                    }
                    break;
                case "Find":
                    if (isValidCommand(commandParts)) {
                        System.out.println(countContacts(contacts, commandParts[1]) + " contacts have found");
                    }
                    break;
                default:
                    System.out.println("Unknown command!");
            }
        }
    }

    private static boolean isValidCommand(String[] commandParts) {
        if (commandParts.length < 2) {
            System.out.println("Missing argument!");
            return false;
        } else if (commandParts.length > 2) {
            System.out.println("Too many arguments!");
            return false;
        }
        return true;
    }

    private static void addContact(HashSet<String> contacts, String commandArgument) {
        if (contacts.contains(commandArgument)) {
            System.out.println("Contact is already exist!");
        } else {
            contacts.add(commandArgument);
        }
    }

    private static int countContacts(HashSet<String> contacts, String query) {
        int queriedContactsCount = 0;
        for (String contact : contacts) {
            if (contact.startsWith(query)) {
                queriedContactsCount++;
            }
        }
        return queriedContactsCount;
    }
}
