package org.telkom.university.code.smell;

import java.time.Year;
import java.util.UUID;

public class User {
    private final String userID;
    private SchoolIdentifier schoolIdentifier;
    private SchoolAccount schoolAccount;
    private GeneralInformation generalInformation;

    public User() {
        this.userID = UUID.randomUUID().toString();
    }

    public void setSchoolIdentifier(SchoolIdentifier schoolIdentifier) {
        this.schoolIdentifier = schoolIdentifier;
    }

    public void setSchoolAccount(SchoolAccount schoolAccount) {
        this.schoolAccount = schoolAccount;
    }

    public void setGeneralInformation(GeneralInformation generalInformation) {
        this.generalInformation = generalInformation;
    }

    public int calculateEnrollmentYear() {
        int currentYear = Year.now().getValue();
        return currentYear - schoolIdentifier.getEnrollmentYear();
    }

    public boolean isValidEmail(String email) {
        return EmailValidator.isValidEmail(email);
    }

    public boolean isStrongPassword(String password) {
        return PasswordValidator.isStrongPassword(password);
    }

    public void updateProfile(Profile profile) {
        setSchoolIdentifier(profile.getSchoolIdentifier());
        setSchoolAccount(profile.getSchoolAccount());
        setGeneralInformation(profile.getGeneralInformation());
        printProfileStatus();
    }

    private void printProfileStatus() {
        int calculatedYear = calculateEnrollmentYear();
        String emailStatus = EmailValidator.getEmailStatus(profile.getSchoolAccount().getEmail());
        String passwordStatus = PasswordValidator.getPasswordStatus(profile.getSchoolAccount().getPassword());
        Printer.printStatus(emailStatus, passwordStatus);
    }

    public enum Gender {
        MALE,
        FEMALE,
        OTHER
    }
}
