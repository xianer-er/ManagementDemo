package View;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Control.Controller;
import Model.GetSet;
import View.Login;

public class ResetPasswords extends JFrame implements ActionListener{
	public Image image;
	Controller controller = new Controller();
	GetSet user = new GetSet(); 
	JButton btnSubmit = new JButton("提交");   
	JButton btnExit = new JButton("退出"); 
	JButton btnEnsure = new JButton("验证密保");
	JTextField jtfUsername = new JTextField();
	JTextField jtfAnswer = new JTextField();
	public JPasswordField jpfCode = new JPasswordField();
	
	public ResetPasswords() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(300, 10);
		this.setLayout(null);
		this.setSize(1000,800);
		this.setResizable(false);
		this.setTitle("找回密码");
		this.setVisible(true);
		btnSubmit.setBounds(200, 700, 80, 50);
		btnExit.setBounds(600, 700, 80, 50);
		jtfUsername.setBounds(200, 100, 200, 50);
		jtfAnswer.setBounds(200, 250,200, 50);
		btnEnsure.setBounds(430, 250, 100, 50);
		jpfCode.setBounds(200, 400, 200, 50);
		jpfCode.setEditable(false);
		this.add(btnSubmit);
		this.add(btnExit);
		this.add(btnEnsure);
		this.add(jtfUsername);
		this.add(jtfAnswer);
		this.add(jpfCode);
		btnSubmit.addActionListener(this); 
		btnExit.addActionListener(this); 
		btnEnsure.addActionListener(this);
		jtfUsername.addActionListener(this); 
		jtfAnswer.addActionListener(this);
		jpfCode.addActionListener(this);
	}
	@Override
	public void paint(Graphics g) {
		btnSubmit.repaint();
		btnExit.repaint();
		jtfUsername.repaint();
		jtfAnswer.repaint();
		jpfCode.repaint();
		btnEnsure.repaint();
		image = new ImageIcon("bin\\Image\\bg2.jpg").getImage();
		g.drawImage(image, 0, 0, this);
		g.drawString("用户名：", 200, 100);
		g.drawString("密保问题：你最喜欢的电影是：", 200, 250);
		g.drawString("新密码(6-10位数字)：", 200, 400);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String code1 = new String(jpfCode.getPassword());
if(e.getSource() == btnSubmit) {

	if((jtfUsername.getText().equals(""))||(jtfAnswer.getText().equals(""))||(code1.equals(""))) {
		JOptionPane.showMessageDialog(null, "请输入正确的信息", "警告提示框", JOptionPane.WARNING_MESSAGE);
	return;
	}else {
		String sss = "\\d{6,10}";
		if(code1.matches(sss)) {
			user.setCode(code1);
			controller.CreateUsers2(user);
			JOptionPane.showMessageDialog(this, "恭喜你，提交成功！重新登录，开启新世界的大门吧！", "提交成功", JOptionPane.CLOSED_OPTION);
			this.dispose();
			new Login();
		}else {
			JOptionPane.showMessageDialog(this,"请按密码规则输入正确密码","警告提示框",JOptionPane.WARNING_MESSAGE);
		}
		}
		
}
if(e.getSource()==btnEnsure) {

	if((jtfUsername.getText().equals(""))||(jtfAnswer.getText().equals(""))) {
		JOptionPane.showMessageDialog(null, "请输入正确的用户名和密保答案", "警告提示框", JOptionPane.WARNING_MESSAGE);
	return;
	}else {
		user.setName(jtfUsername.getText());
		user.setAnswer(jtfAnswer.getText());
		GetSet users = controller.findAnswer(user);
		if(users!=null&&(users.getAnswer().equals(jtfAnswer.getText()))) {       //输入用户名和密报全都正确
			JOptionPane.showMessageDialog(null, "验证成功，您可以输入新密码", "警告提示框", JOptionPane.WARNING_MESSAGE);	
		jpfCode.setEditable(true);
		}else {
			JOptionPane.showMessageDialog(null, "请输入正确的用户名和密保答案", "警告提示框", JOptionPane.WARNING_MESSAGE);
		}
	}}
if(e.getSource()==btnExit) {
	this.dispose();
}		
	}

}
