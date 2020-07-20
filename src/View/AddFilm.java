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
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Control.Controller;
import Model.GetSet;


public class AddFilm extends JFrame implements ActionListener{
	public Image image;
	Controller controller = new Controller();
	public JButton btnExit = new JButton("退出");
	public JTextField jtfFilmname = new JTextField();
	public  JComboBox<String> comboBoxType1 = new JComboBox<String>(controller.findType());
	public  JComboBox<String> comboBoxType2 = new JComboBox<String>(controller.findType());
	public JComboBox<String> comboBoxCountry = new JComboBox<String>(controller.findCountry());
	public JTextField jtfMark = new JTextField();
	public JTextField jtfData = new JTextField();
	public JTextArea jtaIntro = new JTextArea();
	public JTextArea jtaLink = new JTextArea();
	public JButton btnSave = new JButton("保存");
	GetSet user1;
	public AddFilm(GetSet user) {
		this.user1= user;
		this.setLocation(300, 10);
		this.setLayout(null);
		this.setSize(900,800);
		this.setResizable(false);
		this.setTitle("影片信息");
		this.setVisible(true);
		btnSave.setBounds(300, 550, 80, 50);
		btnExit.setBounds(400, 550, 80, 50);
		jtfFilmname.setBounds(250, 50, 200, 50);
		comboBoxCountry.setBounds(520, 80, 60, 30);
		comboBoxType1.setBounds(610, 80, 60, 30);
		comboBoxType2.setBounds(710, 80, 60, 30);
		jtfMark.setBounds(450, 200,60, 30);
		jtfData.setBounds(250, 200, 100, 30);
		jtaIntro.setBounds(250, 280, 500, 150);
		jtaLink.setBounds(250, 450, 330, 60);
		jtaIntro.setLineWrap(true);
		jtaLink.setLineWrap(true);
		this.add(btnExit);
		this.add(jtfFilmname);
		this.add(comboBoxCountry);
		this.add(comboBoxType1);
		this.add(comboBoxType2);
		this.add(jtfMark);  
		this.add(jtfData);
		this.add(jtaIntro);
		this.add(jtaLink);
		this.add(btnSave);
		btnExit.addActionListener(this); 
		btnSave.addActionListener(this); 
	}
	public void paint(Graphics g) {
	
		image = new ImageIcon("bin\\Image\\bg2.jpg").getImage();
		g.drawImage(image, 0, 0, this);
		g.drawString("电影名：", 200, 100);
		g.drawString("国家", 500, 100);
		g.drawString("类型：", 600, 100);
		g.drawString("上映日期：", 200, 250);
		g.drawString("(正确格式：年-月-日)", 180, 280);
		g.drawString("评分：", 400, 250);
		g.drawString("(正确格式：0-10之间的数)", 380, 280);
		g.drawString("简介", 200, 320);
		g.drawString("影片链接", 200, 500);
		jtfFilmname.repaint();
		comboBoxCountry.repaint();
		btnExit.repaint();
		btnSave.repaint();
		comboBoxType1.repaint();
		jtfMark.repaint();
		jtfData.repaint();
		jtaIntro.repaint();
		jtaLink.repaint();
		comboBoxType2.repaint();
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnSave) {
			String newName = jtfFilmname.getText();
			int country=comboBoxCountry.getSelectedIndex();
			int type1 = comboBoxType1.getSelectedIndex();
			int type2 = comboBoxType2.getSelectedIndex();
			Object mark =  jtfMark.getText();
			String data = jtfData.getText();
			Object intro = jtaIntro.getText();
			Object site = jtaLink.getText();
			GetSet film = controller.findFilm(newName);
			if((jtfFilmname.getText().equals(""))||(comboBoxCountry.getSelectedIndex()==0||(comboBoxType1.getSelectedIndex()==0)||(comboBoxType2.getSelectedIndex()==0))) {
				JOptionPane.showMessageDialog(this, "请输入正确的电影名或国家或类型信息", "警告提示框", JOptionPane.WARNING_MESSAGE);
			}
			else {
				String Date = "^(?:(?!0000)[0-9]{4}([-/.]?)(?:(?:0?[1-9]|1[0-2])([-/.]?)(?:0?[1-9]|1[0-9]|2[0-8])|(?:0?[13-9]|1[0-2])([-/.]?)(?:29|30)|(?:0?[13578]|1[02])([-/.]?)31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)([-/.]?)0?2([-/.]?)29)$";
				String Mark = "^[0-9]+([.]{1}[0-9]+){0,1}$";
				String Link = "[a-zA-z]+://[^\\s]*";
				if(film == null) {
					if(data.matches(Date)) {
						if(((String) mark).matches(Mark)) {
							if(((String) site).matches(Link)) {
							controller.addAllData(newName,country,type1,mark,data,intro,site);
							Object filmid = controller.getFilmid(newName);
							controller.addData(filmid, type1);
							controller.addData(filmid, type2);
							JOptionPane.showMessageDialog(this, "保存成功");
							this.dispose();
							new AdministratorInterface(user1);
							}else {
								JOptionPane.showMessageDialog(this, "请输入正确的链接格式", "警告提示框", JOptionPane.WARNING_MESSAGE);
							}
							}else{
							JOptionPane.showMessageDialog(this, "请输入正确的评分格式", "警告提示框", JOptionPane.WARNING_MESSAGE);
						}
					}else {

						JOptionPane.showMessageDialog(this, "请输入正确的日期格式", "警告提示框", JOptionPane.WARNING_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(this, "电影已存在", "警告提示框", JOptionPane.WARNING_MESSAGE);
				}
				}
		}

		if(e.getSource()==btnExit) {
			this.dispose();
		}
	}
}
