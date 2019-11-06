package Jdxz.views;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Jdxz.main.*;
import Jdxz.play_list.JdxzPlayListFrame;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JProgressBar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSlider;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.Timer;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import Jdxz.func.JdxzfuncCutSecondPictureLikeStaticPicture;
import Jdxz.func.JdxzfuncChangeVideoModeToAvi;
import Jdxz.func.JdxzfuncChangeVideoModeToFlv;
import Jdxz.func.JdxzfuncChangeVideoModeToMov;
import Jdxz.func.JdxzfuncChangeVideoModeToMp4;
import Jdxz.func.JdxzfuncCutSecondsPicturesLikeVividGif;;
class HomePanel extends JPanel {  
    ImageIcon icon;  
    Image img;  
    public HomePanel() {  
        //  /img/HomeImg.jpg 是存放在你正在编写的项目的bin文件夹下的img文件夹下的一个图片  
        //icon=new ImageIcon("img/HomeImg11.jpg");  
        //img=icon.getImage();  
    	 icon = new ImageIcon(this.getClass().getClassLoader().getResource("image/HomeImg22.jpg"));
         img=icon.getImage();  
        
    }  
    public void paintComponent(Graphics g) {  
        super.paintComponent(g);  
        //下面这行是为了背景图片可以跟随窗口自行调整大小，可以自己设置成固定大小  
        g.drawImage(img, 0, 0,this.getWidth(), this.getHeight(), this);  
    }  
  
}  
public class JdxzDisplayFram extends JFrame {

	//private JPanel contentPane;
	HomePanel contentPane;
	EmbeddedMediaPlayerComponent playerComponent;
	Graphics g;
	private JLabel jdxzlab;
	private JMenuBar menuBar;
	private HomePanel panel;
	private JButton stopButton;
	private JButton playButton;
	private HomePanel controlPanel;
	private JProgressBar progressBar;
	private JSlider volumControlerSlider;
	private JMenu mnFile;
	private JMenu func;
	private JMenuItem funcCutSecondPictureLikeStaticPicture;
	private JMenuItem funcCutSenondsPicturesLikeVividGif;
	private JMenu funcChangeVideoMode;
	private JMenuItem funcChangeVideoModeToFlv;
	private JMenuItem funcChangeVideoModeToAvi;
	private JMenuItem funcChangeVideoModeToMp4;
	private JMenuItem funcChangeVideoModeToMov;
	private JMenuItem mntmOpenVideo;
	private JMenuItem mntmOpenSubtitle;
	private JMenuItem mntmExit;
	private JButton forwardButton;
	private JButton backwordButton;
	private JButton FullScreenButton;
	private int flag = 0;
	private JdxzKeyBordListenerEven kble;
	private JLabel volumLabel;
	private HomePanel progressTimePanel;
	private JLabel currentLabel;
	private JLabel totalLabel;
	private static JdxzPlayListFrame playListFrame;
	private JButton listButton;

	public JdxzDisplayFram() {
		
		playListFrame = new JdxzPlayListFrame();
		setIconImage(new ImageIcon("picture/icon.png").getImage());
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentMoved(ComponentEvent e) {
				if(playListFrame.getFlag() == 0){
					playListFrame.setVisible(true);
					playListFrame.setBounds(JdxzMain.getFrame().getX() + JdxzMain.getFrame().getWidth() - 15, JdxzMain.getFrame().getY(),400,JdxzMain.getFrame().getHeight());
				}
			}
			@Override
			public void componentResized(ComponentEvent e) {
				
				if(playListFrame.getFlag() == 0 && !JdxzMain.getFrame().getMediaPlayer().isFullScreen()){
					playListFrame.setVisible(true);
					if(Math.abs(JdxzMain.getFrame().getWidth() - Toolkit.getDefaultToolkit().getScreenSize().width ) <= 20){
						playListFrame.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 400), 0,400,JdxzMain.getFrame().getHeight());
						playListFrame.setAlwaysOnTop(true);
					}
					else
						playListFrame.setBounds(JdxzMain.getFrame().getX() + JdxzMain.getFrame().getWidth() - 15, JdxzMain.getFrame().getY(),400,JdxzMain.getFrame().getHeight());
				}
				
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 400);
		kble = new JdxzKeyBordListenerEven();
		kble.keyBordListner();
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		func=new JMenu("功能");
		mnFile = new JMenu("文件");
		
		menuBar.add(mnFile);
		menuBar.add(func);
		
		mntmOpenVideo = new JMenuItem("打开视频");
		mntmOpenVideo.setSelected(true);
		mnFile.add(mntmOpenVideo);

		mntmOpenSubtitle = new JMenuItem("打开字幕");
		mnFile.add(mntmOpenSubtitle);

		mntmExit = new JMenuItem("退出");
		mnFile.add(mntmExit);

		funcCutSecondPictureLikeStaticPicture=new JMenuItem("截图");
		funcCutSecondPictureLikeStaticPicture.setSelected(true);
		func.add(funcCutSecondPictureLikeStaticPicture);
		
		funcCutSenondsPicturesLikeVividGif=new JMenuItem("动态截图");
		funcCutSenondsPicturesLikeVividGif.setSelected(true);
		func.add(funcCutSenondsPicturesLikeVividGif);
		
		funcChangeVideoMode=new JMenu("格式转换为");
		funcChangeVideoModeToFlv=new JMenuItem(".flv");
		funcChangeVideoModeToAvi=new JMenuItem(".avi");
		funcChangeVideoModeToMp4=new JMenuItem(".mp4");
		funcChangeVideoModeToMov=new JMenuItem(".mov");
		
		funcChangeVideoModeToFlv.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JdxzfuncChangeVideoModeToFlv jdxz=new JdxzfuncChangeVideoModeToFlv();
				jdxz.main(null);
			}
		});     
		
		funcChangeVideoModeToAvi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JdxzfuncChangeVideoModeToAvi jdxz=new JdxzfuncChangeVideoModeToAvi();
				jdxz.main(null);
			}
		});
		funcChangeVideoModeToMp4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JdxzfuncChangeVideoModeToMp4 jdxz=new JdxzfuncChangeVideoModeToMp4();
				jdxz.main(null);
			}
		});
	
		funcChangeVideoModeToMov.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JdxzfuncChangeVideoModeToMov jdxz=new JdxzfuncChangeVideoModeToMov();
				jdxz.main(null);
			}
		});
	
		funcChangeVideoMode.add(funcChangeVideoModeToMov);
		funcChangeVideoMode.add(funcChangeVideoModeToMp4);
		funcChangeVideoMode.add(funcChangeVideoModeToAvi);
		funcChangeVideoMode.add(funcChangeVideoModeToFlv);
		
		func.add(funcChangeVideoMode);
		funcChangeVideoMode.setSelected(true);
		
		
		funcCutSecondPictureLikeStaticPicture.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JdxzfuncCutSecondPictureLikeStaticPicture jdxz=new JdxzfuncCutSecondPictureLikeStaticPicture();
				System.out.println(JdxzMain.getPath(getPlayListFrame().getName()));
				jdxz.makeScreenCut("./ffmpeg.exe",JdxzMain.getPath(getPlayListFrame().getName()),(int)JdxzMain.getCurrentTime()/1000);
			}
		});     
		
		funcCutSenondsPicturesLikeVividGif.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JdxzfuncCutSecondsPicturesLikeVividGif jdxz=new JdxzfuncCutSecondsPicturesLikeVividGif();
				System.out.println(JdxzMain.getPath(getPlayListFrame().getName()));
				jdxz.mains(JdxzMain.getPath(getPlayListFrame().getName()));
				//jdxz.mains((int)JdxzMain.getCurrentTime()/1000, JdxzMain.getPath(getPlayListFrame().getName()));//("F:\\kechengsheji\\ffmpeg-20171223-9e5e323-win64-static\\bin\\ffmpeg.exe",JdxzMain.getPath(getPlayListFrame().getName()),(int)JdxzMain.getCurrentTime()/1000);
			}
		});
		
		mntmOpenVideo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JdxzMain.openVedio();
				playListFrame.setList(JdxzMain.getListView().getList());
				playListFrame.getScrollPane().setViewportView(playListFrame.getList());
			}
		});

		mntmOpenSubtitle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JdxzMain.openSubtitle();
			}
		});

		mntmExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JdxzMain.exit();
			}
		});

		contentPane = new HomePanel();
		contentPane.setOpaque(false);
		//contentPane.setBackground(java.awt.Color.blue);
		ImageIcon jdxzimage = new ImageIcon("./jdxz.png");
		jdxzimage.setImage(jdxzimage.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT));
		//setBounds(100, 100, 200, 200);
		//设置一个底层标签带图片，设置所有面板为透明，应该可以显示图片，但显示不了
		jdxzlab=new JLabel(jdxzimage);
		jdxzlab.setIcon(jdxzimage);
		//jdxzlab.setDefaultLocale(getLocale());;
		//jdxzlab.setBackground(java.awt.Color.RED);
		//this.getContentPane().setLayout(getLayout());
		//jdxzlab.setBounds(100, 100, 650, 400);
		//jdxzlab.size(100,100,650,400).
		contentPane.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentMoved(ComponentEvent e) {
				System.out.println();
			}
		});
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		this.getContentPane().add(jdxzlab,-1);
		JPanel videoPanel = new JPanel(){
		    public void paintComponents(Graphics g) {
		     super.paintComponent(g);
		     ImageIcon img = new ImageIcon("jdxz.png");
		     g.drawImage(img.getImage(),0,0,null);
		      }
		   };
		contentPane.add(videoPanel, BorderLayout.CENTER,0);
		//contentPane.setBackground(java.awt.color.ICC_ProfileRGB(255,0,0));
		videoPanel.setLayout(new BorderLayout(0, 0));
		//videoPanel.paintComponents(g);
		playerComponent = new EmbeddedMediaPlayerComponent();
		final Canvas videoSurface = playerComponent.getVideoSurface();
		videoSurface.addMouseListener(new MouseAdapter() {
				String btnText = ">";
				String btnText1 = "全屏";
				Timer mouseTime;
				
				@Override
				public void mouseClicked(MouseEvent e) {
				int i = e.getButton();
				if (i == MouseEvent.BUTTON1) {
					if (e.getClickCount() == 1) {
						mouseTime = new Timer(350, new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								if (playButton.getText() == ">") {
									JdxzMain.play();
									btnText = "||";
									playButton.setText(btnText);
								} else {
									JdxzMain.pause();
									btnText = ">";
									playButton.setText(btnText);
								}
								mouseTime.stop();
							}
						});
						mouseTime.restart();
					} else if (e.getClickCount() == 2 && mouseTime.isRunning()) {
						mouseTime.stop();
						if (flag == 0) {
							JdxzMain.fullScreen();
						} else if (flag == 1) {
							JdxzMain.originalScreen();
						}
					}
				}
			}

		});
		//videoPanel.setLayout(getLayout());;
		videoPanel.add(playerComponent, BorderLayout.CENTER);
		panel = new HomePanel();
		panel.setOpaque(false);
		JLabel jdxzLabelForSolveTheEastProblem=new JLabel();
		jdxzLabelForSolveTheEastProblem.setSize(0, 0);
		
		JLabel jdxzLabelForSolveTheWestProblem=new JLabel();
		jdxzLabelForSolveTheEastProblem.setSize(0, 0);
		
		contentPane.add(jdxzLabelForSolveTheEastProblem,BorderLayout.EAST,0);
		contentPane.add(jdxzLabelForSolveTheWestProblem,BorderLayout.EAST,0);
		
		videoPanel.add(panel,BorderLayout.SOUTH,0);
		panel.setLayout(new BorderLayout());
		//videoPanel.setBackground(java.awt.Color.RED);
		
		controlPanel = new HomePanel();
		
		controlPanel.setOpaque(false);
		FlowLayout flowLayout = (FlowLayout) controlPanel.getLayout();
		panel.add(controlPanel,0);
		
				playButton = new JButton(">");
				//playButton.setForeground(java.awt.Color.red);
				playButton.setOpaque(false);
				playButton.setBackground(java.awt.Color.MAGENTA);
				playButton.setFont(java.awt.Font.getFont(getName(), getFont()));
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
				controlPanel.add(playButton,0);
		
		backwordButton = new JButton("<<");
		backwordButton.setOpaque(false);
		backwordButton.setBackground(java.awt.Color.CYAN);
		backwordButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JdxzMain.jumpTo((float)((progressBar.getPercentComplete() * progressBar.getWidth() - 5) / progressBar.getWidth()));
			}
		});
		controlPanel.add(backwordButton,0);

		volumControlerSlider = new JSlider();
		volumControlerSlider.setPaintLabels(true);
		volumControlerSlider.setSnapToTicks(true);
		volumControlerSlider.setPaintTicks(true);
		volumControlerSlider.setOpaque(false);
		volumControlerSlider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				volumControlerSlider.setValue((int)(e.getX() * ((float)volumControlerSlider.getMaximum() / volumControlerSlider.getWidth())));
			//	volumLabel.setText("" + volumControlerSlider.getValue());
			}
			
		});
		volumControlerSlider.setValue(100);
		volumControlerSlider.setMaximum(120);
		volumControlerSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JdxzMain.setVolum(volumControlerSlider.getValue());
			}
		});
		
		forwardButton = new JButton(">>");
		forwardButton.setOpaque(false);
		forwardButton.setBackground(java.awt.Color.CYAN);
		forwardButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				JdxzMain.jumpTo((float)(((progressBar.getPercentComplete() * progressBar.getWidth() + 10)) / progressBar.getWidth()));
			}
		});
		
				stopButton = new JButton("停止");
				stopButton.setForeground(java.awt.Color.red);
				stopButton.setOpaque(false);
				stopButton.setBackground(java.awt.Color.red);
						stopButton.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								JdxzMain.stop();
								playButton.setText(">");
							}
						});
						controlPanel.add(stopButton);
		controlPanel.add(forwardButton,0);
		
		FullScreenButton = new JButton("全屏");
		//FullScreenButton.setForeground(java.awt.Color.white);
		FullScreenButton.setOpaque(false);
		FullScreenButton.setBackground(java.awt.Color.yellow);
		FullScreenButton.addMouseListener(new MouseAdapter() {
			String btnText = "Full";
			int flag = 0;
			@Override
			public void mouseClicked(MouseEvent e) {
	
				JdxzMain.fullScreen();			
			}
		});
		controlPanel.add(FullScreenButton,0);

		controlPanel.add(volumControlerSlider,0);
		
		volumLabel = new JLabel("" + volumControlerSlider.getValue());
		controlPanel.add(volumLabel,0);
		
		listButton = new JButton();
		listButton.setForeground(java.awt.Color.white);
		listButton.setOpaque(false);
		listButton.setBackground(java.awt.Color.green);
		if(playListFrame.getFlag() == 1){
			listButton.setText("列表>>");
		}
		else if(playListFrame.getFlag() == 0){
			listButton.setText("<<列表");
		}
		listButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(listButton.getText() == "列表>>"){
					playListFrame.setVisible(true);
					if (Math.abs(JdxzMain.getFrame().getWidth() - Toolkit.getDefaultToolkit().getScreenSize().width) <= 20)
						playListFrame.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width - 400, 0, 400,
								JdxzMain.getFrame().getHeight());
					else
						playListFrame.setBounds(JdxzMain.getFrame().getX() + JdxzMain.getFrame().getWidth() - 15,
								JdxzMain.getFrame().getY(), 400, JdxzMain.getFrame().getHeight());
					playListFrame.setFlag(0);
					listButton.setText("<<列表");
				}else if(listButton.getText() == "<<列表"){
					playListFrame.setVisible(false);
					listButton.setText("列表>>");
				}
				

			}
		});
		controlPanel.add(listButton,0);
		
		progressTimePanel = new HomePanel();
		progressTimePanel.setOpaque(false);
		panel.add(progressTimePanel, BorderLayout.NORTH,0);
		progressTimePanel.setLayout(new BorderLayout(0, 0));
//		panel.setLayout(getLayout());;
		progressBar = new JProgressBar();
		//progressBar.setBounds(100, 100, 100, 10);
		//progressBar.setOpaque(false);
		//progressBar.setBackground(java.awt.Color.red);
		progressBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = e.getX();
				JdxzMain.jumpTo(((float) x / progressBar.getWidth()));

			}
		});
		
		currentLabel = new JLabel("00：00");
		progressTimePanel.add(currentLabel, BorderLayout.EAST,0);
		
		totalLabel = new JLabel("02：13");
		progressTimePanel.add(totalLabel, BorderLayout.WEST,0);
		progressTimePanel.add(progressBar, BorderLayout.CENTER,0);
	}

	// Get the video
	public EmbeddedMediaPlayer getMediaPlayer() {
		return playerComponent.getMediaPlayer();
	}

	public JProgressBar getProgressBar() {
		return progressBar;
	}
	
	public EmbeddedMediaPlayerComponent getPlayComponent(){
		return playerComponent;
	}
	
	public JButton getPlayButton(){
		return playButton;
	}

	public JPanel getControlPanel() {
		return controlPanel;
	}


	public void setFlag(int flag){
		this.flag = flag;
	}

	public int getFlag() {
		return flag;
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

	public JPanel getProgressTimePanel() {
		return progressTimePanel;
	}

	public JButton getListButton() {
		return listButton;
	}

	public static JdxzPlayListFrame getPlayListFrame() {
		return playListFrame;
	}
	
}
