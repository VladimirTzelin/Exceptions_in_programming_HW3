import Presenter.Presenter;
import View.ConsoleView;
import View.View;

public class Main {
    public static void main(String[] args) {
        Presenter<View> prog = new Presenter<View>(new ConsoleView());
        prog.start();
    }
}