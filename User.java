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


    public void setGeneralInformation(String firstName, String lastName, String gender, String studentIdentifierNumber) throws Exception {
    public void setGeneralInformation(GeneralInformation generalInformation) throws Exception {
        // Check if the inputs are empty or blank
        if (generalInformation.getFirstName() == null || generalInformation.getFirstName().trim().isEmpty()) {
            throw new Exception("First name should not be null, empty, or blank.");
        }
        if (generalInformation.getLastName() == null || generalInformation.getLastName().trim().isEmpty()) {
            throw new Exception("Last name should not be null, empty, or blank.");
        }

        if (generalInformation.getGender() == null) {
            throw new Exception("Gender should not be null.");
        }

        if (generalInformation.getStudentIdentifierNumber() == null || generalInformation.getStudentIdentifierNumber().trim().isEmpty()) {
            throw new Exception("Student identifier number should not be null, empty, or blank.");
        }

        this.firstName = generalInformation.getFirstName();
        this.lastName = generalInformation.getLastName();
        this.gender = generalInformation.getGender();
        this.studentIdentifierNumber = generalInformation.getStudentIdentifierNumber();

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

    public void updateProfile(String firstName, String lastName, String gender, String studentIdentifierNumber,
                              String programStudy, String faculty, int enrollmentYear, String email,
                              String password, String userName) throws Exception {
        validateStudentIdentifierNumber(studentIdentifierNumber);
        validateEmailAndPassword(email, password);

        setSchoolIdentifier(programStudy, faculty, enrollmentYear);
        setSchoolAccount(email, password, userName);
        setGeneralInformation(firstName, lastName, gender, studentIdentifierNumber);

        int calculateYear = calculateEnrollmentYear();

        printUpdateStatus();
    }

    private void validateStudentIdentifierNumber(String studentIdentifierNumber) throws Exception {
        if (studentIdentifierNumber.length() != 10 || !StringUtils.isNumeric(studentIdentifierNumber)) {
            throw new Exception("Input is not valid.");
        }
    }

    private void validateEmailAndPassword(String email, String password) throws Exception {
        boolean isValidEmail = isValidEmail(email);
        boolean isStrongPassword = isStrongPassword(password);

        if (!isValidEmail && !isStrongPassword) {
            throw new Exception("THIS IS JOKE RIGHT? PLEASE USE VALID EMAIL AND STRONG PASSWORD");
        } else if (!isValidEmail) {
            throw new Exception("PLEASE CHECK YOUR EMAIL");
        } else if (!isStrongPassword) {
            throw new Exception("PLEASE USE BETTER PASSWORD");
        }
    }

    private void printUpdateStatus() {
        System.out.println("UPDATE COMPLETE!");
      
    public void updateProfile(Profile profile) {
        setSchoolIdentifier(profile.getSchoolIdentifier());
        setSchoolAccount(profile.getSchoolAccount());
        setGeneralInformation(profile.getGeneralInformation());
        printProfileStatus(profile);
    }

    private void printProfileStatus(Profile profile) {
        int calculatedYear = calculateEnrollmentYear();
        String emailStatus = EmailValidator.getEmailStatus(profile.getSchoolAccount().getEmail());
        String passwordStatus = PasswordValidator.getPasswordStatus(profile.getSchoolAccount().getPassword());
        String emailStatus = EmailValidator.getEmailStatus(profile.getSchoolAccount().getEmail());
        String passwordStatus = PasswordValidator.getPasswordStatus(profile.getSchoolAccount().getPassword());

        Printer.printStatus(emailStatus, passwordStatus);
    }
}
