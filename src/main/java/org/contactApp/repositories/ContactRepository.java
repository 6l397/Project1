package org.contactApp.repositories;

import org.contactApp.models.Contact;
import java.io.IOException;
import java.util.List;

public interface ContactRepository {
    void addContact (Contact contact);
    void updateContact (Contact oldContact, Contact newContact);
    void showContact (Contact contact) throws IOException;
    void deleteContact (Contact contact);
    List<Contact> searchContact (String wanted);
    void saveContacts ();
}
