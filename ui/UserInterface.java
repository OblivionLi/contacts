package contacts.ui;

import contacts.model.Contact;
import contacts.model.Organization;
import contacts.model.Person;
import contacts.model.PhoneBook;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner;
    private final PhoneBook phoneBook;

    public UserInterface(Scanner scanner) {
        this.scanner = scanner;
        this.phoneBook = new PhoneBook();
    }

    public void start() {
        while (true) {
            System.out.print("[menu] Enter action (add, list, search, count, exit): ");
            String action = this.scanner.nextLine();

            if (action.equals("exit")) {
                break;
            }

            switch (action) {
                case "count" -> System.out.printf("The Phone book has %d records.\n\n", this.phoneBook.getContactList().size());
                case "add" -> this.addContact();
                case "list" -> this.listContacts();
                case "search" -> this.searchContacts();
            }
        }
    }

    private void editContact(Contact contact) {
        if (this.phoneBook.getContactList().isEmpty()) {
            System.out.println("No records to edit!");
            return;
        }

        this.updateContact(contact);
        System.out.println("Saved");
        this.showContactInfo(contact);
    }

    private void updateContact(Contact contact) {
        if (contact instanceof Person person) {
            System.out.print("Select a field (name, surname, birth, gender, number): ");
            String field = this.scanner.nextLine();

            switch (field) {
                case "name" -> contact.setName(this.getName("Enter name:"));
                case "surname" -> person.setSurname(this.getSurname());
                case "birth" -> person.setBirthDate(this.getBirthDate());
                case "gender" -> person.setGender(this.getGender());
                case "number" -> contact.setPhoneNumber(this.getPhoneNumber());
            }
        }

        if (contact instanceof Organization organization) {
            System.out.print("Select a field (name, address, number): ");
            String field = this.scanner.nextLine();

            switch (field) {
                case "name" -> organization.setName(this.getName("Enter name: "));
                case "address" -> organization.setAddress(this.getAddress());
                case "number" -> contact.setPhoneNumber(this.getPhoneNumber());
            }
        }
    }

    private void removeContact(Contact contact) {
        this.phoneBook.removeContact(contact);
        System.out.println("The record removed!");
        System.out.println();
    }

    private void addContact() {
        System.out.print("Enter the type (person, organization): ");
        String type = this.scanner.nextLine();
        if (!type.equalsIgnoreCase("person") && !type.equalsIgnoreCase("organization")) {
            return;
        }

        if (type.equalsIgnoreCase("person")) {
            String name = this.getName("Enter the name: ");
            String surname = this.getSurname();
            String birthdate = this.getBirthDate();
            String gender = this.getGender();
            String phoneNumber = this.getPhoneNumber();

            Contact contact = new Person(name, phoneNumber, surname, gender, birthdate);
            this.phoneBook.addContact(contact);
        }

        if (type.equalsIgnoreCase("organization")) {
            String name = this.getName("Enter the organization name: ");
            String address = this.getAddress();
            String phoneNumber = this.getPhoneNumber();

            Contact contact = new Organization(name, phoneNumber, address);
            this.phoneBook.addContact(contact);
        }

        System.out.println("The record added.");
        System.out.println();
    }

    private void listContacts() {
        if (this.phoneBook.getContactList().isEmpty()) {
            System.out.println("Contact list is empty.");
            return;
        }

        int index = 1;
        for (Contact contact : this.phoneBook.getContactList()) {
            System.out.println(index + ". " + contact.getName());
            index++;
        }

        System.out.println();

        while (true) {
            System.out.print("[list] Enter action ([number], back): ");
            String action = this.scanner.nextLine();

            if (action.equals("back")) {
                break;
            }

            try {
                int number = Integer.parseInt(action);
                if (number < 1 || number > this.phoneBook.getContactList().size()) {
                    break;
                }

                Contact contact = this.phoneBook.getContact(number - 1);
                this.showContactInfo(contact);
                this.handleRecord(contact);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private void handleRecord(Contact contact) {
        while (true) {
            System.out.print("[record] Enter action (edit, delete, menu): ");
            String action = this.scanner.nextLine();

            if (action.equals("menu")) {
                System.out.println();
                break;
            }

            switch (action) {
                case "edit" -> this.editContact(contact);
                case "delete" -> this.removeContact(contact);
            }
        }
    }

    private String getName(String message) {
        System.out.print(message);
        return this.scanner.nextLine();
    }

    private String getAddress() {
        System.out.print("Enter the address: ");
        return this.scanner.nextLine();
    }

    private String getSurname() {
        System.out.print("Enter the surname: ");
        String surname = this.scanner.nextLine();
        if (surname.isEmpty()) {
            System.out.println("Bad surname!");
            return "[no data]";
        }

        return surname;
    }

    private String getPhoneNumber() {
        System.out.print("Enter the number: ");
        return this.scanner.nextLine();
    }

    private String getBirthDate() {
        System.out.print("Enter the birth date: ");
        String birthDay = this.scanner.nextLine();
        if (birthDay.isEmpty()) {
            System.out.println("Bad birth date!");
            return "[no data]";
        }

        return birthDay;
    }

    private String getGender() {
        System.out.print("Enter the gender: ");
        String gender = this.scanner.nextLine();
        if (!gender.equals("M") && !gender.equals("F")) {
            System.out.println("Bad gender!");
            return "[no data]";
        }

        return gender;
    }

    private void searchContacts() {
        System.out.print("Enter search query: ");
        String query = scanner.nextLine();
        List<Contact> searchResults = phoneBook.search(query);

        if (searchResults.isEmpty()) {
            System.out.println("No results found.");
            System.out.println();
            return;
        }

        System.out.println("Found " + searchResults.size() + " results: ");
        for (int i = 0; i < searchResults.size(); i++) {
            Contact contact = searchResults.get(i);
            String name;
            if (contact instanceof Person person) {
                name = person.getName() + " " + person.getSurname();
            } else {
                name = contact.getName();
            }
            System.out.println((i + 1) + ". " + name);
        }
        System.out.println();

        while (true) {
            System.out.print("[search] Enter action ([number]), back, again: ");
            String action = this.scanner.nextLine();

            if (action.equals("again")) {
                this.searchContacts();
                break;
            }

            if (action.equals("back")) {
                System.out.println();
                break;
            }

            try {
                int number = Integer.parseInt(action);
                if (number >= 1 && number <= searchResults.size()) {
                    showContactInfo(searchResults.get(number - 1));
                } else {
                    System.out.println("Invalid input. Please try again.");
                }

                Contact contact = searchResults.get(number - 1);
                this.handleRecord(contact);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private void showContactInfo(Contact contact) {
        if (contact instanceof Organization organization) {
            System.out.println(organization);
        } else if (contact instanceof Person person) {
            System.out.println(person);
        }
        System.out.println();
    }
}
