package sms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MarksDAO {

    private Connection connection;

    public MarksDAO(Connection connection) {
        this.connection = connection;
    }

   
    public boolean addMarks(Marks marks) {
        String sql = "INSERT INTO marks (student_id, course_id, marks, grade) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, marks.getStudentId());
            ps.setInt(2, marks.getCourseId());
            ps.setDouble(3, marks.getMarks());
            ps.setString(4, marks.getGrade());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

   
    public boolean updateMarks(Marks marks) {
        String sql = "UPDATE marks SET marks = ?, grade = ? WHERE student_id = ? AND course_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDouble(1, marks.getMarks());
            ps.setString(2, marks.getGrade());
            ps.setInt(3, marks.getStudentId());
            ps.setInt(4, marks.getCourseId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

   
    public Marks getMarks(int studentId, int courseId) {
        String sql = "SELECT * FROM marks WHERE student_id = ? AND course_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Marks m = new Marks();
                m.setMarksId(rs.getInt("marks_id"));
                m.setStudentId(studentId);
                m.setCourseId(courseId);
                m.setMarks(rs.getDouble("marks"));
                m.setGrade(rs.getString("grade"));
                return m;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<Marks> getMarksByStudent(int studentId) {
        List<Marks> list = new ArrayList<>();
        String sql = "SELECT * FROM marks WHERE student_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Marks m = new Marks();
                m.setMarksId(rs.getInt("marks_id"));
                m.setStudentId(studentId);
                m.setCourseId(rs.getInt("course_id"));
                m.setMarks(rs.getDouble("marks"));
                m.setGrade(rs.getString("grade"));
                list.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}



