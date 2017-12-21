
import control.Handler;
import utils.FileReader;


public final class Main {
    /**
     *  Clasa principala in care se va realiza initializarea sursei de output,
     *  dupa care va fi pasata mai departe classelor de control, unde vor fi
     *  extrase datele si executate cerintele conform acestora.
     */

    private Main() { }

    public static void main(final String[] args) {
        try {
            FileReader fs = new FileReader(args[0]);
            Handler handy = new Handler(fs);
            handy.buildPNG();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
