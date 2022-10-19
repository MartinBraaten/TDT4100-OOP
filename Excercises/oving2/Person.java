package oving2;

import java.util.Date;
import java.util.Arrays;
import java.util.List;

public class Person {

    private static final List<String> landkode = Arrays.asList("ad", "ae", "af", "ag", "ai", "al", "am", "ao", "aq", "ar", "as", "at", "au", "aw", "ax", "az", "ba", "bb", "bd", "be", "bf", "bg", "bh", "bi", "bj", "bl", "bm", "bn", "bo", "bq", "br", "bs", "bt", "bv", "bw", "by", "bz", "ca", "cc", "cd", "cf", "cg", "ch", "ci", "ck", "cl", "cm", "cn", "co", "cr", "cu", "cv", "cw", "cx", "cy", "cz", "de", "dj", "dk", "dm", "do", "dz", "ec", "ee", "eg", "eh", "er", "es", "et", "fi", "fj", "fk", "fm", "fo", "fr", "ga", "gb", "gd", "ge", "gf", "gg", "gh", "gi", "gl", "gm", "gn", "gp", "gq", "gr", "gs", "gt", "gu", "gw", "gy", "hk", "hm", "hn", "hr", "ht", "hu", "id", "ie", "il", "im", "in", "io", "iq", "ir", "is", "it", "je", "jm", "jo", "jp", "ke", "kg", "kh", "ki", "km", "kn", "kp", "kr", "kw", "ky", "kz", "la", "lb", "lc", "li", "lk", "lr", "ls", "lt", "lu", "lv", "ly", "ma", "mc", "md", "me", "mf", "mg", "mh", "mk", "ml", "mm", "mn", "mo", "mp", "mq", "mr", "ms", "mt", "mu", "mv", "mw", "mx", "my", "mz", "na", "nc", "ne", "nf", "ng", "ni", "nl", "no", "np", "nr", "nu", "nz", "om", "pa", "pe", "pf", "pg", "ph", "pk", "pl", "pm", "pn", "pr", "ps", "pt", "pw", "py", "qa", "re", "ro", "rs", "ru", "rw", "sa", "sb", "sc", "sd", "se", "sg", "sh", "si", "sj", "sk", "sl", "sm", "sn", "so", "sr", "ss", "st", "sv", "sx", "sy", "sz", "tc", "td", "tf", "tg", "th", "tj", "tk", "tl", "tm", "tn", "to", "tr", "tt", "tv", "tw", "tz", "ua", "ug", "um", "us", "uy", "uz", "va", "vc", "ve", "vg", "vi", "vn", "vu", "wf", "ws", "ye", "yt", "za", "zm", "zw");

    private String name;
    private String fornavn;
    private String etternavn;
    private String Email;
    private Date birthday;
    private char gender;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        isNameValid(name);
        this.name = name;
    }


    private boolean isNameValid(String name) {
        String[] tokens = name.split(" ");

        if (tokens.length != 2) {
            throw new IllegalArgumentException("Name is not valid. Needs a first and second name.");
        }
        String DummyFornavn = tokens[0];
        String DummyEtternavn = tokens[1];
        if ((DummyFornavn.length() > 1) && (DummyFornavn.matches("[a-zA-Z\s]+")) && (DummyEtternavn.length() > 1) && (DummyEtternavn.matches("[a-zA-Z\s]+"))) {
            this.fornavn = DummyFornavn;
            this.etternavn = DummyEtternavn;
            return true;
        } else {
            throw new IllegalArgumentException("Name is not valid. Must be minimum two letters and only contain letters.");
        }
    }


    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        isEmailValid(email);
        Email = email;
    }
    private boolean isEmailValid(String email) {
        if (email != null) {
            String[] tokens = email.split("\\.");
            if (tokens.length == 3) {
                if (landkode.contains(tokens[2])) {
                    if (tokens[0].equals(fornavn) || tokens[0].equals(fornavn.toLowerCase())) {
                        String[] tokens2 = tokens[1].split("@");
                        if (tokens2[0].equals(etternavn) && !tokens2[1].equals("")|| tokens2[0].equals(etternavn.toLowerCase()) && !tokens2[1].equals("")) {
                            return true;
                        } else {
                            throw new IllegalArgumentException("Not valid email1.");
                        }
                    } else {
                        throw new IllegalArgumentException("Not valid email2.");
                    }
                } else {
                    throw new IllegalArgumentException("Not valid email3.");
                }
            } else {
                    throw new IllegalArgumentException("Not valid email4.");
            }
        } else {
            throw new IllegalArgumentException("Not valid email5.");
        } 
    } 





    public Date getBirthday() {
        return birthday;
    }

    private boolean before(Date birthday) {
        Date today = new Date();
        boolean result = today.before(birthday);
        return result;
    }

    public void setBirthday(Date birthday) {
        if (before(birthday)) {
            throw new IllegalArgumentException("Birthday cannot be in the future.");
        } else {
            this.birthday = birthday;
        }
        
    }
    public char getGender() {
        return gender;
    }
    public void setGender(char gender) {
        if (gender == 'M' || gender == 'F' || gender == '\0') {
            this.gender = gender;
        } else {
            throw new IllegalArgumentException("Not valid gender. Needs to be M, F or \0.");
        }
    }


    public Person() {

    }

    @Override
    public String toString() {
        return "Name: " + name + ", Email: " + Email + ", birtday: " + birthday + ", gender: " + gender;
    }

    public static void main(String[] args) {
        Person Per = new Person();
        Per.setName("Per Viggo");
        Per.setEmail("per.viggo@ntnu.no");
        System.out.println(Per);

    }

    

}
