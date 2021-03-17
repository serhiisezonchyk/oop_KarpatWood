package GUI;


import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;


public class mainGui {

	private JFrame frame;


	private Image img = null;
	{
		img = Toolkit.getDefaultToolkit().createImage(getClass().getResource("/png/fon.png"));
	}
	private JPanel panel = new JPanel() {

		public void paintComponent(Graphics g)  {
			super.paintComponent(g);
			g.drawImage(img, 0, 0, this);
		}
	};
	public JLabel lblMachine1;
	public JLabel lblCar2;
	public JLabel lblperson1;
	public JLabel tree3;
	public JLabel lblperson3;
	public JLabel lblMachine2;
	public JLabel lblperson2;
	public JLabel tree2;
	public JLabel lblRaw;
	public JLabel lblCar1;
	public JLabel lblExportbox;

	public boolean tree1_flag;
	public boolean tree2_flag;
	public boolean tree3_flag;
	public int car1_flag;
	public int car2_flag;
	public boolean machine1_flag;
	public boolean machine2_flag;

	private int rawCount = 0; 

	public int getRawCount() {
		return rawCount;
	}

	public void setRawCount(int rawCount) {
		this.rawCount = rawCount;
	}

	java.net.URL urlTree = mainGui.class.getResource("/png/tree.png");
	java.net.URL urlCar1close = mainGui.class.getResource("/png/Car1.png");
	java.net.URL urlCar2close = mainGui.class.getResource("/png/Car2.png");
	java.net.URL urlCar1open = mainGui.class.getResource("/png/Car1open.png");
	java.net.URL urlCar2open = mainGui.class.getResource("/png/Car2open.png");
	java.net.URL urlPerson = mainGui.class.getResource("/png/person.png");
	java.net.URL urlMachine = mainGui.class.getResource("/png/Machine1.png");
	java.net.URL urlRawboxEmpty = mainGui.class.getResource("/png/rawbox.png");
	java.net.URL urlRawbox = mainGui.class.getResource("/png/sklad.png");
	java.net.URL urlExportbox = mainGui.class.getResource("/png/exportbox.png");
	java.net.URL urlCarInTransit = mainGui.class.getResource("/png/inTransit.png");

	java.net.URL urlRiver = mainGui.class.getResource("/gifs/river.gif");
	java.net.URL urlPersonActive = mainGui.class.getResource("/gifs/person.gif");
	java.net.URL urlMachineActive = mainGui.class.getResource("/gifs/Machine1.gif");
	java.net.URL urlTreeActive = mainGui.class.getResource("/gifs/treee.gif");
	private JLabel lblRiver;
	private JLabel lblRaw1;
	private JSpinner spinner;
	private JLabel tree1;

	public void person_active(JLabel person,boolean flag) {
		if(!flag) {
			person.setIcon(new ImageIcon(urlPerson));
		} else {
			person.setIcon(new ImageIcon(urlPersonActive));
		}
	}

	public void machine_active(JLabel machine,boolean flag) {
		if(!flag) {
			machine.setIcon(new ImageIcon(urlMachine));
		} else {
			machine.setIcon(new ImageIcon(urlMachineActive));
		}
	}

	public void tree_active(JLabel tree,boolean flag) {
		if(!flag) {
			tree.setIcon(new ImageIcon(urlTree));
		} else {
			tree.setIcon(new ImageIcon(urlTreeActive));
		}
	}

	public void rawbox_count(int count) {
		if(count == 0) {
			lblRaw.setIcon(new ImageIcon(urlRawboxEmpty));
		} else {
			lblRaw.setIcon(new ImageIcon(urlRawbox));
		}
	}

	public void car1_active(int flag) {
		switch (flag) {
		case 0:
			lblCar1.setIcon(new ImageIcon(urlCar1close));
			break;

		case 1:
			lblCar1.setIcon(new ImageIcon(urlCar1open));
			break;
		case 2:
			lblCar1.setIcon(new ImageIcon(urlCarInTransit));
			break;
		}
	}

	public void car2_active(int flag) {
		switch (flag) {
		case 0:
			lblCar2.setIcon(new ImageIcon(urlCar2close));
			break;

		case 1:
			lblCar2.setIcon(new ImageIcon(urlCar2open));
			break;

		case 2:
			lblCar2.setIcon(new ImageIcon(urlCarInTransit));
			break;
		}
	}






	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainGui window = new mainGui();
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
	public mainGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {
		try 
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) 
		{
			System.out.println("Error setting native LAF: " + e);
		}
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Project of Sergey Sezonchyk (Karpat Wood)");
		frame.setContentPane(panel);
		panel.setLayout(null);

		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				car1_active(0);
				car2_active(0);
				rawbox_count(rawCount);
				tree_active(tree1, true);
				tree_active(tree2, true);
				tree_active(tree3, true);
				person_active(lblperson1, true);
				person_active(lblperson2, true);
				person_active(lblperson3, true);
				machine_active(lblMachine1, true);
				machine_active(lblMachine2, true);
			}
		});
		btnStart.setBounds(1577, 967, 129, 43);
		panel.add(btnStart);

		JButton btnStop = new JButton("Stop");
		btnStop.setBounds(1740, 967, 113, 43);
		panel.add(btnStop);

		lblRiver = new JLabel("");
		lblRiver.setBounds(319, 872, 669, 149);
		panel.add(lblRiver);
		
		lblCar1 = new JLabel("");
		lblCar1.setBounds(1534, -12, 348, 532);
		panel.add(lblCar1);

		lblCar2 = new JLabel("");
		lblCar2.setBounds(1534, 437, 348, 573);
		panel.add(lblCar2);

		tree2 = new JLabel("");
		tree2.setBounds(64, 353, 186, 249);
		panel.add(tree2);

		tree3 = new JLabel("");
		tree3.setBounds(85, 653, 186, 249);
		panel.add(tree3);

		lblperson1 = new JLabel("");
		lblperson1.setBounds(264, 772, 76, 105);
		panel.add(lblperson1);

		lblperson2 = new JLabel("");
		lblperson2.setBounds(239, 472, 76, 105);
		panel.add(lblperson2);

		lblperson3 = new JLabel("");
		lblperson3.setBounds(264, 170, 76, 105);
		panel.add(lblperson3);

		lblMachine1 = new JLabel("");
		lblMachine1.setBounds(526, 130, 496, 369);
		panel.add(lblMachine1);

		lblMachine2 = new JLabel("");
		lblMachine2.setBounds(526, 626, 477, 384);
		panel.add(lblMachine2);

		lblRaw = new JLabel("");
		lblRaw.setBounds(608, 472, 317, 176);
		panel.add(lblRaw);

		lblExportbox = new JLabel("");
		lblExportbox.setBounds(1167, 458, 317, 176);
		panel.add(lblExportbox);
		
		lblRaw1 = new JLabel("");
		lblRaw1.setBounds(156, 261, 46, 14);
		panel.add(lblRaw1);
		
		spinner = new JSpinner();
		spinner.setBounds(1229, 797, 29, 20);
		panel.add(spinner);
		
		tree1 = new JLabel("");
		tree1.setBounds(64, 69, 186, 249);
		panel.add(tree1);

		frame.setBounds(20, 10, 1888, 1060);
		new Thread(()->{
			car1_active(0);
			car2_active(0);
			rawbox_count(rawCount);
			tree_active(tree1, false);
			tree_active(tree2, false);
			tree_active(tree3, false);
			person_active(lblperson1, false);
			person_active(lblperson2, false);
			person_active(lblperson3, false);
			machine_active(lblMachine1, false);
			machine_active(lblMachine2, false);
			lblExportbox.setIcon(new ImageIcon(urlExportbox));
			lblRiver.setIcon(new ImageIcon(urlRiver));
		}).start();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
