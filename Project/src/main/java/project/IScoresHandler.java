package project;
import java.io.FileNotFoundException; 

public interface IScoresHandler {

    Stud readStudentGrades(Stud stud, String fileName) throws FileNotFoundException;
    void writeScores(Stud stud, String fileName)  throws FileNotFoundException;
}
