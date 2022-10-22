package project;

import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class projectController {
    private Stud stud; 
    private IScoresHandler scoresHandler = new ScoresHandler();
    
    @FXML
    private TextField StudentNrInput, StudentNameInput, subjectCodeInput, subjectNameInput, subjectGradeInput;

    @FXML
    private TextArea StudentNameOutput, StudentNrOutput, averageGrade, medianGrade, bestGrade, worstGrade; 

    @FXML
    private ListView<String> subjectCourseNameOutput, subjectCourseCodeOutput, subjectCourseGradeOutput;

    @FXML
    private Button logInn, addCourse, removeCourse, saveSubjects;  

    @FXML
    private void initialize(){
        //disabler knapper som ikke skal være tilgjengelig fra starten
        removeCourse.disableProperty().set(true);
        addCourse.disableProperty().set(true);
        saveSubjects.disableProperty().set(true);
    }

    @FXML
    private void updateStudentName() throws FileNotFoundException{
        try {
            //henter ut navnet og nummeret som blir tastet inn som input
            String nameStudent = StudentNameInput.getText(); 
            int nrStudent = Integer.parseInt(StudentNrInput.getText()); 
            Stud stud = new Stud(nameStudent, nrStudent);
            this.stud = stud;

            //skriver ut navn og nummeret i tekstboks
            StudentNameOutput.setText(stud.setName(nameStudent));
            StudentNrOutput.setText(String.valueOf(stud.setStudentNr(nrStudent)));

            addCourse.disableProperty().set(false);
            logInn.disableProperty().set(true);
            StudentNameInput.disableProperty().set(true);
            StudentNrInput.disableProperty().set(true);
        }
        catch(NumberFormatException e){ 
            Alert errorMessage = new Alert(AlertType.ERROR);
            errorMessage.setTitle("Error message");
            errorMessage.setContentText("Feil input. Studentnummer må være et tall med 6 siffer.");
            errorMessage.showAndWait();
        }   
        catch(IllegalArgumentException e){
            showErrorMessage(e.getMessage());
        }   
    }

    public void handleLogInAction() throws FileNotFoundException{
        updateStudentName();
        handleGetSubjects();
    }
   
    private void updateGrades(){
        try{
        //henter ut emnenavn, emnekode og karakter som bruker skriver inn 
        String subjectName = subjectNameInput.getText();
        String subjectCode = subjectCodeInput.getText();
        char subjectGrade =  subjectGradeInput.getText().charAt(0); 

        //legger til faget
        Kurs kurs = new Kurs(subjectName, subjectCode, subjectGrade);
        this.stud.addCourse(kurs); 

        ObservableList<String> courseName = FXCollections.observableArrayList();
        ObservableList<String> courseCode = FXCollections.observableArrayList();
        ObservableList<String> courseGrade = FXCollections.observableArrayList();
        
        //henter ut fagene lagret på studenten 
        for (int j = 0; j < this.stud.getCourses().size(); j++) { 
            courseName.add(String.valueOf(this.stud.getCourses().get(j).getSubjectName())); 
            courseCode.add(String.valueOf(this.stud.getCourses().get(j).getSubjectCode())); 
            courseGrade.add(String.valueOf(this.stud.getCourses().get(j).getGrade())); 
        }
        //legger disse til i riktig tekstboks
        subjectCourseNameOutput.setItems(courseName);
        subjectCourseCodeOutput.setItems(courseCode);
        subjectCourseGradeOutput.setItems(courseGrade);
        }

        catch(StringIndexOutOfBoundsException exception){
            Alert errorMessage = new Alert(AlertType.ERROR);
            errorMessage.setTitle("Error message");
            errorMessage.setContentText("Du må fylle alle feltene");
            errorMessage.showAndWait();
        }
        catch (IllegalArgumentException e){
            showErrorMessage(e.getMessage());
        }   
    }

    public void removeButtonIfNoMoreGradesToRemove() {
        if (stud.getCourses().isEmpty()) {
            removeCourse.disableProperty().set(true);
        }    
    }

    public void updateRemoveGrades(){
        try{
        ObservableList<String> courseName = FXCollections.observableArrayList();
        ObservableList<String> courseCode = FXCollections.observableArrayList();
        ObservableList<String> courseGrade = FXCollections.observableArrayList();

        //etter å ha fjernet faget så hentes listen over fag ut 
        for (int j = 0; j < this.stud.getCourses().size(); j++) { 
            courseName.add(String.valueOf(this.stud.getCourses().get(j).getSubjectName())); 
            courseCode.add(String.valueOf(this.stud.getCourses().get(j).getSubjectCode())); 
            courseGrade.add(String.valueOf(this.stud.getCourses().get(j).getGrade())); 
        }
        subjectCourseNameOutput.setItems(courseName);
        subjectCourseCodeOutput.setItems(courseCode);
        subjectCourseGradeOutput.setItems(courseGrade);
        }

        catch (IllegalArgumentException e){
            showErrorMessage(e.getMessage());
        }  
    }

    private void updateAverage(){
        Scores scores = new Scores(this.stud.getCourses()); 
        //regner ut snittet og skriver dette inn i riktig tekstboks
        averageGrade.setText(String.valueOf(scores.calculateAverageGrade()));
    }

    private void updateMedian(){
        Scores scores = new Scores(this.stud.getCourses()); 
        medianGrade.setText(String.valueOf(scores.calculateMedianGrade()));
    }

    private void updateBestGrade(){
        Scores scores = new Scores(this.stud.getCourses()); 
        bestGrade.setText(String.valueOf(scores.calculateBestGrade()));
    }

    private void updateWorstGrade(){
        Scores scores = new Scores(this.stud.getCourses()); 
        worstGrade.setText(String.valueOf(scores.calculateWorstGrade()));
    }
   
    public void handleRemoveCourse() {
        try {
        this.stud.removeCourse();
        updateRemoveGrades();
        updateAverage();
        updateMedian();
        updateBestGrade();
        updateWorstGrade();
        removeButtonIfNoMoreGradesToRemove();
        }
        
        catch (IllegalArgumentException e){
            showErrorMessage(e.getMessage());
        }
    }

    public void handleAddCourses(){
        updateGrades();
        updateAverage();
        updateMedian();
        updateBestGrade();
        updateWorstGrade();
        saveSubjects.disableProperty().set(false);
        removeCourse.disableProperty().set(false);
    }

    //pop up ved feil input
    private void showErrorMessage(String message) {
        Alert errorMessage = new Alert(AlertType.ERROR);
        errorMessage.setTitle("Error message");
        errorMessage.setContentText(message);
        errorMessage.showAndWait();
    }

    public void handleSaveSubjects(){
        try{
            //henter ut studentnummeret og setter dette til å være navnet på filen som lagres 
            String fileName = StudentNrInput.getText(); 
            scoresHandler.writeScores(this.stud, fileName); 
        } catch(FileNotFoundException e){
            showErrorMessage("Kunne ikke lagre karakter");
        }catch(NoSuchElementException e){
        }

        Alert message = new Alert(AlertType.INFORMATION);
        message.setTitle("Message information");
        message.setContentText("Karakterene har blitt lagret!");
        message.showAndWait();
    }

    private void handleGetSubjects(){
        try{
            //finner studentnummeret til studenten og leser ut filen lagret under dette navnet 
            String fileName = StudentNrInput.getText();
            scoresHandler.readStudentGrades(this.stud, fileName); 

            ObservableList<String> courseName = FXCollections.observableArrayList();
            ObservableList<String> courseCode = FXCollections.observableArrayList();
            ObservableList<String> courseGrade = FXCollections.observableArrayList();

            //henter fagene som studenten har tatt, samt fagkode og karakter 
            for (int j = 0; j < this.stud.getCourses().size(); j++) { 
                courseName.add(String.valueOf(this.stud.getCourses().get(j).getSubjectName())); 
                courseCode.add(String.valueOf(this.stud.getCourses().get(j).getSubjectCode()));
                courseGrade.add(String.valueOf(this.stud.getCourses().get(j).getGrade()));
            }
            //legger disse til i riktig tekstboks
            subjectCourseNameOutput.setItems(courseName);
            subjectCourseCodeOutput.setItems(courseCode);
            subjectCourseGradeOutput.setItems(courseGrade);

            updateAverage();
            updateMedian();
            updateBestGrade();
            updateWorstGrade();
            removeButtonIfNoMoreGradesToRemove();
            saveSubjects.disableProperty().set(false);
            removeCourse.disableProperty().set(false);
        }
        catch(FileNotFoundException e){
        }   
        catch(NullPointerException e){
        }
    } 
} 


