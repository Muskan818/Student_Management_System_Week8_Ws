package attendance;

import java.time.LocalDate;

public class AttendanceService {
    private AttendanceDAO attendanceDAO;

    public AttendanceService(AttendanceDAO attendanceDAO){
        this.attendanceDAO = attendanceDAO;
    }

    public boolean markPresent(int studentId, int courseId, LocalDate date){
        if (attendanceDAO.attendanceExists(studentId, courseId, date)){
            return false;
        }
        Attendance attendance = new Attendance(studentId, courseId, date, "PRESENT");
        return attendanceDAO.markAttendance(attendance);
    }

    public boolean markAbsent(int studentId, int courseId, LocalDate date){
        if (attendanceDAO.attendanceExists(studentId, courseId, date)) {
            return false;
        }
        Attendance attendance = new Attendance(studentId, courseId, date, "ABSENT");
        return attendanceDAO.markAttendance(attendance);
    }
}
