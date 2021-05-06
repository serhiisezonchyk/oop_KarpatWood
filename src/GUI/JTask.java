package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class JTask extends JDialog implements IDialog {

	private final JPanel contentPanel = new JPanel();
	private boolean english = true;
	private JLabel taskLabel;
	private JLabel phLab;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JTask dialog = new JTask();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JTask() {
		setResizable(false);
		setTitle("About task");
		setBounds(100, 100, 670, 388);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		setLocation((screenSize.width-this.getWidth())/2, (screenSize.height-this.getHeight())/2);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(143, 188, 143));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			taskLabel = new JLabel("");
			taskLabel.setFont(new Font("Times New Roman", Font.PLAIN, 17));
			taskLabel.setBounds(28, 68, 347, 247);
			contentPanel.add(taskLabel);
		}
		taskLabel.setText("<html>In the Carpathians, a team of lumberjacks harvests the forest.<br>It takes some time to cut down and process the timber.<br>A number of mobile woodworking machines operate in the forest,<br>making a rock from the trunks.<br>" + 
				"The roundabout is loaded into trucks by a loader. Loggers take<br>the trunks to the train and return to the forest<html>");
		{
			JLabel taskName = new JLabel("SibirLes");
			taskName.setHorizontalAlignment(SwingConstants.CENTER);
			taskName.setFont(new Font("Yu Gothic", Font.BOLD, 29));
			taskName.setBounds(207, 22, 227, 48);
			contentPanel.add(taskName);
		}
		{
			phLab = new JLabel("");
			phLab.setBounds(385, 119, 256, 154);
			contentPanel.add(phLab);
		}
		phLab.setIcon(new ImageIcon(JDev.class.getResource("/pngDialog/photo.jpg")));
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(143, 188, 143));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setBackground(new Color(107, 142, 35));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JTask.this.setVisible(false);
					}
				});
				okButton.setActionCommand("Ok");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Укр");
				cancelButton.setBackground(new Color(107, 142, 35));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						boolean test = !english;
						if(test) {
							taskLabel.setText("<html>In the Carpathians, a team of lumberjacks harvests the forest.<br>It takes some time to cut down and process the timber.<br>A number of mobile woodworking machines operate in the forest,<br>making a rock from the trunks.<br>" + 
									"The roundabout is loaded into trucks by a loader. Loggers take<br>the trunks to the train and return to the forest<html>");
							cancelButton.setText("Укр");
							english=true;
						}else {
							taskLabel.setText("<html>У Карпатах бригада лісорубів заготовляє ліс.<br>На спилювання та обро-бку дерева лісоруб витрачає деякий час.<br>У лісі працює кілька пересувних де-ревообробних верстатів, що роблять<br>із стовбурів кругляк." + 
									"Кругляк за допомогою навантажувача навантажують у вантажівки.<br>Лісовози відвозять стовбури до складу і повертаються до лісу<html>");
							cancelButton.setText("Eng");
							english = false;
						}
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
