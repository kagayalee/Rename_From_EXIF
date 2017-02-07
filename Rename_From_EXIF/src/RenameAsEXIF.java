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
	//String Path = "D:" + File.separator +"旅游照片" + File.separator;
	//String OldName = "microMsg.1438827388363.jpg";
    //imgFile = new File(Path + OldName);
    Date date=null;

    //建立文件的mate信息
    Metadata metadata = ImageMetadataReader.readMetadata(imgFile);
    
    //读取directory数组，并找出Original时间
    for (Directory directory : metadata.getDirectories()) { 
        for (Tag tag : directory.getTags()) {  
            //System.out.println(tag.getTagName() +" ## " + tag.getDescription());
            String TagName=tag.getTagName();
            String TagDes=tag.getDescription();
            if(TagName.indexOf("ate")!=-1&TagName.indexOf("riginal")!=-1){
            	try {
            		System.out.println(imgFile.getAbsolutePath()+"时间信息："+TagName+"##"+TagDes);
					date = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss").parse(TagDes);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println(imgFile.getAbsolutePath()+"文件没有Original信息！！！");
				}
            //break;
            }
        }  
    }  
    
    //关联文件的directory数组
//    ExifSubIFDDirectory directory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
    
    
    
    //读取文件ORIGINAL时间(但有时差，暂未能解决)
//  Date date = directory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);
    
//    System.out.println("*"+date+"*");
   
    //转换时间格式
    DateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
    String dateString = format.format(date);
    System.out.println("*"+dateString+"*");
    
    //文件改名
    //File oldfile=imgFile; 
    File newFile=new File(imgFile.getParentFile()+File.separator+"IMG_"+dateString+".jpg"); 
    if(newFile.exists())//若在该目录下已经有一个文件和新文件名相同，则不允许重命名 
        System.out.println(imgFile.getParentFile()+File.separator+"IMG_"+dateString+".jpg :"+"已经存在！"); 
    else{ 
        String sou = imgFile.getAbsolutePath();
    	imgFile.renameTo(newFile); 
        Counter.FileNum=Counter.FileNum+1;
        System.out.println("改名："+Counter.FileNum+"个文件；"+newFile.getAbsolutePath()+"已改名！！！");
        String message = textArea.getText();
        //textArea.append(String.format("%s%n", message));
        //textArea.setText("改名："+Counter.FileNum+"个文件；");
        textArea.append("\r\n"+"已改名："+Counter.FileNum+"个文件；	文件"+sou+" \t已改为 "+newFile.getAbsolutePath());
        
    } 
	}
}
