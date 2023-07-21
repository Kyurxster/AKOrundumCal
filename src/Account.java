
public class Account {
	private String username;
	private int orundum;
	private int op;
	
	// constructor
	public Account(String username) {
		this.username=username;
		this.orundum=0;
		this.op=0;
	} 
	public Account(String username, int orundum, int op) {
		this.username=username;
		this.orundum=orundum;
		this.op=op;
	}
	
	// retrieve info
	public String getUsername() {
		return username;
	}
	public int getOrundum() {
		return orundum;
	}
	public int getOP() {
		return op;
	}
	
	// set info
	public void setOrundum(int orundum) {
		this.orundum=orundum;
	}
	public void setOP(int op) {
		this.op=op;
	}
	
	public void printInfo() {
		System.out.println("Username: "+username);
		System.out.println("Orundum: "+orundum);
		System.out.println("OP: "+op);
	}
}
