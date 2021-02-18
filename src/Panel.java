import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;


public class Assignment extends JFrame implements ActionListener {

	//MySQL configuration field
	private final String userName = "root";
	private final String password = "";
	private final String serverName = "localhost";
	private final int portNumber = 3306;
	private final String dbName = "assignment1";
	private final String tableName = "employee";
	
	//button set up
	private JButton btnNext = new JButton("Next");
	private JButton btnPrevious = new JButton("Previous");
	private JButton btnClear = new JButton("Clear");
	private JButton btnAdd = new JButton("Add");
	private JButton btnDelete = new JButton("Delete");
	private JButton btnUpdate = new JButton("Update");
	
	
	//text field setup
	JTextPane textPane = new JTextPane();
	JTextPane textPane_1 = new JTextPane();
	JTextPane textPane_2 = new JTextPane();
	JTextPane textPane_3 = new JTextPane();
	JTextPane textPane_4 = new JTextPane();
	JTextPane textPane_5 = new JTextPane();
	
	private JPanel contentPane;
	
	public int index = 0;
	
	Statement s = null;
	ResultSet rs = null;
	
	Employess employee = new Employess(); //employee model 
	
	//Window set up, with all the button link to action listener
	public Assignment() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 742);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
				
		btnNext.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnNext.setBounds(467, 91, 141, 35);
		contentPane.add(btnNext);
		btnNext.addActionListener(this);
		
		btnPrevious.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnPrevious.setBounds(467, 231, 141, 35);
		contentPane.add(btnPrevious);
		btnPrevious.addActionListener(this);
		
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnClear.setBounds(467, 371, 141, 35);
		contentPane.add(btnClear);
		btnClear.addActionListener(this);
		
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnAdd.setBounds(56, 539, 141, 35);
		contentPane.add(btnAdd);
		btnAdd.addActionListener(this);
		
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnDelete.setBounds(246, 539, 141, 35);
		contentPane.add(btnDelete);
		btnDelete.addActionListener(this);
		
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnUpdate.setBounds(467, 539, 141, 35);
		contentPane.add(btnUpdate);
		btnUpdate.addActionListener(this);
		
		JLabel lblName = new JLabel("SSN:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblName.setBounds(45, 94, 92, 32);
		contentPane.add(lblName);
		
		JLabel label = new JLabel("Name:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 24));
		label.setBounds(45, 161, 92, 32);
		contentPane.add(label);
		
		JLabel lblDob = new JLabel("DOB:");
		lblDob.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblDob.setBounds(45, 232, 92, 32);
		contentPane.add(lblDob);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblAddress.setBounds(45, 300, 92, 32);
		contentPane.add(lblAddress);
		
		JLabel lblSalary = new JLabel("Salary:");
		lblSalary.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblSalary.setBounds(45, 372, 92, 32);
		contentPane.add(lblSalary);
		
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblGender.setBounds(45, 438, 92, 32);
		contentPane.add(lblGender);
		
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 24));
		textPane.setBounds(140, 94, 261, 32);
		contentPane.add(textPane);
		
		textPane_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		textPane_1.setBounds(140, 161, 261, 32);
		contentPane.add(textPane_1);
		
		textPane_2.setFont(new Font("Tahoma", Font.PLAIN, 24));
		textPane_2.setBounds(140, 232, 261, 32);
		contentPane.add(textPane_2);
		
		textPane_3.setFont(new Font("Tahoma", Font.PLAIN, 24));
		textPane_3.setBounds(140, 300, 261, 32);
		contentPane.add(textPane_3);
		
		textPane_4.setFont(new Font("Tahoma", Font.PLAIN, 24));
		textPane_4.setBounds(140, 372, 261, 32);
		contentPane.add(textPane_4);
		
		textPane_5.setFont(new Font("Tahoma", Font.PLAIN, 24));
		textPane_5.setBounds(140, 438, 261, 32);
		contentPane.add(textPane_5);
	}
	
	public Connection getConnection() throws SQLException {
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", this.userName);
		connectionProps.put("password", this.password);

		conn = DriverManager.getConnection("jdbc:mysql://"
				+ this.serverName + ":" + this.portNumber + "/" + this.dbName + "?serverTimezone=Europe/Dublin&useSSL=false",
				connectionProps);

		return conn;
	}
	
	
	//Next button press function, return and store to the employee constructor
	public Employess getNext() {
		Connection conn = null;
		try {
			conn = this.getConnection();
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
			return null;
		}
		
		try {
			System.out.println(Integer.toString(index));
			s = conn.createStatement ();
			s.executeQuery ("SELECT * FROM `employee` WHERE `id` = " + index);
			rs = s.getResultSet ();
			System.out.print(s);
			while(rs.next()) {
				employee.setName(rs.getString("Name"));
				employee.setAddress(rs.getString("Address"));
				employee.setBDate(rs.getDate("Bdate"));
				employee.setGender(rs.getString("Gender"));
				employee.setSalary(rs.getDouble("Salary"));
				employee.setSSN(rs.getInt("Ssn"));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}
	
	//delete button setup, based on the id of the employee
	public void delete() {
		Connection conn = null;
		try {
			conn = this.getConnection();
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
			return;
		}
		
		try {
			System.out.println(Integer.toString(index));
			s = conn.createStatement ();
			String command = "DELETE FROM `employee` WHERE id = " + index;
			s.execute(command);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return;
	}
	
	//setup previous button
	public Employess getPrevious() {
		Connection conn = null;
		try {
			conn = this.getConnection();
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
			return null;
		}
		
		try {
			System.out.println(Integer.toString(index));
			s = conn.createStatement ();
			s.executeQuery ("SELECT * FROM `employee` WHERE `id` = " + index);
			rs = s.getResultSet ();
			System.out.print(s);
			while(rs.next()) {
				employee.setName(rs.getString("Name"));
				employee.setAddress(rs.getString("Address"));
				employee.setBDate(rs.getDate("Bdate"));
				employee.setGender(rs.getString("Gender"));
				employee.setSalary(rs.getDouble("Salary"));
				employee.setSSN(rs.getInt("Ssn"));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return employee;
	}
	
	public void update() {
		Connection conn = null;
		try {
			conn = this.getConnection();
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
			return;
		}
		
		try {
			String name = textPane_1.getText();
			int ssn = Integer.parseInt(textPane.getText());
			String address = textPane_3.getText();
			double salary = Double.parseDouble(textPane_4.getText());
			String gender = textPane_5.getText();
			Date Bdate = Date.valueOf(textPane_2.getText());
			System.out.println(Integer.toString(index));
			s = conn.createStatement ();
			String command = "UPDATE `employee` SET `Name`='" 
					+ name +"',`Ssn`="
					+ ssn  +",`Address`='"
					+ address + "',`Salary`="
					+ salary + ",`Gender`='"
					+ gender + "',`Bdate`='"
					+ Bdate + "' WHERE id = " + index;
			System.out.println(command);
			s.execute(command);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return;
	}
	
	public void add() {
		Connection conn = null;
		try {
			conn = this.getConnection();
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
			return;
		}
		
		try {
			String name = textPane_1.getText();
			int ssn = Integer.parseInt(textPane.getText());
			String address = textPane_3.getText();
			double salary = Double.parseDouble(textPane_4.getText());
			String gender = textPane_5.getText();
			Date Bdate = Date.valueOf(textPane_2.getText());
			System.out.println(Integer.toString(index));
			s = conn.createStatement ();
			String command = "INSERT INTO `employee` (`Name`, `Ssn`, `Address`, `Salary`, `Gender`, `Bdate`, `id`) VALUES ('" + name +"','"
					+ ssn + "','" + address + "','" + salary + "','" + gender + "','" + Bdate + "','" + index++ + "')";
			System.out.println(command);
			s.execute(command);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return;
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnNext ) {
			index++;
			getNext();
			textPane.setText(Integer.toString(employee.getSSN()));
			textPane_1.setText(employee.getName());
			textPane_2.setText(employee.getBDate().toString());
			textPane_3.setText(employee.getAddress());
			textPane_4.setText(String.valueOf(employee.getSalary()));
			textPane_5.setText(employee.getGender());	
		}
		if(e.getSource() == btnUpdate) {
			update();
		}
		if(e.getSource() == btnDelete) {
			delete();
			textPane.setText(null);
			textPane_1.setText(null);
			textPane_2.setText(null);
			textPane_3.setText(null);
			textPane_4.setText(null);
			textPane_5.setText(null);
		}
		if(e.getSource() == btnPrevious) {
			index--;
			getPrevious();
			textPane.setText(Integer.toString(employee.getSSN()));
			textPane_1.setText(employee.getName());
			textPane_2.setText(employee.getBDate().toString());
			textPane_3.setText(employee.getAddress());
			textPane_4.setText(String.valueOf(employee.getSalary()));
			textPane_5.setText(employee.getGender());	
		}
		if(e.getSource() == btnClear) {
			textPane.setText(null);
			textPane_1.setText(null);
			textPane_2.setText(null);
			textPane_3.setText(null);
			textPane_4.setText(null);
			textPane_5.setText(null);
		}
		if(e.getSource() == btnAdd) {
			add();
		}
		
	}
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Assignment frame = new Assignment();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
