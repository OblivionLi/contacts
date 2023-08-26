package contacts.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Person extends Contact implements Serializable {
    private String surname;
    private String birthDate;
    private String gender;
    private final LocalDateTime creationDate;

    public Person(String name, String phoneNumber, String surname, String gender, String birthDate) {
        super(name, phoneNumber);
        this.surname = surname;
        this.gender = gender;
        this.birthDate = birthDate;
        this.creationDate = LocalDateTime.now();
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
        super.setLastEditDate(LocalDateTime.now());
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
        super.setLastEditDate(LocalDateTime.now());
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
        super.setLastEditDate(LocalDateTime.now());
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    @Override
    public List<String> getEditableFields() {
        List<String> fields = new ArrayList<>();
        fields.add("name");
        fields.add("surname");
        fields.add("birth");
        fields.add("gender");
        fields.add("number");
        return fields;
    }

    @Override
    public void setField(String field, String value) {
        switch (field) {
            case "name" -> super.setName(value);
            case "surname" -> this.setSurname(value);
            case "birth" -> this.setBirthDate(value);
            case "gender" -> this.setGender(value);
            case "number" -> super.setPhoneNumber(value);
        }
    }

    @Override
    public String toString() {
        return "Name: " + super.getName() + " " + this.surname + "\n"
                + "Surname: " + this.surname + "\n"
                + "Birth date: " + this.birthDate + "\n"
                + "Gender: " + this.gender + "\n"
                + "Number: " + super.getPhoneNumber() + "\n"
                + "Time created: " + this.creationDate + "\n"
                + "Time last edit: " + super.getLastEditDate();
    }
}
