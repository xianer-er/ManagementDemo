package View;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Control.Controller;
import Model.GetSet;
import Model.Judge;
import View.Login;
public class Register extends JFrame implements ActionListener{
	public Image image;
	int s;
	GetSet newUser = new GetSet();
	Controller controller = new Controller();
	//创建各种组件   
	public JButton btnSubmit = new JButton("提交");   
	public JButton btnExit = new JButton("退出");
	String[] listData = new String[]{"男", "女"};
	public JComboBox<String> gender = new JComboBox<String>(listData);
	public JTextField jtfUsername = new JTextField();
	public JPasswordField jpfCode = new JPasswordField();
	public JPasswordField jpfCode2 = new JPasswordField();
	public JTextField jtfAnswer = new JTextField();
	public Register(int status) {
		this.s = status;
		this.setLocation(300, 10);
		this.setLayout(null);
		this.setSize(900,800);
		this.setResizable(false);
		this.setTitle("注册");
		this.setVisible(true);
		btnSubmit.setBounds(200, 700, 80, 50);
		btnExit.setBounds(600, 700, 80, 50);
		jtfUsername.setBounds(200, 100, 200, 50);
		jpfCode.setBounds(200, 300, 400, 50);
		jpfCode2.setBounds(200, 400, 400, 50);
		jtfAnswer.setBounds(200, 500,400, 50);
		gender.setBounds(300, 200, 50, 30);
		this.add(btnSubmit);
		this.add(btnExit);
		this.add(jtfUsername);
		this.add(jpfCode);
		this.add(jpfCode2);
		this.add(jtfAnswer);  
		this.add(gender);
		btnSubmit.addActionListener(this); 
		btnExit.addActionListener(this); 
		jtfUsername.addActionListener(this);
		jpfCode.addActionListener(this); 
		jpfCode2.addActionListener(this); 
		jtfAnswer.addActionListener(this); 
	} 
	@Override
	public void paint(Graphics g) {
		gender.repaint();
		btnSubmit.repaint();
		btnExit.repaint();
		jtfUsername.repaint();
		jpfCode.repaint();
		jpfCode2.repaint();
		jtfAnswer.repaint();
		image = new ImageIcon("bin\\Image\\bg2.jpg").getImage();
		g.drawImage(image, 0, 0, this);
		g.drawString("您的性别是", 200, 250);
		g.drawString("用户名（限2-10位字段，只支持数字，字母）：", 200, 100);
		g.drawString("密码（只能是6-10位的数字）：", 200, 320);
		g.drawString("确认密码：", 200, 400);
		g.drawString("密保问题：你最喜欢的电影是：", 200, 500);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String codee = new String(jpfCode2.getPassword());
		String code1 = new String(jpfCode.getPassword());
		if(e.getSource()==btnSubmit) {
			String userName = jtfUsername.getText();
			String code = code1;
			String code2 = codee;
			String answer = jtfAnswer.getText();
			int sex = gender.getSelectedIndex();
			GetSet user = controller.findUsers(userName);
			if((userName.equals(""))||(code.equals(""))||(code2.equals(""))||(answer.equals(""))) {
				JOptionPane.showMessageDialog(null, "请填写完整！", "警告提示框", JOptionPane.WARNING_MESSAGE);
			}else {
				//定义正则表达式
				String ss = "\\w{2,10}";
				String sss = "\\d{6,10}";   
				if(user==null) {               //如果数据库中没有此用户且两次输入密码相同。
					if(userName.matches(ss)) {         //如果用户名满足2-10位       
						if(code.matches(sss)) {         
							if(code.equals(code2)) {      //如果符合6-10位数字
								newUser.setName(userName);
								newUser.setCode(code);
								newUser.setAnswer(answer);
								newUser.setGender(sex);
								newUser.setStatus(1);
								controller.CreateUsers(newUser);
								JOptionPane.showMessageDialog(this, "恭喜你，提交成功！重新登录，开启新世界的大门吧！", "提交成功", JOptionPane.CLOSED_OPTION);
								this.dispose();
								new Login();
							}else {
								JOptionPane.showMessageDialog(this, "两次密码不一致，请输入正确密码", "警告提示框", JOptionPane.WARNING_MESSAGE);
							}
						}else {
							JOptionPane.showMessageDialog(this,"请按密码规则输入正确密码","警告提示框",JOptionPane.WARNING_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(this, "请输入正确的用户名！", "警告提示框", JOptionPane.WARNING_MESSAGE);
					}

				}else{
					JOptionPane.showMessageDialog(this, "该用户名已存在，请换一个试试吧！", "警告提示框", JOptionPane.WARNING_MESSAGE);
				} 
			}
		}
		if(e.getSource()==btnExit) {
			int n=JOptionPane.showConfirmDialog(this,"你确定要丢下我走了么？","film",JOptionPane.YES_NO_OPTION);
			//如果按下exit 按钮出现的操作
			if(n==JOptionPane.NO_OPTION) {
				JOptionPane.showMessageDialog(this, "继续填写吧，提交成功后，就可以开启新世界的大门了", "( •̀ ω •́ )✧",  JOptionPane.CLOSED_OPTION);
				//如果按下yes按钮出现的操作
			}
			if(n==JOptionPane.YES_OPTION) {

				this.dispose();
				new Login();
			}
		}} 
}
