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
	        // 创建一个进度条
	        final JProgressBar progressBar = new JProgressBar();
	        // 设置进度的 最小值 和 最大值
	        progressBar.setMinimum(MIN_PROGRESS);
	        progressBar.setMaximum(MAX_PROGRESS);
	        // 设置当前进度值
	        progressBar.setValue(currentProgress);
	        // 绘制百分比文本（进度条中间显示的百分数）
	        progressBar.setStringPainted(true);
	        // 添加到内容面板
	        panel.add(progressBar);
	        jf.setContentPane(panel);
	        jf.setVisible(true);
	        // 模拟延时操作进度, 每隔 秒更新进度
	        new Timer(10, new ActionListener() {
	            @Override
				public void actionPerformed(ActionEvent e) {
	                currentProgress++;               
	                progressBar.setValue(currentProgress);
	                if(currentProgress==MAX_PROGRESS ) {
	                	jf.dispose();
	                	status = controller.getStatus(name);
	                	if(status==0&&s==0) {
	                		JOptionPane.showMessageDialog(jf, "系统登录成功", "信息提示框", JOptionPane.WARNING_MESSAGE);
	                		new AdministratorInterface(user);
	                	}else if(status==1&&s==1) {
	                		JOptionPane.showMessageDialog(jf, "系统登录成功", "信息提示框", JOptionPane.WARNING_MESSAGE);
	                		new TheFilmInterface(user);    
	                	}else if(status==0&&s==1) {
	                		JOptionPane.showMessageDialog(jf, "无此用户", "alert", JOptionPane.ERROR_MESSAGE);
	                	new Login();
	                	}else if(status==1&&s==0) {
	                		JOptionPane.showMessageDialog(jf, "无此管理员", "alert", JOptionPane.ERROR_MESSAGE);
	                	new Login();
	                	}
	            
	                 }
	               
	            }
	           
	        }).start();
	    }
	   
}
