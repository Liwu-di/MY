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
            //commend.add("-y");  //��������ļ�
            commend.add("-ss");  //������ָ����ʱ�� [-]hh:mm:ss[.xxx]�ĸ�ʽҲ֧��
            commend.add(Integer.toString(second));  
            commend.add("-i");  //-i filename �����ļ�
            commend.add(VideoRealPath);  
            commend.add("-vframes");  //ǿ�Ȳ��ø�ʽfmt
            commend.add("100");  
            //commend.add("-y");  //���ü�¼ʱ�� hh:mm:ss[.xxx]��ʽ�ļ�¼ʱ��Ҳ֧��
            commend.add("-f");
            commend.add("gif");
            commend.add(VividPictueLocationPath+"\\"+curTime+".gif");  
            System.out.println("-------------"+VividPictueLocationPath+"\\"+curTime+".gif");
            
            /*
             *ֻ�ܽ�һ�ζ�̬GIF��ԭ������commend�е����һ��������FFmpeg�õ������һ�������
             *����˵commend����F:\kechengsheji\2018118174238.gif ��FFmpeg��ȴ��
             *F:\kechengsheji\2018118174223.gif
             *������һ�ν�ͼ���ļ����ظ�����������ڴ˴����-y����ʱ����ʹ�õ���ͼƬ��С����Ϊ0������˵���޽ӽ�0
             *��������ĸ�Դ���ڵڶ��ν�ͼʱcommend˳����е����������ȷ����FFmpeg
             */
            /*
             * ��������Ľ�������ǰ�arraylist�Ķ�����try catch�����new��֮ǰ��������������һ�κ�commend
             * ���Ѿ�����һ������ǵ�һ�ε�����ڶ���ʱ������������FFmpegִ��ʱֻ��ִ�е�һ��������Դ˺�����
             * ���ж��ٴγ���ֻ��ʹcommend���������ӣ���FFmpegֻ��ִ�е�һ�ݣ�����FFmpeg�ᱨ�ļ��Ѿ����ڵĴ���
             * ���������-y���һ�ν�ͼҲ�겻�ɣ��������û����ͨ
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
                    	 jdxz.setText("�װ���С��������̬��ͼ�����Ŷ������Ӵ");
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
            	 jdxz.setText("�װ���С�����������أ���̬��ͼʧ����");
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