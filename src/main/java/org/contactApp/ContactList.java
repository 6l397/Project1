package org.contactApp;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.contactApp.converters.GsonConverter;
import org.contactApp.converters.JsonConverter;
import org.contactApp.converters.LocalDateAdapter;
import org.contactApp.models.Contact;
import org.contactApp.models.ContactsDataSource;
import org.contactApp.models.FullName;
import org.contactApp.models.Role;
import org.contactApp.repositories.AppContactRepository;
import org.contactApp.repositories.ContactRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class ContactList {
    private static void showMenu() {
        System.out.println( "Ось, що вміє мій ContactList. Оберіть команду: \n" +
                """
                0. Вихід
                1. Додати контакт
                2. Оновити контакт
                3. Показати контакти
                4. Видалити контакт
                5. Знайти контакт
                6. Зберегти контакт""");
    }

    private static Role selectRole() {
        System.out.println("Оберіть роль контакта:");
        System.out.println("1. Родич");
        System.out.println("2. Знайомий");
        System.out.println("3. Друг");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                return Role.ROLE_RELATIVE;
            case 2:
                return Role.ROLE_FELLOW;
            case 3:
                return Role.ROLE_FRIEND;
            default:
                System.out.println("Невірний вибір. За замовчуванням встановлена роль знайомого.");
                return Role.ROLE_FELLOW;
        }
    }

    public static void main(String[] args) throws IOException {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
        JsonConverter gsonConverter = new GsonConverter(gson);
        ContactsDataSource contactsDataSource = new ContactsDataSource(gsonConverter);
        List<Contact> contactList = contactsDataSource.showContacts();
        if (contactList == null) {
            contactList = new ArrayList<>();
        }
        ContactRepository appContactRepository = new AppContactRepository(contactList, contactsDataSource);
        Scanner scanner = new Scanner(System.in);
        String choice;

        do {
            showMenu();
            choice = scanner.nextLine();

            switch (choice) {
                case "0":
                    System.out.println("Вихід із програми. ");
                    break;
                case "1":
                    System.out.println("Уведіть ім'я: ");
                    String name = scanner.nextLine();

                    System.out.println("Уведіть прізвище: ");
                    String surname = scanner.nextLine();

                    System.out.println("Уведіть улюблену пісню: ");
                    String song = scanner.nextLine();

                    System.out.println("Уведіть дату народження (рррр-мм-дд):");
                    String dateOfBirthString = scanner.nextLine();
                    LocalDate dateOfBirth = LocalDate.parse(dateOfBirthString);

                    Role role = selectRole();

                    FullName fullName = new FullName(name, surname);
                    Contact contact = new Contact(fullName, song, Set.of(role), dateOfBirth);
                    appContactRepository.addContact(contact);

                    System.out.println("Контакт успішно додано.");
                    break;
                case "2":
                    System.out.println("Уведіть індекс контакту, який хочете змінити: ");
                    int updateIndex = scanner.nextInt();
                    scanner.nextLine();

                    if (updateIndex < 0 || updateIndex >= contactList.size()) {
                        System.out.println("Неправильний індекс. ");
                    } else {
                        Contact oldContact = contactList.get(updateIndex);

                        System.out.println("Уведіть змінене ім'я: ");
                        String newName = scanner.nextLine();

                        System.out.println("Уведіть змінене прізвище: ");
                        String newSurname = scanner.nextLine();

                        System.out.println("Уведіть змінену пісню: ");
                        String newSong = scanner.nextLine();

                        System.out.println("Уведіть змінену дату народження (дд.мм.рррр):");
                        String newDateOfBirthString = scanner.nextLine();
                        LocalDate newDateOfBirth = LocalDate.parse(newDateOfBirthString);

                        Role newRole = selectRole();

                        FullName newFullName = new FullName(newName, newSurname);
                        Contact newContact = new Contact(newFullName, newSong, Set.of(newRole), newDateOfBirth);
                        appContactRepository.updateContact(oldContact, newContact);

                        System.out.println("Контакт успішно зміненно. ");
                    }
                    break;
                case "3":
                    System.out.println("Контакти:");
                    for (int i = 0; i < contactList.size(); i++) {
                        System.out.println(i + " - " + contactList.get(i));
                    }
                    break;
                case "4":
                    System.out.println("Уведіть індекс контакту, щоб видалити: ");
                    int deleteIndex = scanner.nextInt();
                    scanner.nextLine();

                    if (deleteIndex < 0 || deleteIndex >= contactList.size()) {
                        System.out.println("Неправильний індекс.");
                    } else {
                        Contact contactToDelete = contactList.get(deleteIndex);
                        appContactRepository.deleteContact(contactToDelete);
                        System.out.println("Контакт успішно видалено.");
                    }
                    break;
                case "5":
                    System.out.println("Уведіть пошуковий запит:");
                    String searchQuery = scanner.nextLine();
                    List<Contact> searchResults = appContactRepository.searchContact(searchQuery);

                    System.out.println("Результат пошуку:");
                    for (Contact result : searchResults) {
                        System.out.println(result);
                    }
                    break;
                case "6":
                    appContactRepository.saveContacts();
                    System.out.println("Контакти збережено успішно.");
                    break;
                default:
                    System.out.println("Спробуйте ще раз.");
                    break;
            }
        } while (!choice.equals("0"));

        scanner.close();
    }
}
