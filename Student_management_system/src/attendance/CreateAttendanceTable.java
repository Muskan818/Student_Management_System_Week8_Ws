package attendance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateAttendanceTable {
    static final String DB = "student_management_system";
    static final String DB_URL = "jdbc:mysql://localhost/" + DB;
    static final String USER = "root";
    static final String PASS = "";
    static final String TABLE = "attendance";

    public static void main(String[] args) {
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();){
            String sql = "CREATE TABLE " + TABLE + " (" +
                    "attendance_id INT AUTO_INCREMENT PRIMARY KEY,"+
                    "student_id INT," +
                    "course_id INT," +
                    "date DATE," +
                    "status VARCHAR(10)," +
                    "UNIQUE(student_id, course_id, date))";
            stmt.executeUpdate(sql);
            System.out.println("Created Attendance Table...");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
