package com.northcoders.drinksapi;
import com.northcoders.drinksapi.config.DbConfig;
import com.northcoders.drinksapi.config.DB;

import java.net.SocketTimeoutException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DbQuery {

    DbConfig dbConfig = new DbConfig();
    Properties config = dbConfig.getConfig();

//    return all planets in the database
//    return a single planet by name
//    return all planets by type
//    POST a planet to the database
//    PATCH a planet object

    public void listAllStudents() throws SQLException {
        String query = "SELECT * FROM planets";

        try (Connection conn = DB.connect(
                config.getProperty("db.url"),
                config.getProperty("db.username"),
                config.getProperty("db.password"));
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int studentId = rs.getInt("student_id");
                String studentName = rs.getString("student_name");
                int age = rs.getInt("age");
                System.out.println("ID: " + studentId + ", Name: " + studentName + ", Age: " + age);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to list all courses
    public void listAllCourses() {
        String query = "SELECT * FROM courses"; // Assume there's a 'courses' table

        try (Connection conn = DB.connect(
                config.getProperty("db.url"),
                config.getProperty("db.username"),
                config.getProperty("db.password"));
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int courseId = rs.getInt("course_id");
                String courseName = rs.getString("course_name");
                System.out.println("Course ID: " + courseId + ", Course Name: " + courseName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get students with top Maths grades above 90
    public void getStudentsWithTopMathsGrade() {
        getStudentsWithMathsGradeAbove(90);
    }

    // Method to get students with Maths grade above a specified value
    public void getStudentsWithMathsGradeAbove(int grade) {
        String query = "SELECT * FROM grades WHERE course_id = 101 AND grade > ?"; // Assume there's a 'maths_grade' column

        try (Connection conn = DB.connect(
                config.getProperty("db.url"),
                config.getProperty("db.username"),
                config.getProperty("db.password"));) {

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, grade);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int studentId = rs.getInt("student_id");
                int mathsGrade = rs.getInt("grade");
                System.out.println("ID: " + studentId + ", Maths Grade: " + mathsGrade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get courses with a name like a specified substring
    public void getCoursesWithNameLike(String courseName) {
        String query = "SELECT * FROM courses WHERE course_name LIKE ?";

        try (Connection conn = DB.connect(
                config.getProperty("db.url"),
                config.getProperty("db.username"),
                config.getProperty("db.password"));
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, "%" + courseName + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int courseId = rs.getInt("course_id");
                String courseNameResult = rs.getString("course_name");
                System.out.println("Course ID: " + courseId + ", Course Name: " + courseNameResult);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get students on courses with a name like a specified substring
    public void getStudentsOnCourseWithNameLike(String courseName) {
        String query = "SELECT s.student_id, s.student_name, c.course_name " +
                "FROM students s " +
                "JOIN student_courses sc ON s.student_id = sc.student_id " +
                "JOIN courses c ON sc.course_id = c.course_id " +
                "WHERE c.course_name LIKE ?";

        try (Connection conn = DB.connect(
                config.getProperty("db.url"),
                config.getProperty("db.username"),
                config.getProperty("db.password"));
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, "%" + courseName + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int studentId = rs.getInt("student_id");
                String studentName = rs.getString("student_name");
                String courseNameResult = rs.getString("course_name");
                System.out.println("Student ID: " + studentId + ", Name: " + studentName + ", Course: " + courseNameResult);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void incrementStudentGrade(int studentId, int courseId) {
//        String query = "UPDATE grades WHERE student_id = ? AND course_id = ? SET grade = \"grade\" + 1";
        String query = "UPDATE grades SET grade = grade + 1 WHERE student_id = ? AND course_id = ? RETURNING student_id, course_id, grade;";

        try (Connection conn = DB.connect(
                config.getProperty("db.url"),
                config.getProperty("db.username"),
                config.getProperty("db.password"));) {

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int studentIdFromResult = rs.getInt("student_id");
                int subjectIdFromResult = rs.getInt("course_id");
                int subjectGrade = rs.getInt("grade");
                System.out.println("ID: " + studentIdFromResult + ", Subject " + subjectIdFromResult + " Grade: " + subjectGrade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudentGrade(int studentId, int courseId) {
        String query = "DELETE FROM grades WHERE student_id = ? AND course_id = ?;";

        try (Connection conn = DB.connect(
                config.getProperty("db.url"),
                config.getProperty("db.username"),
                config.getProperty("db.password"));) {

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            int update = ps.executeUpdate();
            System.out.println("Deleted " + update);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTeachers() {

        String query = "INSERT INTO teachers (teacher_name) VALUES ('John Davy')," +
                "('Simon Morgan')," +
                "('Mat Hatch')," +
                "('John Bargh')," +
                "('Lesley Belly')," +
                "('Jade Bolton')" +
                "RETURNING *;";
        try (Connection conn = DB.connect(
                config.getProperty("db.url"),
                config.getProperty("db.username"),
                config.getProperty("db.password"));) {

            Statement ps = conn.createStatement();
            ps.executeQuery(query);
            System.out.println("Teachers have been fulfilled");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}