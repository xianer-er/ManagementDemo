package Model;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;

import Model.GetSet;
public class QueryMethods {
	public String[] listWhat;
	Connection conn  = new GetConn().getConnection();
	//对于用户的查询信息
	//从用户表中查询用户
	public String getUserCode(String sql ,String username) {
		String code = null;
		try {
			PreparedStatement st =conn.prepareStatement(sql);
			st.setString(1, username);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				code = rs.getString("code");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return code ;
	}
	//更新用户信息
	public void write(String sql, Object... params) {
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				statement.setObject(i + 1, params[i]);
			}
			statement.executeUpdate();
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//查找某一用户的个人信息
	public GetSet findUsers(String sql,String username,String thing) {
		GetSet user = null; 
		String t = thing;
		try {
			PreparedStatement statement =conn.prepareStatement(sql);
			statement.setObject(1, username);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				user = new GetSet();
				user.setID(result.getInt(t));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	//为了获得密保
		public GetSet findUsers1(String sql,GetSet users) {
			GetSet user = null; 
			try {
				PreparedStatement statement =conn.prepareStatement(sql);
				statement.setString(1, users.getName());
				statement.executeQuery();
				ResultSet result = statement.executeQuery();
				while(result.next()) {
					user = new GetSet();
					user.setAnswer(result.getString("answer"));
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
			return user;
		}
		//查找某人性别
		public int getGender(String sql,String userName) {
		int gender =0;
			try {
				PreparedStatement st =conn.prepareStatement(sql);
				st.setString(1, userName);
				ResultSet rs = st.executeQuery();
				while(rs.next()) {
					gender = rs.getInt("gender");
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
			return gender ;
		}
		//查找登陆者身份
		public int getStatus(String sql,String name) {
			int status=0;
			PreparedStatement st = null;
			 ResultSet rs;
			 try {
				 st=conn.prepareStatement(sql);
				 st.setString(1, name);
				 rs = st.executeQuery();
				 while(rs.next()) {
					 status = rs.getInt("status");
				 }
			 }catch (SQLException e) {
					e.printStackTrace();
				}
			return  status;
		}
	//对于电影信息查找
	//查找某一电影的信息
	public GetSet findFilm(String sql,String name,String thing) {
		GetSet user = null; 
		String t = thing;
		try {
			PreparedStatement statement =conn.prepareStatement(sql);
			statement.setObject(1, name);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				user = new GetSet();
				user.setID(result.getInt(t));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	//查询国家和类型表，传到下拉列表框
	public String[] select(String sql, String what) {
		List<String> list = new ArrayList<String>();
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(what));
			}
			listWhat = new String[list.size()];
			listWhat[0] = "全部";
			for (int i = 1; i < list.size(); i++) {
				listWhat[i] = list.get(i - 1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listWhat;
	}
	// 从country获取转换电影country中文名字
	public String getCountryName(String sql,int countryid) { 
		String country = null;
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, countryid);
			ResultSet res = stmt.executeQuery();
			while (res.next()) {
				country = res.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return country;
	}
	//获得数据库表中所有数据
	public  Vector<Vector<Object>> getRows(String sql,String tablename,int a) {

		Vector rows = new Vector();  
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, a);
			ResultSet result1 = preparedStatement.executeQuery();
			if(result1==null)
				JOptionPane.showMessageDialog(null, "结果集中无记录");
			//此对象获得列的数目和类型以及每一列的名称。 
			ResultSetMetaData rsmd = result1.getMetaData();
			while(result1.next()){
				rows.addElement(getRowData(result1,rsmd));
			}
		} catch (SQLException e) {
			System.out.println("未成功打开数据库。");
			e.printStackTrace();
		}
		return rows;
	}
	// 得到数据库表头
	public  Vector getHead(String sql){
		Vector columnHeads = null;
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			ResultSet result1 = preparedStatement.executeQuery();
			if(result1==null)
				JOptionPane.showMessageDialog(null, "结果集中无记录");
			columnHeads = new Vector();
			ResultSetMetaData rsmd = result1.getMetaData();
			for(int i = 1; i < rsmd.getColumnCount(); i++)
				columnHeads.addElement(rsmd.getColumnName(i));
		} catch (SQLException e) {
			System.out.println("未成功打开数据库。");
			e.printStackTrace();
		}
		return columnHeads;
	}
	// 得到user数据库表头1
		public  Vector getHead1(String sql){
			Vector columnHeads = null;
			try {
				PreparedStatement preparedStatement = conn.prepareStatement(sql);
				ResultSet result1 = preparedStatement.executeQuery();
				if(result1==null)
					JOptionPane.showMessageDialog(null, "结果集中无记录");
				columnHeads = new Vector();
				ResultSetMetaData rsmd = result1.getMetaData();
				for(int i = 1; i < rsmd.getColumnCount()-2; i++)
					columnHeads.addElement(rsmd.getColumnName(i));
			} catch (SQLException e) {
				System.out.println("未成功打开数据库。");
				e.printStackTrace();
			}
			return columnHeads;
		}
	//获得每个电影在relation表获得电影的所有typeid
	public  ArrayList<Integer> allType(Object filmid) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		String sql="select typeid from relation where filmid=?";
		try { 
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setObject(1, filmid);
			ResultSet res = stmt.executeQuery();
			while(res.next()) {
				list.add(res.getInt("typeid"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	//获得每个电影对应的所有电影类型的中文
	public String types(ResultSet rs,ResultSetMetaData rsmd) {
		String types=" ";
		int t=0;
		ArrayList<Integer> type = new ArrayList<Integer>();
		try {
			for(int i=0; i <rsmd.getColumnCount(); i++){
				type = allType(rs.getInt(1));
			}
			for(int j=0;j<type.size();j++) {
				t=type.get(j);
				getTypeName(t);
				types +=getTypeName(t)+" ";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return types;
	}
	// 得到数据库中下一行数据
	private  Vector getRowData(ResultSet rs,ResultSetMetaData rsmd) throws SQLException{
		Vector currentRow = new Vector();
		for(int i = 1; i <= rsmd.getColumnCount(); i++){
			int filmid = rs.getInt(1);
			if(i == 4) {
				currentRow.addElement(types(rs,rsmd));
			}
			else 
				currentRow.addElement(rs.getString(i));
		}                     //加上在type列上的时候获取每一个数据，需写一个获得类型的方法
		return currentRow;
	}
	// 从type表中获取单个type中文名字
	public String getTypeName(int typeid) { 
		String type = null;
		String sql = "SELECT type from type where typeid = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, typeid);
			ResultSet res = stmt.executeQuery();
			while (res.next()) {
				type = res.getString("type");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return type;
	}
	// 从type表中获取多个type中文名字
	public String typesName(int filmid) {
		String types=" ";
		int t=0;
		ArrayList<Integer> type = new ArrayList<Integer>();
		type = allType(filmid);
		for(int j=0;j<type.size();j++) {
			t=type.get(j);
			getTypeName(t);
			types +=getTypeName(t)+" ";
		}
		return types;
	}

	//根据电影名字从film表中查询电影
	public Vector<Vector<Object>> getContent1(String sql){
		Vector<Vector<Object>> content = new Vector();
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
//			stmt.setString(1, filmName);
			ResultSet res = stmt.executeQuery();
			ResultSetMetaData rsmd = res.getMetaData();
			while(res.next()) {
				content.addElement(getRowData(res,rsmd));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return content;
	}
	// 根据索引所传过来的参数，来进行相应的搜索
		public Vector<Vector<Object>> getContent2(int country, int type,int a)  {
			String sql1 = "select * from film where country=? and type=? limit ?,8";
			String sql2 = "select * from film where country=? limit ?,8";
			String sql3 = "select * from film where id=? limit ?,8;";
			Vector<Vector<Object>>	content = new Vector();
			ResultSet result1;
			ResultSetMetaData rsmd;
			PreparedStatement preparedStatement = null;
			try {
				if ((country != 0) && (type != 0)) {
					preparedStatement = conn.prepareStatement(sql1);
					preparedStatement.setInt(1, country );
					preparedStatement.setInt(2,type );
					preparedStatement.setInt(3, a );
				} else if (type == 0 && country != 0) {
					preparedStatement = conn.prepareStatement(sql2);
					preparedStatement.setInt(1, country);
					preparedStatement.setInt(2, a );
				} else if (country == 0 && type != 0) {
					for(int i=0;i<getfilmid(type).size();i++) {
						int id = getfilmid(type).get(i);
						preparedStatement = conn.prepareStatement(sql3);
						preparedStatement.setInt(1, id);
						preparedStatement.setInt(2, a );
						result1 = preparedStatement.executeQuery();
						result1.next();
						if(result1 != null) {
							rsmd = result1.getMetaData();
							content.addElement(getRowData(result1, rsmd));
						}
					}
					return content;
				}
				result1 = preparedStatement.executeQuery();
				if (result1 == null) 
					JOptionPane.showMessageDialog(null, "结果集中无记录");
				// 此对象获得列的数目和类型以及每一列的名称。
				rsmd = result1.getMetaData();
				while (result1.next()) {
					content.addElement(getRowData(result1, rsmd));
				}
			} catch (SQLException e) {
				System.out.println("未成功打开数据库。");
				e.printStackTrace();
			}
			return content;
		}
//从relation表中查找已知typeid所对应的filmid
public ArrayList<Integer> getfilmid(int typeid) {

	ArrayList<Integer> filmids = new ArrayList<Integer>();
	String sql = "select filmid from relation where typeid = ?";
	try {
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, typeid);
		ResultSet res = stmt.executeQuery();
		while(res.next()) {
			filmids.add(res.getInt("filmid"));
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}

	return filmids;
}
// 从film获取符合所有country条件的type的id
public int getTypeId(int countryid) { 
	int type = 0;
	String sql = "SELECT type from film where country = ?";
	try {
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, countryid);
		ResultSet res = stmt.executeQuery();
		while (res.next()) {
			type = res.getInt("type");
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return type;
}
//查询数据总行数
	public int getCount(String sql) {
		int count = 0;
		 PreparedStatement st = null;
		 ResultSet rs;
		 try {
			 st = conn.prepareStatement(sql);
			 rs = st.executeQuery();
			 if(rs.next()) {  
		           count=rs.getInt("totalCount");  
		        } 
		 }catch (SQLException e) {
				e.printStackTrace();
			}
		return count;
	}
	//创建用户
	public void setUser(String sql,GetSet user,String username) {
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				user.setID(rs.getInt("id"));
				user.setName(rs.getString("username"));
				user.setCode(rs.getString("code"));
				user.setGender(rs.getInt("gender"));
				user.setAnswer(rs.getString("answer"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//根据用户名查询符合条件的用户
	public Vector<Vector<Object>> ugetContent1(String sql) {
		
			Vector<Vector<Object>> content = new Vector();
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
//				stmt.setString(1, userName);
				ResultSet res = stmt.executeQuery();
				ResultSetMetaData rsmd = res.getMetaData();
				while(res.next()) {
					content.addElement(getRowData(res,rsmd));
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
			return content;
		}
	//根据性别查找用户信息
	public Vector<Vector<Object>> ugetContent2(String sql,int gender, int number1) {

		Vector<Vector<Object>> content = new Vector();
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, gender);
			stmt.setInt(2, number1);
			ResultSet res = stmt.executeQuery();
			ResultSetMetaData rsmd = res.getMetaData();
			while(res.next()) {
				content.addElement(getRowData(res,rsmd));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return content;
	}
	//在film表中找国家的id
	public int getCountryId(String sql, Object filmname) {
		int id = 0;
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setObject(1, filmname);
			ResultSet res = stmt.executeQuery();
			ResultSetMetaData rsmd = res.getMetaData();
			while(res.next()) {
				id = res.getInt("country");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	//修改film表中的数据
	public Vector<Vector<String>> changeData(String sql ,String name ,int country,int type,Object mark ,Object data,Object intro,Object site,Object filmid){
		 Vector<Vector<String>> newData =  new Vector();
		 PreparedStatement st = null;
		 ResultSet rs;
		 ResultSetMetaData rsmd;
		 try {
			 st=conn.prepareStatement(sql);
			 st.setString(1, name);
			 st.setInt(2, country);
			 st.setInt(3,type);
			 st.setObject(4, mark);
			 st.setObject(5, data);
			 st.setObject(6, intro);
			 st.setObject(7, site);
			 st.setObject(8, filmid);
			 st.executeUpdate();
			 
		 }catch (SQLException e) {
				e.printStackTrace();
			}
		return newData;
	}
//增加relation表中的数据
	public Vector<Vector<String>> addData(String sql ,Object filmid, int typeid){
		 Vector<Vector<String>> newData =  new Vector();
		 PreparedStatement st = null;
		 ResultSet rs;
		 ResultSetMetaData rsmd;
		 try {
			 st=conn.prepareStatement(sql);
			 st.setInt(1, typeid);
			 st.setObject(2, filmid);
			 st.executeUpdate(); 
			 
		 }catch (SQLException e) {
				e.printStackTrace();
			}
		return newData;}
	//删除relation表中的数据
	public Vector<Vector<String>> deleteData(String sql,Object id){
		 Vector<Vector<String>> newData =  new Vector();
		 PreparedStatement st = null;
		 ResultSet rs;
		 ResultSetMetaData rsmd;
		 try {
			 st = conn.prepareStatement(sql);
			 st.setObject(1, id);
			st.executeUpdate();
		 }catch (SQLException e) {
				e.printStackTrace();
			}
		return newData;
	}
	//更新film表格内容数据
		public Vector<Vector<String>> addAllData(String sql,String name ,int country,int type,Object mark ,Object data,Object intro,Object site){
			 Vector<Vector<String>> newData =  new Vector();
			 PreparedStatement st = null;
			 ResultSet rs;
			 ResultSetMetaData rsmd;
			 try {
				 st=conn.prepareStatement(sql);
				 st.setString(1, name);
				 st.setInt(2, country);
				 st.setInt(3,type);
				 st.setObject(4, mark);
				 st.setObject(5, data);
				 st.setObject(6, intro);
				 st.setObject(7, site);
				 st.executeUpdate();
				 
			 }catch (SQLException e) {
					e.printStackTrace();
				}
			return newData;}
		//在film表中查询filmid
		public Object getFilmid(String sql, String filmname) {
			Object id = null;
			PreparedStatement st = null;
			 ResultSet rs;
			try {
				st=conn.prepareStatement(sql);
				st.setString(1,filmname);
				rs= st.executeQuery();
				while(rs.next()) {
					id = rs.getObject("id");
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
			return id;
		}
	
}