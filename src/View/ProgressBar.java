package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.WindowConstants;

import Control.Controller;
import Model.GetSet;

public class ProgressBar {
	 private static final int MIN_PROGRESS = 0;
	    private static final int MAX_PROGRESS = 100;
	 Controller controller = new Controller();
	 Login l;
	 int status;

	 
	 String name;
	    private static int currentProgress = MIN_PROGRESS;
	    public ProgressBar(GetSet user,int s) {
	    	
	    	this.name = user.getName();
	        JFrame jf = new JFrame("waiting");
	        jf.setSize(100, 100);
	        jf.setLocationRelativeTo(null);
	        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	        JPanel panel = new JPanel();
	        // ����һ��������
	        final JProgressBar progressBar = new JProgressBar();
	        // ���ý��ȵ� ��Сֵ �� ���ֵ
	        progressBar.setMinimum(MIN_PROGRESS);
	        progressBar.setMaximum(MAX_PROGRESS);
	        // ���õ�ǰ����ֵ
	        progressBar.setValue(currentProgress);
	        // ���ưٷֱ��ı����������м���ʾ�İٷ�����
	        progressBar.setStringPainted(true);
	        // ��ӵ��������
	        panel.add(progressBar);
	        jf.setContentPane(panel);
	        jf.setVisible(true);
	        // ģ����ʱ��������, ÿ�� ����½���
	        new Timer(10, new ActionListener() {
	            @Override
				public void actionPerformed(ActionEvent e) {
	                currentProgress++;               
	                progressBar.setValue(currentProgress);
	                if(currentProgress==MAX_PROGRESS ) {
	                	jf.dispose();
	                	status = controller.getStatus(name);
	                	if(status==0&&s==0) {
	                		JOptionPane.showMessageDialog(jf, "ϵͳ��¼�ɹ�", "��Ϣ��ʾ��", JOptionPane.WARNING_MESSAGE);
	                		new AdministratorInterface(user);
	                	}else if(status==1&&s==1) {
	                		JOptionPane.showMessageDialog(jf, "ϵͳ��¼�ɹ�", "��Ϣ��ʾ��", JOptionPane.WARNING_MESSAGE);
	                		new TheFilmInterface(user);    
	                	}else if(status==0&&s==1) {
	                		JOptionPane.showMessageDialog(jf, "�޴��û�", "alert", JOptionPane.ERROR_MESSAGE);
	                	new Login();
	                	}else if(status==1&&s==0) {
	                		JOptionPane.showMessageDialog(jf, "�޴˹���Ա", "alert", JOptionPane.ERROR_MESSAGE);
	                	new Login();
	                	}
	            
	                 }
	               
	            }
	           
	        }).start();
	    }
	   
}
