package Jdxz.func;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.omg.CORBA.portable.InputStream;

import Jdxz.play_list.JdxzDialog;

import javax.swing.JFileChooser;

public class JdxzfuncCutSecondPictureLikeStaticPicture {
	private JFileChooser chooseStaticPictureLocation=new JFileChooser(".");
	private List<String> commend = new ArrayList<String>();
	private ProcessBuilder builder = new ProcessBuilder();
	private String path; 
    public void makeScreenCut(String ffmepgPath,String videoRealPath,int second){  
	chooseStaticPictureLocation.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    	int result=chooseStaticPictureLocation.showOpenDialog(null);
//    	chooseStaticPictureLocation.setAcceptAllFileFilterUsed(false);
//    	chooseStaticPictureLocation.addChoosableFileFilter(new ExcelFileFilter("jpg"));
    	if(result == JFileChooser.APPROVE_OPTION)
    	{
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
//    		Calendar cal = Calendar.getInstance(); 
//    		java.util.Date date = cal.getTime(); 
//
//    		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMddhhmmssSSS"); 
//
//    		String myTime = sdFormat.format(currentTime);
    		//�����ѡ����ļ�����·����
    	   path = chooseStaticPictureLocation.getSelectedFile().getAbsolutePath();
    	   //System.out.println(path);
    	   /*
    	    * ffmepg�ڽ�ͼʱ��������Ϊ��ʼ��ͼ��ʱ�����λ�ò��ԣ�Ӧ���ڴ��ļ�֮ǰ
    	    */
    	   commend.add(ffmepgPath);
           commend.add("-y");  //��������ļ�
           commend.add("-ss");  //������ָ����ʱ�� [-]hh:mm:ss[.xxx]�ĸ�ʽҲ֧��
           commend.add(Integer.toString(second));  
           commend.add("-i");  //-i filename �����ļ�
           commend.add(videoRealPath);  
           commend.add("-f");  //ǿ�Ȳ��ø�ʽfmt
           commend.add("image2");  
           commend.add("-t");  //���ü�¼ʱ�� hh:mm:ss[.xxx]��ʽ�ļ�¼ʱ��Ҳ֧��
           commend.add("0.001");  
           commend.add(path+"\\"+curTime+".jpg");  
           try {  
           builder.command(commend);  
           builder.redirectErrorStream(true);  
           System.out.println("��Ƶ��ͼ��ʼ...");  
     
           Process process = builder.start();  
           java.io.InputStream in = process.getInputStream();  
           byte[] bytes = new byte[1024];  
           System.out.print("���ڽ��н�ͼ�����Ժ�");  
           while (in.read(bytes)!= -1){  
           	
           }       		
   			JdxzDialog jdxz=new JdxzDialog();
          	 jdxz.setBounds(300, 300, 400, 200);
				 jdxz.setVisible(true);
				 jdxz.getCancelButton().setVisible(false);
          	 jdxz.setText("����ӴС�����������������ͼ�ˣ�");
          	 jdxz.getOkButton().addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						jdxz.setVisible(false);
						//jf.setVisible(false);
					}
				});
         
   	
   		
   			
              System.out.println("false");  
   		
               System.out.println("��Ƶ��ȡ���...");  
     
           } catch (IOException e) {  
               e.printStackTrace();  
               JdxzDialog jdxz=new JdxzDialog();
           	 jdxz.setBounds(300, 300, 400, 200);
				 jdxz.setVisible(true);
				 jdxz.getCancelButton().setVisible(false);
           	 jdxz.setText("���ܰ�С���������������ܰ����ͼ�������أ�");
           	 jdxz.getOkButton().addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						jdxz.setVisible(false);
						//jf.setVisible(false);
					}
				});
               System.out.println("��Ƶ��ͼʧ�ܣ�");  
           }  
    	}
        
   }  
}
