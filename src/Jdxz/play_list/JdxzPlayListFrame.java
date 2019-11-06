package Jdxz.play_list;
/*DefaultListMode��JList
 * Ԫ�صĲ����ɾ����������Ƽ򵥵Ĺ��ܣ�ʵ�������ɲ���һ�����׵����顣��������뷨������һ�����鹹��JList����ô��ֻҪ�������е�Ԫ�����ӻ���٣���ôJList��������Ȼ��
 * ���ӻ���١�������Ǹ��������ʶ�����������ݵ��޸Ĳ���Ӱ�쵽JList����ȷ�������ǣ���һ���ر����������JList��Ҳ����DefaultListModel�࣬Ȼ���������������
 * �ӻ��߼��٣���ôJList��ܿ��������Ӧ��Ϊʲô��������ô�����أ��鿴����Դ�����֪�����̳���AbstractListModel�࣬�ڲ���һ��Vector���洢���ݡ����������Ԫ��
 * �����У��������˸����fireIntervalAdded������ͬ��������ɾ��Ԫ�ط����У��������˸����fireIntervalRemoved��������˿��Խ�JList������ˢ��������ڲ�Ԫ�صĻ�����
 * ����JList�У��ڲ���ʾ��ÿһ��Ԫ�ؽ���һ��Cell�����Ǿ�Ҫ����JList��setCellRenderer������ʵ�֡��÷�������һ��ʵ����ListCellRenderer�ӿڵĲ��������ýӿ�
 * ֻ��һ������getListCellRendererComponent�����ǣ��÷�����ʵ���ǱȽϼ򵥵ģ���ֻ��Ҫ����һ��Component���󲢷��������������������һ��Cell����ˣ�������
 * �ѵ����ڣ�����޸����Component���ڲ���ʾ��ʽ���Ӷ�ʹ������������һ��Cell����ʾ��ʽ��һ�����������Ƕ�����һ��JPanel��Ϊ����Cell�Ķ�����Ϊ��JPanel����ʾ���ֺ�ͼ
 * ���Ǻܷ���ġ����˵һ��С���ɣ�����ʵ��һ���࣬��JPanel�̳ж�����ͬʱ��ʵ����ListCellRenderer�ӿڣ���ô����ֻ��ʵ����һ����Ϳ�����
 */
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.print.attribute.standard.Media;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import Jdxz.main.JdxzMain;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;

class HomeJScrollPane extends JScrollPane {  
    ImageIcon icon;  
    Image img;  
    public HomeJScrollPane() {  
        //  /img/HomeImg.jpg �Ǵ���������ڱ�д����Ŀ��bin�ļ����µ�img�ļ����µ�һ��ͼƬ  
        //icon=new ImageIcon(getClass().getResource("/HomeImg1.jpg"));   
       
    	//img=icon.getImage();  
    	 icon = new ImageIcon(this.getClass().getClassLoader().getResource("image/HomeImg77.jpg"));//icon=new ImageIcon("img/HomeImg11.jpg");  
         img=icon.getImage();  
    }  
    public void paintComponent(Graphics g) {  
        super.paintComponent(g);  
        //����������Ϊ�˱���ͼƬ���Ը��洰�����е�����С�������Լ����óɹ̶���С  
        g.drawImage(img, 0, 0,this.getWidth(), this.getHeight(), this);  
    }  
  
}  

class HomePanel extends JPanel {  
    ImageIcon icon;  
    Image img;  
    public HomePanel() {  
        //  /img/HomeImg.jpg �Ǵ���������ڱ�д����Ŀ��bin�ļ����µ�img�ļ����µ�һ��ͼƬ  
        //icon=new ImageIcon(getClass().getResource("/HomeImg.jpg"));  
        //img=icon.getImage();  
    	 icon = new ImageIcon(this.getClass().getClassLoader().getResource("image/HomeImg22.jpg"));  
         img=icon.getImage();
    }  
    public void paintComponent(Graphics g) {  
        super.paintComponent(g);  
        //����������Ϊ�˱���ͼƬ���Ը��洰�����е�����С�������Լ����óɹ̶���С  
        g.drawImage(img, 0, 0,this.getWidth(), this.getHeight(), this);  
    }  
  
}  

public class JdxzPlayListFrame extends JFrame {

	private String name;
	private HomePanel contentPane;
	private int flag = 0;
	private int findflag=0;
	private JList list = new JList();
	private HomeJScrollPane scrollPane;
	private HomePanel panel;
	private JButton historyClearButton;
	private DefaultListModel dlm = new DefaultListModel();
	private JButton searchButton;
	private JTextField searchtField;
	private HomePanel panel_1;
	final JdxzDialog dialog = new JdxzDialog();
	/**
	 * Create the frame,to display the watched history .
	 */
	public JdxzPlayListFrame() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				flag = 1;
				JdxzMain.getFrame().getListButton().setText("�б�>>");
				JdxzMain.getControlFrame().getListButton().setText(JdxzMain.getFrame().getListButton().getText());
			}
		});
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setMaximizedBounds(new Rectangle((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 400, 0, 400,
				(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()));
		// setBounds(100, 100, 229, 394);
		try {
			if (!JdxzMain.getListHistory().readHistory().isEmpty())
				setList(JdxzMain.getListHistory().readHistory());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		contentPane = new HomePanel();
		contentPane.setOpaque(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		scrollPane = new HomeJScrollPane();
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);  
		//scrollPane.setBackground(java.awt.Color.CYAN);
		//scrollPane.setEnabled(false);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		list.setOpaque(false);
		/*
		 * ��δ�����Ϊ����JList�ĵ�Ԫ��Ϊ͸��
		 */
		list.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList jlist, Object o, int i, boolean bln, boolean bln1) {
                Component listCellRendererComponent = super.getListCellRendererComponent(jlist, o, i, bln, bln1);
                JLabel label=(JLabel) listCellRendererComponent;
                label.setForeground(java.awt.Color.orange);
                label.setOpaque(false);
                
                return label;
            }
        });
		//(JLabel)list.getCellRenderer().setOpaque(false);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					//get movie in list to play
					String name = JdxzMain.getListView().getList()
							.get(JdxzMain.getListView().getList().size() - 1 - list.getSelectedIndex());
//					System.out.println("list hight: " + list.getSelectedIndex());
//					System.out.println("item: " + name);
					JdxzMain.openVedioFromList(name);
					setList(JdxzMain.getListView().getList());
					getScrollPane().setViewportView(getList());
				}
			}
		});
		//list.
		contentPane.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(getList());

		panel = new HomePanel();
		panel.setOpaque(false);
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));

		panel_1 = new HomePanel();
		panel_1.setOpaque(false);
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		searchtField = new JTextField();
		searchtField.setOpaque(false);
		searchtField.setText("");
		panel_1.add(searchtField);
		searchtField.setColumns(10);

		//History search when the records are very huge and hard to find out the one you want
		searchButton = new JButton("������ʷ");
		searchButton.setForeground(java.awt.Color.red);
		searchButton.setOpaque(false);
		searchButton.setBackground(java.awt.Color.BLUE);
		searchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int j=list.getModel().getSize();//�÷����ǻ�ȡJList������ֵ�ĸ�����
				//System.out.println(j);
				String s2=(String)searchtField.getText();
			    for(int i=0;i<j;++i)
			    {
			    	String s1=list.getModel().getElementAt(i).toString();
			    	//System.out.println(s1+s2);
			    	if(s1.equals(s2)/*if(s11==s2)*/)//i�ǵ�i��Ԫ��
			    	{
			    		findflag=1;
			    		//System.out.println("afafaf");
			    		dialog.setVisible(true);
						dialog.getCancelButton().setVisible(false);
						dialog.setText("�װ���С���������ҵ���Ƶ��Ŷ������������Ӵ��");
						dialog.setBounds(JdxzMain.getFrame().getPlayListFrame().getX() + 15,
								JdxzMain.getFrame().getPlayListFrame().getY() + 100, 350, 115);
						//get okbutton and give its new function 
						dialog.getOkButton().addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								dialog.setVisible(false);
							}
						});
			    		name = JdxzMain.getListView().getList()
								.get(JdxzMain.getListView().getList().size()-1 - i);
						//name = JdxzMain.getListView().getList()
							//	.get(i);
			    		System.out.println(name);
						JdxzMain.openVedioFromList(name);
						setList(JdxzMain.getListView().getList());
						getScrollPane().setViewportView(getList());
						break;
			    	}
			    }
			    if(findflag==0)
			    {
			    	
					dialog.setVisible(true);
					dialog.getCancelButton().setVisible(false);
					dialog.setText("%>_<% ��������û�ҵ������Ƶ�G���ǵü��Ϻ�׺��!");
					dialog.setBounds(JdxzMain.getFrame().getPlayListFrame().getX() + 15,
							JdxzMain.getFrame().getPlayListFrame().getY() + 100, 350, 115);
					//get okbutton and give its new function 
					dialog.getOkButton().addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							dialog.setVisible(false);
						}
					});
			    }
				findflag=0;
			}
		});
		panel_1.add(searchButton);

		historyClearButton = new JButton("�����ʷ");
		historyClearButton.setForeground(java.awt.Color.BLACK);
		historyClearButton.setOpaque(false);
		historyClearButton.setBackground(java.awt.Color.cyan);
		
		panel_1.add(historyClearButton);
		
		//Clear history
		historyClearButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				final JdxzDialog dialog = new JdxzDialog();
				dialog.setVisible(true);
				dialog.setText("С����������������Ӵ����ȷ��ɾ��������ʷ��¼��");
				dialog.setBounds(JdxzMain.getFrame().getPlayListFrame().getX() + 15,
						JdxzMain.getFrame().getPlayListFrame().getY() + 100, 350, 115);
				dialog.getCancelButton().addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						dialog.setVisible(false);
					}
				});

				dialog.getOkButton().addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						try {
							dialog.setVisible(false);
							JdxzMain.getListHistory().cleanHistory();
							dlm.clear();
							list.setModel(dlm);
							scrollPane.setViewportView(getList());
							JdxzDialog jdxz=new JdxzDialog();
				           	 jdxz.setBounds(300, 300, 400, 200);
								 jdxz.setVisible(true);
								 jdxz.getCancelButton().setVisible(false);
				           	 jdxz.setText("�������������ʷ��¼ɾ���ˣ���ʷ��¼��������˵С������Ư����");
				           	 jdxz.getOkButton().addMouseListener(new MouseAdapter() {
									@Override
									public void mouseClicked(MouseEvent e) {
										jdxz.setVisible(false);
										//jf.setVisible(false);
									}
								});
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							JdxzDialog jdxz=new JdxzDialog();
				           	 jdxz.setBounds(300, 300, 400, 200);
								 jdxz.setVisible(true);
								 jdxz.getCancelButton().setVisible(false);
				           	 jdxz.setText("��ʷ��¼˵��̫Ư���ˣ������뿪�㣬������ɾ��ʧ���ˣ�");
				           	 jdxz.getOkButton().addMouseListener(new MouseAdapter() {
									@Override
									public void mouseClicked(MouseEvent e) {
										jdxz.setVisible(false);
										//jf.setVisible(false);
									}
								});
						}

					}
				});

			}

		});

	}
	//get the first movie name 
	public String getName(){
		name = list.getModel().getElementAt(0).toString();
		return name;
	}
	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public JList getList() {

		return list;
	}

	public void setList(ArrayList<String> arrayList) {

		dlm = new DefaultListModel();
		for (int i = arrayList.size() - 1; i >= 0; i--) {
			dlm.addElement(arrayList.get(i));//�������������б�����ģ��
		}
		list.setModel(dlm);
	}
   
	public JScrollPane getScrollPane() {
		return scrollPane;
	}

}