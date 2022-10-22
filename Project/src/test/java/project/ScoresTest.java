package project;

import static org.junit.jupiter.api.Assertions.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ScoresTest {

    private Scores scores;

    @BeforeEach
    public void setup() throws FileNotFoundException {
        Stud per = new Stud("Per Viggo", 999999);
        Kurs course = new Kurs("java", "TTT1234", 'B');  
        Kurs course1 = new Kurs("jawa", "ATT1234", 'A');
        Kurs course2 = new Kurs("OOP", "TKT4100", 'E');
        per.addCourse(course);
        per.addCourse(course1);
        per.addCourse(course2);
        Scores scores = new Scores(per.getCourses());
        this.scores = scores;
    }
    
    @Test
    public void calculateIfScoresEqualsNullTest() {
        // Hvis listen med emner i vitnemålet er tomt så skal den tomme mengden 'Ø' returneres.
        List<Kurs> tomListe = new ArrayList<Kurs>();
        scores = new Scores(tomListe);
        assertEquals('Ø', scores.calculateAverageGrade());
        assertEquals('Ø', scores.calculateMedianGrade());
        assertEquals('Ø', scores.calculateBestGrade());
        assertEquals('Ø', scores.calculateWorstGrade());
    }

    @Test
    public void calculateAverageGradeTest() {
        assertEquals('C', scores.calculateAverageGrade());
    }

    @Test
    public void calculateMedianGradeTest() {
        assertEquals('B', scores.calculateMedianGrade());
    }

    @Test
    public void calculateBestGradeTest() {
        assertEquals('A', scores.calculateBestGrade());
    }

    @Test
    public void calculateWorstGradeTest() {
        assertEquals('E', scores.calculateWorstGrade());
    }
}
