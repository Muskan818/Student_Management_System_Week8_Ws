package enrollment;

import course.Course;
import java.util.*;

public class EnrollmentDAO {
    private Map<Integer, List<Course>> enrollments = new HashMap<>();

    public boolean enrollStudent(int studentId, Course course) {
        enrollments.computeIfAbsent(studentId, k -> new ArrayList<>()).add(course);
        return true;
    }

    public boolean removeEnrollment(int studentId, int courseId) {
        List<Course> courses = enrollments.get(studentId);
        if (courses == null) return false;
        courses.removeIf(c -> c.getCourseId() == courseId);
        return true;
    }

    public List<Course> getCoursesByStudent(int studentId) {
        return enrollments.getOrDefault(studentId, new ArrayList<>());
    }
}
