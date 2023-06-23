package org.contactApp.repositories;

import org.contactApp.models.Contact;

import java.util.List;

public interface ContactRepository {
    void addContact (Contact contact);
    void updateContact (Contact contact);
    void readContact (Contact contact);
    void deleteContact (Contact contact);
    List<Contact> searchContact (String wanted);
    void safeContacts ();
}
