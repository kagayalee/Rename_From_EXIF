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
		JFrame frame = new JFrame("�ļ���Ϣ����");      // �½�һ�����壬����ı����ǡ����е����ݣ�
	    JPanel panel = new JPanel();			   // ����������࣬������swing�У�
	    textArea = new JTextArea();     //  JTextArea  �ı����� JTextField�ı��� 
	    textArea.setRows(10);
	    panel.setLayout(new GridLayout());        // Layout�ǲ��֣� GridLayout �����Ͳ���
	    textArea.setText("	�˴���ʾ�ļ�������Ϣ");
	    //��TextArea������ݹ���ʱ���ɹ�����
	    panel.add(new JScrollPane(textArea));
	    frame.add(panel);                     //���½��Ĵ����������壻
	    frame.setSize(400,400);                  //���ô���ĳߴ�
	    frame.setLocation(100, 200);				//���ô����λ��
	    frame.setVisible(true);                  //ע����һ��һ��Ҫ�����ã�ʹ���������ɼ���false�ǲ��ɼ���
	    frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);  //�����˵���رհ�ť�����ã�
		
		  JFileChooser jfc=new JFileChooser();
	    jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
	    //jfc.showDialog(new JLabel(), "��ѡ��������ϸ�ļ�,ע�⣺�ļ���ʽ�����ǡ�xls");
	    jfc.showOpenDialog(new JLabel());
	    
	    file=jfc.getSelectedFile();
	    if(file.isDirectory()){
	        System.out.println("�ļ���:"+file.getAbsolutePath());
	    }else if(file.isFile()){
	        System.out.println("�ļ�:"+file.getAbsolutePath());
	    }
	}
}
