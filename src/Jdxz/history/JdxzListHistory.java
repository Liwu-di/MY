package Jdxz.history;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import Jdxz.func.JdxzfuncChangeVideoModeToFlv;

public class JdxzListHistory {
	private static String path1 = "./Jdxzoutput.txt";
	private static String path2 = "./Jdxzoutput1.txt";
	public JdxzListHistory(){
		if(JdxzfuncChangeVideoModeToFlv.checkfile(path1))
		{
			File jdxz=new File("./Jdxzoutput.txt");
		}
		if(JdxzfuncChangeVideoModeToFlv.checkfile(path1))
		{
			File jdxz=new File("./Jdxzoutput1.txt");
		}
	}

	//private static String path3 = "D:\\Jdxzoutput2.txt";
	// input list to file to save
	public static void writeHistory(ArrayList<String> list) throws IOException{
		if(JdxzfuncChangeVideoModeToFlv.checkfile(path1))
		{
			File jdxz=new File("./Jdxzoutput.txt");
		}
		if(JdxzfuncChangeVideoModeToFlv.checkfile(path1))
		{
			File jdxz=new File("./Jdxzoutput1.txt");
		}
		FileOutputStream fos = new FileOutputStream(path1);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(list);
		oos.close();
	}
	//Read history:movie name record from the file by object using
	public static ArrayList<String> readHistory() throws ClassNotFoundException, IOException{
		if(JdxzfuncChangeVideoModeToFlv.checkfile(path1))
		{
			File jdxz=new File("./Jdxzoutput.txt");
		}
		if(JdxzfuncChangeVideoModeToFlv.checkfile(path1))
		{
			File jdxz=new File("./Jdxzoutput1.txt");
		}
		FileInputStream fis = new FileInputStream(path1);
		ObjectInputStream ois = new ObjectInputStream(fis);
		ArrayList<String> historyList = (ArrayList<String>) ois.readObject();
		ois.close();
		return historyList;
	}
	
	//Write history:movie name and absolutely path record to the file
	public static void writeHistory(HashMap<String, String> map) throws IOException{
		if(JdxzfuncChangeVideoModeToFlv.checkfile(path1))
		{
			File jdxz=new File("./Jdxzoutput.txt");
		}
		if(JdxzfuncChangeVideoModeToFlv.checkfile(path1))
		{
			File jdxz=new File("./Jdxzoutput1.txt");
		}
		FileOutputStream fos = new FileOutputStream(path2);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(map);
		oos.close();
	}
	
	//Read history:movie name and path record from the file by using object
	public static HashMap<String, String> readHistoryMap() throws ClassNotFoundException, IOException{
		if(JdxzfuncChangeVideoModeToFlv.checkfile(path1))
		{
			File jdxz=new File("./Jdxzoutput.txt");
		}
		if(JdxzfuncChangeVideoModeToFlv.checkfile(path1))
		{
			File jdxz=new File("./Jdxzoutput1.txt");
		}
		FileInputStream fis = new FileInputStream(path2);
		ObjectInputStream ois = new ObjectInputStream(fis);
		HashMap<String, String> historyMap = (HashMap<String, String>) ois.readObject();
		ois.close();
		return historyMap;
	}
	
	//Clear storage history record
	public static void cleanHistory() throws IOException{
		if(JdxzfuncChangeVideoModeToFlv.checkfile(path1))
		{
			File jdxz=new File("./Jdxzoutput.txt");
		}
		if(JdxzfuncChangeVideoModeToFlv.checkfile(path1))
		{
			File jdxz=new File(" ./Jdxzoutput1.txt");
		}
		//只有RandomAccessFile才有seek搜寻方法，而这个方法适用于文件
		RandomAccessFile file1 = new RandomAccessFile(path1, "rw");
		RandomAccessFile file2 = new RandomAccessFile(path2, "rw");
		//FileChannel是一个连接到文件的通道。可以通过文件通道读写文件。FileChannel无法设置为非阻塞模式，它总是运行在阻塞模式下。我们无法直接打开一个FileChannel，需要通过使用一个
		//RandomAccessFile来获取一个FileChannel
		FileChannel fc1 = file1.getChannel();
		FileChannel fc2 = file2.getChannel();
		fc1.truncate(1);
		fc2.truncate(1);
	}
}