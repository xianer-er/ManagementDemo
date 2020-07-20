package Control;

import java.util.ArrayList;
import java.util.Vector;

import Model.GetSet;
import Model.QueryMethods;

public class Controller {
	QueryMethods querymethods =new QueryMethods();
	//增加数据
	//增加用户信息
	public void CreateUsers(GetSet newUser) {
		querymethods.write("insert ignore into user (username,code,gender,status,answer) values (?,?,?,?,?) ",newUser.getName(),newUser.getCode(),newUser.getGender(),newUser.getStatus(),newUser.getAnswer());
	}
	//增加relation信息
	public void addData(Object filmid,int typeid) {
		querymethods.addData("insert into relation (typeid,filmid) values(?,?)",filmid,typeid); 
	}
	//增加film信息
	public void addAllData(String name ,int country,int type,Object mark ,Object data,Object intro,Object site) {
		querymethods.addAllData("insert into film (name,country,type,mark,date,intro,site) values(?,?,?,?,?,?,?)",name, country, type,mark,data,intro,site);
	}
	//删除数据
	//删除relation表数据
	public void deleteDate(Object id) {
		querymethods.deleteData("delete from relation where filmid =?", id);
	}
	//删除film数据
	public void deleteDate1(Object id) {
		querymethods.deleteData("delete from film where id =?", id);
	}
	//删除用户数据
	public void deleteDate2(Object id) {
		querymethods.deleteData("delete from user where id =?", id);
	}
	//修改数据
	//修改密码
	public void CreateUsers2(GetSet newCode) {
		querymethods.write("update user set code = ? where username = ? ",newCode.getCode(),newCode.getName());
	}
	//修改用户信息
	public void CreateUsers3(GetSet newUser) {
		querymethods.write("update user set username = ? where gender = ?",newUser.getName(),newUser.getGender());
	}
	//修改电影信息
	public void changeData(String name ,int country,int type,Object mark ,Object data,Object intro,Object site, Object filmid) {
		querymethods.changeData("update film set name=?,country = ?,type = ? ,mark =?,date = ?, intro = ?, site = ?where id = ?", name, country, type,mark,data,intro,site,filmid);
	}
	//查询数据
	//查询用户密码
	public String getUserCode (String userName) {
		return querymethods.getUserCode("select code from user where username = ?", userName);
	}
	//查询用户性别
	public int getGender (String userName) {
		return querymethods.getGender("select gender from user where username = ?", userName);
	}
	//根据名字查询电影
	public GetSet findFilm(String name ) {
		return querymethods.findFilm("select * from film where name=? ", name,"id");
	}
	//根据用户名查询用户id
	public GetSet findUsers(String userName ) {
		return querymethods.findUsers("select * from user where username=? ", userName,"id");
	}
	//根据用户名查询用户
	public GetSet findAnswer(GetSet user) {
		return querymethods.findUsers1("select * from user where username = ?", user);
	}
	//查询type中所有类型
	public String[] findType(){
		return querymethods.select("SELECT * FROM type","type");
	}
	//查询country中所有国家
	public String[] findCountry() {
		return querymethods.select("SELECT * FROM country", "country");
	}
	//查询整个表
	public Vector<Vector<Object>> getContent(int a,String tablename) {
		return querymethods.getRows("select * from "+tablename+" limit ? , 8", tablename,a);
	}
	//查询film表头
	public Vector getHead(String tableName) {
		return querymethods.getHead("select * from "+tableName);
	}
	//查询uesr表头
	public Vector getHead1(String tableName) {
		return querymethods.getHead1("select * from "+tableName);
	}
	//根据国家id从country表查询国家
	public String getCountryName(int countryid) {
		return querymethods.getCountryName("SELECT country from country where id=?", countryid);
	}
	//根据电影名模糊查询电影
	public Vector<Vector<Object>> getContent1(String filmName){
		return querymethods.getContent1("select * from film where name like '%"+filmName+"%'");
	}
	//根据用户名模糊查询用户
	public Vector<Vector<Object>> ugetContent1(String userName){
		return querymethods.ugetContent1("select * from user where username like '%"+userName+"%'");
	}
	//联合查询
	public Vector<Vector<Object>> getContent2(int country ,int type , int number1) {
		return querymethods.getContent2(country,type,number1);
	}
	//查询不同性别的用户
	public Vector<Vector<Object>> ugetContent2(int gender , int number1) {
		return querymethods.ugetContent2("select * from user where gender=?  limit ?,8",gender,number1);
	}
	//查询类型的名字
	public String getTypesName( int filmid) {
		return querymethods.typesName(filmid);
	}
	//查询类型的id
	public int getTypeId(int countryid) {
		return querymethods.getTypeId(countryid);
	}
	//查询film表总行数
	public int getCount() {
		return querymethods.getCount("select count(*) as totalCount from film");
	}
	//查询user表总行数
	public int getCount1() {
		return querymethods.getCount("select count(*) as totalCount from user");
	}
	//根据用户名查询用户
	public void setUser(GetSet user,String username) {
		querymethods.setUser("select * from user where username = ?", user,username);
	}
	//根据用户名查询用户性别并转化为中文
	public String getGender1(String userName) {
		String sex = null;
		int i =  querymethods.getGender("select gender from user where username = ?", userName);
		switch(i) {
		case 0:
			sex = "男";
			break;
		case 1:
			sex = "女";
			break;
		}
		return sex;
	}
	//根据电影名查询对应的countryid
	public int getCountryId(Object filmname) {
		return querymethods.getCountryId("select country from film where name = ?",filmname);
	}
	//根据filmid在relation中查询其所有的type
	public ArrayList<Integer> getTypes(Object  filmid){
		return querymethods.allType(filmid);
	}
	//根据电影名查询他对应的id
	public Object getFilmid(String filmname) {
		return querymethods.getFilmid("select id from film where name = ?",filmname);
	}
	//根据用户名查询他对应的状态
	public int getStatus(String name) {
		return querymethods.getStatus("select status from user where username = ?", name);
	}
}
