package project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class KursTest {
    private Kurs kurs;

    @BeforeEach
    public void setup() {
        kurs = new Kurs("OOP", "TDT4100", 'B');
    }
    
    @Test
    public void testConstructor() {
        Assertions.assertEquals("OOP", kurs.getSubjectName());
        Assertions.assertEquals("TDT4100", kurs.getSubjectCode());
        Assertions.assertEquals('B', kurs.getGrade());
    }
    
    @Test
    public void isSubjectNameValidTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Kurs("1", "TDT4100", 'B'); 
        });
        Assertions.assertTrue(kurs.getSubjectName().matches("[a-zA-Z\s]+"));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Kurs(" ", "TDT4100", 'B'); 
        });
    }

    @Test
    public void isSubjectCodeValidTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Kurs("OOP", "TDT41", 'B'); 
        });

       Assertions.assertThrows(IllegalArgumentException.class, () -> {
        new Kurs("OOP", "TDT410B", 'B'); 
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Kurs("OOP", "TDT4100000", 'B'); 
            });
        
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Kurs("OOP", " ", 'B'); 
            });
            
        Assertions.assertTrue(kurs.getSubjectCode().substring(0, 2).matches("[A-Z]+"));
        Assertions.assertTrue(kurs.getSubjectCode().substring(3, 7).matches("[0-9]+"));
        Assertions.assertFalse(kurs.getSubjectCode().length()!=7);

        
    }

    @Test
    public void isGradeValidTest() {  
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Kurs("OOP", "TDT4100", ' '); 
        });

       Assertions.assertThrows(IllegalArgumentException.class, () -> {
        new Kurs("OOP", "TDT4100", '1'); 
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Kurs("OOP", "TDT4100", 'W'); 
            });
        Assertions.assertTrue(Character.toString(kurs.getGrade()).matches("[A-F]"));
    }   
}
