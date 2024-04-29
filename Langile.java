package Erronka3;

/**
 * Langile klasea langileen informazioa gordetzeko erabiliko den entitatea da.
 */
public class Langile {
    private int id; // Langilearen identifikazioa
    private String izena; // Langilearen izena
    private String abizena; // Langilearen abizena
    private String emaila; // Langilearen emaila
    private String telefono; // Langilearen telefonoa
    private String kontratazioa; // Langilearen kontratazio data

    /**
     * Langilearen konstruktorea.
     *
     * @param id            Langilearen identifikazioa.
     * @param izena         Langilearen izena.
     * @param abizena       Langilearen abizena.
     * @param emaila        Langilearen emaila.
     * @param telefono      Langilearen telefono zenbakia.
     * @param kontratazioa  Langilearen kontratazio data.
     */
    public Langile(int id, String izena, String abizena, String emaila, String telefono, String kontratazioa) {
        super();
        this.id = id;
        this.izena = izena;
        this.abizena = abizena;
        this.emaila = emaila;
        this.telefono = telefono;
        this.kontratazioa = kontratazioa;
    }

    // Getter eta setter metodoak

    /**
     * Langilearen identifikazioa itzultzen du.
     *
     * @return Langilearen identifikazioa.
     */
    public int getId() {
        return id;
    }

    /**
     * Langilearen identifikazioa ezartzen du.
     *
     * @param id Langilearen identifikazioa.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Langilearen izena itzultzen du.
     *
     * @return Langilearen izena.
     */
    public String getIzena() {
        return izena;
    }

    /**
     * Langilearen izena ezartzen du.
     *
     * @param izena Langilearen izena.
     */
    public void setIzena(String izena) {
        this.izena = izena;
    }

    /**
     * Langilearen abizena itzultzen du.
     *
     * @return Langilearen abizena.
     */
    public String getAbizena() {
        return abizena;
    }

    /**
     * Langilearen abizena ezartzen du.
     *
     * @param abizena Langilearen abizena.
     */
    public void setAbizena(String abizena) {
        this.abizena = abizena;
    }

    /**
     * Langilearen emaila itzultzen du.
     *
     * @return Langilearen emaila.
     */
    public String getEmaila() {
        return emaila;
    }

    /**
     * Langilearen emaila ezartzen du.
     *
     * @param emaila Langilearen emaila.
     */
    public void setEmaila(String emaila) {
        this.emaila = emaila;
    }

    /**
     * Langilearen telefono zenbakia itzultzen du.
     *
     * @return Langilearen telefono zenbakia.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Langilearen telefono zenbakia ezartzen du.
     *
     * @param telefono Langilearen telefono zenbakia.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Langilearen kontratazio data itzultzen du.
     *
     * @return Langilearen kontratazio data.
     */
    public String getKontratazioa() {
        return kontratazioa;
    }

    /**
     * Langilearen kontratazio data ezartzen du.
     *
     * @param kontratazioa Langilearen kontratazio data.
     */
    public void setKontratazioa(String kontratazioa) {
        this.kontratazioa = kontratazioa;
    }
}