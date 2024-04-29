package Erronka3;

/**
 * Klase hau ProdEguneratu izenekoa da, produktuaren informazioa gordetzeko erabiltzen da.
 */
public class ProdEguneratu {

    private String iDProd; // Produktuaren identifikatzailea
    private String izenaProd; // Produktuaren izena
    private String deskribapenaProd; // Produktuaren deskribapena
    private String balioaProd; // Produktuaren balioa
    private String salneurriaProd; // Produktuaren salneurria
    private String kategoriaProd; // Produktuaren kategoria

    /**
     * ProdEguneratu klasearen eraikitzailea. Produktuaren identifikatzailea, izena, deskribapena, balioa,
     * salneurria eta kategoria emanik, ProdEguneratu objektua sortzen du.
     * 
     * @param iDProd Produktuaren identifikatzailea
     * @param izenaProd Produktuaren izena
     * @param deskribapenaProd Produktuaren deskribapena
     * @param balioaProd Produktuaren balioa
     * @param salneurriaProd Produktuaren salneurria
     * @param kategoriaProd Produktuaren kategoria
     */
    public ProdEguneratu(String iDProd, String izenaProd, String deskribapenaProd, String balioaProd, String salneurriaProd, String kategoriaProd) {
        super();
        
        this.iDProd = iDProd;
        this.izenaProd = izenaProd;
        this.deskribapenaProd = deskribapenaProd;
        this.balioaProd = balioaProd;
        this.salneurriaProd = salneurriaProd;
        this.kategoriaProd = kategoriaProd;
    }

    /**
     * Produktuaren identifikatzailea itzultzen du.
     * 
     * @return Produktuaren identifikatzailea
     */
    public String getiDProd() {
        return iDProd;
    }

    /**
     * Produktuaren identifikatzailea ezartzen du.
     * 
     * @param iDProd Produktuaren identifikatzailea
     */
    public void setiDProd(String iDProd) {
        this.iDProd = iDProd;
    }

    /**
     * Produktuaren izena itzultzen du.
     * 
     * @return Produktuaren izena
     */
    public String getIzenaProd() {
        return izenaProd;
    }

    /**
     * Produktuaren izena ezartzen du.
     * 
     * @param izenaProd Produktuaren izena
     */
    public void setIzenaProd(String izenaProd) {
        this.izenaProd = izenaProd;
    }

    /**
     * Produktuaren deskribapena itzultzen du.
     * 
     * @return Produktuaren deskribapena
     */
    public String getDeskribapenaProd() {
        return deskribapenaProd;
    }

    /**
     * Produktuaren deskribapena ezartzen du.
     * 
     * @param deskribapenaProd Produktuaren deskribapena
     */
    public void setDeskribapenaProd(String deskribapenaProd) {
        this.deskribapenaProd = deskribapenaProd;
    }

    /**
     * Produktuaren balioa itzultzen du.
     * 
     * @return Produktuaren balioa
     */
    public String getBalioaProd() {
        return balioaProd;
    }

    /**
     * Produktuaren balioa ezartzen du.
     * 
     * @param balioaProd Produktuaren balioa
     */
    public void setBalioaProd(String balioaProd) {
        this.balioaProd = balioaProd;
    }

    /**
     * Produktuaren salneurria itzultzen du.
     * 
     * @return Produktuaren salneurria
     */
    public String getSalneurriaProd() {
        return salneurriaProd;
    }

    /**
     * Produktuaren salneurria ezartzen du.
     * 
     * @param salneurriaProd Produktuaren salneurria
     */
    public void setSalneurriaProd(String salneurriaProd) {
        this.salneurriaProd = salneurriaProd;
    }

    /**
     * Produktuaren kategoria itzultzen du.
     * 
     * @return Produktuaren kategoria
     */
    public String getKategoriaProd() {
        return kategoriaProd;
    }

    /**
     * Produktuaren kategoria ezartzen du.
     * 
     * @param kategoriaProd Produktuaren kategoria
     */
    public void setKategoriaProd(String kategoriaProd) {
        this.kategoriaProd = kategoriaProd;
    }
}