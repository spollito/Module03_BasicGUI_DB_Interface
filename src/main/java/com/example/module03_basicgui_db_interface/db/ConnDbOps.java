package com.example.module03_basicgui_db_interface.db;
import com.example.module03_basicgui_db_interface.Person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author MoaathAlrajab
 */
public class ConnDbOps {
    final String MYSQL_SERVER_URL = "jdbc:mysql://csc311burusvserver.mysql.database.azure.com";
    final String DB_URL = "jdbc:mysql://csc311burusvserver.mysql.database.azure.com:3306/db311";
    final String USERNAME = "burusv";
    final String PASSWORD = "CSC311serv";

    public Connection connectToDatabase() {
        try {
            return DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }



    public List<Person> getAllPersons() {
        List<Person> persons = new ArrayList<>();
        String query = "SELECT * FROM persons";

        try (Connection conn = connectToDatabase();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Person person = new Person(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getString("password")
                );
                persons.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return persons;
    }

    public void insertPerson(Person person) {
        String query = "INSERT INTO persons (name, email, phone, address, password) VALUES (?, ?, ?, ?, ?)";


        try (Connection conn = connectToDatabase();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, person.getName());
            pstmt.setString(2, person.getEmail());
            pstmt.setString(3, person.getPhone());
            pstmt.setString(4, person.getAddress());
            pstmt.setString(5, person.getPassword());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePerson(Person person) {
        String query = "UPDATE persons SET name=?, email=?, phone=?, address=?, password=? WHERE id=?";

        try (Connection conn = connectToDatabase();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, person.getName());
            pstmt.setString(2, person.getEmail());
            pstmt.setString(3, person.getPhone());
            pstmt.setString(4, person.getAddress());
            pstmt.setString(5, person.getPassword());
            pstmt.setInt(6, person.getId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePerson(int id) {
        String query = "DELETE FROM persons WHERE id=?";

        try (Connection conn = connectToDatabase();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
