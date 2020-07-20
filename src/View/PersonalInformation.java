package View;

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
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Control.Controller;
import Model.GetSet;

public class PersonalInformation extends JFrame implements ActionListener{
	public Image image;
	Controller controller = new Controller();
	GetSet uuser ;
	public JPanel panel = new JPanel();                     //创建新的面板
	JTextField jtfUsername = new JTextField();
	String[] listData = new String[]{"男", "女"};
	public JComboBox<String> gender = new JComboBox<String>(listData);
	JButton btnSubmit = new JButton("保存");   
	JButton btnExit = new JButton("退出"); 
	JButton btnEdit = new JButton("编辑");
	JButton btnNewcode = new JButton("重置密码");
	public PersonalInformation(GetSet user) {
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setLocation(300, 10);
		this.setLayout(null);
		this.setSize(700,800);
		this.setResizable(false);
		this.setTitle("我的信息");
		this.setVisible(true);
		jtfUsername.setText(user.getName());
		gender.setSelectedIndex(user.getGender());
		uuser = user;
		gender.setBounds(200, 300, 70,40);
		btnEdit.setBounds(400, 200, 70, 40);
		btnNewcode.setBounds(250,500,100, 50);
		btnSubmit.setBounds(100, 500, 80, 50);
		btnExit.setBounds(500, 500,80, 50);
		jtfUsername.setBounds(200, 200, 200, 40);
		panel.setBounds(10, 10, 500, 500);
		this.add(gender);
		this.add(btnEdit);
		this.add(btnNewcode);
		this.add(btnSubmit);
		this.add(btnExit);
		this.add(jtfUsername);
		jtfUsername.setEditable(false);
		btnSubmit.addActionListener(this); 
		btnExit.addActionListener(this); 
		btnEdit.addActionListener(this);
		jtfUsername.addActionListener(this); 
		btnNewcode.addActionListener(this);
	}
	@Override
	public void paint(Graphics g) {
		panel.repaint();
		jtfUsername.repaint();
		btnSubmit.repaint();
		btnExit.repaint();
		btnEdit.repaint();
		btnNewcode.repaint();
		gender.repaint();
		image = new ImageIcon("bin\\Image\\bg2.jpg").getImage();
		g.drawImage(image, 0, 0, this);
		g.drawString("我的用户名(2-10位字母或数字）：", 200, 200);
		g.drawString("我的性别(男/女):", 200, 300);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnEdit) {
			jtfUsername.setEditable(true);     //按下编辑按钮，设置用户名可编辑。

		}
		if(e.getSource()==btnSubmit) {
			GetSet newUser = new GetSet();
			newUser.setGender(gender.getSelectedIndex());
			newUser.setName(jtfUsername.getText());
			controller.CreateUsers3(newUser);

			if((jtfUsername.getText().equals(""))) {
				JOptionPane.showMessageDialog(null, "请输入正确的用户名或性别不能为空", "警告提示框", JOptionPane.WARNING_MESSAGE);
			}else {
				String username = jtfUsername.getText(); 
				int sex = gender.getSelectedIndex();
				String ss = "\\w{2,10}";
				GetSet usersname= controller.findUsers(username);     //调用按照用户输入的数据查询数据库方法
				if(usersname == null) {               //如果数据库中没有此用户且两次输入密码相同。
					if(username.matches(ss)) {         //如果用户名满足2-10位      
						uuser.setName(username);
						uuser.setGender(sex);
						controller.CreateUsers3(uuser);;
						JOptionPane.showMessageDialog(this, "恭喜你，保存成功！", "保存成功", JOptionPane.CLOSED_OPTION);
						this.dispose();
						new TheFilmInterface(uuser);
					}else {
						JOptionPane.showMessageDialog(this, "请输入正确的用户名！", "警告提示框", JOptionPane.WARNING_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(this, "该用户名已存在，请换一个试试吧！", "警告提示框", JOptionPane.WARNING_MESSAGE);
				}
			}


		}
		if(e.getSource()==btnNewcode) {
			this.dispose();
			new ResetPasswords();
		}
		if(e.getSource()==btnExit) {
			this.dispose();
		}

	}
//	public static void main(String[] args) {
//		new PersonalInformation();
//	}
}
