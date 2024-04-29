package Erronka3;

/**
 * Main klasea aplikazioaren sarrera punktua da.
 * Hauetan aplikazioa exekutatzen da, eta login interfazea bistaratzen da hasieran.
 */
public class Main {
    /**
     * Main metodoa aplikazioa exekutatzeko erabiliko den sarrera puntu nagusia da.
     * @param args Komando-lerro argumentuak, aplikazioa exekutatzerakoan erabiliko direnak.
     */
    public static void main(String[] args) {
        // Aplikazioa hasieratu login interfazea bistaratzeko
        Login login = new Login();
        login.setVisible(true);
    }
}
