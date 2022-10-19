package oving4;

public class Partner {
    String name;
    Partner partner;
    
    public String getName() {
        return name;
    }
    
    public Partner getPartner() {
        return partner;
    }

	public void setPartner(Partner partner) {
		if (this.partner == partner) {								
			return;
		}
		Partner oldPartner = this.partner;
		this.partner = partner;										
		if (oldPartner != null && oldPartner.partner == this) {		
			oldPartner.setPartner(null);
		}if (this.partner != null) {								
			this.partner.setPartner(this);
		}
		
	}

    public Partner(String name) {
        this.name = name;
    }
    
    


    @Override
    public String toString() {
        return getName();
    }

    public static void main(String[] args) {
        Partner viggo = new Partner("viggo");
        Partner per = new Partner("per");
        System.out.println(viggo);
        //viggo.getPartner();
        viggo.setPartner(per);
        viggo.getPartner();
        System.out.println(viggo);
    }

    
}
