package lk;

import java.net.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

class UpdateMima extends JFrame implements ActionListener {

	JFrame f;
	Container cp;
	JPanel jp1, jp2, jp3, jp4, jp, jpanelWest;
	JButton jbt1, jbt2;// 按钮，确定、取消
	JLabel label; // 标签：修改密码
	JTextField name;
	JPasswordField tf1, tf2, tf3; // 定义文本框
	JLabel label1, label2, label3, label4;
	String sno;

	UpdateMima() {
	}

	UpdateMima(String username) {
		sno = username;
		f = new JFrame();
		cp = f.getContentPane(); // 初始化
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		jp4 = new JPanel();
		jpanelWest = new JPanel();
		jp = new JPanel();
		// ------------------------------------------------
		jbt1 = new JButton("确定");
		jbt2 = new JButton("取消");

		// ------------------------------------------------
		label = new JLabel("<html><font color=#CC00FF size='4'>修改密码</font>",
				SwingConstants.CENTER);
		label.setForeground(Color.blue);
		label.setFont(new Font("BOLD", Font.BOLD, 15));
		name = new JTextField(20);
		// name.setEditable(false);

		// ------------------------------------------------
		tf1 = new JPasswordField(20);
		tf2 = new JPasswordField(20);
		tf3 = new JPasswordField(20);

		// ------------------------------------------------
		// 布局,添加控件

		jp1.add(jbt1);
		jp1.add(jbt2);

		jp1.add(new JLabel("您好" + "xx" + "欢迎登陆学生信息系统"));

		JPanel jpanel = new JPanel();
		jpanel.add(label);

		JPanel pp4 = new JPanel();
		JPanel jpane4 = new JPanel();

		cp.add(jpanel, "North");
		JPanel pp2 = new JPanel(new GridLayout(6, 1));
		JPanel pp3 = new JPanel();
		pp4.setLayout(new GridLayout(6, 1));
		pp4.add(new JLabel("用户名: ", SwingConstants.RIGHT));
		pp2.add(name);
		pp4.add(new JLabel("原密码: ", SwingConstants.RIGHT));

		pp2.add(tf1);
		pp4.add(new JLabel(" 新密码: ", SwingConstants.RIGHT));
		pp2.add(tf2);
		pp4.add(new JLabel("确认密码: ", SwingConstants.RIGHT));
		pp2.add(tf3);
		pp2.add(new JLabel());
		JPanel jpbutton = new JPanel();
		jpbutton.add(jbt1);
		jpbutton.add(jbt2);
		pp2.add(jpbutton);

		// pp3.add(jbt1);

		// pp3.add(jbt2);

		cp.add(pp4, "West");
		cp.add(pp2, "Center");
		// cp.add(pp3,"South");

		cp.add(jpane4, "East");

		// ------------------------------------------------
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screen = kit.getScreenSize();
		int x = screen.width; /* 取得显示器窗口的宽度 */
		int y = screen.height;
		f.setSize(350, 330); /* 取得显示器窗口的高度 */
		int xcenter = (x - 350) / 2;
		int ycenter = (y - 330) / 2;
		f.setLocation(xcenter, ycenter);/* 显示在窗口中央 */
		// f.setTitle("修改密码");
		f.setVisible(true);

		// -------------------------------------------------
		jbt1.addActionListener(this);// 注册监听器
		jbt2.addActionListener(this);

		/*
		 * f.addWindowListener(new WindowAdapter(){ public void
		 * windowClosing(WindowEvent e){ System.exit(0); } } );
		 */
	}

	public void updateM() {

		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("加载驱动程序失败!");
		}
		try {
			String url = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=Book.mdb";// 直接使用当前类目录下的数据库文件
			Connection con = DriverManager.getConnection(url);
			Statement sql = con.createStatement();
			String uname = name.getText().trim();
			String queryMima = "select * from user where 用户名='" + uname + "'";
			ResultSet rs = sql.executeQuery(queryMima);
			if (rs.next()) {

				String newMima = tf2.getText().trim();
				String s = "update user set 密码='" + newMima + "' where 用户名 ='"
						+ uname + "'";
				sql = con.createStatement();
				int updateMima = sql.executeUpdate(s);
				if (updateMima == 1) {
					JOptionPane.showMessageDialog(f, "密码修改成功！");

				}
				con.close();
				f.repaint();
			} else {
				JOptionPane.showMessageDialog(null, "该用户不存在", "警告！",
						JOptionPane.YES_NO_OPTION);

			}
			name.setText("");
			tf1.setText("");
			tf2.setText("");
			tf3.setText("");
		} catch (SQLException g) {
			System.out.println("E Code" + g.getErrorCode());
			System.out.println("E M" + g.getMessage());
		}

	}

	// ------------------------------------------------
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("确定")) {
			if (name.getText().equals("") || tf1.getText().equals("")
					|| tf2.getText().equals("") || tf3.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "请填写用户的所有信息", "提示",
						JOptionPane.YES_NO_OPTION);
				return;
			}

			if (tf2.getText().trim().equals(tf3.getText().trim()))
				updateM();

		} else if (cmd.equals("取消"))
			f.hide();
	}

	public static void main(String[] args) {
		new UpdateMima("");
	}
}
