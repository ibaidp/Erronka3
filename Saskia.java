package Erronka3;

/**
 * Klase hau Saskia izenekoa da, produktu bakoitzaren informazioa gordetzeko erabiltzen da.
 */
public class Saskia {
    private String prodIzena; // Produktuaren izena
    private String prodBalioa; // Produktuaren balioa
    private String prodId; // Produktuaren identifikatzailea

    /**
     * Saskia klasearen eraikitzailea. Produktuaren izena, balioa eta identifikatzailea 
     * emanik, Saskia objektua sortzen du.
     * 
     * @param prodIzena Produktuaren izena
     * @param prodBalioa Produktuaren balioa
     * @param prodId Produktuaren identifikatzailea
     */
    public Saskia(String prodIzena, String prodBalioa, String prodId) {
        super();
        this.prodIzena = prodIzena;
        this.prodBalioa = prodBalioa;
        this.prodId = prodId;
    }

    /**
     * Produktuaren izena itzultzen du.
     * 
     * @return Produktuaren izena
     */
    public String getProdIzena() {
        return prodIzena;
    }

    /**
     * Produktuaren izena ezartzen du.
     * 
     * @param prodIzena Produktuaren izena
     */
    public void setProdIzena(String prodIzena) {
        this.prodIzena = prodIzena;
    }

    /**
     * Produktuaren balioa itzultzen du.
     * 
     * @return Produktuaren balioa
     */
    public String getProdBalioa() {
        return prodBalioa;
    }

    /**
     * Produktuaren balioa ezartzen du.
     * 
     * @param prodBalioa Produktuaren balioa
     */
    public void setProdBalioa(String prodBalioa) {
        this.prodBalioa = prodBalioa;
    }

    /**
     * Produktuaren identifikatzailea itzultzen du.
     * 
     * @return Produktuaren identifikatzailea
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * Produktuaren identifikatzailea ezartzen du.
     * 
     * @param prodId Produktuaren identifikatzailea
     */
    public void setProdId(String prodId) {
        this.prodId = prodId;
    }
}