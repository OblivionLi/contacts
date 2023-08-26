package contacts.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PhoneBook implements Serializable {
    private List<Contact> contactList;

    public PhoneBook() {
        this.contactList = new ArrayList<>();
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public void addContact(Contact contact) {
        this.contactList.add(contact);
    }

    public void removeContact(Contact contact) {
        this.contactList.remove(contact);
    }

    public Contact getContact(int index) {
        return this.contactList.get(index);
    }

    public void saveToFile(String fileName) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(this.contactList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile(String fileName) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            this.contactList = (List<Contact>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Contact> search(String query) {
        List<Contact> results = new ArrayList<>();
        for (Contact contact : this.contactList) {
            if (this.matchesSearchQuery(contact, query)) {
                results.add(contact);
            }
        }
        return results;
    }

    private boolean matchesSearchQuery(Contact contact, String query) {
        List<String> editableFields = contact.getEditableFields();
        StringBuilder contactData = new StringBuilder();

        for (String field : editableFields) {
            String fieldValue = getFieldValue(contact, field).toLowerCase();
            contactData.append(fieldValue);
        }

        return contactData.toString().contains(query.toLowerCase());
    }

    private String getFieldValue(Contact contact, String field) {
        switch (field) {
            case "name" -> {
                return contact.getName();
            }
            case "surname" -> {
                if (contact instanceof Person) {
                    return ((Person) contact).getSurname();
                }
            }
            case "birth" -> {
                if (contact instanceof Person) {
                    return ((Person) contact).getBirthDate();
                }
            }
            case "gender" -> {
                if (contact instanceof Person) {
                    return ((Person) contact).getGender();
                }
            }
            case "number" -> {
                return contact.getPhoneNumber();
            }
            case "address" -> {
                if (contact instanceof Organization) {
                    return ((Organization) contact).getAddress();
                }
            }
        }
        return "";
    }
}
