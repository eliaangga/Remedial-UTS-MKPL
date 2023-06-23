package org.telkom.university.code.smell;

import org.apache.commons.lang3.StringUtils;
import java.time.Year;
import java.util.UUID;
import java.util.regex.Pattern;

public class User {
    private final String userID;
    private SchoolIdentifier schoolIdentifier;
    private SchoolAccount schoolAccount;
    private GeneralInformation generalInformation;

    public User() {
        this.userID = UUID.randomUUID().toString();
    }

    public void setSchoolIdentifier(String programStudy, String faculty, int enrollmentYear) throws Exception {
        schoolIdentifier = new SchoolIdentifier(programStudy, faculty, enrollmentYear);
    }

    public void setSchoolAccount(String email, String password, String userName) throws Exception {
        schoolAccount = new SchoolAccount(email, password, userName);
    }

    public void setGeneralInformation(String firstName, String lastName, Gender gender, String studentIdentifierNumber) throws Exception {
        generalInformation = new GeneralInformation(firstName, lastName, gender, studentIdentifierNumber);
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

    public void updateProfile(String firstName, String lastName, Gender gender, String studentIdentifierNumber,
                              String programStudy, String faculty, int enrollmentYear, String email,
                              String password, String userName) throws Exception {

        setSchoolIdentifier(programStudy, faculty, enrollmentYear);
        setSchoolAccount(email, password, userName);
        setGeneralInformation(firstName, lastName, gender, studentIdentifierNumber);

        int calculatedYear = calculateEnrollmentYear();

        String emailStatus = EmailValidator.getEmailStatus(email);
        String passwordStatus = PasswordValidator.getPasswordStatus(password);


        Printer.printStatus(emailStatus, passwordStatus);
    }

    public enum Gender {
        MALE,
        FEMALE,
        OTHER
    }
}
