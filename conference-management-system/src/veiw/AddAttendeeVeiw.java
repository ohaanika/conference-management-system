package veiw;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;



public class AddAttendeeVeiw extends JFrame {
	private JPanel contentPane;
	private JTextField txttype;
	private int tableNumber = -1;
	
	private JComboBox comboBox;
	private JComboBox comboBox1;
	private JComboBox comboBox2;
	private JButton btnOrder;
	private String error = null;
	private JLabel errorMessage;
	
	public AddAttendeeVeiw() {
		initComponents();
		refreshData();
	}
	
	public void initComponents() {
	
	errorMessage = new JLabel(error);
	errorMessage.setForeground(Color.RED);
	errorMessage.setBounds(100, 250, 350, 29);
	
	setTitle(" Add Attendee");
	setBounds(100, 100, 492, 329);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	 
	
	
	
	txttype = new JTextField();
	txttype.setText("type");
	txttype.setBounds(200, 25, 100, 30);
	contentPane.add(txttype);
	txttype.setColumns(10);
	txttype.addFocusListener(new FocusAdapter() {
		@Override
		public void focusGained(FocusEvent e) {
			txttype.setText("");
		}

		@Override
		public void focusLost(FocusEvent e) {
			if (txttype.getText().isEmpty()) {
				txttype.setText("type");
			}
		}
	});
	
	}
}
