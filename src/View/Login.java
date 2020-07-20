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

	//������ť
	public JButton btnLogin = new JButton("��¼"); 
	public JButton btnForgetPassword = new JButton("��������");
	public JButton btnAbout = new JButton("����");
	public JButton btnRegister = new JButton("ע��");
	//���������б��
	 String[] list = new String[] {"����Ա","�û�"};
	 public  JComboBox<String> comboBoxStatus = new JComboBox<String>(list);
	//�����ı���
	public JTextField jtfUsername = new JTextField();
	public JPasswordField jpfUsercode = new JPasswordField();
	// �½�һ������ģ��
	Font font = new Font("����", Font.BOLD, 40); 
	GetSet user1;
	public int status ;
	public Login() {
		//���ڽ��������
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //��������رս���
		this.setLocation(300, 10);
		this.setLayout(null);
		this.setSize(1000, 800);
		this.setResizable(false);                              //��������С��
		this.setTitle("��¼");
		this.setVisible(true);
		//�������λ��
		btnLogin.setBounds(600, 600, 130, 50);
		btnForgetPassword.setBounds(820, 450, 130, 50);
		btnAbout.setBounds(400, 600, 130, 50);
		btnRegister.setBounds(200, 600, 130, 50);
		comboBoxStatus.setBounds(500, 200, 80, 30);
		jtfUsername.setBounds(350, 285, 440, 110);
		jpfUsercode.setBounds(350, 400, 440, 110);
		// �����ı���Ϊ͸��
		jtfUsername.setOpaque(false); 
		jpfUsercode.setOpaque(false);
		jtfUsername.setFont(font);
		jpfUsercode.setFont(font);
		// ���������frame����
		this.add(comboBoxStatus);
		this.add(btnLogin); 
		this.add(btnForgetPassword);
		this.add(btnAbout);
		this.add(btnRegister);
		this.add(jtfUsername);
		this.add(jpfUsercode);
		// ��������ϼ���
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
				JOptionPane.showMessageDialog(this,"��������ȷ���û���������","������ʾ��",JOptionPane.WARNING_MESSAGE);
			}
		}
		if(e.getSource() == btnForgetPassword) {
			new ResetPasswords();
		}
		
		if(e.getSource() == btnAbout) {
			JOptionPane.showMessageDialog(this, "��ӭ�����ɶ��ĵ�Ӱ���磬����������Բ鿴�����ĵ�Ӱ����Ϣ.\n�����ˣ���������", "����",
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
