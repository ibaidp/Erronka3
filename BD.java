package Erronka3;

import java.sql.Statement;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class BD {
    // Klaseko atributuak
    private String url; // Datu-basearen URL-a
    private String user; // Datu-basearen erabiltzailea
    private String pass; // Datu-basearen pasahitza
    private Bezeroak[] bezeroak = new Bezeroak[0]; // Bezero-objetuak gordetzeko array-a
    private Saltzaileak[] saltzaileak = new Saltzaileak[0]; // Saltzaile-objetuak gordetzeko array-a
    private Langile[] langile = new Langile[0]; // Langile-objetuak gordetzeko array-a
    private ProdEguneratu[] produktuak = new ProdEguneratu[0]; // Produktu-objetuak gordetzeko array-a
    private Saskia[] saskia = new Saskia[0]; // Saskia-objetuak gordetzeko array-a
    private Connection conn; // Datu-basearekin konektatutako konexioa

    // Eraikitzailea
    public BD() {
        this.url = "jdbc:oracle:thin:@localhost:1521/xe"; 
        this.user = "ERRONKA";
        this.pass = "123";
    }   

    // Konexioa sortzeko metodoa
    public Connection konexioa() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, pass);
            return conn;
        } catch (Exception e) {
            System.out.println("Konexio errorea: " + e);
        }
        return conn;
    }

    // Bezeroen datuak datu-baseatik kargatzeko metodoa
    public void bezeroakKargatu() throws SQLException {
        Connection conn = konexioa();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM BEZERO ORDER BY IZENA");
        while (rs.next()) {        
            Bezeroak b = new Bezeroak (rs.getInt("id"), rs.getString("izena"), rs.getString("abizena"), rs.getString("helbidea"), rs.getString("emaila"));               
            bezeroak = Arrays.copyOf(bezeroak, bezeroak.length + 1);
            bezeroak[bezeroak.length - 1] = b;
        }
        rs.close();
        stmt.close(); 
        conn.close();
    }

    // Produktuen datuak datu-baseatik kargatzeko metodoa
    public void produktuakKargatu() throws SQLException {
        Connection conn = konexioa();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUKTU ORDER BY IZENA");
        while (rs.next()) {        
        	ProdEguneratu p = new ProdEguneratu(rs.getString("id"), rs.getString("izena"), rs.getString("deskribapena"), rs.getString("balioa"), rs.getString("salneurria"), rs.getString("id_kategoria"));               
            produktuak = Arrays.copyOf(produktuak, produktuak.length + 1);
            produktuak[produktuak.length - 1] = p;
        }
        rs.close();
        stmt.close(); 
        conn.close();
    }

    // Saltzaileen datuak datu-baseatik kargatzeko metodoa
    public void saltzaileakKargatu() throws SQLException {
        Connection conn = konexioa();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM SALTZAILE");
        while (rs.next()) {         
            Saltzaileak s = new Saltzaileak (rs.getInt("id"), rs.getString("erabiltzailea"), rs.getString("pasahitza"));               
            saltzaileak = Arrays.copyOf(saltzaileak, saltzaileak.length + 1);
            saltzaileak[saltzaileak.length - 1] = s;
        }
        rs.close();
        stmt.close(); 
        conn.close();
    }

    // Langileen datuak datu-baseatik kargatzeko metodoa
    public void langileakKargatu() throws SQLException {
        Connection conn = konexioa();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM LANGILE ORDER BY IZENA");                      
        while (rs.next()) {
            Langile l = new Langile (rs.getInt("id"), rs.getString("izena"), rs.getString("abizena"), rs.getString("emaila"), rs.getString("telefonoa"), rs.getString("kontratazio_data"));               
            langile = Arrays.copyOf(langile, langile.length + 1);
            langile[langile.length - 1] = l;
        }
        rs.close();
        stmt.close(); 
        conn.close();
    }

    // Saskien datuak datu-baseatik kargatzeko metodoa
    public void saskiaKargatu() throws SQLException {
        Connection conn = konexioa();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT IZENA, BALIOA, ID FROM PRODUKTU ORDER BY IZENA");                      
        while (rs.next()) {
            Saskia s = new Saskia (rs.getString("IZENA"), rs.getString("BALIOA"), rs.getString("ID"));               
            saskia = Arrays.copyOf(saskia, saskia.length + 1);
            saskia[saskia.length - 1] = s;
        }
        rs.close();
        stmt.close(); 
        conn.close();
    }

    public void konektatu() throws SQLException {
        conn = DriverManager.getConnection(url, user, pass);
    }

    /**
     * Produktu berria datu-basean gehitzeko metodoa.
     * @param produktuIzena Produktuaren izena
     * @param deskribapena Produktuaren deskribapena
     * @param prezioa Produktuaren prezioa
     * @param salneurria Produktuaren salneurria
     * @param kategoria Produktuaren kategoria
     * @throws SQLException
     */
    public void produktuaGehitu(String produktuIzena, String deskribapena, String prezioa, String salneurria, String kategoria) throws SQLException {
        String produktuId = getMaxIdprod();
        String sql = "INSERT INTO PRODUKTU (ID, IZENA, DESKRIBAPENA, BALIOA, SALNEURRIA, ID_KATEGORIA) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = this.conn.prepareStatement(sql)) {
            pstmt.setString(1, produktuId);
            pstmt.setString(2, produktuIzena);
            pstmt.setString(3, deskribapena);
            pstmt.setString(4, prezioa);
            pstmt.setString(5, salneurria);
            pstmt.setString(6, kategoria);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    /**
     * Erabiltzaile berria datu-basean gordeko duen metodoa.
     * @param izena Erabiltzailearen izena
     * @param abizena Erabiltzailearen abizena
     * @param helbidea Erabiltzailearen helbidea
     * @param emaila Erabiltzailearen emaila
     * @throws SQLException
     */
    public void erabiltzaileaGorde(String izena, String abizena, String helbidea, String emaila) throws SQLException {
        String bezeroId = getMaxId();
        String insert = "INSERT INTO BEZERO (ID, IZENA, ABIZENA, HELBIDEA, EMAILA) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = this.conn.prepareStatement(insert)) {
            pstmt.setString(1, bezeroId);
            pstmt.setString(2, izena);
            pstmt.setString(3, abizena);
            pstmt.setString(4, helbidea);
            pstmt.setString(5, emaila);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    /**
     * Bezeroen identifikadore maximoa lortzen duen metodoa.
     * @return Bezeroen identifikadore maximoa
     * @throws SQLException
     */
    public String getMaxId() throws SQLException {
        String maxId = null;
        String konsulta = "SELECT MAX(ID) AS MAX_ID FROM BEZERO";
        try (PreparedStatement pstmt = conn.prepareStatement(konsulta)) {
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                maxId = rs.getString("MAX_ID");
                int maxID = Integer.parseInt(maxId);
                maxID++;
                maxId = Integer.toString(maxID);
            }
        }
        return maxId;
    }
    
    /**
     * Produktu baten stock-aren kantitatea lortzen duen metodoa.
     * @param productName Produktuaren izena
     * @return Produktuaren stock-aren kantitatea
     * @throws SQLException
     */
    public int getProdStock(String productName) throws SQLException {
        int stock = 0;
        Connection conn = konexioa();
        String query = "SELECT SUM(KOPURUA) AS STOCK FROM INBENTARIO WHERE ID_PRODUKTU = (SELECT ID FROM PRODUKTU WHERE IZENA = ?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, productName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                stock = resultSet.getInt("STOCK");
            }
        }
        return stock;
    }

    /**
     * Produktu identifikadore maximoa lortzen duen metodoa.
     * @return Produktu identifikadore maximoa
     * @throws SQLException
     */
    public String getMaxIdprod() throws SQLException {
        String maxIdprod = null;
        String konsulta = "SELECT MAX(ID) AS MAX_ID FROM PRODUKTU";
        try (PreparedStatement pstmt = conn.prepareStatement(konsulta)) {
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                maxIdprod = rs.getString("MAX_ID");
                int maxIDprod = Integer.parseInt(maxIdprod);
                maxIDprod++;
                maxIdprod = Integer.toString(maxIDprod);
            }
        }
        return maxIdprod;
    }

    /**
     * Bezeroa eguneratzeko metodoa.
     * @param izena Bezeroaren izena
     * @param abizena Bezeroaren abizena
     * @param helbidea Bezeroaren helbidea
     * @param emaila Bezeroaren emaila
     * @param bezId Bezeroaren identifikadorea
     * @throws SQLException
     */
    public void bezeroaEguneratu(String izena, String abizena, String helbidea, String emaila, String bezId) throws SQLException {
        String kont = "UPDATE BEZERO SET IZENA = ?, ABIZENA = ?, HELBIDEA = ?, EMAILA = ? WHERE ID = ?";
        try (PreparedStatement pstmt = this.conn.prepareStatement(kont)) {
            pstmt.setString(1, izena);
            pstmt.setString(2, abizena);
            pstmt.setString(3, helbidea);
            pstmt.setString(4, emaila);
            pstmt.setString(5, bezId);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    /**
     * Produktua eguneratzeko metodoa.
     * @param izena Produktuaren izena
     * @param desk Produktuaren deskribapena
     * @param balioa Produktuaren balioa
     * @param salneurria Produktuaren salneurria
     * @param kategoria Produktuaren kategoria
     * @param prodId Produktuaren identifikadorea
     * @throws SQLException
     */
    public void produktuakEguneratu(String izena, String desk, double balioa, double salneurria, int kategoria, String prodId) throws SQLException {
        String kont = "UPDATE PRODUKTU SET IZENA = ?, DESKRIBAPENA = ?, BALIOA = ?, SALNEURRIA = ? ,ID_KATEGORIA = ? WHERE ID = ?";
        try (PreparedStatement pstmt = this.conn.prepareStatement(kont)) {
            pstmt.setString(1, izena);
            pstmt.setString(2, desk);
            pstmt.setDouble(3, balioa);
            pstmt.setDouble(4, salneurria);
            pstmt.setInt(5, kategoria);
            pstmt.setString(6, prodId);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
    
    /**
     * Produktuaren identifikadorea lortzen duen metodoa.
     * @param productName Produktuaren izena
     * @return Produktuaren identifikadorea
     * @throws SQLException
     */
    public int getProdId(String productName) throws SQLException {
        int productId = 0;
        Connection conn = konexioa();
        String query = "SELECT ID FROM PRODUKTU WHERE IZENA = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, productName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                productId = resultSet.getInt("ID");
            }
        }
        return productId;
    }

    /**
     * Produktuaren stock-a eguneratzen duen metodoa.
     * @param cantidad Stock-aren kantitatea
     * @param productoId Produktuaren identifikadorea
     * @throws SQLException
     */
    public void actualizarStock(int cantidad, String productoId) throws SQLException {
        String sql = "{call STOCKKOPURUA(?, ?)}";
        try (CallableStatement cstmt = conn.prepareCall(sql)) {
            cstmt.setInt(1, cantidad);
            cstmt.setString(2, productoId);
            cstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    /**
     * Konexioa deskonektatzen duen metodoa.
     * @throws SQLException
     */
    public void deskonektatu() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    /**
     * Bezeroak itzultzen dituen metodoa.
     * @return Bezeroak
     */
    public Bezeroak[] getBezeroak() {
        return bezeroak;
    }

    /**
     * Saltzaileak itzultzen dituen metodoa.
     * @return Saltzaileak
     */
    public Saltzaileak[] getSaltzaileak() {
        return saltzaileak;
    }

    /**
     * Langileak itzultzen dituen metodoa.
     * @return Langileak
     */
    public Langile[] getLangileak() {
        return langile;
    }

    /**
     * Saskiak itzultzen dituen metodoa.
     * @return Saskiak
     */
    public Saskia[] getSaskia() {
        return saskia;
    }

    /**
     * Produktuak itzultzen dituen metodoa.
     * @return Produktuak
     */
    public ProdEguneratu[] getProduktuak() {
        return produktuak;
    }
    
}