package oving6.delegation.office;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//import java.util.Map;

public class Printer {

	HashMap<Employee, ArrayList<String>> printHistorie;


    public Printer() {
		printHistorie = new HashMap<Employee, ArrayList<String>>();
	}

	public void printDocument(String document, Employee employee) {
        ArrayList<String> tmp = new ArrayList<String>();
		if(printHistorie.containsKey(employee)){
			tmp = printHistorie.get(employee);
		}
		tmp.add(document);
		printHistorie.put(employee, tmp);
    }

    public List<String> getPrintHistory(Employee employee) {
        if(printHistorie.get(employee) == null){
			return new ArrayList<String>();
		}
		return new ArrayList<String>(printHistorie.get(employee)); //printHistorie.get(employee)
    }

}
