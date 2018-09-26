import Model.Student;

import java.sql.*;
import java.util.Scanner;

public class ConnectSQL {
    private static String DB_URL = "jdbc:sqlserver://localhost:1433;"
            + "databaseName=mydb;"
            + "integratedSecurity=true";
    private static String USER_NAME = "sa";
    private static String PASSWORD = "123123";

    public static Connection getConnection(String dbURL, String userName,
                                           String password) {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            conn = DriverManager.getConnection(dbURL, userName, password);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    public void getData() {
        try {
            // connnect to database 'mydb'
            Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
            // crate statement
            Statement stmt = conn.createStatement();
            // get data from table 'student'
            ResultSet rs = stmt.executeQuery("select * from student");
            // show data
            while (rs.next()) {
                System.out.println(rs.getInt(1) +   // Get Id
                        "  " + rs.getString(2) +    // Get Name
                        "  " + rs.getString(3));    // Get Age
            }
            // close connection
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void insertData() {
        Student st = new Student();
        Scanner sc = new Scanner(System.in);
        try {
            Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
            String sql = "INSERT INTO student VALUES (?, ?, ?)";

            System.out.print("Input student name: ");
            st.setName(sc.nextLine().trim());
            sc.nextLine();

            System.out.print("Input student age: ");
            st.setAge(sc.nextInt());

            System.out.print("Input class of student: ");
            st.setId_class(sc.nextInt());

            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, st.getName());
            statement.setInt(2, st.getAge());
            statement.setInt(3, st.getId_class());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new student was inserted successfully!");
            }
            // close connection
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void updateData() {
        Student st = new Student();
        Scanner sc = new Scanner(System.in);
        try {
            Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
            String sql = "UPDATE student SET name=?, age=?, id_class=? WHERE id=?";

            System.out.print("Who student you want change( Input his ID): ");
            st.setId(sc.nextInt());
            sc.nextLine();

            System.out.println("Current Student: \n" + getStudentByID(st.getId()).toString());

            System.out.print("Input new student name: ");
            st.setName(sc.nextLine().trim());

            System.out.print("Input new student age: ");
            st.setAge(sc.nextInt());

            System.out.print("Input new class of student: ");
            st.setId_class(sc.nextInt());

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, st.getName());
            statement.setInt(2, st.getAge());
            statement.setInt(3, st.getId_class());
            statement.setInt(4, st.getId());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("This student was updated successfully!");
            }
            // close connection
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void deleteData() {
        Student st = new Student();
        Scanner sc = new Scanner(System.in);
        try {
            Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
            String sql = "DELETE FROM student WHERE id=?";

            System.out.print("Who student you want delete( Input his ID): ");
            st.setId(sc.nextInt());

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, st.getId());

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A student was deleted successfully!");
            }
            // close connection
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Student getStudentByID(int id) {
        Student st = new Student();
        try {
            Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
            Statement stmt = conn.createStatement();
            // get data from table 'student'
            ResultSet rs = stmt.executeQuery("select * from student WHERE id=" + id);
            // show data
            while (rs.next()) {
                st.setId(id);
                st.setName(rs.getString(2));
                st.setAge(rs.getInt(3));
                st.setId_class(rs.getInt(4));
            }
            // close connection
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return st;
    }

    public void getStudentByID() {
        Student st = new Student();
        try {
            Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
            Statement stmt = conn.createStatement();
            // get data from table 'student'
            Scanner sc = new Scanner(System.in);
            System.out.print("Input ID of student: ");
            int id = sc.nextInt();
            ResultSet rs = stmt.executeQuery("select * from student WHERE id=" + id);
            // show data

            if (rs.next()) {
                st.setId(rs.getInt(1));
                st.setName(rs.getString(2));
                st.setAge(rs.getInt(3));
                st.setId_class(rs.getInt(4));

                System.out.println(st.toString());
            } else {
                System.out.println("Data is not found!!!");
            }
            // close connection
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}


