package Jdxz.play_list;
/*DefaultListMode与JList
 * 元素的插入和删除，这个看似简单的功能，实现起来可不是一件容易的事情。最天真的想法：我用一个数组构造JList，那么我只要将数组中的元素增加或减少，那么JList的内容自然会
 * 增加或减少。这绝对是个错误的认识，对数组内容的修改不会影响到JList。正确的做法是，用一个特别的类来构造JList，也就是DefaultListModel类，然后对这个类的内容添
 * 加或者减少，那么JList会很快的做出响应。为什么这个类会这么神奇呢？查看他的源代码得知，他继承自AbstractListModel类，内部用一个Vector来存储数据。在它的添加元素
 * 方法中，他调用了父类的fireIntervalAdded方法，同理，在他的删除元素方法中，他调用了父类的fireIntervalRemoved方法，因此可以将JList的内容刷。后就是内部元素的绘制了
 * 。在JList中，内部显示的每一个元素叫做一个Cell。我们就要借助JList的setCellRenderer方法来实现。该方法接受一个实现了ListCellRenderer接口的参数，而该接口
 * 只有一个方法getListCellRendererComponent，但是，该方法的实现是比较简单的，他只需要构造一个Component对象并返回这个对象，这个对象就是一个Cell。因此，真正的
 * 难点在于，如何修改这个Component的内部显示方式，从而使得他可以满足一个Cell的显示方式。一般来讲，我们都是用一个JPanel作为绘制Cell的对象，因为在JPanel上显示文字和图
 * 像都是很方便的。最后说一个小技巧，我们实现一个类，从JPanel继承而来，同时又实现了ListCellRenderer接口，那么我们只用实现这一个类就可以了
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
        //  /img/HomeImg.jpg 是存放在你正在编写的项目的bin文件夹下的img文件夹下的一个图片  
        //icon=new ImageIcon(getClass().getResource("/HomeImg1.jpg"));   
       
    	//img=icon.getImage();  
    	 icon = new ImageIcon(this.getClass().getClassLoader().getResource("image/HomeImg77.jpg"));//icon=new ImageIcon("img/HomeImg11.jpg");  
         img=icon.getImage();  
    }  
    public void paintComponent(Graphics g) {  
        super.paintComponent(g);  
        //下面这行是为了背景图片可以跟随窗口自行调整大小，可以自己设置成固定大小  
        g.drawImage(img, 0, 0,this.getWidth(), this.getHeight(), this);  
    }  
  
}  

class HomePanel extends JPanel {  
    ImageIcon icon;  
    Image img;  
    public HomePanel() {  
        //  /img/HomeImg.jpg 是存放在你正在编写的项目的bin文件夹下的img文件夹下的一个图片  
        //icon=new ImageIcon(getClass().getResource("/HomeImg.jpg"));  
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
				JdxzMain.getFrame().getListButton().setText("列表>>");
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
		 * 这段代码是为了让JList的单元格为透明
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
		searchButton = new JButton("搜索历史");
		searchButton.setForeground(java.awt.Color.red);
		searchButton.setOpaque(false);
		searchButton.setBackground(java.awt.Color.BLUE);
		searchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int j=list.getModel().getSize();//该方法是获取JList中所有值的个数。
				//System.out.println(j);
				String s2=(String)searchtField.getText();
			    for(int i=0;i<j;++i)
			    {
			    	String s1=list.getModel().getElementAt(i).toString();
			    	//System.out.println(s1+s2);
			    	if(s1.equals(s2)/*if(s11==s2)*/)//i是第i个元素
			    	{
			    		findflag=1;
			    		//System.out.println("afafaf");
			    		dialog.setVisible(true);
						dialog.getCancelButton().setVisible(false);
						dialog.setText("亲爱的小辣鸡，我找到视频了哦，大辣鸡爱你哟！");
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
					dialog.setText("%>_<% ，大辣鸡没找到这个视频欸，记得加上后缀啊!");
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

		historyClearButton = new JButton("清除历史");
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
				dialog.setText("小辣鸡，大辣鸡爱你哟，你确定删除所有历史记录？");
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
				           	 jdxz.setText("大辣鸡帮你把历史纪录删除了，历史纪录恋恋不舍说小辣鸡好漂亮！");
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
				           	 jdxz.setText("历史纪录说你太漂亮了，不想离开你，大辣鸡删除失败了！");
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
			dlm.addElement(arrayList.get(i));//创建并且设置列表数据模型
		}
		list.setModel(dlm);
	}
   
	public JScrollPane getScrollPane() {
		return scrollPane;
	}

}