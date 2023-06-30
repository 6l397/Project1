package org.contactApp.models;

import org.contactApp.converters.JsonConverter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ContactsDataSource {
    private final JsonConverter jsonConverter;
    private final String pathFile = "ContactList.json";

    public ContactsDataSource(JsonConverter jsonConverter) {
        this.jsonConverter = jsonConverter;
    }

    public List<Contact> showContacts() throws IOException {
        if (!Files.exists(Path.of(pathFile))) {
            createFile();
        }

        String jsonContacts = Files.readString(Path.of(pathFile));
        return jsonConverter.fromJson(jsonContacts);
    }

    public void writeContact(List<Contact> contactList) {
        String jsonContacts = jsonConverter.toJson(contactList);
        try {
            Files.writeString(Path.of(pathFile), jsonContacts);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createFile() throws IOException {
        Files.createFile(Path.of(pathFile));
    }
}
