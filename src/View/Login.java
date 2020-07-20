package View;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Control.Controller;
import Model.GetSet;
import Model.Judge;
import View.Login;
import View.Register;

public class Login extends JFrame implements ActionListener {
	Judge judge = new Judge();
	Controller controller = new Controller();
	public Image image;

	//创建按钮
	public JButton btnLogin = new JButton("登录"); 
	public JButton btnForgetPassword = new JButton("忘记密码");
	public JButton btnAbout = new JButton("关于");
	public JButton btnRegister = new JButton("注册");
	//创建下拉列表框
	 String[] list = new String[] {"管理员","用户"};
	 public  JComboBox<String> comboBoxStatus = new JComboBox<String>(list);
	//创建文本框
	public JTextField jtfUsername = new JTextField();
	public JPasswordField jpfUsercode = new JPasswordField();
	// 新建一个字体模板
	Font font = new Font("宋体", Font.BOLD, 40); 
	GetSet user1;
	public int status ;
	public Login() {
		//对于界面的设置
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //点击×不关闭界面
		this.setLocation(300, 10);
		this.setLayout(null);
		this.setSize(1000, 800);
		this.setResizable(false);                              //不可以最小化
		this.setTitle("登录");
		this.setVisible(true);
		//组件设置位置
		btnLogin.setBounds(600, 600, 130, 50);
		btnForgetPassword.setBounds(820, 450, 130, 50);
		btnAbout.setBounds(400, 600, 130, 50);
		btnRegister.setBounds(200, 600, 130, 50);
		comboBoxStatus.setBounds(500, 200, 80, 30);
		jtfUsername.setBounds(350, 285, 440, 110);
		jpfUsercode.setBounds(350, 400, 440, 110);
		// 调整文本框为透明
		jtfUsername.setOpaque(false); 
		jpfUsercode.setOpaque(false);
		jtfUsername.setFont(font);
		jpfUsercode.setFont(font);
		// 把组件加入frame界面
		this.add(comboBoxStatus);
		this.add(btnLogin); 
		this.add(btnForgetPassword);
		this.add(btnAbout);
		this.add(btnRegister);
		this.add(jtfUsername);
		this.add(jpfUsercode);
		// 给组件加上监听
		btnLogin.addActionListener(this); 
		btnForgetPassword.addActionListener(this);
		btnAbout.addActionListener(this);
		btnRegister.addActionListener(this);
		comboBoxStatus.addActionListener(this);
	}
	@Override
	public void paint(Graphics g) {
		comboBoxStatus.repaint();
		btnLogin.repaint();
		btnForgetPassword.repaint();
		btnAbout.repaint();
		btnRegister.repaint();
		jtfUsername.repaint();
		jpfUsercode.repaint();
		image = new ImageIcon("bin\\Image\\login.jpg").getImage();
		g.drawImage(image, 0, 0, this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String code = new String(jpfUsercode.getPassword());
		if(e.getSource() == btnLogin) {
			if(code.equals(controller.getUserCode(jtfUsername.getText()))) {
				GetSet user = new GetSet();
				user.setName(jtfUsername.getText());
				user.setAll(user, jtfUsername.getText());
				this.user1 = user;
				this.dispose();
				new ProgressBar(user1,comboBoxStatus.getSelectedIndex());
			}else {
				JOptionPane.showMessageDialog(this,"请输入正确的用户名或密码","警告提示框",JOptionPane.WARNING_MESSAGE);
			}
		}
		if(e.getSource() == btnForgetPassword) {
			new ResetPasswords();
		}
		
		if(e.getSource() == btnAbout) {
			JOptionPane.showMessageDialog(this, "欢迎来到仙儿的电影世界，在这里你可以查看所给的电影的信息.\n制作人：三组郝庆会", "关于",
					JOptionPane.CLOSED_OPTION);
		}
		if(e.getSource() ==btnRegister) {
			this.dispose();
			new Register(comboBoxStatus.getSelectedIndex());
		}
	}
	public static void main(String[] args) {
		new Login();
	}
}
