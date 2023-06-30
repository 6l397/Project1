package org.contactApp.models;

import java.time.LocalDate;
import java.util.Set;
public class Contact {
    private FullName fullName;
    private String song;
    private Set<Role> roles;
    private LocalDate dateBirth;

    public Contact(FullName fullName, String song, Set<Role> roles, LocalDate dateBirth) {
        this.fullName = fullName;
        this.song = song;
        this.roles = roles;
        this.dateBirth = dateBirth;
    }

    public FullName getFullName() {
        return fullName;
    }

    public String getSong() {
        return song;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    @Override
    public String toString() {
        return fullName.getName() + " " + fullName.getSurname() +
                ", улюблена пісня: " + song +
                ", роль: " + getRolesAsString() +
                ", дата народження: " + dateBirth;
    }
    private String getRolesAsString() {
        StringBuilder rolesString = new StringBuilder();
        for (Role role : roles) {
            rolesString.append(roleToString(role)).append(", ");
        }
        rolesString.setLength(rolesString.length() - 2);
        return rolesString.toString();
    }
    private String roleToString(Role role) {
        switch (role) {
            case ROLE_RELATIVE:
                return "родич";
            case ROLE_FELLOW:
                return "знайомий";
            case ROLE_FRIEND:
                return "друг";
            default:
                return "";
        }
    }
}
