package Jdxz.func;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JFileChooser;

import Jdxz.main.JdxzMain;
import Jdxz.play_list.JdxzDialog;

public class JdxzfuncCutSecondsPicturesLikeVividGif {
	private static JFileChooser chooseVividPictureLocation;
	private static List<String> commend ;
	private ProcessBuilder builder = new ProcessBuilder();
	private static String VividPictueLocationPath; 
	//public  void mains(int second,String VideoRealPath)
	public  void mains(String VideoRealPath){													
		chooseVividPictureLocation=new JFileChooser(".");
		chooseVividPictureLocation.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    	int result=chooseVividPictureLocation.showOpenDialog(null);
    	if(result == JFileChooser.APPROVE_OPTION)
    	{
    		commend = new ArrayList<String>();
    		int second=(int)JdxzMain.getCurrentTime()/1000;
    		VividPictueLocationPath=chooseVividPictureLocation.getSelectedFile().getAbsolutePath();
    		new SimpleDateFormat("yyyyMMddHHmmssSSS") .format(new Date() );
    		Calendar Cld = Calendar.getInstance();
    		int YY = Cld.get(Calendar.YEAR) ;
    		int MM = Cld.get(Calendar.MONTH)+1;
    		int DD = Cld.get(Calendar.DATE);
    		int HH = Cld.get(Calendar.HOUR_OF_DAY);
    		int MM1 = Cld.get(Calendar.MINUTE);
    		int SS = Cld.get(Calendar.SECOND);
    		String curTime = Integer.toString(YY)+Integer.toString(MM)+Integer.toString(DD)+Integer.toString(HH)+Integer.toString(MM1)+Integer.toString(SS);
    		//System.out.println(curTime);
    		commend.add("./ffmpeg.exe");  
            //commend.add("-y");  //覆盖输出文件
            commend.add("-ss");  //搜索到指定的时间 [-]hh:mm:ss[.xxx]的格式也支持
            commend.add(Integer.toString(second));  
            commend.add("-i");  //-i filename 输入文件
            commend.add(VideoRealPath);  
            commend.add("-vframes");  //强迫采用格式fmt
            commend.add("100");  
            //commend.add("-y");  //设置纪录时间 hh:mm:ss[.xxx]格式的记录时间也支持
            commend.add("-f");
            commend.add("gif");
            commend.add(VividPictueLocationPath+"\\"+curTime+".gif");  
            System.out.println("-------------"+VividPictueLocationPath+"\\"+curTime+".gif");
            
            /*
             *只能截一次动态GIF的原因在于commend中的最后一个命令与FFmpeg得到的最后一个命令不符
             *比如说commend中是F:\kechengsheji\2018118174238.gif 而FFmpeg中却是
             *F:\kechengsheji\2018118174223.gif
             *会与上一次截图的文件名重复，但是如果在此处添加-y命令时，会使得到的图片大小仅仅为0，或者说无限接近0
             *所以问题的根源在于第二次截图时commend顺序表中的命令如何正确传给FFmpeg
             */
            /*
             * 上述问题的解决方案是把arraylist的对象在try catch语句中new，之前的问题在于运行一次后commend
             * 中已经有了一分命令，是第一次的命令，第二次时会接受命令，但是FFmpeg执行时只会执行第一份命令，所以此后无论
             * 运行多少次程序，只会使commend中命令增加，但FFmpeg只会执行第一份，所以FFmpeg会报文件已经存在的错误
             * 但添加命令-y后第一次截图也完不成，这个问题没有想通
             */
            
            try {  
//                Runtime runtime = Runtime.getRuntime();  
//                Process proce = null;  
//                ProcessBuilder builder = new ProcessBuilder(commend);  
//                builder.command(commend);  
//                builder.start(); 
                Process videoProcess = new ProcessBuilder(commend).redirectErrorStream(true).start();
                                       
                new PrintStream(videoProcess.getInputStream()).start();
                             
                videoProcess.waitFor();
                             
                 
            } catch (Exception e) {  
                e.printStackTrace();  
               
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
             try 
             {
                 while(this != null) 
                 {
                     int _ch = __is.read();
                     if(_ch != -1)
                     {
                    	//System.out.println(".");
                    	 System.out.print((char)_ch); 
                     }
                     else
                     {
                    	 JdxzDialog jdxz=new JdxzDialog();
                    	 jdxz.setBounds(300, 300, 400, 200);
     					 jdxz.setVisible(true);
     					 jdxz.getCancelButton().setVisible(false);
                    	 jdxz.setText("亲爱的小辣鸡，动态截图完成了哦，爱你哟");
                    	 jdxz.getOkButton().addMouseListener(new MouseAdapter() {
     						@Override
     						public void mouseClicked(MouseEvent e) {
     							jdxz.setVisible(false);
     						}
     					});
                    	 break;
                     }
                 }
             } 
             catch (Exception e) 
             {
                 e.printStackTrace();
                 JdxzDialog jdxz=new JdxzDialog();
            	 jdxz.setBounds(300, 300, 400, 200);
					 jdxz.setVisible(true);
					 jdxz.getCancelButton().setVisible(false);
            	 jdxz.setText("亲爱的小辣鸡，呜呜呜，动态截图失败了");
            	 jdxz.getOkButton().addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							jdxz.setVisible(false);
						}
					});
             } 
         }
     }
    }