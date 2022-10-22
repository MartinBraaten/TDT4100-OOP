package project;

public class Kurs {
    private String subjectName;
    private String subjectCode;
    private char grade;

    public Kurs(String subjectName, String subjectCode, char grade) {
        isSubjectNameValid(subjectName);
        isSubjectCodeValid(subjectCode);
        isGradeValid(grade);

        this.subjectName = subjectName;
        this.subjectCode = subjectCode;
        this.grade = grade;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public char getGrade() {
        return grade;
    }

    private boolean isSubjectNameValid(String subjectName) {

        if (subjectName == " "){
            throw new IllegalArgumentException("Du har ikke skrevet inn noe fagnavn.");
        }
        //sjekker at navnet på faget bare inneholder bokstaver 
        if (subjectName.matches("[a-zA-Z\s]+")){
            return true;
        } else {
            throw new IllegalArgumentException("Det skal kun være bokstaver i fagnavnet");
        }
    }

    private boolean isSubjectCodeValid(String subjectCode) {
        //sjekker at lengden på fagkoden er riktig 
        if (subjectCode.length() != 7) {
            throw new IllegalArgumentException("Fagkoden skal bestå av 7 tegn");
        }
        
        //deler emnekoden i 2 deler: en del med bokstaver og en del med tall
        String sub1 = subjectCode.substring(0, 2);
        String sub2 = subjectCode.substring(3, 7);
            
        if (!sub1.matches("[A-Z]+")) {
            throw new IllegalArgumentException("De første 3 tegnene skal være bokstaver");
        }

        //sjekker at andre delen av emnekoden bare består av tall
        if (!sub2.matches("[0-9]+")) {
            throw new IllegalArgumentException("De siste 4 tegnene skal være tall");
        } else return true;
    }

    private boolean isGradeValid(char grade) {
        //sjekker at karakteren enten er A, B, C, D, E eller F
        if (grade != 'A' && grade != 'B' && grade != 'C' && grade != 'D' && grade != 'E' && grade != 'F') {
            throw new IllegalArgumentException("Ikke gyldig karakter");
        } else return true;
    }
}
