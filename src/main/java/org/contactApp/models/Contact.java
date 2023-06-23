package org.contactApp.models;

import java.time.LocalDate;

public record Contact (FullName fullName, String number, String song, LocalDate dateOfBirth) {

}
