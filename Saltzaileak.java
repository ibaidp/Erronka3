package Erronka3;

/**
 * Klase hau Saltzaileak izenekoa da, saltzaileen informazioa gordetzeko erabiltzen da.
 */
public class Saltzaileak {
    private int idSaltzaile; // Saltzailearen identifikatzailea
    private String erabiltzaileSaltzaile; // Saltzailearen erabiltzailea
    private String pasahitzaSaltzaile; // Saltzailearen pasahitza

    /**
     * Saltzaileak klasearen eraikitzailea. Saltzailearen identifikatzailea, erabiltzailea eta pasahitza 
     * emanik, Saltzaileak objektua sortzen du.
     * 
     * @param idSaltzaile Saltzailearen identifikatzailea
     * @param erabiltzaileSaltzaile Saltzailearen erabiltzailea
     * @param pasahitzaSaltzaile Saltzailearen pasahitza
     */
    public Saltzaileak(int idSaltzaile, String erabiltzaileSaltzaile, String pasahitzaSaltzaile) {
        super();
        this.idSaltzaile = idSaltzaile;
        this.erabiltzaileSaltzaile = erabiltzaileSaltzaile;
        this.pasahitzaSaltzaile = pasahitzaSaltzaile;
    }

    /**
     * Saltzailearen identifikatzailea itzultzen du.
     * 
     * @return Saltzailearen identifikatzailea
     */
    public int getIdSaltzaile() {
        return idSaltzaile;
    }

    /**
     * Saltzailearen identifikatzailea ezartzen du.
     * 
     * @param idSaltzaile Saltzailearen identifikatzailea
     */
    public void setIdSaltzaile(int idSaltzaile) {
        this.idSaltzaile = idSaltzaile;
    }

    /**
     * Saltzailearen erabiltzailea itzultzen du.
     * 
     * @return Saltzailearen erabiltzailea
     */
    public String getErabiltzaileSaltzaile() {
        return erabiltzaileSaltzaile;
    }

    /**
     * Saltzailearen erabiltzailea ezartzen du.
     * 
     * @param erabiltzaileSaltzaile Saltzailearen erabiltzailea
     */
    public void setErabiltzaileSaltzaile(String erabiltzaileSaltzaile) {
        this.erabiltzaileSaltzaile = erabiltzaileSaltzaile;
    }

    /**
     * Saltzailearen pasahitza itzultzen du.
     * 
     * @return Saltzailearen pasahitza
     */
    public String getPasahitzaSaltzaile() {
        return pasahitzaSaltzaile;
    }

    /**
     * Saltzailearen pasahitza ezartzen du.
     * 
     * @param pasahitzaSaltzaile Saltzailearen pasahitza
     */
    public void setPasahitzaSaltzaile(String pasahitzaSaltzaile) {
        this.pasahitzaSaltzaile = pasahitzaSaltzaile;
    }
}