package View;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Control.Controller;
import Model.GetSet;

public class SingleFilm extends JFrame implements ActionListener{
	public Image image;
	//创建各种组件   
	//	public JButton btnSubmit = new JButton("提交");   
	public JButton btnExit = new JButton("退出");
	public JTextField jtfFilmname = new JTextField();
	public JTextField jtfCountry =  new JTextField();
	public JTextField jtfType =  new JTextField();
	public JTextField jtfMark = new JTextField();
	public JTextField jtfData = new JTextField();
	public JTextArea jtaIntro = new JTextArea();
	public JTextArea jtaLink = new JTextArea();
	public SingleFilm(Object filmName,Object country,Object type,Object mark ,Object data,Object intro, Object link) {
		jtfFilmname.setText((String) filmName);
		jtfCountry.setText((String) country);
		jtfType.setText((String) type);
		jtfMark.setText((String) mark);
		jtfData.setText((String) data);
		jtaIntro.setText((String) intro);
		jtaLink.setText((String) link);
		this.setLocation(300, 10);
		this.setLayout(null);
		this.setSize(900,800);
		this.setResizable(false);
		this.setTitle("影片信息");
		this.setVisible(true);
		btnExit.setBounds(380, 550, 80, 50);
		jtfFilmname.setBounds(250, 50, 200, 50);
		jtfCountry.setBounds(250, 150, 60, 30);
		jtfType.setBounds(450, 150, 60, 30);
		jtfMark.setBounds(450, 200,60, 30);
		jtfData.setBounds(250, 200, 100, 30);
		jtaIntro.setBounds(250, 280, 500, 150);
		jtaLink.setBounds(250, 450, 330, 60);
		jtfFilmname.setEditable(false);
		jtfCountry.setEditable(false);
		jtfType.setEditable(false);
		jtfMark.setEditable(false);
		jtfData.setEditable(false);
		jtaIntro.setEditable(false);
		jtaLink.setEditable(false);
		jtaIntro.setLineWrap(true);
		jtaLink.setLineWrap(true);
		this.add(btnExit);
		this.add(jtfFilmname);
		this.add(jtfCountry);
		this.add(jtfType);
		this.add(jtfMark);  
		this.add(jtfData);
		this.add(jtaIntro);
		this.add(jtaLink);
		btnExit.addActionListener(this); 
	}
	@Override
	public void paint(Graphics g) {
		jtfFilmname.repaint();
		jtfCountry.repaint();
		btnExit.repaint();
		jtfType.repaint();
		jtfMark.repaint();
		jtfData.repaint();
		jtaIntro.repaint();
		jtaLink.repaint();
	
		image = new ImageIcon("bin\\Image\\bg2.jpg").getImage();
		g.drawImage(image, 0, 0, this);
		g.drawString("电影名：", 200, 100);
		g.drawString("国家", 200, 200);
		g.drawString("类型：", 400, 200);
		g.drawString("上映日期：", 200, 250);
		g.drawString("评分：", 400, 250);
		g.drawString("简介", 200, 320);
		g.drawString("影片链接", 200, 500);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnExit) {
			this.dispose();
		}

	}
}
