package principal;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Ventana {

	private JFrame mainFrame;
	private JTextField txtBuscar;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	
	
	
	private String [] columnName= {"ID", "Nombre", "Especie", "Raza", "Genero"};
	private Object [][] tableContent = new Object[29][columnName.length];
	private JTable table_1;
	private ConexionDB conexionDB;	    
		
		

	
	
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana window = new Ventana();
					window.frmRegistroDeAnimales.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	*/
	
	
	public Ventana() {
		
		try {
			initialize();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mainFrame = new JFrame();
		mainFrame.setTitle("Registro de Animales");
		mainFrame.setBounds(100, 100, 800, 600);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setResizable(false);
		
		JPanel panel = new JPanel();
		mainFrame.getContentPane().add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{89, 89, 71, 89, 0, 0, 0, 0, 0, 0, 0, 53, 0, 0, 0, -4, 22, 0};
		gbl_panel.rowHeights = new int[]{23, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		
		JButton btnNewButton = new JButton("A\u00F1adir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		panel.add(btnNewButton, gbc_btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Actualizar");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 0;
		panel.add(btnNewButton_1, gbc_btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Eliminar");
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_2.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnNewButton_2.gridx = 2;
		gbc_btnNewButton_2.gridy = 0;
		panel.add(btnNewButton_2, gbc_btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Buscar");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtBuscar.getText().length()==0) {
					conexionDB.setResultSet("SELECT * FROM animales");
					setTableContent(conexionDB.getResultSet());
				}
				/*
				else if() {
					
				}
				*/
				else{					
					//bucles para limpiar la tabla
					for (int i = 0; i < table_1.getRowCount(); i++) {
					      for(int j = 0; j < table_1.getColumnCount(); j++) {
					          table_1.setValueAt("", i, j);
					      }
					   }

				}
				txtBuscar.setText(null);
				table_1.updateUI();
			}
		});
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton_3.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_3.gridx = 8;
		gbc_btnNewButton_3.gridy = 0;
		panel.add(btnNewButton_3, gbc_btnNewButton_3);
		
		txtBuscar = new JTextField();
		GridBagConstraints gbc_txtBuscar = new GridBagConstraints();
		gbc_txtBuscar.gridwidth = 4;
		gbc_txtBuscar.insets = new Insets(0, 0, 0, 5);
		gbc_txtBuscar.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBuscar.gridx = 9;
		gbc_txtBuscar.gridy = 0;
		panel.add(txtBuscar, gbc_txtBuscar);
		txtBuscar.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		mainFrame.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(38, 81, 46, 14);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Especie");
		lblNewLabel_1.setBounds(38, 120, 46, 14);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Raza");
		lblNewLabel_2.setBounds(49, 163, 35, 14);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Genero");
		lblNewLabel_3.setBounds(38, 205, 46, 14);
		panel_1.add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setBounds(88, 78, 86, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(88, 117, 86, 20);
		panel_1.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(90, 160, 86, 20);
		panel_1.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(90, 202, 86, 20);
		panel_1.add(textField_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(266, 44, 474, 483);
		panel_1.add(scrollPane);
		
		table_1 = new JTable(tableContent, columnName);
		table_1.setFillsViewportHeight(true);
		scrollPane.setViewportView(table_1);
		
		conexionDB= new ConexionDB();
		
		mainFrame.setVisible(true); /*HACER VISIBLE EL FRAME*/
	}
	
	/*Getters & Setters*/
	
	
	
	public void setTableContent(ResultSet rs) {
		try {
			/*
		rs.last();
		tableContent= new Object [rs.getRow()][columnNumbers];
		rs.first();
		*/
		
		for (int i = 0; i < tableContent.length; i++) {
			
			
				if(rs.next()) {
					tableContent[i][0]=rs.getLong(1);
					tableContent[i][1]=rs.getString(2);
					tableContent[i][2]=rs.getString(3);
					tableContent[i][3]=rs.getString(4);
					tableContent[i][4]=rs.getString(5);
				}else break;
			}
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

		}
	}

