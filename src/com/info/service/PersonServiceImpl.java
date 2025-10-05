package com.info.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import com.poly.info.db.ConnexionDB;
import com.info.model.Person;

@WebService(endpointInterface="com.info.service.PersonService")
public class PersonServiceImpl implements PersonService {
    private Connection getConnection() throws SQLException {
        return ConnexionDB.getConnexion();
    }

    @Override
    public boolean addPerson(Person p) {
        if (p == null || p.getName() == null) {
            return false;
        }
        String sql = "INSERT INTO person (name, age) VALUES (?, ?)";
        try (Connection cn = getConnection();
             PreparedStatement pstmt = cn.prepareStatement(sql)) {
            pstmt.setString(1, p.getName());
            pstmt.setInt(2, p.getAge());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deletePerson(int id) {
        String sql = "DELETE FROM person WHERE id = ?";
        try (Connection cn = getConnection();
             PreparedStatement pstmt = cn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Person getPersonByName(String name) {
        if (name == null) {
            return null;
        }
        String sql = "SELECT id, name, age FROM person WHERE name = ?";
        try (Connection cn = getConnection();
             PreparedStatement pstmt = cn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extractPersonFromResultSet(rs);
                }
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Person getPerson(int id) {
        String sql = "SELECT id, name, age FROM person WHERE id = ?";
        try (Connection cn = getConnection();
             PreparedStatement pstmt = cn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extractPersonFromResultSet(rs);
                }
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Person[] getAllPersons() {
        String sql = "SELECT id, name, age FROM person";
        List<Person> personList = new ArrayList<>();
        try (Connection cn = getConnection();
             Statement stmt = cn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                personList.add(extractPersonFromResultSet(rs));
            }
            return personList.toArray(new Person[0]);
        } catch (SQLException e) {
            e.printStackTrace();
            return new Person[0];
        }
    }

    private Person extractPersonFromResultSet(ResultSet rs) throws SQLException {
        return new Person(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getInt("age")
        );
    }
}