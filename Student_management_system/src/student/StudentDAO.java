package student;

import java.util.*;

public class StudentDAO {
    private Map<Integer, Student> students = new HashMap<>();

    public boolean addStudent(Student student) {
        students.put(student.getStudentId(), student);
        return true;
    }

    public boolean updateStudent(Student student) {
        students.put(student.getStudentId(), student);
        return true;
    }

    public boolean deleteStudent(int studentId) {
        students.remove(studentId);
        return true;
    }

    public Student getStudentById(int studentId) {
        return students.get(studentId);
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students.values());
    }
}

