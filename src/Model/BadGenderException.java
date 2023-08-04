package Model;

public class BadGenderException extends Exception {
    String inputString;

    public BadGenderException(String inputString) {
        this.inputString = inputString;
    }

    @Override
    public String getMessage() {
        return "Неправильно указан пол (используйте латинские буквы f или m) \n";
    }
}