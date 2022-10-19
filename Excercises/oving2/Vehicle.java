package oving2;

public class Vehicle {
    char vehicleType;
    char fuelType;
    String regnr;

    public char getVehicleType() {
        return vehicleType;
    }
    public void setVehicleType(char vehicleType) {
        this.vehicleType = vehicleType;
    }
    public char getFuelType() {
        return fuelType;
    }
    public void setFuelType(char fuelType) {
        this.fuelType = fuelType;
    }
    public String getRegnr() {
        return regnr;
    }
    public void setRegnr(String regnr) {
        this.regnr = regnr;
    }


    public Vehicle(char vehicleType, char fuelType, String regnr) {
        this.vehicleType = vehicleType;
        this.fuelType = fuelType;
        this.regnr = regnr;
    }

    @Override
    public String toString() {
        return "Type of vehicle: " + vehicleType + ", fuel: " + fuelType + ", regnr: " + regnr;
    }

    public static void main(String[] args) {
        Vehicle asd = new Vehicle('C', 'B', "ASD123");
        System.out.println(asd);
    }




    
}
