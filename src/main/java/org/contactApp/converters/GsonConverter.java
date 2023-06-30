package org.contactApp.converters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.contactApp.models.Contact;

import java.time.LocalDate;
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

    public static Gson createGson() {
        return new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
    }
}
