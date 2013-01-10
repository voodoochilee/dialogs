import java.io.Serializable;


public class Customer implements Serializable {
	
	private String name;
	private String address;
	private double balance;
	
	
	public Customer(String name, String address, double balance) {
		super();
		this.name = name;
		this.address = address;
		this.balance = balance;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	
}
