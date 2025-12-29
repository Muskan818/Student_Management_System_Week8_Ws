package course;

import java.util.*;

public class CourseDAO {
    private Map<Integer, Course> courses = new HashMap<>();

    public boolean addCourse(Course course) {
        courses.put(course.getCourseId(), course);
        return true;
    }

    public boolean updateCourse(Course course) {
        courses.put(course.getCourseId(), course);
        return true;
    }

    public boolean deleteCourse(int courseId) {
        courses.remove(courseId);
        return true;
    }

    public List<Course> getAllCourses() {
        return new ArrayList<>(courses.values());
    }
}

