package contacts.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Organization extends Contact implements Serializable {
    private String address;
    private final LocalDateTime creationDate;

    public Organization(String name, String phoneNumber, String address) {
        super(name, phoneNumber);
        this.address = address;
        this.creationDate = LocalDateTime.now();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        super.setLastEditDate(LocalDateTime.now());
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    @Override
    public List<String> getEditableFields() {
        List<String> fields = new ArrayList<>();
        fields.add("name");
        fields.add("address");
        fields.add("number");
        return fields;
    }

    @Override
    public void setField(String field, String value) {
        switch (field) {
            case "name" -> super.setName(value);
            case "address" -> this.setAddress(value);
            case "number" -> super.setPhoneNumber(value);
        }
    }

    @Override
    public String toString() {
        return "Organization name: " + super.getName() + "\n"
                + "Address: " + this.address + "\n"
                + "Number: " + super.getPhoneNumber() + "\n"
                + "Time created: " + this.creationDate + "\n"
                + "Time last edit: " + super.getLastEditDate();
    }
}
