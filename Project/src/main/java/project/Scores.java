package project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Scores { 
    private char averageGrade;
    private char medianGrade;
    private char bestGrade;
    private char worstGrade;
    private List<Kurs> courses = new ArrayList<Kurs>();

    public Scores(List<Kurs> courses) {
        this.courses = courses;  
    }
        
    public char calculateAverageGrade() { 
        int sum = 0;
        if (courses.isEmpty()){
            return averageGrade = 'Ø'; // returnerer den tomme mengden dersom listen over fag er tom 
        } else {
            //henter ut karakteren i listen og legger til poeng til sum utifra hvilken karakter 
            for (int i = 0; i < courses.size(); i++) {
                if (courses.get(i).getGrade()=='A') sum += 5;
                if (courses.get(i).getGrade()=='B') sum += 4;
                if (courses.get(i).getGrade()=='C') sum += 3;
                if (courses.get(i).getGrade()=='D') sum += 2;
                if (courses.get(i).getGrade()=='E') sum += 1;
                if (courses.get(i).getGrade()=='F') sum += 0;
            }
            
            //summen blir gjort om til en karakter 
            double average = sum / courses.size();
            if (4.5 <= average) {
                this.averageGrade = 'A';
            }
            if (3.5 <= average && average < 4.5) {
                this.averageGrade = 'B';
            }
            if (2.5 <= average && average < 3.5) {
                this.averageGrade = 'C';
            }
            if (1.5 <= average && average < 2.5) {
                this.averageGrade = 'D';
            }
            if (0.5 <= average && average < 1.5) {
                this.averageGrade = 'E';
            }
            if (0 <= average && average < 0.5) {
                this.averageGrade = 'F';
            }
            return averageGrade; 
        }  
    }

    public char calculateMedianGrade() {
        if (courses.isEmpty()){
            return medianGrade = 'Ø'; 
        }
        else {
            List<Integer> medianList = new ArrayList<>();
            int median;
            // Gjør om bokstavkarakterer til tall, og legger de inn i listen og sorterer listen
            for (int i = 0; i < courses.size(); i++) { 
                if (courses.get(i).getGrade()=='A') medianList.add(5);
                if (courses.get(i).getGrade()=='B') medianList.add(4);
                if (courses.get(i).getGrade()=='C') medianList.add(3);
                if (courses.get(i).getGrade()=='D') medianList.add(2);
                if (courses.get(i).getGrade()=='E') medianList.add(1);
                if (courses.get(i).getGrade()=='F') medianList.add(0);
            }

        Collections.sort(medianList);
        median = medianList.get(medianList.size()/2); // Henter ut elementet i midten

        //gjør om tallet til en bokstavkarakter igjen
        if (median == 5) {
            this.medianGrade = 'A';
        }
        if (median == 4) {
            this.medianGrade = 'B';
        }
        if (median == 3) {
            this.medianGrade = 'C';
        }
        if (median == 2) {
            this.medianGrade = 'D';
        }
        if (median == 1) {
            this.medianGrade = 'E';
        }
        if (median == 0) {
            this.medianGrade = 'F';
        }
        return medianGrade; 
        }
        
    }

    public char calculateBestGrade() {
        if (courses.isEmpty()){
            return 'Ø'; 
        } else {
            return courses.stream().map(Kurs::getGrade).min((i,j) -> i.compareTo(j)).get();
        }
    }

    public char calculateWorstGrade() {
        if (courses.isEmpty()){
            return bestGrade = 'Ø'; 
        } else {
            return courses.stream().map(Kurs::getGrade).max((i,j) -> i.compareTo(j)).get();
    }
}
}