package Jdxz.main;

import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.SwingWorker;
import com.sun.jna.NativeLibrary;
import Jdxz.history.JdxzListHistory;
import Jdxz.play_list.JdxzViewList;
import Jdxz.views.JdxzControlFrame;
import Jdxz.views.JdxzDisplayFram;
import Jdxz.views.JdxzLogo;
import Jdxz.views.JdxzVideoTime;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.player.embedded.DefaultAdaptiveRuntimeFullScreenStrategy;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;


public class JdxzMain
{

	private static JdxzDisplayFram frame;
	private static String filePath;
	private static JdxzControlFrame controlFrame;
	private static JdxzVideoTime videoTime;
	private static JdxzViewList listView = new JdxzViewList();
	private static JdxzListHistory listHistory = new JdxzListHistory();
	private static long totalTime;
	private static long currentTime;
	public static void main(String[] args) 
	{
		
		try 
		{
			listView.setList(listHistory.readHistory());
			listView.setMap(listHistory.readHistoryMap());
		}
		catch (ClassNotFoundException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch (IOException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//选择平台
		//String s=LibVlc.INSTANCE.libvlc_get_version();lib/
		//System.out.println(s);
		/*
		 * VLCJ中提供了一种方法可以找到库对应的位置，但其返回的字符串包含有[AWT-EventQueue-0] INFO uk.co.caprica.vlcj.binding.LibVlcFactory - libvlc:
		 * 这样的前缀提示，而且在java中文件路径是以双斜线分层的，所以此处有改进的地方在于如何利用函数隐式配置路径，暂时还没有想到解决方法
		 */
		if (RuntimeUtil.isWindows())
			//filePath="/";
			filePath="./lib/VLC";//filePath = "C:\\Program Files\\VideoLAN\\VLC";
		else if (RuntimeUtil.isMac())
			filePath = "/Applications/VLC.app/Contents/MacOS/lib";
		else if (RuntimeUtil.isNix())
			filePath = "/home/linux/vlc/install/lib";

		NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), filePath);//必需的,因为根据您的操作系统命名不同的本地库。
		//System.out.println(LibVlc.INSTANCE.libvlc_get_version());

		EventQueue.invokeLater(new Runnable() {
			public void run() 
			{
				try 
				{
					//System.out.println(LibVlc.INFO.getInstance());
					//System.out.println(LibVlc.INFO.getInstance());
					frame = new JdxzDisplayFram();
					frame.setVisible(true);
					controlFrame = new JdxzControlFrame();
					videoTime = new JdxzVideoTime();
					//String[] optionDecode = { "--subsdec-encoding=GB18030" };
					// frame.getMediaPlayer().playMedia("D:\\Downloads\\weidainying.mp4",optionDecode);
					//frame.getMediaPlayer().prepareMedia("D:\\Downloads\\weidainying.mp4", optionDecode);
					//Publish progress of movie and get the total time and current time
					/*
					 * 这里写一个SwingWorker,这是一个用于需要在后台线程中运行长时间运行任务的情况，
					 * 并可在完成后或者在处理过程中提供更新。SwingWorker 的子类必须实现 doInBackground() 方法
					 */
					new SwingWorker<String, Integer>()
					{

						@Override
						protected String doInBackground() throws Exception 
						{
							while (true) 
							{
								
								//当前时间和总时间
								totalTime = frame.getMediaPlayer().getLength();
								currentTime = frame.getMediaPlayer().getTime();
								videoTime.timeCalculate(totalTime, currentTime);
								frame.getCurrentLabel().setText(videoTime.getMinitueCurrent() + ":" + videoTime.getSecondCurrent());
								frame.getTotalLabel().setText(videoTime.getMinitueTotal() + ":" + videoTime.getSecondTotal());
								controlFrame.getCurrentLabel().setText(frame.getCurrentLabel().getText());
								controlFrame.getTotalLabel().setText(frame.getTotalLabel().getText());
								
								//当前时间播放进程比
								float percent = (float) currentTime / totalTime;
								publish((int) (percent * 100));
								Thread.sleep(200);
							}
						}

						protected void process(java.util.List<Integer> chunks) 
						{
							for (int v : chunks) 
							{
								frame.getProgressBar().setValue(v);
								controlFrame.getProgressBar().setValue(v);
							}
						};
					}.execute();
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	//各项功能框架
	public static void play() 
	{
		frame.getMediaPlayer().play();
		frame.getPlayButton().setText("||");

	}

	//暂停
	public static void pause() 
	{
		frame.getMediaPlayer().pause();
		frame.getPlayButton().setText(">");
	}

	//停止
	public static void stop() 
	{
		frame.getMediaPlayer().stop();
		frame.getPlayButton().setText(">");
	}

	//快进
	public static void forword(float to) 
	{
		frame.getMediaPlayer().setTime((long) (to * frame.getMediaPlayer().getLength()));
	}

	//倒退
	public static void backword() 
	{
		frame.getPlayComponent().backward(frame.getMediaPlayer());
	}

	//显示当前时间
	public static void jumpTo(float to) 
	{
		frame.getMediaPlayer().setTime((long) (to * frame.getMediaPlayer().getLength()));
	}

	//设置音量
	public static void setVolum(int v) 
	{
		frame.getMediaPlayer().setVolume(v);
		frame.getVolumLabel().setText("" + frame.getMediaPlayer().getVolume());
		controlFrame.getVolumLabel().setText("" + frame.getMediaPlayer().getVolume());
	}

	//从电脑打开文件
	public static void openVedio() 
	{
		JFileChooser chooser = new JFileChooser(".");
		int v = chooser.showOpenDialog(null);
		if (v == JFileChooser.APPROVE_OPTION) 
		{
			File file = chooser.getSelectedFile();
			String name = file.getName();
			String path = file.getAbsolutePath();
			//System.out.println("name: " + name + " abpath: " +
			//file.getAbsolutePath() + " path: " + file.getPath());
			if (listView.getList().contains(name))
			{
				listView.getList().remove(name);
				listView.setList(name);
				listView.setMap(name, path);
			}
			else 
			{
				listView.setList(name);
				listView.setMap(name, path);
			}		
			//保存到历史
			try 
			{
				listHistory.writeHistory(listView.getList());
				listHistory.writeHistory(listView.getMap());
				System.out.println("read: " + listHistory.readHistory());
				System.out.println("read1: " + listHistory.readHistoryMap());
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			frame.getMediaPlayer().playMedia(file.getAbsolutePath());
			frame.getPlayButton().setText("||");
		}
	}


	//从历史中打开文件
	public static void openVedioFromList(String name)
	{

		String path = listView.getMap().get(name);
		if (listView.getList().contains(name)) 
		{
			listView.getList().remove(name);
			listView.setList(name);
			listView.setMap(name, path);
		}
		else 
		{
			listView.setList(name);
			listView.setMap(name, path);
		}
		// System.out.println("name: " + name + " abpath: " + path);

		// 保存历史
		try 
		{
			listHistory.writeHistory(listView.getList());
			listHistory.writeHistory(listView.getMap());
			System.out.println("read: " + listHistory.readHistory());
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frame.getMediaPlayer().playMedia(path);
		frame.getPlayButton().setText("||");
	}

	//打开字幕
	public static void openSubtitle() 
	{
		
		JFileChooser chooser = new JFileChooser(".");
		int v = chooser.showOpenDialog(null);
		//String options[] = {"--subsdec-encoding=GB18030"};
		if (v == JFileChooser.APPROVE_OPTION) 
		{
			File file = chooser.getSelectedFile();
			frame.getMediaPlayer().setSubTitleFile(file);
		}
	}

	//exit(0)
	public static void exit() 
	{
		frame.getMediaPlayer().release();
		System.exit(0);
	}

	//全屏
	public static void fullScreen()
	{
		frame.getMediaPlayer().setFullScreenStrategy(new DefaultAdaptiveRuntimeFullScreenStrategy(frame));
		frame.getProgressBar().setVisible(false);
		frame.getControlPanel().setVisible(false);
		frame.getProgressTimePanel().setVisible(false);
		frame.getJMenuBar().setVisible(false);
		frame.getMediaPlayer().setFullScreen(true);
		controlFrame.getVolumLabel().setText("" + frame.getMediaPlayer().getVolume());
		controlFrame.getListButton().setText("列表>>");
		controlFrame.getListButton().setOpaque(false);
		controlFrame.getListButton().setBackground(java.awt.Color.black);
		frame.setFlag(1);
		frame.getPlayComponent().getVideoSurface().addMouseMotionListener(new MouseMotionListener() 
		{

			@Override
			public void mouseMoved(MouseEvent e)
			{
				// TODO Auto-generated method stub
				if (frame.getFlag() == 1)
				{
					controlFrame.setLocation((frame.getWidth() - controlFrame.getWidth()) / 2,
							frame.getHeight() - controlFrame.getHeight());
					controlFrame.setVisible(true);
					controlFrame.getVolumControlerSlider().setValue(frame.getVolumControlerSlider().getValue());
					controlFrame.getPlayButton().setOpaque(false);
					controlFrame.getPlayButton().setBackground(java.awt.Color.BLUE);
					if (frame.getMediaPlayer().isPlaying())
						controlFrame.getPlayButton().setText("||");
					else
						controlFrame.getPlayButton().setText(">");

				}

			}

			@Override
			public void mouseDragged(MouseEvent e) 
			{
				// TODO Auto-generated method stub

			}
		});

	}

	//退出全屏
	public static void originalScreen() 
	{
		frame.getProgressBar().setVisible(true);
		frame.getControlPanel().setVisible(true);
		frame.getProgressTimePanel().setVisible(true);
		frame.getJMenuBar().setVisible(true);
		frame.getMediaPlayer().setFullScreen(false);
		frame.setFlag(0);
		if (frame.getMediaPlayer().isPlaying())
			frame.getPlayButton().setText("||");
		else
			frame.getPlayButton().setText(">");

		if (frame.getPlayListFrame().getFlag() == 1)
		{
			frame.getListButton().setText("列表>>");
		} else if (frame.getPlayListFrame().getFlag() == 0)
		{
			frame.getListButton().setText("<<列表");
		}
		controlFrame.setVisible(false);
	}

	public static JdxzDisplayFram getFrame()
	{
		return frame;
	}

	public static JdxzControlFrame getControlFrame() 
	{
		return controlFrame;
	}

//	public void setLogo()
//	{
//		JdxzLogo logo = new JdxzLogo();
//		frame.getMediaPlayer().setLogo(logo.getLogo());
//	}

	public static String getPath(String name)
	{
		return listView.getMap().get(name);
	}
	public static JdxzViewList getListView() 
	{
		return listView;
	}

	public static JdxzListHistory getListHistory() 
	{
		return listHistory;
	}
	public static long getCurrentTime()
	{
		return currentTime;
	}

}
