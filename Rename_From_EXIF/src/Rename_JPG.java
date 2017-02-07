
 
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.io.IOException;  
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.drew.imaging.ImageMetadataReader;  
import com.drew.imaging.ImageProcessingException;
import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.metadata.Directory;  
import com.drew.metadata.Metadata;  
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.ExifSubIFDDirectory; 

public class Rename_JPG {
	
	public static void main(String[] args) throws ImageProcessingException, IOException {
		
		
		
		JFrame frame = new JFrame("照片文件改名小程序@Lizheng");      // 新建一个窗体，窗体的标题是“”中的内容，
	    JPanel panel = new JPanel();			   // 是面板容器类，包含在swing中；
	    JTextArea textArea = new JTextArea();     //  JTextArea  文本区， JTextField文本框 
	    textArea.setRows(10);
	    panel.setLayout(new GridLayout());        // Layout是布局； GridLayout 网格型布局
	    textArea.setText("\r\n	此程序可将手机，数码相机等拍摄的JPG文件的名称按照照片元数据“EXIF信息”中的拍摄时间改名\r\n	制作人：李峥\r\n	制作时间：2017年2月5日\r\n\r\n	说明：选择一个目录,目录中所有.jpg文件的名称将改为：IMG_YYYYMMDD_HHMMSS.jpg 的格式\r\n	选定目录后，此窗口将监控文件改名过程，批处理完成前，不要关闭。");
	    //当TextArea里的内容过长时生成滚动条
	    panel.add(new JScrollPane(textArea));
	    frame.add(panel);                     //在新建的窗体中添加面板；
	    frame.setSize(1000,500);                  //设置窗体的尺寸
	    frame.setLocation(50, 20);				//设置窗体的位置
	    frame.setVisible(true);                  //注意这一步一定要最后调用，使得这个窗体可见，false是不可见；
	    frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);  //定义了点击关闭按钮的作用；
		
		JFileChooser jfc=new JFileChooser("c:");
	    jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
	    jfc.showDialog(new JLabel(), "确定");
	    //jfc.showOpenDialog(new JLabel());
	    
	    File file=jfc.getSelectedFile();
	    if(file.isDirectory()){
	        System.out.println("文件夹:"+file.getAbsolutePath());
	    }else if(file.isFile()){
	        System.out.println("文件:"+file.getAbsolutePath());
	    }
	    
		//String FilePath = "D:" + File.separator +"旅游照片" + File.separator;
        //File file = new File(FilePath);
        print(file,textArea);
    }
	
	
	
	
	//递归显示文件名，并逐一改文件名
	public static void print(File f,JTextArea textArea){
		
        if(f!=null){
            if(f.isDirectory()){
                File[] fileArray=f.listFiles();
                if(fileArray!=null){
                    for (int i = 0; i < fileArray.length; i++) {
                        //递归调用
                        print(fileArray[i],textArea);
                    }
                }
            }
            else{
               // System.out.println(f);
               //System.out.println(f.getParentFile());
                try {
                	//按照文件的EXIF信息改文件名
					RenameAsEXIF.Rename(f,textArea);
				} catch (ImageProcessingException | NullPointerException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        }
    }
	
	
	
	
	
}