package org.telkom.university.code.smell;

import java.time.Year;
import java.util.UUID;

public class User {
    private final String userID;
    private SchoolIdentifier schoolIdentifier;
    private SchoolAccount schoolAccount;
    private GeneralInformation generalInformation;

    public User() {
        this.userID = UUID.randomUUID().toString(); // Bikin ID unik dengan UUID
    }

    public void setSchoolIdentifier(SchoolIdentifier schoolIdentifier) {
        this.schoolIdentifier = schoolIdentifier; 
    }

    public void setSchoolAccount(SchoolAccount schoolAccount) {
        this.schoolAccount = schoolAccount; 
    }

    // set generalInformation sama objek GeneralInformation
    public void setGeneralInformation(GeneralInformation generalInformation) {
        this.generalInformation = generalInformation; 
    }

    // funsi hitung tahun masuk berdasarkan tahun sekarang dan EnrollmentYear
    public int calculateEnrollmentYear() {
        int currentYear = Year.now().getValue();
        return currentYear - schoolIdentifier.getEnrollmentYear();
    }

    // ngecek apakah email valid menggunakan EmailValidator
    public boolean isValidEmail(String email) {
        return EmailValidator.isValidEmail(email);
    }

    // cek apakah password kuat menggunakan PasswordValidator
    public boolean isStrongPassword(String password) {
        return PasswordValidator.isStrongPassword(password);
    }

    // update profil dengan parameter yang dikasih
    public void updateProfile(String firstName, String lastName, String gender, String studentIdentifierNumber,
                              String programStudy, String faculty, int enrollmentYear, String email,
                              String password, String userName) throws Exception {
        validateStudentIdentifierNumber(studentIdentifierNumber); 
        validateEmailAndPassword(email, password); // Validasi email dan password

        setSchoolIdentifier(programStudy, faculty, enrollmentYear); 
        setSchoolAccount(email, password, userName); 
        setGeneralInformation(firstName, lastName, gender, studentIdentifierNumber); 

        int calculateYear = calculateEnrollmentYear();

        printUpdateStatus(); // Print status update
    }

    // Validasi studentIdentifierNumber
    private void validateStudentIdentifierNumber(String studentIdentifierNumber) throws Exception {
        if (studentIdentifierNumber.length() != 10 || !StringUtils.isNumeric(studentIdentifierNumber)) {
            throw new Exception("Input is not valid."); // Jika tidak valid, lempar exception
        }
    }

    // Validasi email dan password
    private void validateEmailAndPassword(String email, String password) throws Exception {
        boolean isValidEmail = isValidEmail(email);
        boolean isStrongPassword = isStrongPassword(password);

        if (!isValidEmail && !isStrongPassword) {
            throw new Exception("THIS IS JOKE RIGHT? PLEASE USE VALID EMAIL AND STRONG PASSWORD"); // Email dan password tidak valid
        } else if (!isValidEmail) {
            throw new Exception("PLEASE CHECK YOUR EMAIL"); // Email ga valid
        } else if (!isStrongPassword) {
            throw new Exception("PLEASE USE BETTER PASSWORD"); // Password ga kuat
        }
    }

    // Print status update
    private void printUpdateStatus() {
        System.out.println("UPDATE COMPLETE!"); // Update selesai
    }

    // Update profil dengan objek Profile
    public void updateProfile(Profile profile) {
        setSchoolIdentifier(profile.getSchoolIdentifier()); 
        setSchoolAccount(profile.getSchoolAccount()); 
        setGeneralInformation(profile.getGeneralInformation()); 
        printProfileStatus(profile); // Print status profil
    }

    // Print status profil
    private void printProfileStatus(Profile profile) {
        int calculatedYear = calculateEnrollmentYear();
        String emailStatus = EmailValidator.getEmailStatus(profile.getSchoolAccount().getEmail()); // Status email
        String passwordStatus = PasswordValidator.getPasswordStatus(profile.getSchoolAccount().getPassword()); // Status password

        Printer.printStatus(emailStatus, passwordStatus); // Print status
    }
}
