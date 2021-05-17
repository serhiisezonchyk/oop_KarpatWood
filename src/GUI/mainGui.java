package GUI;


import model.*;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;



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



	private JLabel lblRiver;
	private JLabel lblRaw1;
	private JLabel tree1;
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
	private JSpinner spinner_7;
	private JSpinner spinner_7_1;
	private JSpinner spinner_4_1_1;
	private JSpinner spinner_4_1;
	private boolean status ;

	RawCollection rc;
	TimberCollection tc;
	Garage gr;
	int id = 0;

	java.net.URL urlRawboxEmpty = mainGui.class.getResource("/png/rawbox.png");
	java.net.URL urlRawbox = mainGui.class.getResource("/png/sklad.png");
	java.net.URL urlExportbox = mainGui.class.getResource("/png/exportbox.png");
	java.net.URL urlRiver = mainGui.class.getResource("/gifs/river.gif");
	java.net.URL urlSound = mainGui.class.getResource("/music/mario.wav");
	Sound sound =  new Sound(urlSound);
	Thread t1;
	Thread tPersons;
	Thread tMachine;

	WoodWorker[] workers = new WoodWorker[3];
	Workbench[] banches = new Workbench[2];


	public synchronized void drawAnimation(JLabel src, JLabel dst, boolean ready){
		ImageIcon img = getImage(ready);
		JLabel br = new JLabel(img);
		int x = (src.getX()+src.getWidth()/2);
		int y = (src.getY()+src.getHeight()/2);
		int x2 = (dst.getX()+dst.getWidth()/2);
		int y2 = (dst.getY()+dst.getHeight()/2);
		int lenX= (x2-x);
		int lenY = (y2-y);

		int len = (int)Math.round(Math.sqrt((double)(lenX * lenX + lenY * lenY)));
		int n = 120;
		int dx = lenX/n;
		int dy= lenY/n;
		br.setBounds(x, y, img.getIconWidth(), img.getIconHeight());
		panel.add(br);
		(new Thread(() -> {
			for (int sx = x, sy = y, i = 0; i < n; sx += dx, sy += dy, i++) {
				br.setVisible(true);
				br.setLocation(sx, sy);

				try {
					Thread.sleep(1000/60);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(i == n-1)
					br.setVisible(false);
				if(EventQueue.isDispatchThread())
					panel.repaint();
			}
		})).start();
	}
	private ImageIcon getImage(boolean raw){
		ImageIcon img = null;
		if(!raw) {
			img = new ImageIcon(mainGui.class.getResource("/png/raw.png"));
		}else {
			img = new ImageIcon(mainGui.class.getResource("/png/brus.png"));}
		return img;
	}

	/*
	 * Без фотки
	 */

	//	public void drawAnimation(JLabel src, JLabel dst,boolean rr){
	//		int x = (src.getX()+src.getWidth()/2);
	//		int y = (src.getY()+src.getHeight()/2);
	//		int x2 = (dst.getX()+dst.getWidth()/2);
	//		int y2 = (dst.getY()+dst.getHeight()/2);
	//		Graphics2D g2d = (Graphics2D) frame.getGraphics();
	//		(new Thread(() -> {
	//			for (int sx = x, sy = y, i = 0; i < 120; sx += (x2-x)/120, sy += (y2-y)/120, i++) {
	//				g2d.setColor(Color.CYAN);
	//				// you can draw a rectangle
	//				g2d.fillRect(sx,sy, 40,40);
	//				// or try to draw an image
	//				try {
	//					Thread.sleep(1000/120);
	//				} catch (InterruptedException e) {
	//					e.printStackTrace();
	//				}
	//
	//				//				g2d.clearRect(sx-(x2-x)/120, sy-(y2-y)/120, 40, 40);
	//				// if you'll draw a rectangle, please fix the size of it
	//				g2d.setColor(Color.WHITE);
	//				g2d.fillRect(sx,sy, 40,40);
	//				//panel.updateUI();
	//			}
	//		})).start();
	//	}


	public void setActivityWorker(JLabel lbl,boolean pause) {
		if(lbl==lblperson1) {
			if(pause) {
				lblperson1.setIcon(new ImageIcon(mainGui.class.getResource("/png/person.png")));
				tree_active(tree1, false);
			}else {
				lblperson1.setIcon(new ImageIcon(mainGui.class.getResource("/gifs/person.gif")));
				tree_active(tree1, true);
			}
		}else if(lbl==lblperson2) {
			if(pause) {
				lblperson2.setIcon(new ImageIcon(mainGui.class.getResource("/png/person.png")));
				tree_active(tree2, false);
			}else {
				lblperson2.setIcon(new ImageIcon(mainGui.class.getResource("/gifs/person.gif")));
				tree_active(tree2, true);
			}
		}else if(lbl==lblperson3) {
			if(pause) {
				lblperson3.setIcon(new ImageIcon(mainGui.class.getResource("/png/person.png")));
				tree_active(tree3, false);
			}else {
				lblperson3.setIcon(new ImageIcon(mainGui.class.getResource("/gifs/person.gif")));
				tree_active(tree3, true);
			}
		}
	}

	public void person_active(int timeOfWork,JLabel person,boolean flag,int num) {
		if(!flag) {
			person.setIcon(new ImageIcon(mainGui.class.getResource("/png/person.png")));
		} else {
			if (rc == null)
				rc = new RawCollection((short)(int)spinner_7.getValue(), lblRaw, this,slider);
			person.setIcon(new ImageIcon(mainGui.class.getResource("/gifs/person.gif")));
			workers[num] = new WoodWorker(timeOfWork,rc, person, this);;
			tPersons  = new Thread(workers[num]);
			tPersons.start();
		}
	}



	public synchronized void setActivityMachine(JLabel lbl,boolean pause) {
		if(lbl == lblMachine1) {
			if(pause) {
				lblMachine1.setIcon(new ImageIcon(mainGui.class.getResource("/png/Machine1.png")));
			}else {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				lblMachine1.setIcon(new ImageIcon(mainGui.class.getResource("/gifs/Machine1.gif")));
			}
		}else if(lbl == lblMachine2) {
			if(pause) {
				lblMachine2.setIcon(new ImageIcon(mainGui.class.getResource("/png/Machine1.png")));
			}else {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				lblMachine2.setIcon(new ImageIcon(mainGui.class.getResource("/gifs/Machine1.gif")));
			}
		}
	}
	public void machine_active(int timeOfWork,JLabel machine,boolean flag, int num ) {
		if(!flag) {
			machine.setIcon(new ImageIcon(mainGui.class.getResource("/png/Machine1.png")));
		} else {
			if (gr == null)
				gr = new Garage(lblCar1, lblCar2,this,(int)spinner_5.getValue(),(int)spinner_6.getValue());
			if (tc == null)
				tc = new TimberCollection((short)(int)spinner_7_1.getValue(), gr, lblExportbox, this,slider_1);
			//			machine.setIcon(new ImageIcon(mainGui.class.getResource("/gifs/Machine1.gif")));
			banches[num] = new Workbench(timeOfWork,rc,tc, machine, this,id);
			id++;
			tMachine = new Thread(banches[num]);
			tMachine.start();
		}
	}

	public void tree_active(JLabel tree,boolean flag) {
		if(!flag) {
			tree.setIcon(new ImageIcon(mainGui.class.getResource("/png/tree.png")));
		} else {
			tree.setIcon(new ImageIcon(mainGui.class.getResource("/gifs/treee.gif")));
		}
	}

	public void rawbox_count(int count) {
		if(count == 0) {
			lblRaw.setIcon(new ImageIcon(urlRawboxEmpty));
		} else {
			lblRaw.setIcon(new ImageIcon(urlRawbox));
		}
	}

	public void setCarActivity(JLabel lbl, int status) {
		if(lbl == lblCar1) {
			car1_active(status);
		}else if(lbl == lblCar2) {
			car2_active(status);
		}

	}

	public void car1_active(int flag) {
		switch (flag) {
		case 0:
			lblCar1.setIcon(new ImageIcon(mainGui.class.getResource("/png/Car1.png")));
			break;
		case 1:
			lblCar1.setIcon(new ImageIcon(mainGui.class.getResource("/png/Car1open.png")));
			break;
		case 2:
			lblCar1.setIcon(new ImageIcon(mainGui.class.getResource("/png/inTransit.png")));
			break;
		}
	}

	public void car2_active(int flag) {
		switch (flag) {
		case 0:
			lblCar2.setIcon(new ImageIcon(mainGui.class.getResource("/png/Car2.png")));
			break;

		case 1:
			lblCar2.setIcon(new ImageIcon(mainGui.class.getResource("/png/Car2open.png")));
			break;

		case 2:
			lblCar2.setIcon(new ImageIcon(mainGui.class.getResource("/png/inTransit.png")));
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

		slider = new JSlider(0,10);
		slider.setMajorTickSpacing(1);
		slider.setValue(0);
		slider.setSnapToTicks(true);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setMaximum(10);
		slider.setForeground(new Color(0, 0, 0));
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setMinorTickSpacing(1);

		slider.setOrientation(SwingConstants.VERTICAL);
		slider.setBounds(1100, 838, 62, 160);
		panel.add(slider);

		slider_1 = new JSlider();
		slider_1.setMajorTickSpacing(1);
		slider_1.setValue(0);
		slider_1.setSnapToTicks(true);
		slider_1.setPaintTicks(true);
		slider_1.setPaintLabels(true);
		slider_1.setOrientation(SwingConstants.VERTICAL);
		slider_1.setMaximum(10);
		slider_1.setForeground(new Color(0, 0, 0));
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

		spinner_4_1 = new JSpinner();
		spinner_4_1.setBounds(1534, 479, 62, 20);
		panel.add(spinner_4_1);

		spinner_4_1_1 = new JSpinner();
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
				status = true;
				btnStart.setEnabled(false);
				spinner.setEnabled(false);
				spinner_1.setEnabled(false);
				spinner_2.setEnabled(false);
				spinner_3.setEnabled(false);
				spinner_4.setEnabled(false);
				spinner_5.setEnabled(false);
				spinner_6.setEnabled(false);
				for (int i=0;i< workers.length;i++)
					workers[i] = null;
				for (int i=0;i< banches.length;i++)
					banches[i] = null;
				System.gc();
				doRun();
			}
		});
		btnStart.setBounds(1577, 967, 129, 43);
		panel.add(btnStart);

		btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				status = false;
				spinner.setEnabled(true);
				spinner_1.setEnabled(true);
				spinner_2.setEnabled(true);
				spinner_3.setEnabled(true);
				spinner_4.setEnabled(true);
				spinner_5.setEnabled(false);
				spinner_6.setEnabled(false);
				spinner_7.setEnabled(false);
				spinner_7_1.setEnabled(false);
				spinner_4_1.setEnabled(false);
				spinner_4_1_1.setEnabled(false);
				sound.stop();
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
		
		JSeparator separator_1 = new JSeparator();
		mnNewMenu.add(separator_1);
		
		JMenuItem emstopMenuItem = new JMenuItem("Emergency stop");
		emstopMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (int i=0;i< workers.length;i++)
					workers[i] = null;
				for (int i=0;i< banches.length;i++)
					banches[i] = null;
				tc = null;
				rc = null;
				gr = null;
				spinner.setEnabled(true);
				spinner_1.setEnabled(true);
				spinner_2.setEnabled(true);
				spinner_3.setEnabled(true);
				spinner_4.setEnabled(true);
				spinner_5.setEnabled(true);
				spinner_6.setEnabled(true);
				spinner_7.setEnabled(true);
				spinner_7_1.setEnabled(true);
				spinner_4_1.setEnabled(false);
				spinner_4_1_1.setEnabled(false);
			}
		});
		mnNewMenu.add(emstopMenuItem);

		spinner_7 = new JSpinner();
		spinner_7.setBounds(1100, 772, 62, 20);
		panel.add(spinner_7);

		spinner_7_1 = new JSpinner();
		spinner_7_1.setBounds(1177, 772, 62, 20);
		panel.add(spinner_7_1);

		JLabel lblNewLabel_3 = new JLabel("Size");
		lblNewLabel_3.setBounds(1100, 748, 46, 14);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_3_1 = new JLabel("Size");
		lblNewLabel_3_1.setBounds(1177, 748, 46, 14);
		panel.add(lblNewLabel_3_1);

		JSlider slider_2 = new JSlider();
		slider_2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				sound.setVolume((float)slider_2.getValue()/100);
			}
		});
		slider_2.setPaintLabels(true);
		slider_2.setBackground(new Color(0, 100, 0));
		slider_2.setBounds(24, 995, 200, 26);
		panel.add(slider_2);

		JLabel lblNewLabel_4 = new JLabel("Volume");
		lblNewLabel_4.setFont(new Font("Snap ITC", Font.BOLD, 18));
		lblNewLabel_4.setBackground(new Color(0, 100, 0));
		lblNewLabel_4.setForeground(new Color(165, 42, 42));
		lblNewLabel_4.setBounds(24, 967, 92, 14);
		panel.add(lblNewLabel_4);


		frame.setBounds(20, 10, 1888, 1060);
		new Thread(()->{
			spinner.setValue(2000);
			spinner_1.setValue(2500);
			spinner_2.setValue(3000);
			spinner_3.setValue(4000);
			spinner_4.setValue(4000);
			spinner_5.setValue(5);
			spinner_6.setValue(5);
			spinner_7.setValue(10);
			spinner_7_1.setValue(10);

			tree_active(tree1, false);
			tree_active(tree2, false);
			tree_active(tree3, false);
			car1_active(0);
			car2_active(0);
			rawbox_count(0);
			person_active((int)spinner.getValue(),lblperson3, false,0);
			person_active((int)spinner_1.getValue(),lblperson2, false,1);
			person_active((int)spinner_2.getValue(),lblperson1, false,2);
			machine_active((int)spinner_3.getValue(),lblMachine1, false,0);
			machine_active((int)spinner_4.getValue(),lblMachine2, false,1);
			lblExportbox.setIcon(new ImageIcon(urlExportbox));
			lblRiver.setIcon(new ImageIcon(urlRiver));
		}).start();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	/* Метод с запуском 
	 * всех елеметов
	 * (Отэто все нужно в потоки позасовывать:()
	 */
	protected void doRun() {
		System.gc();
		sound.setVolume((float)0.5);
		sound.play();
		slider.setMaximum((int) spinner_7.getValue());
		slider_1.setMaximum((int) spinner_7_1.getValue());
		spinner_5.setEnabled(false);
		spinner_6.setEnabled(false);
		spinner_7.setEnabled(false);
		spinner_7_1.setEnabled(false);
		spinner_4_1.setEnabled(false);
		spinner_4_1_1.setEnabled(false);
		car1_active(1);
		car2_active(1);
		rawbox_count(0);
		person_active((int)spinner.getValue(),lblperson3, true,0);
		person_active((int)spinner_1.getValue(),lblperson2, true,1);
		person_active((int)spinner_2.getValue(),lblperson1, true,2);
		machine_active((int)spinner_3.getValue(),lblMachine1, true,0);
		machine_active((int)spinner_4.getValue(),lblMachine2, true,1);
	}

	/*Ожидание завершения запущенных потоков
	 * (По идее нужно запускать каждый елемент в отдельном)
	 */
	private void onEndOfPlay() {
		new Thread() {
			public void run() {
				try {
					tPersons.join();
					for (int i=0;i< workers.length;i++)
						workers[i] = null;
					for (int i=0;i< banches.length;i++)
						banches[i] = null;
					tc = null;
					rc = null;
					gr = null;
					spinner.setEnabled(true);
					spinner_1.setEnabled(true);
					spinner_2.setEnabled(true);
					spinner_3.setEnabled(true);
					spinner_4.setEnabled(true);
					spinner_5.setEnabled(true);
					spinner_6.setEnabled(true);
					spinner_7.setEnabled(true);
					spinner_7_1.setEnabled(true);
					spinner_4_1.setEnabled(false);
					spinner_4_1_1.setEnabled(false);
					id = 0;
					System.gc();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				btnStart.setEnabled(true);
				
			}
		}.start();
		

	}


	public boolean isPlaying() {
		return status;
	}
	public JSpinner getSpinnerCar1() {
		return spinner_4_1;
	}

	public JSpinner getSpinnerCar2() {
		return spinner_4_1_1;
	}

	public int getMaxRaw() {
		return (int)spinner_7.getValue();
	}
}

