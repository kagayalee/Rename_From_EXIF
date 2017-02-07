import java.awt.GridLayout;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class LaunchWindow {
	static int FileNum = 0;
	static File file= null;
	static JTextArea textArea= null;
	public void LaunchWindow(){
		JFrame frame = new JFrame("文件信息窗口");      // 新建一个窗体，窗体的标题是“”中的内容，
	    JPanel panel = new JPanel();			   // 是面板容器类，包含在swing中；
	    textArea = new JTextArea();     //  JTextArea  文本区， JTextField文本框 
	    textArea.setRows(10);
	    panel.setLayout(new GridLayout());        // Layout是布局； GridLayout 网格型布局
	    textArea.setText("	此处显示文件改名信息");
	    //当TextArea里的内容过长时生成滚动条
	    panel.add(new JScrollPane(textArea));
	    frame.add(panel);                     //在新建的窗体中添加面板；
	    frame.setSize(400,400);                  //设置窗体的尺寸
	    frame.setLocation(100, 200);				//设置窗体的位置
	    frame.setVisible(true);                  //注意这一步一定要最后调用，使得这个窗体可见，false是不可见；
	    frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);  //定义了点击关闭按钮的作用；
		
		  JFileChooser jfc=new JFileChooser();
	    jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
	    //jfc.showDialog(new JLabel(), "请选择续趸明细文件,注意：文件格式必须是。xls");
	    jfc.showOpenDialog(new JLabel());
	    
	    file=jfc.getSelectedFile();
	    if(file.isDirectory()){
	        System.out.println("文件夹:"+file.getAbsolutePath());
	    }else if(file.isFile()){
	        System.out.println("文件:"+file.getAbsolutePath());
	    }
	}
}
