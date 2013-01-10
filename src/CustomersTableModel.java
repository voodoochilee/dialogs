import java.util.List;

import javax.swing.table.AbstractTableModel;


public class CustomersTableModel extends AbstractTableModel {
	
	private List<Customer> customers;
	
	public CustomersTableModel(List<Customer> customers) {
		this.customers = customers;
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		return customers.size();
	}
	
	@Override
	public String getColumnName(int column) {
		if(column == 0) {
			return "Name";
		}
		if(column == 1) {
			return "Address";
		}
		if(column == 2) {
			return "Balance";
		}
		return null;
	}

	@Override
	public Object getValueAt(int rowNumber, int columnNumber) {
		Customer customer = customers.get(rowNumber);
		if(columnNumber == 0) {
			return customer.getName();
		}
		if(columnNumber == 1) {
			return customer.getAddress();
		}
		if(columnNumber == 2) {
			return customer.getBalance();
		}
		return null;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	

}
