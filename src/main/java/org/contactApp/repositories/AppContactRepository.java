package org.contactApp.repositories;

import org.contactApp.models.Contact;
import org.contactApp.models.ContactsDataSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class AppContactRepository implements ContactRepository {

    private final ContactsDataSource contactsDataSource;
    private List<Contact> contactList;

    public AppContactRepository(List<Contact> contactList, ContactsDataSource contactsDataSource) {
        this.contactList = contactList != null ? contactList : new ArrayList<>();
        this.contactsDataSource = contactsDataSource;
    }

    @Override
    public void addContact(Contact contact) {
        contactList.add(contact);
    }

    @Override
    public void updateContact(Contact oldContact, Contact newContact) {
        int index = contactList.indexOf(oldContact);
        contactList.set(index, newContact);
    }

    @Override
    public void showContact(Contact contact) throws IOException {
        contactsDataSource.showContacts();
    }

    @Override
    public void deleteContact(Contact contact) {
        contactList.remove(contact);
    }

    @Override
    public List<Contact> searchContact(String search) {
        List<Contact> searchResults = new ArrayList<>();
        for (Contact contact : contactList) {
            if (contact.getFullName().getName().contains(search) ||
                    contact.getFullName().getSurname().contains(search) ||
                    contact.getSong().contains(search)) {
                searchResults.add(contact);
            }
        }
        return searchResults;
    }

    @Override
    public void saveContacts() {
        contactsDataSource.writeContact(contactList);
    }
}
