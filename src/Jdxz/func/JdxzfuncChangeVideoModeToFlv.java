package Jdxz.func;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;  
import java.util.Calendar;
import java.util.Date;
import java.util.List;  
/*
 * Java Calendar 类时间操作，。演示了获取时间，日期时间的累加和累减，以及比较。
	注意事项：
	Calendar 的 month 从 0 开始，也就是全年 12 个月由 0 ~ 11 进行表示。
	而 Calendar.DAY_OF_WEEK 定义和值如下：
	Calendar.SUNDAY = 1
	Calendar.MONDAY = 2
	Calendar.TUESDAY = 3
	Calendar.WEDNESDAY = 4
	Calendar.THURSDAY = 5
	Calendar.FRIDAY = 6
	Calendar.SATURDAY = 7
 */
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Jdxz.main.JdxzMain;
import Jdxz.play_list.JdxzDialog;
import Jdxz.play_list.JdxzPlayListFrame;

public class JdxzfuncChangeVideoModeToFlv{
	
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
	           	 jdxz.setText("爱你哟小辣鸡，大辣鸡帮你把视频转成flv格式了！");
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
	              	 jdxz.setText("难受啊小辣鸡，大辣鸡不能帮你把视频转成flv格式了，呜呜呜！");
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
            status = processFLV(inPATH);// 直接将文件转为flv文件  
        } else if (type == 1) {  
            String avifilepath = processAVI(type);  
            if (avifilepath == null)  
                return false;// avi文件没有得到  
            status = processFLV(avifilepath);// 将avi转为flv  
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
    private static String processAVI(int type) {  
        List<String> commend = new ArrayList<String>();  
        commend.add("./mencoder.exe");  
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
            return "c:\\ffmpeg\\output\\a.avi";  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
    }  
    // ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）  
    private static boolean processFLV(String oldfilepath) {  
  
        if (!checkfile(oldfilepath)) {  
            System.out.println(oldfilepath + " is not file");  
            return false;  
        }  
          
        
        List<String> commend = new ArrayList<String>();  
        commend.add("./ffmpeg.exe");  
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
        inPATH=inPATH+".flv";  
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
