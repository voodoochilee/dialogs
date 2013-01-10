import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;


public class CustomerDialog extends JDialog {

	private JLabel nameLabel = new JLabel("Name");
	private JLabel addressLabel = new JLabel("Address");
	private JLabel balanceLabel = new JLabel("Balance");
	private JTextField nameTextField = new JTextField(20);
	private JTextField addressTextField = new JTextField(20);
	private JTextField balanceTextField = new JTextField(20);
	private JButton okButton = new JButton("OK");
	private JButton cancelButton = new JButton("Cancel");
	
	private Customer customer;

	public CustomerDialog(Frame owner, boolean modal) {
		super(owner, modal);
		this.setLayout(new MigLayout());
		this.add(nameLabel);
		this.add(nameTextField, "wrap");
		this.add(addressLabel);
		this.add(addressTextField, "wrap");
		this.add(balanceLabel);
		this.add(balanceTextField, "wrap paragraph");
		this.add(okButton, "skip 1, tag ok");
		this.add(cancelButton, "tag cancel");
		
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CustomerDialog.this.setVisible(false);
			}
			
		});
		
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nameTextField.getText();
				String address = addressTextField.getText();
				String balanceAsString = balanceTextField.getText();
				double balance = Double.parseDouble(balanceAsString);
				Customer newCustomer = new Customer(name, address, balance);
				CustomerDialog.this.setCustomer(newCustomer);
				CustomerDialog.this.setVisible(false);
			}
			
		});
		
	}
	
	public Customer getCustomer() {
		return this.customer;
	}
	
	public void setCustomer(Customer c) {
		this.customer = c;
	}
	

}
