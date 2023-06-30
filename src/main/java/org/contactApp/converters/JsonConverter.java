package org.contactApp.converters;

import org.contactApp.models.Contact;

import java.util.List;

public interface JsonConverter {
    String toJson (List<Contact> contactList);
    List<Contact> fromJson (String contactList);
}
