package lab13;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.management.AttributeChangeNotification;
import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;
import javax.management.ObjectName;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.io.Serializable;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.awt.event.ItemEvent;

public class Board extends NotificationBroadcasterSupport implements BoardMBean {

	private JFrame frame;
	private JTextField advertTextField;
	private JTextField durationTextField;
	private JButton btnSave;

	private List<Advert> list = new ArrayList<>();
	private int current;
	private JCheckBox chckbxNewCheckBox;

	private final ReentrantLock lock = new ReentrantLock();

	private ObjectName tmpObjectName;
	private MBeanServer server;
	protected int sequenceNumber = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Board window = new Board();
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
	public Board() {
		server = ManagementFactory.getPlatformMBeanServer();
		try {
			tmpObjectName = new ObjectName("lab13.Board:type=Controller,name=Board");
			server.registerMBean(this, tmpObjectName);
		} catch (MalformedObjectNameException | InstanceAlreadyExistsException | NotCompliantMBeanException
				| MBeanRegistrationException e) {
			e.printStackTrace();
		}

		addAdvert("REKLAMA 1!", 3000, "advert1");
		addAdvert("REKLAMA 3", 2000, "advert2");
		addAdvert("REKLAMA 3", 4000, "advert3");
		current = 0;

		initialize();
		Advert advert = list.get(current);
		advertTextField.setText(advert.getText());
		durationTextField.setText(String.valueOf(advert.getDuration()));

		cycle();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 713, 247);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Has\u0142o", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(55, 35, 581, 89);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		advertTextField = new JTextField();
		advertTextField.setEnabled(false);
		advertTextField.setBounds(6, 16, 565, 66);
		panel.add(advertTextField);
		advertTextField.setHorizontalAlignment(SwingConstants.CENTER);
		advertTextField.setFont(new Font("Tahoma", Font.BOLD, 30));
		advertTextField.setColumns(10);

		JButton btnPrev = new JButton("<");
		btnPrev.setEnabled(false);
		btnPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPrevious();
			}
		});
		btnPrev.setBounds(10, 69, 41, 33);
		frame.getContentPane().add(btnPrev);

		JButton btnNext = new JButton(">");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showNext();
			}
		});
		btnNext.setEnabled(false);
		btnNext.setBounds(646, 69, 41, 33);
		frame.getContentPane().add(btnNext);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Czas wy\u015Bwietlania has\u0142a", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(131, 135, 146, 43);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		durationTextField = new JTextField();
		durationTextField.setEnabled(false);
		durationTextField.setBounds(6, 16, 130, 20);
		panel_1.add(durationTextField);
		durationTextField.setColumns(10);

		chckbxNewCheckBox = new JCheckBox("Tryb edycji");
		chckbxNewCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (chckbxNewCheckBox.isSelected()) {
					lock.lock();
					btnPrev.setEnabled(true);
					btnNext.setEnabled(true);
					durationTextField.setEnabled(true);
					btnSave.setEnabled(true);
					advertTextField.setEnabled(true);
				} else {
					try {
					} finally {
						lock.unlock();
					}
					btnPrev.setEnabled(false);
					btnNext.setEnabled(false);
					durationTextField.setEnabled(false);
					btnSave.setEnabled(false);
					advertTextField.setEnabled(false);
				}
			}
		});
		chckbxNewCheckBox.setBounds(39, 148, 97, 23);
		frame.getContentPane().add(chckbxNewCheckBox);

		btnSave = new JButton("Zapisz has\u0142o");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Advert advert = list.get(current);
				String text = advertTextField.getText();
				int dur = advert.getDuration();
				try {
					dur = Integer.parseInt(durationTextField.getText());
					if (dur <= 200 || dur >= 30000)
						throw new NumberFormatException();
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "B³¹d", "Niepoprawna d³ugoœæ wyœwietlania has³a.",
							JOptionPane.ERROR_MESSAGE);
				}
				if (!advert.getText().equals(text)) {
					// TextNotify
					Notification n = new AttributeChangeNotification(0, sequenceNumber++, System.currentTimeMillis(),
							"User changed advert text: " + advert.getText() + " -> " + text, "text", "String",
							advert.getText(), text);
					sendNotification(n);
				}
				if (advert.getDuration() != dur) {
					// DurationNotify
					Notification n = new AttributeChangeNotification(0, sequenceNumber++, System.currentTimeMillis(),
							"User changed advert duration: " + advert.getDuration() + " -> " + dur, "dur", "int",
							advert.getDuration(), dur);
					sendNotification(n);
				}
				advert.setText(text);
				advert.setDuration(dur);

			}
		});
		btnSave.setEnabled(false);
		btnSave.setBounds(286, 148, 113, 23);
		frame.getContentPane().add(btnSave);
	}

	private void showPrevious() {
		if (current == 0) {
			current = list.size() - 1;
		} else {
			--current;
		}
		Advert advert = list.get(current);
		advertTextField.setText(advert.getText());
		durationTextField.setText(String.valueOf(advert.getDuration()));
	}

	private void showNext() {
		++current;
		if (current >= list.size()) {
			current = 0;
		}
		Advert advert = list.get(current);
		advertTextField.setText(advert.getText());
		durationTextField.setText(String.valueOf(advert.getDuration()));
	}

	private void cycle() {
		Thread thread = new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						Advert advert = list.get(current);
						int duration = advert.getDuration();
						Thread.sleep(duration);
						lock.lock();
						try {
						} finally {
							lock.unlock();
						}
						showNext();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		thread.start();
	}

	@Override
	public void start() {
		try {
		} finally {
			lock.unlock();
		}
	}

	@Override
	public void stop() {
		lock.lock();
	}

	void addAdvert(String text, int dur, String name) {
		Advert advert = new Advert(text, dur);
		list.add(advert);

		try {
			tmpObjectName = new ObjectName("lab13.Advert:type=Controller,name=" + name);
			server.registerMBean(advert, tmpObjectName);
		} catch (MalformedObjectNameException | InstanceAlreadyExistsException | MBeanRegistrationException
				| NotCompliantMBeanException e) {
			e.printStackTrace();
		}
	}
}
