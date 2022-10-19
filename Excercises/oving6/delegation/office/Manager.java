package oving6.delegation.office;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.function.BinaryOperator;

public class Manager implements Employee{
    private Collection<Employee> employees;
    private int taskCount;
    private int totalResources;
	private Random rand = new Random();

    public Manager (Collection<Employee> employees) {
        if (employees.isEmpty()) {
            throw new IllegalArgumentException("Ingen employees");
        } else {
            this.employees = employees;
        }
    }





    private Employee delegation() {
		int tilfeldig = rand.nextInt(employees.size()); 
        List<Employee> tmp = new ArrayList<>(employees);
		return tmp.get(tilfeldig);
	}

    @Override
    public double doCalculations(BinaryOperator<Double> operation, double value1, double value2) {
        taskCount++;
        return delegation().doCalculations(operation, value1, value2);
    }

    @Override
    public void printDocument(String document) {
        taskCount++;
        delegation().printDocument(document);
    }

    @Override
    public int getTaskCount() {
        return taskCount;
    }

    @Override
    public int getResourceCount() {
        totalResources = 0;
        for (Employee employee : employees) {
            totalResources += employee.getResourceCount();
        }
        totalResources++;
        return totalResources; 
    }

    
    @Override
    public String toString() {
        return "Manager [employees=" + employees + ", taskCount=" + getTaskCount() + ", totalResources="
                + getResourceCount() + "]";
    }



    public static void main(String[] args) {
        Collection<Employee> employees1 = new ArrayList<>();
        Printer printer1 = new Printer();
        Clerk clerk1 = new Clerk(printer1);
        Clerk clerk2 = new Clerk(printer1);
        Clerk clerk3 = new Clerk(printer1);
        Clerk clerk4 = new Clerk(printer1);
        employees1.add(clerk1);
        employees1.add(clerk2);
        employees1.add(clerk3);
        employees1.add(clerk4);
        Manager manager = new Manager(employees1);
/*         clerk1.printDocument("document1");
        clerk1.printDocument("document2");
        clerk1.printDocument("document3");
        clerk1.doCalculations((a,b) -> a+b , 1, 2);
        clerk2.printDocument("document1");
        clerk2.printDocument("document2");
        clerk2.printDocument("document3");
        clerk2.doCalculations((a,b) -> a-b , 1, 2); */

        manager.printDocument("dokument");
        manager.printDocument("dokument1");
        manager.printDocument("dokument2");
        manager.printDocument("dokument3");
        manager.printDocument("dokument4");
        manager.doCalculations((a,b) -> a-b , 1, 2);
        //System.out.println(printer1.getPrintHistory(clerk1));  
        System.out.println(manager);
        System.out.println(printer1.getPrintHistory(clerk1));
        System.out.println(printer1.getPrintHistory(clerk2));
        System.out.println(printer1.getPrintHistory(clerk3));
        System.out.println(printer1.getPrintHistory(clerk4));
        double eff = (double) manager.getTaskCount()/manager.getResourceCount();
        System.out.println("Effectiveness: "+ eff);

        //Manager manager2 = new Manager(manager);



    }

}
