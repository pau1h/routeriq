package parser;
import java.sql.*;

public class Database {
    String url = "jdbc:sqlite:devices.db";
    public Database(){ //establishes database connection & creates new table if it doesn't exist
        try(Connection conn = DriverManager.getConnection(url)){
            if(conn != null){
                String sql = "CREATE TABLE IF NOT EXISTS devices (" +
                        "	mac text PRIMARY KEY," +
                        "	ipv4 text," +
                        "	name text," +
                        "   active INTEGER," +
                        "   label text" +
                        ");"; //mac address, ipv4, device name, connection status (1 on : 0 off), user label
                var stmt = conn.createStatement();
                stmt.execute(sql);
            }
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }

    }

    public void insertOrUpdate(Device d){ //makes new row if device doesnt exist, otherwise update existing row. TODO: On first run devices should be updated without triggering rules due to changes
        if(deviceExists(d.getMac())){ //update ipv4 addr, name, and active status
            String sql = "SELECT active FROM devices WHERE mac = ?";
            try(Connection conn = DriverManager.getConnection(url)){
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, d.getMac());
                ResultSet rs = pstmt.executeQuery();
                if(rs.getInt("active") != d.isActive()){
                    //trigger events based on connection status
                    System.out.println(d.getName() + " has " + (d.isActive() == 1 ? "connected to" : "disconnected from") +" the router");
                }

            }catch(SQLException e){
                System.err.println(e.getMessage());
            }
            updateDevice(d);


        }else{
            insertDevice(d);
        }

    }


    public void insertDevice(Device d){ //Inserting new device into DB
        String sql = "INSERT INTO devices(mac,ipv4,name,active) VALUES(?, ?, ?, ?)";
        try(Connection conn = DriverManager.getConnection(url)){
            try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, d.getMac());
                pstmt.setString(2, d.getIpv4());
                pstmt.setString(3, d.getName());
                pstmt.setInt(4, d.isActive());
                pstmt.executeUpdate();
            }
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }

    public void updateDevice(Device d){
        String sql = "UPDATE devices SET ipv4 = ? , "
                + " name = ? ,"
                + " active = ? "
                + " WHERE mac = ?";
        try(Connection conn = DriverManager.getConnection(url)){
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, d.getIpv4());
            pstmt.setString(2, d.getName());
            pstmt.setInt(3, d.isActive());
            pstmt.executeUpdate();
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }



    public boolean deviceExists(String mac){ //checks if the device exists already in the DB
        String sql = "SELECT EXISTS(SELECT 1 FROM devices WHERE mac = ?)";
        try(Connection conn = DriverManager.getConnection(url)){
            try(PreparedStatement pstmt = conn.prepareStatement(sql)){
                pstmt.setString(1, mac);
                ResultSet rs = pstmt.executeQuery();
                if(rs.next()) return rs.getInt(1) == 1; //returns true if the device exists, otherwise false
            }
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return false;

    }






}
