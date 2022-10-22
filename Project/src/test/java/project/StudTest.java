package project;

import java.io.File;
import java.io.FileNotFoundException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StudTest {
    private Stud student; 
    private Kurs kurs;
    private ScoresHandler scoresHandler = new ScoresHandler(); 

    @BeforeEach
    public void setup() throws FileNotFoundException{
        student = new Stud("Human Name", 100000);
        kurs = new Kurs("OOP", "TDT4100", 'A');
        student.addCourse(kurs);
        scoresHandler.writeScores(student, "100000");
    }
    
    @Test
    public void testConstructor() {
        Assertions.assertEquals("Human Name", student.getName());
        Assertions.assertEquals(100000, student.getStudentNr());
    }

    @Test
    public void testInvalidStudentNr() throws FileNotFoundException{
        //student med nr finnes lagret fra før, vil sjekke at det ikke går ann å opprette en ny student med samme studentNummer
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Stud("Ola Nordmann", 100000);
        }); 
    }

    @Test
    public void doesCourseExistTest() {
        // kurs er allerede lagret på student. Skal utløse argumentexception hvis det eksisterer.
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            student.addCourse(kurs);
        });
    }

    @Test 
    public void addCourseTest(){
        Kurs nyttkurs = new Kurs("Java", "TDT4111", 'B');
        student.addCourse(nyttkurs);

        Assertions.assertEquals("Java", student.getCourses().get(1).getSubjectName());
        Assertions.assertEquals("TDT4111", student.getCourses().get(1).getSubjectCode());
        Assertions.assertEquals('B', student.getCourses().get(1).getGrade());
    }

    @Test
    public void removeCourseTest() {

        student.removeCourse();
        Assertions.assertEquals(0, student.getCourses().size());

        //sjekker at det blir utløst argumentexception når man prøver å fjerne fag fra tom liste
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
        student.removeCourse();
        });
    }

    @Test
    public void isStudentNrValidTest() {
        //tester at det blir utløst en IllegalArgumentException dersom studentnr er ugyldig
        
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            student.setStudentNr(100); 
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            student.setStudentNr(0); 
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            student.setStudentNr(-1); 
        });
        Assertions.assertFalse(String.valueOf(student.getStudentNr()).length() != 6);
    }

    @Test
    public void isNameValidTest() {
        //tester at det blir utløst en IllegalArgumentException dersom navnet er ugyldig

        Assertions.assertTrue(student.getName().matches("[a-zA-Z\s]+"));
        Assertions.assertFalse(student.getName().matches("[0-9]+"));
        Assertions.assertFalse(student.getName().length() < 5);

        String[] tokens = student.getName().split(" ");
        Assertions.assertEquals(2, tokens.length);
        Assertions.assertFalse(tokens[0].length() < 2 && tokens[1].length() < 2);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            student.setName(" "); 
        });
        
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            student.setName("Human"); 
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            student.setName("human 1"); 
        });
    }



    @AfterAll
    static void teardown(){
        File newTestFile = ScoresHandler.getFilePath("100000").toFile();
        newTestFile.delete(); 
    }
}
