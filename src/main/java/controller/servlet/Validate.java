/* package controller.servlet;

import java.sql.*;

public class Validate {

    public static boolean checkUser(String username, String pass) {
        boolean st = false;
        try {

            //loading drivers for mysql
            Class.forName("com.mysql.cj.jdbc.Driver");

            //creating connection with the database
            //Connection con = ;

            PreparedStatement ps = con.prepareStatement
                    ("select * from account where InlogNaam=? and pass=?");

            ps.setString(1, username);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            st = rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return st;
    }
} */