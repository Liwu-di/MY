package Jdxz.func;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import Jdxz.main.JdxzMain;
import Jdxz.play_list.JdxzDialog;
import Jdxz.views.JdxzDisplayFram;

public class JdxzfuncChangeVideoModeToAvi {
	private static JFileChooser chooseInputFileWantToChangeLocation=new JFileChooser(".");
	private static String inPATH;
	public static String TypeWantToChangeTo;
  
    public static void main(String[] args) {  
    	int result=chooseInputFileWantToChangeLocation.showOpenDialog(null);
    	if(result == JFileChooser.APPROVE_OPTION)
    	{
    		inPATH = chooseInputFileWantToChangeLocation.getSelectedFile().getAbsolutePath();
    		if (!checkfile(inPATH)) {  
    			System.out.println(inPATH + " is not file");   
    		}
    		if (process()) {  
    			JdxzDialog jdxz=new JdxzDialog();
           	 jdxz.setBounds(300, 300, 400, 200);
				 jdxz.setVisible(true);
				 jdxz.getCancelButton().setVisible(false);
           	 jdxz.setText("爱你哟小辣鸡，大辣鸡帮你把视频转成avi格式了！");
           	 jdxz.getOkButton().addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						jdxz.setVisible(false);
						//jf.setVisible(false);
					}
				});
            System.out.println("ok");  
    		} 
    		else {
    			JdxzDialog jdxz=new JdxzDialog();
              	 jdxz.setBounds(300, 300, 400, 200);
   				 jdxz.setVisible(true);
   				 jdxz.getCancelButton().setVisible(false);
              	 jdxz.setText("难受啊小辣鸡，大辣鸡不能帮你把视频转成avi格式了，呜呜呜！");
              	 jdxz.getOkButton().addMouseListener(new MouseAdapter() {
   					@Override
   					public void mouseClicked(MouseEvent e) {
   						jdxz.setVisible(false);
   						//jf.setVisible(false);
   					}
   				});
               System.out.println("false");  
    		}
    	}
    }  
  
    private static boolean process() {  
    	 
        int type = checkContentType();  
        boolean status = false;  
        if (type == 0) {  
            //System.out.println("直接将文件转为flv文件");  
            status = processAvi(inPATH);// 直接将文件转为flv文件  
        } else if (type == 1) {  
            processAVI(type);  
        }  
        return status;  
    }  
  
    private static int checkContentType() {  
        String type = inPATH.substring(inPATH.lastIndexOf(".") + 1, inPATH.length())  
                .toLowerCase();  
        // ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）  
        if (type.equals("avi")) {  
            return 0;  
        } else if (type.equals("mpg")) {  
            return 0;  
        } else if (type.equals("wmv")) {  
            return 0;  
        } else if (type.equals("3gp")) {  
            return 0;  
        } else if (type.equals("mov")) {  
            return 0;  
        } else if (type.equals("mp4")) {  
            return 0;  
        } else if (type.equals("asf")) {  
            return 0;  
        } else if (type.equals("asx")) {  
            return 0;  
        } else if (type.equals("flv")) {  
            return 0;  
        }  
        // 对ffmpeg无法解析的文件格式(wmv9，rm，rmvb等),  
        // 可以先用别的工具（mencoder）转换为avi(ffmpeg能解析的)格式.  
        else if (type.equals("wmv9")) {  
            return 1;  
        } else if (type.equals("rm")) {  
            return 1;  
        } else if (type.equals("rmvb")) {  
            return 1;  
        }  
        return 9;  
    }  
    public static boolean checkfile(String path) {  
        File file = new File(path);  
        if (!file.isFile()) {  
            return false;  
        }  
        return true;  
    }  
    // 对ffmpeg无法解析的文件格式(wmv9，rm，rmvb等), 可以先用别的工具（mencoder）转换为avi(ffmpeg能解析的)格式.  
    private static void processAVI(int type) {  
        List<String> commend = new ArrayList<String>();  
        commend.add("./mencoder.exe"); //commend.add("F:\\kechengsheji\\VLC\\plugins\\mux\\ffmpeg-20171223-9e5e323-win64-static\\bin\\mencoder");  
        commend.add(inPATH);  
        commend.add("-oac");  
        commend.add("lavc");  
        commend.add("-lavcopts");  
        commend.add("acodec=mp3:abitrate=64");  
        commend.add("-ovc");  
        commend.add("xvid");  
        commend.add("-xvidencopts");  
        commend.add("bitrate=600");  
        commend.add("-of");  
        commend.add("avi");  
        commend.add("-o"); 
        inPATH=inPATH.substring(0,inPATH.lastIndexOf(".") + 1);
        inPATH=inPATH+"avi";       
        //System.out.println(inPATH);
        commend.add(inPATH);  
        try {  
            ProcessBuilder builder = new ProcessBuilder();  
            builder.command(commend);  
            builder.start();  
        } catch (Exception e) {  
            e.printStackTrace();    
        }  
    }  
    // ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）  
    private static boolean processAvi(String oldfilepath) {  
  
        if (!checkfile(oldfilepath)) {  
            System.out.println(oldfilepath + " is not file");  
            return false;  
        }  
          
        
        List<String> commend = new ArrayList<String>();  
        commend.add("./ffmpeg.exe");//commend.add("F:\\kechengsheji\\VLC\\plugins\\mux\\ffmpeg-20171223-9e5e323-win64-static\\bin\\ffmpeg");  
        commend.add("-y"); 
        commend.add("-i");  
        commend.add(oldfilepath);  
        commend.add("-ab");  
        commend.add("56");  
        commend.add("-ar");  
        commend.add("22050");  
        commend.add("-qscale");  
        commend.add("8");  
        commend.add("-r");  
        commend.add("15");  
        commend.add("-s");  
        commend.add("600x500");  
        inPATH=inPATH.substring(0,inPATH.lastIndexOf("."));
        inPATH=inPATH+".avi";  
        System.out.println(inPATH);
        commend.add(inPATH);  
        try {  
            //Runtime runtime = Runtime.getRuntime();  
//            Process proce = null;  
//            ProcessBuilder builder = new ProcessBuilder(commend);  
//            builder.command(commend);  
//            builder.start(); 
            Process videoProcess = new ProcessBuilder(commend).redirectErrorStream(true).start();
                                   
            new PrintStream(videoProcess.getInputStream()).start();
                         
            videoProcess.waitFor();
                         
             return true;  
        } catch (Exception e) {  
            e.printStackTrace();  
            return false;  
        }  
    }  
}  
class PrintStream extends Thread 
 {
	
     java.io.InputStream __is = null;
     public PrintStream(java.io.InputStream is) 
     {
         __is = is;
     } 
 
     public void run() 
     {
//    	 JFrame jf = new JFrame("正在转换中....");  
//         Container contentPane = jf.getContentPane();  
//         contentPane.setLayout(new BorderLayout());  
//         JTextArea jta = new JTextArea(10,30 );  
//         jta.setTabSize(4);  
//         jta.setLineWrap(true);// 激活自动换行功能  
//         jta.setWrapStyleWord(true);// 激活断行不断字功能  
//         jta.setBackground(Color.green);  
//         JScrollPane jscrollPane = new JScrollPane(jta); 
//         jscrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
// 		 jscrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
//         JPanel jpanel = new JPanel();  
//         jpanel.setLayout(new GridLayout(1, 3));    
//         contentPane.add(jscrollPane, BorderLayout.CENTER);  
//         jf.setSize(700, 300);  
//         jf.setLocation(300, 250);  
//         jf.setVisible(true);  
//         jf.setVisible(true);
 
         try 
         {
             while(this != null) 
             {
                 int _ch = __is.read();
        
                 if(_ch != -1)
                 {
                	//jta.append((char)_ch+"");
                	System.out.print((char)_ch); 
                 }
                 else
                 {
                	 break;
                 }
             }
         } 
         catch (Exception e) 
         {
             e.printStackTrace();
         } 
     }
 }


