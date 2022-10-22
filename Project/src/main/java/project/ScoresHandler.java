package project;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.Scanner;

public class ScoresHandler implements IScoresHandler{
    @Override
    public Stud readStudentGrades(Stud stud, String fileName)throws FileNotFoundException{
        //henter ut filen lagret under riktig filnavn 
        try(Scanner scanner = new Scanner (getFilePath(fileName).toFile())){
            String studentName = scanner.nextLine(); 
            
            if (!studentName.equals("null")) {
                stud.setName(studentName); 
            }
            
            int studentNumber = Integer.parseInt(scanner.nextLine());
            if(studentNumber != 0){
                stud.setStudentNr(studentNumber);
            }
            
            //leser hver linje i file og lager de som subjectName, subjectCode og grade
            while (scanner.hasNextLine()) {
                String[] properties = scanner.nextLine().split(":");
                String subjectName = properties[0];
                String subjectCode = properties[1].replaceAll(" ", "");
                String subjectGradeString = properties[2]; 
                char subjectGrade = subjectGradeString.charAt(1); 

            //verdiene som hentes ut for subjectName, subjectCode og grade legges til i kurslisten til studenten 
                Kurs kurs = new Kurs(subjectName, subjectCode, subjectGrade);
                stud.addCourse(kurs);
            }
            return stud; 
        }
    }

    //skriver ut karaterer til tekstfil 
    @Override
    public void writeScores(Stud stud, String fileName) throws FileNotFoundException {
        try (
            //henter ut filen lagret under riktig filnavn dersom det allerde finnes en fra før av, ellers lages det en ny fil 
            PrintWriter writer = new PrintWriter(getFilePath(fileName).toFile())){
            writer.println(stud.getName());
            writer.println(stud.getStudentNr());

            //henter ut fagene til studenten i listen over fag studenten har tatt og legger disse til i dokumentet 
            for(Kurs kurs : stud.getCourses()){
                writer.println(String.format("%s: %s: %s", kurs.getSubjectName(), kurs.getSubjectCode(), kurs.getGrade()));
            }
            writer.close ();
        }
    }

    public static Path getFilePath(String fileName){
        //henter ut filen som ligger lagret under riktig filnavn
        return Path.of("src\\main\\java\\project\\scores\\").resolve(fileName + ".txt"); 
    }
}




