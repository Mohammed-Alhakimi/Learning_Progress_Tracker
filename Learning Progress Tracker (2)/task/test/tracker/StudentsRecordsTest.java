package tracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;

class StudentsRecordsTest {
    StudentsRecords records;

    @BeforeEach
    void setup() {
        records = new StudentsRecords();
        getUniqueListStudents().forEach(student -> {
            records.map.put(student.getId(), student);
        });
    }

    @ParameterizedTest(name = "{index} => can't add {0}")
    @MethodSource("studentsWithAlreadyExistingEmail")
    void testAddingStudentsWithExistingEmails(Student s) {
        boolean exist = records.addStudentToTheMap(s);
        assertFalse(exist);
    }

    @ParameterizedTest(name = "{index} => can add {0}")
    @MethodSource("studentsWithNewEmail")
    void testAddingStudentsWithNewEmails(Student s) {
        boolean exist = records.addStudentToTheMap(s);
        assertTrue(exist);
    }


    static ArrayList<Student> getUniqueListStudents() {
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("Roba", "Aljbour", "Robaaljbour@gmail.com"));
        students.add(new Student("Ali", "AlHibsi", "aliAlhibsi@live.com"));
        students.add(new Student("Bader", "Alfarsi", "badoor@dubai.com"));
        students.add(new Student("bader", "farsi", "badoor@sharjah.com"));
        students.add(new Student("Mohammed", "Al-hakimi", "alhakimi1998@gmail.com"));
        return students;
    }

    static ArrayList<Student> studentsWithAlreadyExistingEmail() {
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("mazen", "Alhakimi", "Robaaljbour@gmail.com"));
        students.add(new Student("Saeed", "Hamad", "aliAlhibsi@live.com"));
        return students;
    }

    static ArrayList<Student> studentsWithNewEmail() {
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("Roba", "Aljbour", "Rorojbour@gmail.com"));
        students.add(new Student("bader", "farsi", "gmailgmail@live.com"));
        return students;
    }

}