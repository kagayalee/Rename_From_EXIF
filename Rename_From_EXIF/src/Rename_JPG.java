
 
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
		
		
		
		JFrame frame = new JFrame("��Ƭ�ļ�����С����@Lizheng");      // �½�һ�����壬����ı����ǡ����е����ݣ�
	    JPanel panel = new JPanel();			   // ����������࣬������swing�У�
	    JTextArea textArea = new JTextArea();     //  JTextArea  �ı����� JTextField�ı��� 
	    textArea.setRows(10);
	    panel.setLayout(new GridLayout());        // Layout�ǲ��֣� GridLayout �����Ͳ���
	    textArea.setText("\r\n	�˳���ɽ��ֻ�����������������JPG�ļ������ư�����ƬԪ���ݡ�EXIF��Ϣ���е�����ʱ�����\r\n	�����ˣ����\r\n	����ʱ�䣺2017��2��5��\r\n\r\n	˵����ѡ��һ��Ŀ¼,Ŀ¼������.jpg�ļ������ƽ���Ϊ��IMG_YYYYMMDD_HHMMSS.jpg �ĸ�ʽ\r\n	ѡ��Ŀ¼�󣬴˴��ڽ�����ļ��������̣����������ǰ����Ҫ�رա�");
	    //��TextArea������ݹ���ʱ���ɹ�����
	    panel.add(new JScrollPane(textArea));
	    frame.add(panel);                     //���½��Ĵ����������壻
	    frame.setSize(1000,500);                  //���ô���ĳߴ�
	    frame.setLocation(50, 20);				//���ô����λ��
	    frame.setVisible(true);                  //ע����һ��һ��Ҫ�����ã�ʹ���������ɼ���false�ǲ��ɼ���
	    frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);  //�����˵���رհ�ť�����ã�
		
		JFileChooser jfc=new JFileChooser("c:");
	    jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
	    jfc.showDialog(new JLabel(), "ȷ��");
	    //jfc.showOpenDialog(new JLabel());
	    
	    File file=jfc.getSelectedFile();
	    if(file.isDirectory()){
	        System.out.println("�ļ���:"+file.getAbsolutePath());
	    }else if(file.isFile()){
	        System.out.println("�ļ�:"+file.getAbsolutePath());
	    }
	    
		//String FilePath = "D:" + File.separator +"������Ƭ" + File.separator;
        //File file = new File(FilePath);
        print(file,textArea);
    }
	
	
	
	
	//�ݹ���ʾ�ļ���������һ���ļ���
	public static void print(File f,JTextArea textArea){
		
        if(f!=null){
            if(f.isDirectory()){
                File[] fileArray=f.listFiles();
                if(fileArray!=null){
                    for (int i = 0; i < fileArray.length; i++) {
                        //�ݹ����
                        print(fileArray[i],textArea);
                    }
                }
            }
            else{
               // System.out.println(f);
               //System.out.println(f.getParentFile());
                try {
                	//�����ļ���EXIF��Ϣ���ļ���
					RenameAsEXIF.Rename(f,textArea);
				} catch (ImageProcessingException | NullPointerException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        }
    }
	
	
	
	
	
}