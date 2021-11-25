import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GroceryList {
	
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	
	public void Connect()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/grocerylist", "root","");
            
        }
        catch (ClassNotFoundException ex)
        {
          ex.printStackTrace();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
 
    }
	public void table_load() {
		try {
			pst=con.prepareStatement("select * from grocery");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		
		}
		
	}
	
	

	private JFrame frame;
	private JTextField textGroceryName;
	private JTextField textQuantity;
	private JTextField textPrice;
	private JTable table;
	private JTextField textGroceryId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GroceryList window = new GroceryList();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GroceryList() {
		initialize();
		Connect();
		table_load();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 925, 537);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Grocery List");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(10, 10, 112, 35);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(24, 345, 592, 123);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Grocery Name");
		lblNewLabel_1.setFont(new Font("Serif", Font.BOLD, 14));
		lblNewLabel_1.setBounds(54, 61, 119, 31);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Quantity");
		lblNewLabel_1_1.setFont(new Font("Serif", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(227, 61, 119, 31);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Price");
		lblNewLabel_1_1_1.setFont(new Font("Serif", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(411, 61, 119, 31);
		panel.add(lblNewLabel_1_1_1);
		
		textGroceryName = new JTextField();
		textGroceryName.setBounds(54, 32, 96, 19);
		panel.add(textGroceryName);
		textGroceryName.setColumns(10);
		
		textQuantity = new JTextField();
		textQuantity.setColumns(10);
		textQuantity.setBounds(207, 32, 96, 19);
		panel.add(textQuantity);
		
		textPrice = new JTextField();
		textPrice.setColumns(10);
		textPrice.setBounds(382, 32, 96, 19);
		panel.add(textPrice);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
String groceryName,quantity,price;
				
				groceryName = textGroceryName.getText();
				quantity = textQuantity.getText();
				price = textPrice.getText();
				
				try {
					pst = con.prepareStatement(" insert into grocery set name= ?,quantity_Kg=?,price=?");
					pst.setString(1, groceryName);
					pst.setString(2, quantity);
					pst.setString(3, price);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "added to list..");
					table_load();
					          
					textGroceryName.setText("");
					textQuantity.setText("");
					textPrice.setText("");
					textGroceryName.requestFocus();
					   }
					 
					catch (SQLException e1)
					        {
					e1.printStackTrace();
					}
				
				
				
				
				
			}
		});
		btnNewButton.setBounds(711, 356, 85, 35);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		btnExit.setBounds(711, 73, 85, 35);
		frame.getContentPane().add(btnExit);
		
		JButton btnNewButton_1_1 = new JButton("Clear");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textGroceryName.setText("");
				textQuantity.setText("");
				textPrice.setText("");
				textGroceryName.requestFocus();
			}
		});
		btnNewButton_1_1.setBounds(711, 425, 85, 35);
		frame.getContentPane().add(btnNewButton_1_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 73, 592, 253);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(160, 10, 456, 35);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		textGroceryId = new JTextField();
		textGroceryId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					String id = textGroceryId.getText();
					 
	                pst = con.prepareStatement("select name,quantity_Kg,price from grocery where id = ?");
	                pst.setString(1, id);
	                ResultSet rs = pst.executeQuery();
	                if(rs.next()==true) {
	                	String groceryName = rs.getString(1);
		                String quantity = rs.getString(2);
		                String price = rs.getString(3);
		                
		                textGroceryName.setText(groceryName);
		                textQuantity.setText(quantity);
		                textPrice.setText(price);
	                	
	                }
	                else {
	                	textGroceryName.setText("");
			            textQuantity.setText("");
			            textPrice.setText("");
	                	
	                }
				}
				catch (SQLException ex) {
					
				}
			}
				
				
				
			
		});
		textGroceryId.setColumns(10);
		textGroceryId.setBounds(188, 10, 130, 19);
		panel_1.add(textGroceryId);
		
		JLabel lblNewLabel_1_2 = new JLabel("Grocery ID");
		lblNewLabel_1_2.setFont(new Font("Serif", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(75, 8, 103, 19);
		panel_1.add(lblNewLabel_1_2);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String groceryName,quantity,price,groceryId;
				
				groceryName = textGroceryName.getText();
				quantity = textQuantity.getText();
				price = textPrice.getText();
				groceryId  = textGroceryId.getText();
				try {
					pst = con.prepareStatement("update grocery set name= ?,quantity_Kg=?,price=? where id =?");
					pst.setString(1, groceryName);
					            pst.setString(2, quantity);
					            pst.setString(3, price);
					            pst.setString(4, groceryId);
					            pst.executeUpdate();
					            JOptionPane.showMessageDialog(null, "Item in the list Updated..");
					            table_load();
					          
					            textGroceryName.setText("");
					            textQuantity.setText("");
					            textPrice.setText("");
					            textGroceryName.requestFocus();
					}
				catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
				
			}
		});
		btnUpdate.setBounds(711, 145, 85, 35);
		frame.getContentPane().add(btnUpdate);
		
		JButton btndelete = new JButton("Delete");
		btndelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String groceryId;
				groceryId  = textGroceryId.getText();
				
				try {
				pst = con.prepareStatement("delete from grocery where id =?");
				            pst.setString(1, groceryId);
				            pst.executeUpdate();
				            JOptionPane.showMessageDialog(null, " Selected Item Deleted.. ");
				            table_load();
				          
				            textGroceryName.setText("");
				            textQuantity.setText("");
				            textPrice.setText("");
				            textGroceryName.requestFocus();
				            
				}
				  catch (SQLException e1) {
					  e1.printStackTrace();
				}
			}
		
			
		});
		btndelete.setBounds(711, 224, 85, 35);
		frame.getContentPane().add(btndelete);
	}

}
