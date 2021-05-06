package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class JDev extends JDialog implements IDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDev dialog = new JDev();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDev() {
		setResizable(false);
		setTitle("About developers");
		setBounds(100, 100, 670, 388);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		setLocation((screenSize.width-this.getWidth())/2, (screenSize.height-this.getHeight())/2);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(143, 188, 143));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		
		
		JLabel lblNewLabel = new JLabel("Developers");
		lblNewLabel.setFont(new Font("Yu Gothic", Font.BOLD, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(189, 11, 279, 30);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Pinchuk S.S");
		lblNewLabel_1.setFont(new Font("Yu Gothic", Font.BOLD, 17));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(20, 294, 141, 21);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Sezonchyk S.S");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Yu Gothic", Font.BOLD, 17));
		lblNewLabel_1_1.setBounds(503, 294, 141, 21);
		contentPanel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("Students of group PE-191");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		lblNewLabel_2.setBounds(187, 52, 281, 14);
		contentPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Name:");
		lblNewLabel_3.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(304, 77, 46, 14);
		contentPanel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Pinchuk Stanislav");
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		lblNewLabel_3_1.setBounds(171, 96, 128, 14);
		contentPanel.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_2 = new JLabel("Sezonchyk Serhii");
		lblNewLabel_3_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_2.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		lblNewLabel_3_2.setBounds(355, 94, 128, 14);
		contentPanel.add(lblNewLabel_3_2);
		
		JLabel lblNewLabel_3_3 = new JLabel("Grade book num:");
		lblNewLabel_3_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_3.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		lblNewLabel_3_3.setBounds(264, 121, 128, 14);
		contentPanel.add(lblNewLabel_3_3);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("\u2116");
		lblNewLabel_3_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1_1.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		lblNewLabel_3_1_1.setBounds(171, 146, 128, 14);
		contentPanel.add(lblNewLabel_3_1_1);
		
		JLabel lblNewLabel_3_1_2 = new JLabel("\u2116");
		lblNewLabel_3_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1_2.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		lblNewLabel_3_1_2.setBounds(355, 147, 128, 14);
		contentPanel.add(lblNewLabel_3_1_2);
		
		JLabel lblNewLabel_3_3_1 = new JLabel("Email:");
		lblNewLabel_3_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_3_1.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		lblNewLabel_3_3_1.setBounds(264, 171, 128, 14);
		contentPanel.add(lblNewLabel_3_3_1);
		
		JLabel lblNewLabel_3_1_3 = new JLabel("Pinchuk Stanislav");
		lblNewLabel_3_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1_3.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		lblNewLabel_3_1_3.setBounds(171, 198, 128, 14);
		contentPanel.add(lblNewLabel_3_1_3);
		
		JLabel lblNewLabel_3_1_4 = new JLabel("sezonchik@ukr.net");
		lblNewLabel_3_1_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1_4.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		lblNewLabel_3_1_4.setBounds(355, 199, 128, 14);
		contentPanel.add(lblNewLabel_3_1_4);
		
		JLabel lblNewLabel_3_3_1_1 = new JLabel("Number:");
		lblNewLabel_3_3_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_3_1_1.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		lblNewLabel_3_3_1_1.setBounds(264, 229, 128, 14);
		contentPanel.add(lblNewLabel_3_3_1_1);
		
		JLabel lblNewLabel_3_1_3_1 = new JLabel("+380636495216");
		lblNewLabel_3_1_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1_3_1.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		lblNewLabel_3_1_3_1.setBounds(171, 254, 128, 14);
		contentPanel.add(lblNewLabel_3_1_3_1);
		
		JLabel lblNewLabel_3_1_3_1_1 = new JLabel("+380935182100");
		lblNewLabel_3_1_3_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1_3_1_1.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		lblNewLabel_3_1_3_1_1.setBounds(355, 255, 128, 14);
		
		
		contentPanel.add(lblNewLabel_3_1_3_1_1);
		
		JLabel labelStas = new JLabel("");
		labelStas.setBounds(10, 53, 151, 230);
		contentPanel.add(labelStas);
		
		JLabel labelSergey = new JLabel("");
		labelSergey.setBounds(493, 53, 151, 230);
		
		contentPanel.add(labelSergey);
		labelSergey.setIcon(new ImageIcon(JDev.class.getResource("/pngDialog/sergey.jpg")));
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(143, 188, 143));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setForeground(new Color(0, 0, 0));
				okButton.setBackground(new Color(107, 142, 35));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JDev.this.setVisible(false);
					}
				});
				okButton.setActionCommand("Ok");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("OK!");
				cancelButton.setBackground(new Color(107, 142, 35));
				cancelButton.setForeground(new Color(0, 0, 0));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JDev.this.setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
