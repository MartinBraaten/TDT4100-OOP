package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Stud {

    private String name;
    private int studentNr;
    private List <Kurs> grades = new ArrayList<Kurs>(); 
    private List <String> subjectNameList = new ArrayList<String>(); 
    private List <String> subjectCodeList = new ArrayList<String>(); 
    private ArrayList<Integer> studentNrList = new ArrayList<>();

    public Stud(String name, int studentNr) throws FileNotFoundException {
        isNameValid(name);
        this.name = name;
        isStudentNrValid(studentNr);
        this.studentNr = studentNr;
    }

    public int getStudentNr() {
        return studentNr;
    }

    public String getName() {
        return name;
    }

    public List<Kurs> getCourses() {
        return new ArrayList<>(grades);
    }

    public List<String> getSubjects(){
        return new ArrayList<>(subjectNameList);
    }

    public List<String> getSubjectsCodeList(){
        return new ArrayList<>(subjectCodeList);
    }

    private boolean doesCourseExist(Kurs course){
        if(getSubjects().contains(course.getSubjectName()) || getSubjectsCodeList().contains(course.getSubjectCode())){
            return true;
        } else return false;
    }

    public void addCourse(Kurs course) {
        if (!doesCourseExist(course)) {
            grades.add(course);
            subjectNameList.add(course.getSubjectName());
            subjectCodeList.add(course.getSubjectCode()); 
        } else {
            throw new IllegalArgumentException("Faget er allerede i vitnemålet.");
        }
    }

    public void removeCourse() {
        if(this.grades.isEmpty()){
            throw new IllegalArgumentException("Vitnemålet er tomt. Det er ikke mulig å fjerne flere fag.");
        } 
        int index = this.grades.size()-1;
        this.grades.remove(index);  // Fjerner siste elementet i lista.
        this.subjectNameList.remove(index);
        this.subjectCodeList.remove(index); 

    }

    public String setName(String name) {
        isNameValid(name);
        if (this.getName() == null){
            throw new IllegalArgumentException("Student is null");
        }
        this.name = name;
        return name; 
    } 

    private boolean isNameValid(String name) {
        String[] tokens = name.split(" ");
        if (tokens.length != 2) {
            throw new IllegalArgumentException("Navn er ikke gyldig. Må ha et fornavn og et etternavn.");
        }
        // fornavn og etternavn er lengre enn 2 bokstaver og består kun av bokstaver
        if ((tokens[0].length() > 1) && (tokens[0].matches("[a-zA-Z\s]+")) && (tokens[1].length() > 1) && (tokens[1].matches("[a-zA-Z\s]+"))) {
            return true;
        } else {
            throw new IllegalArgumentException("Navn er ikke gyldig. Kan kun ha bokstaver og være minst to bokstaver langt.");
        }
    }
 
    public int setStudentNr(int studentNr) throws FileNotFoundException {
        isStudentNrValid(studentNr);
        this.studentNr = studentNr;
        return studentNr;    
    }

    
    private boolean isStudentNrValid(int studentNr) throws FileNotFoundException  {
        if (String.valueOf(studentNr).length() != 6) {
            throw new IllegalArgumentException("Studentnr skal ha 6 siffer.");
        } else if (studentNr < 0) {
            throw new IllegalArgumentException("Studentnr kan ikke være negativt.");
        } else if(!isStudentNrUnique(studentNr)){
            throw new IllegalArgumentException("Studentnr kan ikke eksistere fra før");
        }
        else return true;
    }

    private boolean isStudentNrUnique(int studentNumber) throws FileNotFoundException{
        makingStudentNrList();
        //Sjekker om studentnr-input er i listen over tidligere studentnr som er registrert. 
        if (studentNrList.contains(studentNumber)) {
            Path pathOfFile = Path.of("src\\main\\java\\project\\scores\\").resolve(studentNumber + ".txt");
            try(Scanner scanner = new Scanner (pathOfFile.toFile())){
                String studentName = scanner.nextLine();
                if (studentName.equals(getName())){
                    return true; // studentnr er registrert på dette navnet fra før av
                }
                else return false; 
            }
        }
        else return true;
    }

    // Funksjon for å gå gjennom alle filene i scores-mappen og lagre studentnr i en liste
    private void makingStudentNrList() throws FileNotFoundException {
        createDirectoryIfNotExists();
        File folder = new File("src\\main\\java\\project\\scores");
        File[] listOfFiles = folder.listFiles();
        studentNrList = new ArrayList<>(); // tømmer listen om det skulle være noe i den fra før. 

        for (File file : listOfFiles) {
            try (Scanner scanner2 = new Scanner(file)){
                scanner2.nextLine(); //hopper over navnet til studenten i filen
                int studentNumber2 = Integer.parseInt(scanner2.nextLine());
                studentNrList.add(studentNumber2);
            }
        }
    }

    private void createDirectoryIfNotExists() {
        File testFile = new File("src\\main\\java\\project\\scores\\testfil.txt");
        if (!testFile.exists()) {
            File nyMappe = new File("src\\main\\java\\project\\scores");
            nyMappe.mkdir();
        }
    }
}
