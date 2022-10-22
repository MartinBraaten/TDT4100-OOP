package project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ScoresHandlerTest  {
    private Stud stud;
    private ScoresHandler scoresHandler = new ScoresHandler(); 

    private void newStudent() throws FileNotFoundException{
        stud = new Stud("Test Name", 579204);
        Kurs course = new Kurs("Object orientert programmering", "TDT4100" , 'A');
        stud.addCourse(course); 
        scoresHandler.writeScores(stud, "579204");
    }

    @BeforeEach
    public void setup() throws FileNotFoundException{
        newStudent();
    }

    @Test
    public void testReadFromFile() throws FileNotFoundException{
        Stud savedStud = scoresHandler.readStudentGrades(new Stud("randdom random", 111111), "579204"); 
        assertEquals(stud.getName(), savedStud.getName());
        assertEquals(stud.getStudentNr(), savedStud.getStudentNr());
        for (int i = 0; i < stud.getCourses().size(); i++) {
            assertEquals(stud.getCourses().get(i).getGrade(), savedStud.getCourses().get(i).getGrade());
            assertEquals(stud.getCourses().get(i).getSubjectCode(), savedStud.getCourses().get(i).getSubjectCode());
            assertEquals(stud.getCourses().get(i).getSubjectName(), savedStud.getCourses().get(i).getSubjectName());
        }
    }

    @Test 
    public void testReadNonExistingFile(){
        Assertions.assertThrows(
            FileNotFoundException.class, () ->  { 
                scoresHandler.readStudentGrades(stud, "invalid");
            });
    }

   @Test 
    public void testWriteToFile() throws IOException{
        try{
            Stud newStud = new Stud("Test Name", 579204);
            Kurs course = new Kurs("Object orientert programmering", "TDT4100" , 'A');
            newStud.addCourse(course); 
            scoresHandler.writeScores(newStud, "579212");
        } catch (FileNotFoundException e){
            fail("Could not write to file"); 
        }

        byte[] testFile = null, newFile= null;
        try{
            testFile = Files.readAllBytes(ScoresHandler.getFilePath("579204"));
        } catch (IOException e){
            fail("Could not read file");
        }
        
        try {
            newFile = Files.readAllBytes(ScoresHandler.getFilePath("579212"));
        } catch (IOException e) {
            fail("Could not read saved file");
        }
        assertTrue(Arrays.equals(testFile, newFile));
    }

    @AfterAll
    static void teardown(){
        File newTestFile = ScoresHandler.getFilePath("579204").toFile();
        File testFile = ScoresHandler.getFilePath("579212").toFile();
        newTestFile.delete(); 
        testFile.delete();
    }
}
