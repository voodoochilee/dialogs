import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;


public class MainWindow extends JFrame {
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu fileMenu = new JMenu("File");
	private JMenu customerMenu = new JMenu("Customer");
	private JMenu editMenu = new JMenu("Edit");
	private JMenuItem newMenuItem = new JMenuItem("New");
	private JMenuItem openMenuItem = new JMenuItem("Open");
	private JMenuItem saveMenuItem = new JMenuItem("Save");
	private JMenuItem newCustomerMenuItem = new JMenuItem("New Customer");
	
	private List<Customer> customers = new ArrayList<Customer>();
	
	CustomersTableModel tableModel = new CustomersTableModel(customers);
	JTable table = new JTable(tableModel);
	JScrollPane scrollPane = new JScrollPane(table);
	
	public MainWindow() {
		this.setLayout(new MigLayout());
		this.setLocationRelativeTo(null);
		this.setJMenuBar(menuBar);
		menuBar.add(fileMenu);
		menuBar.add(customerMenu);
		menuBar.add(editMenu);
		fileMenu.add(newMenuItem);
		fileMenu.add(openMenuItem);
		fileMenu.add(saveMenuItem);
		customerMenu.add(newCustomerMenuItem);
		
		newCustomerMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				CustomerDialog dialog = new CustomerDialog(MainWindow.this, true);
				dialog.setLocationRelativeTo(MainWindow.this);
				dialog.pack();
				dialog.setVisible(true);
				customers.add(dialog.getCustomer());
				tableModel.fireTableDataChanged();
				System.out.println(customers.size());
			}
			
		});
		
		saveMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				
				FileOutputStream fos = null;
				try {
					fos = new FileOutputStream("john.dat");
					ObjectOutputStream out = new ObjectOutputStream(fos);
					out.writeObject(customers);
				} 
				catch (IOException e) {
					e.printStackTrace();
				} 
				finally {
					try {
						fos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
		});
		
		openMenuItem.addActionListener(new ActionListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					FileInputStream fis = new FileInputStream("john.dat");
					ObjectInputStream in = new ObjectInputStream(fis);
					customers = (List<Customer>) in.readObject();
					tableModel.setCustomers(customers);
					tableModel.fireTableDataChanged();
				} 
				catch (IOException e) {
					e.printStackTrace();
				} 
				catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
				
			}
			
		});
		
		customers.add(new Customer("John Smith", "Slane", 123.45));
		customers.add(new Customer("Jane Doe", "Navan", 456.99));
		customers.add(new Customer("Derry Lynn", "Fermanagh", 546.77));
		this.add(scrollPane);
	}
	
	public static void main(String[] args) {
		MainWindow window  = new MainWindow();
		window.setDefaultCloseOperation(EXIT_ON_CLOSE);
		window.setSize(500, 300);
		window.setVisible(true);
		String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
		try {
			UIManager.setLookAndFeel(lookAndFeel);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
