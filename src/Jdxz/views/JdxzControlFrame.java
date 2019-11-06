package Jdxz.views;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Jdxz.main.JdxzMain;

import javax.swing.JProgressBar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSlider;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.JLabel;



public class JdxzControlFrame extends JFrame {

	private JPanel contentPane;
	private JButton playButton;
	private JButton backwordButton;
	private JProgressBar progressBar;
	private JSlider volumControlerSlider;
	private JButton smallButton;
	private JLabel volumLabel;
	private JPanel progressTimepanel;
	private JLabel currentLabel;
	private JLabel totalLabel;
	private JButton listButton;

	/**
	 * Create the frame,a new control frame after enter full screen model.
	 */
	public JdxzControlFrame() {
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setType(Type.UTILITY);
		setResizable(false);
		setUndecorated(true);
		setOpacity(0.5f);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 623, 66);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JPanel panel = new JPanel();
		
		contentPane.add(panel, BorderLayout.CENTER);

		backwordButton = new JButton("<<");
		backwordButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JdxzMain.jumpTo((float) ((progressBar.getPercentComplete() * progressBar.getWidth() - 5)
						/ progressBar.getWidth()));
			}
		});

		playButton = new JButton(">");
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		playButton.addMouseListener(new MouseAdapter() {
			String btnText = ">";

			@Override
			public void mouseClicked(MouseEvent e) {
				if (playButton.getText() == ">") {
					JdxzMain.play();
					btnText = "||";
					playButton.setText(btnText);
				} else {
					JdxzMain.pause();
					btnText = ">";
					playButton.setText(btnText);
				}
			}
		});
		panel.add(playButton);
		panel.add(backwordButton);
		JButton stopButton = new JButton("停止");
		stopButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JdxzMain.stop();
				playButton.setText(">");
			}
		});
		panel.add(stopButton);

		JButton forwardButton = new JButton(">>");
		forwardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JdxzMain.jumpTo((float) (((progressBar.getPercentComplete() * progressBar.getWidth() + 15))//
						/ progressBar.getWidth()));
			}
		});
		panel.add(forwardButton);
		
		smallButton = new JButton("缩小");
		smallButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JdxzMain.originalScreen();
			}
		});
		panel.add(smallButton);

		volumControlerSlider = new JSlider();
		//volumControlerSlider.setOpaque(false);
		//volumControlerSlider.setBackground(java.awt.Color.blue);
		volumControlerSlider.setPaintTicks(true);
		volumControlerSlider.setSnapToTicks(true);
		volumControlerSlider.setPaintLabels(true);
		panel.add(volumControlerSlider);
		// volumControlerSlider.setValue(MyMain.getFrame().getVolumControlerSlider().getValue());
		volumControlerSlider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				volumControlerSlider.setValue((int) (e.getX()
						* ((float) volumControlerSlider.getMaximum() / volumControlerSlider.getWidth())));//音量的百分比
				JdxzMain.getFrame().getVolumControlerSlider().setValue(volumControlerSlider.getValue());
			}
		});
		volumControlerSlider.setMaximum(120);
		//volumControlerSlider.setBackground(java.awt.Color.black);
		//volumControlerSlider.setOpaque(false);
		volumLabel = new JLabel("0");
		panel.add(volumLabel);

		listButton = new JButton();
		listButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (listButton.getText() == "列表>>") {
					JdxzMain.getFrame().getPlayListFrame().setVisible(true);
					JdxzMain.getFrame().getPlayListFrame().setBounds(
							(int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()
									- JdxzMain.getFrame().getPlayListFrame().getWidth(),
							0, 400, JdxzMain.getFrame().getHeight());
					JdxzMain.getFrame().getPlayListFrame().setFlag(0);
					listButton.setText("<<列表");
				} else if (listButton.getText() == "<<列表") {
					JdxzMain.getFrame().getPlayListFrame().setVisible(false);
					listButton.setText("列表>>");
				}
			}
		});
		panel.add(listButton);
		panel.setBackground(java.awt.Color.BLACK);
		panel.setOpaque(false);
		panel.repaint();
		progressTimepanel = new JPanel();
		contentPane.add(progressTimepanel, BorderLayout.NORTH);
		progressTimepanel.setLayout(new BorderLayout(0, 0));

		progressBar = new JProgressBar();
		progressTimepanel.add(progressBar, BorderLayout.CENTER);
		progressBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = e.getX();
				JdxzMain.jumpTo(((float) x / progressBar.getWidth()));

			}
		});

		currentLabel = new JLabel("00:00");
		progressTimepanel.add(currentLabel, BorderLayout.WEST);

		totalLabel = new JLabel("00:00");
		progressTimepanel.add(totalLabel, BorderLayout.EAST);
		volumControlerSlider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				JdxzMain.setVolum(volumControlerSlider.getValue());
			}
		});


	}

	public JProgressBar getProgressBar() {
		return progressBar;
	}

	public void setProgressBar(JProgressBar progressBar) {
		this.progressBar = progressBar;
	}

	public JButton getPlayButton() {
		return playButton;
	}

	public JSlider getVolumControlerSlider() {
		return volumControlerSlider;
	}

	public JLabel getVolumLabel() {
		return volumLabel;
	}

	public JLabel getCurrentLabel() {
		return currentLabel;
	}

	public JLabel getTotalLabel() {
		return totalLabel;
	}

	public JButton getListButton() {
		return listButton;
	}

}
