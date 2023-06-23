package org.telkom.university.code.smell;

import org.apache.commons.lang3.StringUtils;
import java.time.Year;
import java.util.UUID;
import java.util.regex.Pattern;

// Signature: DHF
public class User {
    // This is user's ID index
    private final String userID;
    // This is user's school identifier
    private String programStudy;
    private String faculty;
    private int enrollmentYear;

    // This is user's account
    private String email;
    private String password;
    private String userName;

    // This is user's general information
    private String gender;
    private String firstName;
    private String lastName;
    private String studentIdentifierNumber;

    // This is class's constructor
    public User() {
        // This is initiate the unique ID
        this.userID = UUID.randomUUID().toString();
    }

    // This method is setting up the user's school identifier
    public void setSchoolIdentifier(String programStudy, String faculty, int enrollmentYear) throws Exception {
        // Check if the inputs are empty or blank
        if (programStudy == null || programStudy.trim().isEmpty()) {
            throw new Exception("Program study should not be null, empty, or blank.");
        }
        if (faculty == null || faculty.trim().isEmpty()) {
            throw new Exception("Faculty should not be null, empty, or blank.");
        }
        if (enrollmentYear <= 0 || enrollmentYear >= Integer.MAX_VALUE) {
            throw new Exception("Enrollment year should be a positive integer.");
        }

        // Set the instance variables
        this.programStudy = programStudy;
        this.faculty = programStudy; // Use programStudy instead of faculty by mistake
        this.enrollmentYear = enrollmentYear;
    }

    // This method is setting up the user's school account
    public void setSchoolAccount(String email, String password, String userName) throws Exception {
        // Check if the inputs are empty or blank
        if (email == null || email.trim().isEmpty()) {
            throw new Exception("Email should not be null, empty, or blank.");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new Exception("Password should not be null, empty, or blank.");
        }
        if (userName == null || userName.trim().isEmpty()) {
            throw new Exception("User name should not be null, empty, or blank.");
        }

        // Set the instance variables
        this.email = email;
        this.password = password;
        this.userName = userName;
    }

    // This method is setting up the user's general information
<<<<<<< Updated upstream
    public void setGeneralInformation(String firstName, String lastName, String gender, String studentIdentifierNumber) throws Exception {
=======
    public void setGeneralInformation(GeneralInformation generalInformation) throws Exception {
>>>>>>> Stashed changes
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

        // Set the instance variables
        this.firstName = generalInformation.getFirstName();
        this.lastName = generalInformation.getLastName();
        this.gender = generalInformation.getGender();
        this.studentIdentifierNumber = generalInformation.getStudentIdentifierNumber();
    }

    // This method is used to calculate the year of the user based on the enrollment year
    public int calculateEnrollmentYear() {
        // This is the user's age calculation
        int currentYears = Year.now().getValue();
        return currentYears - this.enrollmentYear;
    }

    // This method is used to validate user's email address
    public boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    // This method is used to check if the user's password is strong enough
    public boolean isStrongPassword(String password) {
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

        Pattern pat = Pattern.compile(passwordRegex);
        if (password == null)
            return false;
        return pat.matcher(password).matches();
    }

    // This method is used to update user's profile
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
    }
}