package org.contactApp.converters;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.contactApp.models.Contact;
import java.util.List;

public class GsonConverter implements JsonConverter {
    private final Gson gson;

    public GsonConverter(Gson gson) {
        this.gson = gson;
    }

    @Override
    public String toJson(List<Contact> contactList) {
        return gson.toJson(contactList);
    }

    @Override
    public List<Contact> fromJson(String contactList) {
        return gson.fromJson(contactList, new TypeToken<List<Contact>>() {
        }.getType());
    }
}
