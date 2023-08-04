package Model;

public class BadPhoneException extends Exception {
    String inputString;

    public BadPhoneException(String inputString) {
        this.inputString = inputString;
    }

    @Override
    public String getMessage() {
        return "Ошибка распознавания номера" + inputString + "\n";
    }
}