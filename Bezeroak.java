package Erronka3;

/**
 * Bezeroak klaseak bezeroen informazioa gordetzeko erabiliko den klasea da.
 */
public class Bezeroak {
    private int idBezeroak; 
    private String izenaBezeroak; 
    private String abizenaBezeroak; 
    private String helbideaBezeroak; 
    private String emailaBezeroak; 
    
    /**
     * Bezeroak klasearen konstruktorea.
     * Sortu bezero bat izen, abizena, helbide eta email bidez.
     * @param idBezeroak Bezeroaren identifikadorea.
     * @param izenaBezeroak Bezeroaren izena.
     * @param abizenaBezeroak Bezeroaren abizena.
     * @param helbideaBezeroak Bezeroaren helbidea.
     * @param emailaBezeroak Bezeroaren emaila.
     */
    public Bezeroak(int idBezeroak, String izenaBezeroak, String abizenaBezeroak, String helbideaBezeroak, String emailaBezeroak) {
        super(); 
        
        this.idBezeroak = idBezeroak;
        this.izenaBezeroak = izenaBezeroak;
        this.abizenaBezeroak = abizenaBezeroak;
        this.helbideaBezeroak = helbideaBezeroak;
        this.emailaBezeroak = emailaBezeroak;
    }

    /**
     * Bezeroaren identifikadorearen getter metoda.
     * @return Bezeroaren identifikadorea.
     */
    public int getIdBezeroak() {
        return idBezeroak;
    }

    /**
     * Bezeroaren identifikadorearen setter metoda.
     * @param idBezeroak Bezeroaren identifikadorea.
     */
    public void setIdBezeroak(int idBezeroak) {
        this.idBezeroak = idBezeroak;
    }

    /**
     * Bezeroaren izenaren getter metoda.
     * @return Bezeroaren izena.
     */
    public String getIzenaBezeroak() {
        return izenaBezeroak;
    }

    /**
     * Bezeroaren izenaren setter metoda.
     * @param izenaBezeroak Bezeroaren izena.
     */
    public void setIzenaBezeroak(String izenaBezeroak) {
        this.izenaBezeroak = izenaBezeroak;
    }

    /**
     * Bezeroaren abizenaren getter metoda.
     * @return Bezeroaren abizena.
     */
    public String getAbizenaBezeroak() {
        return abizenaBezeroak;
    }

    /**
     * Bezeroaren abizenaren setter metoda.
     * @param abizenaBezeroak Bezeroaren abizena.
     */
    public void setAbizenaBezeroak(String abizenaBezeroak) {
        this.abizenaBezeroak = abizenaBezeroak;
    }

    /**
     * Bezeroaren helbidearen getter metoda.
     * @return Bezeroaren helbidea.
     */
    public String getHelbideaBezeroak() {
        return helbideaBezeroak;
    }

    /**
     * Bezeroaren helbidearen setter metoda.
     * @param helbideaBezeroak Bezeroaren helbidea.
     */
    public void setHelbideaBezeroak(String helbideaBezeroak) {
        this.helbideaBezeroak = helbideaBezeroak;
    }

    /**
     * Bezeroaren emailaren getter metoda.
     * @return Bezeroaren emaila.
     */
    public String getEmailaBezeroak() {
        return emailaBezeroak;
    }

    /**
     * Bezeroaren emailaren setter metoda.
     * @param emailaBezeroak Bezeroaren emaila.
     */
    public void setEmailaBezeroak(String emailaBezeroak) {
        this.emailaBezeroak = emailaBezeroak;
    }
}