
import java.sql.*;
import java.util.Scanner;

public class StudentManagement {

    private static Connection connect() throws Exception {
        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/studentdb",
            "root",
            "password"        // change password
        );
    }

    public static void addStudent() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter student name: ");
        String name = sc.nextLine();

        Connection con = connect();
        PreparedStatement ps = con.prepareStatement("INSERT INTO students(name) VALUES(?)");
        ps.setString(1, name);
        ps.executeUpdate();
        System.out.println("Student added successfully!");
    }

    public static void viewStudents() throws Exception {
        Connection con = connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM students");

        System.out.println("\n--- Student List ---");
        while (rs.next()) {
            System.out.println(rs.getInt("id") + " - " + rs.getString("name"));
        }
    }

    public static void updateStudent() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter student ID to update: ");
        int id = sc.nextInt(); sc.nextLine();

        System.out.print("Enter new name: ");
        String name = sc.nextLine();

        Connection con = connect();
        PreparedStatement ps = con.prepareStatement("UPDATE students SET name=? WHERE id=?");
        ps.setString(1, name);
        ps.setInt(2, id);

        ps.executeUpdate();
        System.out.println("Student updated successfully!");
    }

    public static void deleteStudent() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter student ID to delete: ");
        int id = sc.nextInt();

        Connection con = connect();
        PreparedStatement ps = con.prepareStatement("DELETE FROM students WHERE id=?");
        ps.setInt(1, id);

        ps.executeUpdate();
        System.out.println("Student deleted!");
    }

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Student\n2. View Students\n3. Update Student\n4. Delete Student\n5. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: addStudent(); break;
                case 2: viewStudents(); break;
                case 3: updateStudent(); break;
                case 4: deleteStudent(); break;
                case 5: System.exit(0);
            }
        }
    }
}
