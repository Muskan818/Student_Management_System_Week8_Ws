package attendance;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDAO {
    private Connection connection;

    AttendanceDAO(Connection connection){
        this.connection = connection;
    }

    public boolean markAttendance(Attendance attendance){
        String sql = "INSERT INTO attendance (student_id, course_id, date, status) VALUES (?,?,?,?)";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, attendance.getStudentId());
            ps.setInt(2, attendance.getCourseId());
            ps.setDate(3, Date.valueOf(attendance.getDate()));
            ps.setString(4, attendance.getStatus());
            return ps.executeUpdate() > 0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean attendanceExists(int studentId, int courseId, LocalDate date){
        String sql = "SELECT 1 FROM attendance WHERE student_id = ? AND course_id = ? AND date = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            ps.setDate(3, Date.valueOf(date));
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public List<Attendance> getAttendanceByStudent(int studentId){
        List<Attendance> attendancesList = new ArrayList<>();
        String sql = "SELECT * FROM attendance WHERE student_id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Attendance attendance = new Attendance(
                        rs.getInt("student_id"),
                        rs.getInt("course_id"),
                        rs.getDate("date").toLocalDate(),
                        rs.getString("status")
                );
                attendancesList.add(attendance);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return attendancesList;
    }

    public List<Attendance> getAttendanceByCourse(int courseId){
        List<Attendance> attendancesList = new ArrayList<>();
        String sql = "SELECT * FROM attendance WHERE course_id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, courseId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Attendance attendance = new Attendance(
                        rs.getInt("student_id"),
                        rs.getInt("course_id"),
                        rs.getDate("date").toLocalDate(),
                        rs.getString("status")
                );
                attendancesList.add(attendance);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return attendancesList;
    }

    public double calculateAttendancePercentage(int studentId, int courseId){
        String sql = "SELECT COUNT(*) total, " +
                "SUM(CASE WHEN status='PRESENT' THEN 1 ELSE 0 END) present " +
                "FROM attendance WHERE student_id=? AND course_id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int total = rs.getInt("total");
                int present = rs.getInt("present");
                if (total == 0) return 0;
                return (present * 100.0) / total;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
