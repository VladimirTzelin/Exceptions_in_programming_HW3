package Model;

public class BadFIOException extends Exception {

    String inputString;

    public BadFIOException(String inputString) {
        this.inputString = inputString;
    }

    @Override
    public String getMessage() {
        return "Неправильный формат ФИО '" + inputString + "'. Ф.И.О состоят из букв.\n";
    }
}