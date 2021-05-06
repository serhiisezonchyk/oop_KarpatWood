package GUI;


import model.*;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.RepaintManager;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;



public class mainGui {

	private JFrame frame;
	private JTask getTask = new JTask();
	private JDev getDevs = new JDev();
	
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

	java.net.URL urlSound = mainGui.class.getResource("/music/mario.wav");
	Sound sound =  new Sound(urlSound);
	Thread t1;
	
	Thread tm1;
	Thread tm2;
	private JLabel lblRiver;
	private JLabel lblRaw1;
	private JLabel tree1;

	RawCollection rc;
	private WoodWorker ww;
	private Workbench wb;
	TimberCollection tc;
	Garage gr;
	private JSpinner spinner_3;
	private JSpinner spinner_4;
	private JLabel lblTimeOfWork_1;
	private JLabel lblTimeOfWork_3;
	private JSpinner spinner_5;
	private JSpinner spinner_6;
	private JLabel lblTimeOfWork_4;
	private JLabel lblTimeOfWork_5;
	private JSlider slider;
	private JSlider slider_1;
	private JButton btnStop;
	private JSpinner spinner;
	private JSpinner spinner_1;
	private JSpinner spinner_2;
	private JButton btnStart;

	public void drawAnimation(JLabel src, JLabel dst){
		int x = src.getX();
		int y = src.getY();
		int x2 = dst.getX();
		int y2 = dst.getY();
		Graphics2D g2d = (Graphics2D) frame.getGraphics();
		(new Thread(() -> {
			for (int sx = x, sy = y, i = 0; i < 120; sx += (x2-x)/120, sy += (y2-y)/120, i++) {
				g2d.setColor(Color.CYAN);
				// you can draw a rectangle
				g2d.fillRect(sx,sy, 40,40);
				// or try to draw an image
				//ImageIcon im= new ImageIcon(getImage());

				//g2d.drawImage(getImage(), sx, sy, null);
				try {
					Thread.sleep(1000/120);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				panel.repaint(sx, sy, 100, 200);
				// if you'll draw a rectangle, please fix the size of it
				//g2d.fillRect(sx,sy, 236,58);
				//g2d.fillRect(sx,sy, 40,40);
			}
		})).start();
	}

	private Image getImage(){
		Image img = null;
		try {
			java.net.URL url = WoodWorker.class.getResource("../png/brus.png");
			img = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}
	public void person_active(int timeOfWork,JLabel person,boolean flag) {
		if(!flag) {
			person.setIcon(new ImageIcon(urlPerson));
		} else {
			if (rc == null)
				rc = new RawCollection((short)5, lblRaw, this,slider);
			person.setIcon(new ImageIcon(urlPersonActive));
			ww = new WoodWorker(timeOfWork,rc, person, this);
			new Thread(ww).start();
		}
	}
	int id = 0;
	public void machine_active(int size,JLabel machine,boolean flag) {
		if(!flag) {
			machine.setIcon(new ImageIcon(urlMachine));
		} else {
			if (gr == null)
				gr = new Garage(size, lblCar1, this);
			if (tc == null)
				tc = new TimberCollection((short)size, gr, lblExportbox, this,slider_1);
			machine.setIcon(new ImageIcon(urlMachineActive));
			wb = new Workbench(0,rc,tc, machine, this,id);
			id++;
			new Thread(wb).start();
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
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
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
		frame.setTitle("Project of Sergey Sezonchyk and Stanislav Pinchuk (Karpat Wood)");
		frame.setContentPane(panel);
		panel.setLayout(null);

		lblRiver = new JLabel("");
		lblRiver.setBounds(319, 872, 669, 149);
		panel.add(lblRiver);

		lblCar1 = new JLabel("");
		lblCar1.setBounds(1534, -12, 348, 532);
		panel.add(lblCar1);

		lblCar2 = new JLabel("");
		lblCar2.setBounds(1544, 437, 348, 573);
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

		tree1 = new JLabel("");
		tree1.setBounds(64, 69, 186, 249);
		panel.add(tree1);

		spinner = new JSpinner();
		spinner.setBounds(10, 126, 62, 20);
		panel.add(spinner);

		spinner_1 = new JSpinner();
		spinner_1.setBounds(10, 417, 62, 20);
		panel.add(spinner_1);

		spinner_2 = new JSpinner();
		spinner_2.setBounds(10, 772, 65, 20);
		panel.add(spinner_2);

		JLabel lblNewLabel = new JLabel("Time of work 1st");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(8, 101, 131, 14);
		panel.add(lblNewLabel);

		JLabel lblTimeOfWork = new JLabel("Time of work 2nd");
		lblTimeOfWork.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTimeOfWork.setForeground(Color.WHITE);
		lblTimeOfWork.setBounds(10, 392, 129, 14);
		panel.add(lblTimeOfWork);

		JLabel lblTimeOfWork_2 = new JLabel("Time of work 3rd");
		lblTimeOfWork_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTimeOfWork_2.setForeground(Color.WHITE);
		lblTimeOfWork_2.setBounds(10, 746, 129, 14);
		panel.add(lblTimeOfWork_2);

		spinner_3 = new JSpinner();
		spinner_3.setBounds(863, 417, 62, 20);
		panel.add(spinner_3);

		spinner_4 = new JSpinner();
		spinner_4.setBounds(863, 922, 62, 20);
		panel.add(spinner_4);

		lblTimeOfWork_1 = new JLabel("Handling  2nd");
		lblTimeOfWork_1.setForeground(Color.BLACK);
		lblTimeOfWork_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTimeOfWork_1.setBounds(935, 925, 129, 14);
		panel.add(lblTimeOfWork_1);

		lblTimeOfWork_3 = new JLabel("Handling  1st");
		lblTimeOfWork_3.setForeground(Color.BLACK);
		lblTimeOfWork_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTimeOfWork_3.setBounds(935, 420, 129, 14);
		panel.add(lblTimeOfWork_3);

		slider = new JSlider();
		slider.setSnapToTicks(true);
		slider.setMaximum(15);
		slider.setForeground(Color.WHITE);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setOrientation(SwingConstants.VERTICAL);
		slider.setBounds(1100, 838, 62, 160);
		panel.add(slider);

		slider_1 = new JSlider();
		slider_1.setSnapToTicks(true);
		slider_1.setPaintTicks(true);
		slider_1.setPaintLabels(true);
		slider_1.setOrientation(SwingConstants.VERTICAL);
		slider_1.setMaximum(15);
		slider_1.setForeground(Color.WHITE);
		slider_1.setBounds(1177, 838, 62, 160);
		panel.add(slider_1);

		JLabel lblNewLabel_1 = new JLabel("Raw count");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(1100, 805, 62, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Export count");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_1.setBounds(1177, 805, 76, 14);
		panel.add(lblNewLabel_1_1);

		JSpinner spinner_4_1 = new JSpinner();
		spinner_4_1.setBounds(1534, 479, 62, 20);
		panel.add(spinner_4_1);

		JSpinner spinner_4_1_1 = new JSpinner();
		spinner_4_1_1.setBounds(1534, 636, 62, 20);
		panel.add(spinner_4_1_1);

		JLabel lblNewLabel_2 = new JLabel("Count in 1st auto");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(1522, 454, 131, 14);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Count in 2nd auto");
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2_1.setBounds(1522, 611, 131, 14);
		panel.add(lblNewLabel_2_1);

		spinner_5 = new JSpinner();
		spinner_5.setBounds(1259, 882, 62, 20);
		panel.add(spinner_5);

		spinner_6 = new JSpinner();
		spinner_6.setBounds(1259, 924, 62, 20);
		panel.add(spinner_6);

		lblTimeOfWork_4 = new JLabel("Max count in 1st car");
		lblTimeOfWork_4.setForeground(Color.BLACK);
		lblTimeOfWork_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTimeOfWork_4.setBounds(1330, 885, 139, 14);
		panel.add(lblTimeOfWork_4);

		lblTimeOfWork_5 = new JLabel("Max count in 2nd car");
		lblTimeOfWork_5.setForeground(Color.BLACK);
		lblTimeOfWork_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTimeOfWork_5.setBounds(1331, 925, 153, 14);
		panel.add(lblTimeOfWork_5);
		/*Кнопочка старт
		 */
		btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnStart.setEnabled(false);

				spinner.setEnabled(false);
				spinner_1.setEnabled(false);
				spinner_2.setEnabled(false);
				spinner_3.setEnabled(false);
				spinner_4.setEnabled(false);
				spinner_5.setEnabled(false);
				spinner_6.setEnabled(false);
				sound.play();
				
				Thread t1 = new Thread(new Runnable() {
					@Override
					public void run() {
						doRun();
					}
				});  
				t1.start();
			}
		});
		btnStart.setBounds(1577, 967, 129, 43);
		panel.add(btnStart);

/* Кнопочка стоп, пока что только выключает музыку
 * 
 */
		btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sound.stop();
				sound.close();
				sound.setVolume(0);
				onEndOfPlay();
			}
		});
		btnStop.setBounds(1740, 967, 113, 43);
		panel.add(btnStop);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(new Color(0, 0, 0));
		menuBar.setBounds(0, 0, 1882, 21);
		panel.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Task");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IDialog dlg = (IDialog) getTask;
				dlg.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JSeparator separator = new JSeparator();
		mnNewMenu.add(separator);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Developers");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IDialog dlg = (IDialog) getDevs;
				dlg.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);


		frame.setBounds(20, 10, 1888, 1060);
		new Thread(()->{
			car1_active(0);
			car2_active(0);
			rawbox_count(rawCount);
			tree_active(tree1, false);
			tree_active(tree2, false);
			tree_active(tree3, false);
			person_active((int)spinner.getValue(),lblperson1, false);
			person_active((int)spinner_1.getValue(),lblperson2, false);
			person_active((int)spinner_2.getValue(),lblperson3, false);
			machine_active((int)spinner_5.getValue(),lblMachine1, false);
			machine_active((int)spinner_6.getValue(),lblMachine2, false);
			lblExportbox.setIcon(new ImageIcon(urlExportbox));
			lblRiver.setIcon(new ImageIcon(urlRiver));
		}).start();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public JSlider getSlider() {
		return slider;
	}
	public JSlider getSlider_1() {
		return slider_1;
	}
	
	/* Метод с запуском 
	 * всех елеметов
	 * (Отэто все нужно в потоки позасовывать:()
	 */
	protected void doRun() {
		car1_active(0);
		car2_active(0);
		rawbox_count(rawCount);
		tree_active(tree1, true);
		tree_active(tree2, true);
		tree_active(tree3, true);
		person_active((int)spinner.getValue(),lblperson1, true);
		person_active((int)spinner_2.getValue(),lblperson2, true);
		person_active((int)spinner_3.getValue(),lblperson3, true);
		machine_active((int)spinner_5.getValue(),lblMachine1, true);
		machine_active((int)spinner_6.getValue(),lblMachine2, true);
	}

	/*Ожидание завершения запущенных потоков
	 * (По идее нужно запускать каждый елемент в отдельном)
	 */
	private void onEndOfPlay() {
		btnStart.setEnabled(true);
		new Thread() {
			public void run() {
				try {
					t1.join();
					//Вообще должно здесь работать
					//btnStart.setEnabled(true); 
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}.start();
	}

	public JButton getBtnStop() {
		return btnStop;
	}
	public JSpinner getSpinner() {
		return spinner;
	}
	public JSpinner getSpinner_1() {
		return spinner_1;
	}
	public JSpinner getSpinner_2() {
		return spinner_2;
	}
	public JButton getBtnStart() {
		return btnStart;
	}
}

