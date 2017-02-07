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

import javax.swing.JTextArea;

import com.drew.imaging.ImageMetadataReader;  
import com.drew.imaging.ImageProcessingException;
import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.metadata.Directory;  
import com.drew.metadata.Metadata;  
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.ExifSubIFDDirectory; 

public class RenameAsEXIF  {
	public static void Rename(File imgFile,JTextArea textArea) throws ImageProcessingException,NullPointerException,IOException{
	//String Path = "D:" + File.separator +"������Ƭ" + File.separator;
	//String OldName = "microMsg.1438827388363.jpg";
    //imgFile = new File(Path + OldName);
    Date date=null;

    //�����ļ���mate��Ϣ
    Metadata metadata = ImageMetadataReader.readMetadata(imgFile);
    
    //��ȡdirectory���飬���ҳ�Originalʱ��
    for (Directory directory : metadata.getDirectories()) { 
        for (Tag tag : directory.getTags()) {  
            //System.out.println(tag.getTagName() +" ## " + tag.getDescription());
            String TagName=tag.getTagName();
            String TagDes=tag.getDescription();
            if(TagName.indexOf("ate")!=-1&TagName.indexOf("riginal")!=-1){
            	try {
            		System.out.println(imgFile.getAbsolutePath()+"ʱ����Ϣ��"+TagName+"##"+TagDes);
					date = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss").parse(TagDes);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println(imgFile.getAbsolutePath()+"�ļ�û��Original��Ϣ������");
				}
            //break;
            }
        }  
    }  
    
    //�����ļ���directory����
//    ExifSubIFDDirectory directory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
    
    
    
    //��ȡ�ļ�ORIGINALʱ��(����ʱ���δ�ܽ��)
//  Date date = directory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);
    
//    System.out.println("*"+date+"*");
   
    //ת��ʱ���ʽ
    DateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
    String dateString = format.format(date);
    System.out.println("*"+dateString+"*");
    
    //�ļ�����
    //File oldfile=imgFile; 
    File newFile=new File(imgFile.getParentFile()+File.separator+"IMG_"+dateString+".jpg"); 
    if(newFile.exists())//���ڸ�Ŀ¼���Ѿ���һ���ļ������ļ�����ͬ�������������� 
        System.out.println(imgFile.getParentFile()+File.separator+"IMG_"+dateString+".jpg :"+"�Ѿ����ڣ�"); 
    else{ 
        String sou = imgFile.getAbsolutePath();
    	imgFile.renameTo(newFile); 
        Counter.FileNum=Counter.FileNum+1;
        System.out.println("������"+Counter.FileNum+"���ļ���"+newFile.getAbsolutePath()+"�Ѹ���������");
        String message = textArea.getText();
        //textArea.append(String.format("%s%n", message));
        //textArea.setText("������"+Counter.FileNum+"���ļ���");
        textArea.append("\r\n"+"�Ѹ�����"+Counter.FileNum+"���ļ���	�ļ�"+sou+" \t�Ѹ�Ϊ "+newFile.getAbsolutePath());
        
    } 
	}
}
