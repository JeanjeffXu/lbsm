import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.Container;
import java.util.*;
import java.sql.*;
 
 class Login extends JFrame  implements  ActionListener	
{   Container cp=null;
   JFrame f=null;
   JButton j1,j2;
   JTextField t1;
   JPasswordField t2;
   JLabel jlable1,jlable2;
    Color c;
   JPanel jp1,jp2;
      
	Login(){
		 
		f=new JFrame("小型图书管理系统");
		j1=new JButton("确定");
		j2=new JButton("取消");
		cp=f.getContentPane();
		jlable1=new JLabel(" 输入用户名");
		jlable2=new JLabel(" 用户密码");
		 
		jp1=new JPanel();
		jp2=new JPanel();
		t1=new JTextField(18);
		t2=new JPasswordField(18);
		
        jp1.add(jlable1);
        jp1.add(t1);
        jp1.add(jlable2);
		jp1.add(t2);
        JLabel  JL=new JLabel("<html><font color=#CC00FF size='7'><i>欢迎登陆</i></font>",SwingConstants.CENTER);
	    cp.add(JL,"North");
		jp2.add(j1);
		jp2.add(j2);
	    cp.add(jp1,"Center");
	    cp.add("South",jp2);
		jp1.setBackground(new Color(255,153,255));
	   
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension screen=kit.getScreenSize();
		int x=screen.width;					/*取得显示器窗口的宽度*/
		int y=screen.height;					/*取得显示器窗口的高度*/
		//setSize(x,y); /*让系统窗口平铺整个显示器窗口*/
 
	 	f.setSize(300,300);			
	    int xcenter=(x-300)/2;
	    int ycenter=(y-300)/2;
	    f.setLocation(xcenter,ycenter);/*显示在窗口中央*/
	  
	    f.setVisible(true);
   
    //-----------------------------------------------------	 
       j1.addActionListener(this);//注册事件监听器
       j2.addActionListener(this);
       f.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		}
		);
	}
    public void confirm()//验证用户和密码是否存在
	  {

        try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		   }catch(ClassNotFoundException e){System.out.println("加载驱动程序失败!");}
		try{
			String url = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=Book.mdb";//直接使用当前类目录下的数据库文件
			Connection con=DriverManager.getConnection(url);
			Statement sql=con.createStatement();
			String uname=t1.getText().trim();
			String Mima=t2.getText().trim();
			String queryMima="select * from user where 用户名='"+uname+"' and 密码='"+Mima+"'";
			ResultSet rs=sql.executeQuery(queryMima);
            if(rs.next())
			{
				new Book(uname);
				f.hide();
		        con.close();
				 
            }else{
				JOptionPane.showMessageDialog(null,"该用户不存在","提示！", 
					          JOptionPane.YES_NO_OPTION);
				
			    }
              
				t1.setText("");
				t2.setText("");
				
		  } catch(SQLException g)
				{
				System.out.println("E Code"+g.getErrorCode());
				System.out.println("E M"+g.getMessage());
			     }

	   }
    public void actionPerformed(ActionEvent e)
       {
		 String cmd=e.getActionCommand();
		  if(cmd.equals("确定")){
			  confirm();
		  }
		  else if(cmd.equals("取消")){
			  f.dispose();
		  }
	   }

    public static void main(String []arg){
		   
		   Login a=new Login();
     }
}
