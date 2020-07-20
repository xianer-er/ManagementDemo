package View;

import java.awt.Color;

import java.awt.Graphics;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;


import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;


import Control.Controller;
import Model.GetSet;
import Model.Judge;
import View.Login;

public class TheFilmInterface extends JFrame implements ActionListener{
	Controller controller = new Controller();
	GetSet uuser = new GetSet();
	Judge judge = new Judge();
	ImageIcon u = new ImageIcon("bin\\Image\\user.jpg");
	int page =1;
	int number1 =((page-1)*8); 
	public Image image;
	public DefaultTableModel tableModel; // 默认显示的表格
	public JTable table; // 表格
	public JPanel panel; // 面板
	public JLabel label = new JLabel("当前位置是第"+page+"页",JLabel.CENTER);
	public JLabel label2 = new JLabel("跳转到第              页",JLabel.CENTER);
	public JLabel label3 = new JLabel("输入片名：");
	public JTextField jtfFilmname = new JTextField();
	public JTextField jtfPage = new JTextField("1");
	public JButton btnSearch = new JButton("搜索"); // 创建按钮
	public JButton btnExit = new JButton("退出账户");
	public JButton btnUser = new JButton("", u);
	public JButton btnBack = new JButton("上一页");
	public JButton btnNext = new JButton("下一页");
	public JButton btnFirst = new JButton("首页");
	public JButton btnLast = new JButton("末页");
	public JButton btnGo = new JButton("Go");
	// 创建下拉列表框
	public  JComboBox<String> comboBoxType = new JComboBox<String>(controller.findType());
	public JComboBox<String> comboBoxCountry = new JComboBox<String>(controller.findCountry());
	Vector<Vector<Object>> rowData = controller.getContent(number1, "film");
	// 取得数据库的表的表头数据
	Vector columnNames = controller.getHead("film");
	GetSet user1;

	public TheFilmInterface(GetSet user) {
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置为单击关闭窗口时并结束程序
		this.setLocation(400, 10); // 设置窗口显示的位置
		this.setLayout(null); // 取消窗口的默认布局管理器
		this.setSize(950, 800); // 设置窗口尺寸
		this.setResizable(false); // 设置窗口大小不可改变
		this.setTitle("影片库");
		this.uuser = user;
		// 面板里的按钮
		panel = new JPanel(); // 新建按钮组件面板
		panel.setLayout(null);// 设置面板的布局方式为空，就可以改变表格的大小与位置了
		// 将各按钮组件依次添加到面板中
		this.add(btnFirst);
		this.add(btnLast);
		this.add(btnSearch);
		this.add(btnBack);
		this.add(btnNext);
		this.add(btnGo);
		this.add(jtfFilmname);
		this.add(jtfPage);
		this.add(comboBoxType);
		this.add(comboBoxCountry);
		this.add(label);
		this.add(label2);
		this.add(label3);
		panel.setBounds(280, 100, 600, 400); // 面板大小
		btnGo.setBounds(670, 550, 70, 30);
		jtfPage.setBounds(560, 555, 30, 20);
		label2.setBounds(500, 550,110, 30);
		label.setBounds(500, 600, 100, 30);
		btnFirst.setBounds(300, 600, 90, 30);
		btnLast.setBounds(750, 600, 90, 30);
		btnSearch.setBounds(750, 160, 70, 40);
		label3.setBounds(290, 170, 70,20);
		btnBack.setBounds(300,550, 90, 30);
		btnNext.setBounds(750,550, 90, 30);
		btnExit.setBounds(50, 600, 100, 50);
		btnUser.setBounds(50, 80, 100, 100);
		jtfFilmname.setBounds(350, 160, 150, 40);
		comboBoxType.setBounds(650, 170, 70, 30);
		comboBoxCountry.setBounds(550, 170, 70, 30);
		// 把按钮和面板加入窗口
		this.add(btnExit);
		this.add(panel);
		this.add(btnUser);
		// 对各个按钮实现监听
		comboBoxCountry.addActionListener(this);
		comboBoxType.addActionListener(this);
		btnGo.addActionListener(this);
		btnSearch.addActionListener(this);
		btnFirst.addActionListener(this);
		btnLast.addActionListener(this);
		btnBack.addActionListener(this);
		btnNext.addActionListener(this);
		btnExit.addActionListener(this);
		btnUser.addActionListener(this);
		jtfFilmname.addActionListener(this);
		// 新建表格
		tableModel = new DefaultTableModel(rowData, columnNames); // 定义一个含有参数的 DefaultTableModel的类，来实现tablemodel接口
		// 把tableModel加入到table上。
		table = new JTable(tableModel) {
			@Override
			public boolean isCellEditable(int rowData, int column) { // 设置表格可被选中不可被编辑
				return false;
			}
		};

		//建立一个表格的列模型
		TableColumnModel cm = table.getColumnModel();
		//为表格第1.3列设置固定的宽度
		TableColumn column3 = cm.getColumn(3);
		TableColumn column1 = cm.getColumn(1);
		column1.setPreferredWidth(190);
		column3.setPreferredWidth(210);
		table.setRowHeight(35);//设置行高                                            
		table.setColumnModel(cm);          //把上面设置的宽度应用到model中
		// 转换电影国家和类型的id为汉字
		for (int i = 0; i < table.getRowCount(); i++) {
			table.setValueAt(controller.getCountryName(Integer.parseInt((String) rowData.get(i).get(2))), i, 2);
		}
		// 新建滚动窗格
		JScrollPane s = new JScrollPane(table);
		s.setBounds(10, 100, 550, 550);
		table.getTableHeader().setReorderingAllowed(false); // 使table的列宽无法拖动
		panel.add(s);

	}

	@Override
	public void paint(Graphics g) {
		comboBoxCountry.repaint();
		comboBoxType.repaint();
		label3.repaint();
		label.repaint();
		label2.repaint();
		jtfFilmname.repaint();
		btnSearch.repaint();
		btnExit.repaint();
		btnBack.repaint();
		btnNext.repaint();
		btnUser.repaint();
		btnGo.repaint();
		btnLast.repaint();
		btnFirst.repaint();
		panel.repaint();
		image = new ImageIcon("bin\\Image\\bg1.jpg").getImage();
		g.drawImage(image, 0, 0, this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) {
					int r= table.getSelectedRow();
					Object name = table.getValueAt(r, 1);
					Object country = table.getValueAt(r, 2);
					Object type = table.getValueAt(r, 3);
					Object mark = table.getValueAt(r, 4);
					Object date = table.getValueAt(r, 5);
					Object intro = table.getValueAt(r, 6);
					Object site = table.getValueAt(r, 7);
					new SingleFilm(name,country,type,mark,date,intro,site);
				}
			}

		});
		if(e.getSource()==btnUser) {
			new PersonalInformation(uuser);
		}
		if(e.getSource() == btnSearch) {
			Vector<Vector<Object>> rowData = new Vector();
			int country = comboBoxCountry.getSelectedIndex();
			int type = comboBoxType.getSelectedIndex();
			rowData= judge.judgeContent(jtfFilmname.getText(),type,country,number1);

			tableModel.setDataVector(rowData,columnNames);
			switch(judge.a) {
			case 0:
				for (int i = 0; i < table.getRowCount(); i++) { // 转化数字为中文
					table.setValueAt(controller.getCountryName(Integer.parseInt((String) rowData.get(i).get(2))), i, 2);
				}
				for (int i = 0; i < table.getRowCount(); i++) {
					table.setValueAt(controller.getTypesName(Integer.parseInt((String) rowData.get(i).get(0))), i, 3);
				}
				break;
			case 1:
				for (int i = 0; i < table.getRowCount(); i++) {
					table.setValueAt(controller.getCountryName(country), i, 2);
				}
				break;
			case 2:
				for (int i = 0; i < table.getRowCount(); i++) {
					table.setValueAt(controller.getCountryName(Integer.parseInt((String) rowData.get(i).get(2))), i, 2);
				}
				for (int i = 0; i < table.getRowCount(); i++) {
					table.setValueAt(controller.getTypesName(Integer.parseInt((String) rowData.get(i).get(0))), i, 3);
				}
				break;
			case 3:
				for (int i = 0; i <rowData.size(); i++) {
					table.setValueAt(controller.getCountryName(country), i, 2);
				}
				break;
			default:
			}
		}
		if(e.getSource() == btnBack) {
			if(page==1) {
				JOptionPane.showMessageDialog(this, "当前已经是第一页", "alert", JOptionPane.ERROR_MESSAGE); 
			}else {
				number1-=8;
				page = number1/8+1;
				label.setText("当前位置是第"+page+"页");  
				System.out.println(page);
				delectDate();
				Vector<Vector<Object>>	rowData = judge.judgeContent(jtfFilmname.getText(),comboBoxType.getSelectedIndex(),comboBoxCountry.getSelectedIndex(),number1);
				tableModel.setDataVector(rowData, columnNames);
				for (int i = 0; i < table.getRowCount(); i++) {
					table.setValueAt(controller.getCountryName(Integer.parseInt((String) rowData.get(i).get(2))), i, 2);
				}}
		}

		//查询下一页
		int count;
		count=((controller.getCount()%8)==0)? (controller.getCount()/8): (controller.getCount()/8)+1;
		if(e.getSource()==btnNext) {
			if(page<count) {
				number1+=8;       
				page = number1/8+1;
				label.setText("当前位置是第"+page+"页");             //用来更新标签里的内容
				delectDate();
				Vector<Vector<Object>> rowData = judge.judgeContent(jtfFilmname.getText(),comboBoxType.getSelectedIndex(),comboBoxCountry.getSelectedIndex(),number1);
				tableModel.setDataVector(rowData, columnNames);
				for (int i = 0; i < table.getRowCount(); i++) {
					table.setValueAt(controller.getCountryName(Integer.parseInt((String) rowData.get(i).get(2))), i, 2);
				}}else if(page==count||page>=rowData.size()){
					JOptionPane.showMessageDialog(this, "当前已经是最后一页", "alert", JOptionPane.ERROR_MESSAGE);
				}
		}
		if(e.getSource() == btnGo) {
			page=Integer.parseInt(jtfPage.getText());
			String p = "^[1-9]\\d*$";
			if(page<=count&&page>0&&(!jtfPage.getText().matches(p))) {
			number1=(page-1)*8;
			label.setText("当前位置是第"+page+"页");
			Vector<Vector<Object>> rowData = judge.judgeContent(jtfFilmname.getText(),comboBoxType.getSelectedIndex(),comboBoxCountry.getSelectedIndex(),number1);
			tableModel.setDataVector(rowData, columnNames);
			for (int i = 0; i < table.getRowCount(); i++) {
				table.setValueAt(controller.getCountryName(Integer.parseInt((String) rowData.get(i).get(2))), i, 2);

		}}else {
			JOptionPane.showMessageDialog(this, "无", "alert", JOptionPane.ERROR_MESSAGE);
		}}
		if(e.getSource()==btnFirst) {
			page=1;
			number1=(page-1)*8;
			label.setText("当前位置是第"+page+"页");
			Vector<Vector<Object>> rowData = judge.judgeContent(jtfFilmname.getText(),comboBoxType.getSelectedIndex(),comboBoxCountry.getSelectedIndex(),number1);
			tableModel.setDataVector(rowData, columnNames);
			for (int i = 0; i < table.getRowCount(); i++) {
				table.setValueAt(controller.getCountryName(Integer.parseInt((String) rowData.get(i).get(2))), i, 2);
			}
		}
		if(e.getSource() == btnLast) {
			page=count;
			number1=(page-1)*8;
			label.setText("当前位置是第"+page+"页");
			Vector<Vector<Object>> rowData = judge.judgeContent(jtfFilmname.getText(),comboBoxType.getSelectedIndex(),comboBoxCountry.getSelectedIndex(),number1);
			tableModel.setDataVector(rowData, columnNames);
			for (int i = 0; i < table.getRowCount(); i++) {
				table.setValueAt(controller.getCountryName(Integer.parseInt((String) rowData.get(i).get(2))), i, 2);
			}
		}
		if(e.getSource()==btnExit) {
			this.dispose();
			new Login();
		}

	}
	public void delectDate(){	
		int i=table.getSelectedRow();
		if(i<0) {
			return;
		}
		rowData.remove(i);
		table.updateUI();

	}
}
