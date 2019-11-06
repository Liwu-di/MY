package Jdxz.views;

import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;
import Jdxz.main.JdxzMain;

public class JdxzKeyBordListenerEven {

	public void keyBordListner(){
		Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
			@Override
			public void eventDispatched(AWTEvent event) {
				// TODO Auto-generated method stub
				if(((KeyEvent)event).getID()==KeyEvent.KEY_PRESSED){
					switch (((KeyEvent)event).getKeyCode()) {
					case KeyEvent.VK_RIGHT:{//快进的一个小应用
						int a = JdxzMain.getFrame().getVolumControlerSlider().getValue();
						JdxzMain.getFrame().getVolumControlerSlider().setValue(a);
						JdxzMain.forword((float)(((JdxzMain.getFrame().getProgressBar().getPercentComplete() * JdxzMain.getFrame().getProgressBar().getWidth() + 10)) / JdxzMain.getFrame().getProgressBar().getWidth()));
					}
						break;
					case KeyEvent.VK_LEFT:{//
						JdxzMain.jumpTo((float)((JdxzMain.getFrame().getProgressBar().getPercentComplete() * JdxzMain.getFrame().getProgressBar().getWidth() - 5) / JdxzMain.getFrame().getProgressBar().getWidth()));
					}
						break;
					case KeyEvent.VK_ESCAPE:{//切换全屏或者小屏
						if(!JdxzMain.getFrame().getMediaPlayer().isFullScreen())
							JdxzMain.fullScreen();
						else
							JdxzMain.originalScreen();
						
					}
						break;
					case KeyEvent.VK_UP:{//音量
						JdxzMain.getFrame().getVolumControlerSlider().setValue(JdxzMain.getFrame().getVolumControlerSlider().getValue() + 1);
						JdxzMain.getControlFrame().getVolumControlerSlider().setValue(JdxzMain.getFrame().getVolumControlerSlider().getValue());
//						MyMain.getFrame().getVolumLabel().setText("" + MyMain.getFrame().getVolumControlerSlider().getValue());
					}
						break;
					case KeyEvent.VK_DOWN:
						JdxzMain.getFrame().getVolumControlerSlider().setValue(JdxzMain.getFrame().getVolumControlerSlider().getValue() - 1);
						JdxzMain.getControlFrame().getVolumControlerSlider().setValue(JdxzMain.getFrame().getVolumControlerSlider().getValue());
						break;
					case KeyEvent.VK_SPACE:{
						if(JdxzMain.getFrame().getMediaPlayer().isPlaying()){
							JdxzMain.pause();
							JdxzMain.getControlFrame().getPlayButton().setText(JdxzMain.getFrame().getPlayButton().getText());
						}
						else{
							JdxzMain.play();
							JdxzMain.getControlFrame().getPlayButton().setText(JdxzMain.getFrame().getPlayButton().getText());
						}
					}
						break;
					}
				}
			}
		}, AWTEvent.KEY_EVENT_MASK);
	}
}
