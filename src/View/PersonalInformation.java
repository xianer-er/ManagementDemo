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
	public JPanel panel = new JPanel();                     //�����µ����
	JTextField jtfUsername = new JTextField();
	String[] listData = new String[]{"��", "Ů"};
	public JComboBox<String> gender = new JComboBox<String>(listData);
	JButton btnSubmit = new JButton("����");   
	JButton btnExit = new JButton("�˳�"); 
	JButton btnEdit = new JButton("�༭");
	JButton btnNewcode = new JButton("��������");
	public PersonalInformation(GetSet user) {
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setLocation(300, 10);
		this.setLayout(null);
		this.setSize(700,800);
		this.setResizable(false);
		this.setTitle("�ҵ���Ϣ");
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
		g.drawString("�ҵ��û���(2-10λ��ĸ�����֣���", 200, 200);
		g.drawString("�ҵ��Ա�(��/Ů):", 200, 300);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnEdit) {
			jtfUsername.setEditable(true);     //���±༭��ť�������û����ɱ༭��

		}
		if(e.getSource()==btnSubmit) {
			GetSet newUser = new GetSet();
			newUser.setGender(gender.getSelectedIndex());
			newUser.setName(jtfUsername.getText());
			controller.CreateUsers3(newUser);

			if((jtfUsername.getText().equals(""))) {
				JOptionPane.showMessageDialog(null, "��������ȷ���û������Ա���Ϊ��", "������ʾ��", JOptionPane.WARNING_MESSAGE);
			}else {
				String username = jtfUsername.getText(); 
				int sex = gender.getSelectedIndex();
				String ss = "\\w{2,10}";
				GetSet usersname= controller.findUsers(username);     //���ð����û���������ݲ�ѯ���ݿⷽ��
				if(usersname == null) {               //������ݿ���û�д��û�����������������ͬ��
					if(username.matches(ss)) {         //����û�������2-10λ      
						uuser.setName(username);
						uuser.setGender(sex);
						controller.CreateUsers3(uuser);;
						JOptionPane.showMessageDialog(this, "��ϲ�㣬����ɹ���", "����ɹ�", JOptionPane.CLOSED_OPTION);
						this.dispose();
						new TheFilmInterface(uuser);
					}else {
						JOptionPane.showMessageDialog(this, "��������ȷ���û�����", "������ʾ��", JOptionPane.WARNING_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(this, "���û����Ѵ��ڣ��뻻һ�����԰ɣ�", "������ʾ��", JOptionPane.WARNING_MESSAGE);
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
