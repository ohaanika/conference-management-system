package veiw;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse223.resto.controller.InvalidInputException;




class MainMenuClient extends JFrame {
	
	
	
	private final JPanel buttonsPane = new JPanel();
	private final JPanel contentPane = new JPanel();
	//private TableVisualizer tableVisualizer;
	//private RestoApp restoApp = RestoAppApplication.getRestoApp();
	private JLabel errorMessage;
	private String error = null;
	//public static HashMap<Seat, Integer> seatsh;
	public MainMenuClient() {
	errorMessage.setForeground(Color.RED);
	errorMessage.setBounds(361, 500, 350, 29);
	buttonsPane.add(errorMessage);

	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setTitle("Conference Management System");
	setBounds(0, 0, 1200, 900);
	getContentPane().setLayout(new BorderLayout());
	buttonsPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	
	
	
	JButton btnOrderItem = new JButton("Create Attendee");
	btnOrderItem.setBounds(188, 536, 208, 25);
	buttonsPane.add(btnOrderItem);
	btnOrderItem.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent evt) {
			try {
				tableVisualizer.OrderItem();
				errorMessage.setText(null);
			} catch (InvalidInputException e) {
				error = e.getMessage();
				errorMessage.setText(e.getMessage());
			}
			
		}
	});
	
}}
