import java.sql.*;
import java.util.*;

public class StudentDAO {

    public void addStudent(Student s) {
        try (Connection con = DBConnection.getConnection()) {
            String query = "INSERT INTO students VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, s.getId());
            ps.setString(2, s.getName());
            ps.setInt(3, s.getAge());
            ps.setString(4, s.getDepartment());
            ps.executeUpdate();
            System.out.println("Student Added Successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(int id, String newName) {
        try (Connection con = DBConnection.getConnection()) {
            String query = "UPDATE students SET name=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, newName);
            ps.setInt(2, id);
            ps.executeUpdate();
            System.out.println("Student Updated Successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(int id) {
        try (Connection con = DBConnection.getConnection()) {
            String query = "DELETE FROM students WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Student Deleted Successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewStudent(int id) {
        try (Connection con = DBConnection.getConnection()) {
            String query = "SELECT * FROM students WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("ID: " + rs.getInt(1));
                System.out.println("Name: " + rs.getString(2));
                System.out.println("Age: " + rs.getInt(3));
                System.out.println("Department: " + rs.getString(4));
            } else {
                System.out.println("No student found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
