package Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DataChecker {
    public static int dataCount = 6;

    private String firstName;
    private String lastName;
    private String surnameName;
    private LocalDate birthDate;
    private Long phoneNumber;
    private Gender gender;

    public DataChecker() {
    }

    public void CheckData(String[] splitedString) throws ParsingDataStringException {
        if (splitedString == null) {
            throw new NullPointerException("Нет данных");
        }

        StringBuilder fullErrorsMessages = new StringBuilder();
        for (String string : splitedString) {
            if (Character.isLetter(string.charAt(0))) {
                if (string.length() == 1) {
                    if (this.gender == null) {
                        try {
                            this.gender = checkGender(string);
                        } catch (BadGenderException e) {
                            fullErrorsMessages.append(e.getMessage());
                        }
                    } else {
                        fullErrorsMessages.append("Ошибка в значении\n");
                    }
                } else {
                    if (this.lastName == null) {
                        try {
                            this.lastName = checkFIO(string);
                        } catch (BadFIOException e) {
                            fullErrorsMessages.append(e.getMessage());
                        }
                    } else if (this.firstName == null) {
                        try {
                            this.firstName = checkFIO(string);
                        } catch (BadFIOException e) {
                            fullErrorsMessages.append(e.getMessage());
                        }
                    } else if (this.surnameName == null) {
                        try {
                            this.surnameName = checkFIO(string);
                        } catch (BadFIOException e) {
                            fullErrorsMessages.append(e.getMessage());
                        }
                    } else {
                        fullErrorsMessages.append("Ошибка распознования Ф.И.О.\n");
                    }
                }
            } else {

                if (string.matches("[0-9]{2}\\.[0-9]{2}\\.[0-9]{4}")) {
                    if (this.birthDate == null) {
                        try {
                            this.birthDate = checkBirthDate(string);
                        } catch (BadBirthDateException e) {
                            fullErrorsMessages.append(e.getMessage());
                        }
                    } else {
                        fullErrorsMessages.append("Ошибка распознования значений Дата рождения'"
                                + this.birthDate + "','" + string + "'\n");
                    }
                } else {
                    if (this.phoneNumber == null) {
                        try {
                            this.phoneNumber = checkPhoneNumber(string);
                        } catch (BadPhoneException e) {
                            fullErrorsMessages.append(e.getMessage());
                        }
                    } else {
                        fullErrorsMessages.append("Ошибка распознавания телефонныго номера'"
                                + this.phoneNumber + "','" + string + "'\n");
                    }
                }

            }
        }
        if (!fullErrorsMessages.isEmpty()) {
            throw new ParsingDataStringException(fullErrorsMessages.toString());
        }
    }

    public String getLastName() {
        return lastName;
    }

    private String checkFIO(String inputString) throws BadFIOException {
        if (inputString.toLowerCase().matches("^[a-zа-яё]*$")) {
            return inputString;
        } else {
            throw new BadFIOException(inputString);
        }
    }

    private long checkPhoneNumber(String inpuString) throws BadPhoneException {
        if (inpuString.length() == 10) {
            try {
                return Long.parseLong(inpuString);
            } catch (NumberFormatException e) {
                throw new BadPhoneException(inpuString);
            }
        } else {
            throw new BadPhoneException(inpuString);
        }
    }

    private Gender checkGender(String inputString) throws BadGenderException {
        try {
            return Gender.valueOf(inputString);
        } catch (IllegalArgumentException e) {
            throw new BadGenderException(inputString);
        }
    }

    private LocalDate checkBirthDate(String inputString) throws BadBirthDateException {
        try {
            return LocalDate.parse(inputString,
                    DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        } catch (DateTimeParseException e) {
            throw new BadBirthDateException(inputString);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<").append(lastName).append(">")
                .append("<").append(firstName).append(">")
                .append("<").append(surnameName).append(">")
                .append("<").append(birthDate.toString()).append(">")
                .append("<").append(phoneNumber).append(">")
                .append("<").append(gender).append(">");
        return sb.toString();
    }

}