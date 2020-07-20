package Control;

import java.util.ArrayList;
import java.util.Vector;

import Model.GetSet;
import Model.QueryMethods;

public class Controller {
	QueryMethods querymethods =new QueryMethods();
	//��������
	//�����û���Ϣ
	public void CreateUsers(GetSet newUser) {
		querymethods.write("insert ignore into user (username,code,gender,status,answer) values (?,?,?,?,?) ",newUser.getName(),newUser.getCode(),newUser.getGender(),newUser.getStatus(),newUser.getAnswer());
	}
	//����relation��Ϣ
	public void addData(Object filmid,int typeid) {
		querymethods.addData("insert into relation (typeid,filmid) values(?,?)",filmid,typeid); 
	}
	//����film��Ϣ
	public void addAllData(String name ,int country,int type,Object mark ,Object data,Object intro,Object site) {
		querymethods.addAllData("insert into film (name,country,type,mark,date,intro,site) values(?,?,?,?,?,?,?)",name, country, type,mark,data,intro,site);
	}
	//ɾ������
	//ɾ��relation������
	public void deleteDate(Object id) {
		querymethods.deleteData("delete from relation where filmid =?", id);
	}
	//ɾ��film����
	public void deleteDate1(Object id) {
		querymethods.deleteData("delete from film where id =?", id);
	}
	//ɾ���û�����
	public void deleteDate2(Object id) {
		querymethods.deleteData("delete from user where id =?", id);
	}
	//�޸�����
	//�޸�����
	public void CreateUsers2(GetSet newCode) {
		querymethods.write("update user set code = ? where username = ? ",newCode.getCode(),newCode.getName());
	}
	//�޸��û���Ϣ
	public void CreateUsers3(GetSet newUser) {
		querymethods.write("update user set username = ? where gender = ?",newUser.getName(),newUser.getGender());
	}
	//�޸ĵ�Ӱ��Ϣ
	public void changeData(String name ,int country,int type,Object mark ,Object data,Object intro,Object site, Object filmid) {
		querymethods.changeData("update film set name=?,country = ?,type = ? ,mark =?,date = ?, intro = ?, site = ?where id = ?", name, country, type,mark,data,intro,site,filmid);
	}
	//��ѯ����
	//��ѯ�û�����
	public String getUserCode (String userName) {
		return querymethods.getUserCode("select code from user where username = ?", userName);
	}
	//��ѯ�û��Ա�
	public int getGender (String userName) {
		return querymethods.getGender("select gender from user where username = ?", userName);
	}
	//�������ֲ�ѯ��Ӱ
	public GetSet findFilm(String name ) {
		return querymethods.findFilm("select * from film where name=? ", name,"id");
	}
	//�����û�����ѯ�û�id
	public GetSet findUsers(String userName ) {
		return querymethods.findUsers("select * from user where username=? ", userName,"id");
	}
	//�����û�����ѯ�û�
	public GetSet findAnswer(GetSet user) {
		return querymethods.findUsers1("select * from user where username = ?", user);
	}
	//��ѯtype����������
	public String[] findType(){
		return querymethods.select("SELECT * FROM type","type");
	}
	//��ѯcountry�����й���
	public String[] findCountry() {
		return querymethods.select("SELECT * FROM country", "country");
	}
	//��ѯ������
	public Vector<Vector<Object>> getContent(int a,String tablename) {
		return querymethods.getRows("select * from "+tablename+" limit ? , 8", tablename,a);
	}
	//��ѯfilm��ͷ
	public Vector getHead(String tableName) {
		return querymethods.getHead("select * from "+tableName);
	}
	//��ѯuesr��ͷ
	public Vector getHead1(String tableName) {
		return querymethods.getHead1("select * from "+tableName);
	}
	//���ݹ���id��country���ѯ����
	public String getCountryName(int countryid) {
		return querymethods.getCountryName("SELECT country from country where id=?", countryid);
	}
	//���ݵ�Ӱ��ģ����ѯ��Ӱ
	public Vector<Vector<Object>> getContent1(String filmName){
		return querymethods.getContent1("select * from film where name like '%"+filmName+"%'");
	}
	//�����û���ģ����ѯ�û�
	public Vector<Vector<Object>> ugetContent1(String userName){
		return querymethods.ugetContent1("select * from user where username like '%"+userName+"%'");
	}
	//���ϲ�ѯ
	public Vector<Vector<Object>> getContent2(int country ,int type , int number1) {
		return querymethods.getContent2(country,type,number1);
	}
	//��ѯ��ͬ�Ա���û�
	public Vector<Vector<Object>> ugetContent2(int gender , int number1) {
		return querymethods.ugetContent2("select * from user where gender=?  limit ?,8",gender,number1);
	}
	//��ѯ���͵�����
	public String getTypesName( int filmid) {
		return querymethods.typesName(filmid);
	}
	//��ѯ���͵�id
	public int getTypeId(int countryid) {
		return querymethods.getTypeId(countryid);
	}
	//��ѯfilm��������
	public int getCount() {
		return querymethods.getCount("select count(*) as totalCount from film");
	}
	//��ѯuser��������
	public int getCount1() {
		return querymethods.getCount("select count(*) as totalCount from user");
	}
	//�����û�����ѯ�û�
	public void setUser(GetSet user,String username) {
		querymethods.setUser("select * from user where username = ?", user,username);
	}
	//�����û�����ѯ�û��Ա�ת��Ϊ����
	public String getGender1(String userName) {
		String sex = null;
		int i =  querymethods.getGender("select gender from user where username = ?", userName);
		switch(i) {
		case 0:
			sex = "��";
			break;
		case 1:
			sex = "Ů";
			break;
		}
		return sex;
	}
	//���ݵ�Ӱ����ѯ��Ӧ��countryid
	public int getCountryId(Object filmname) {
		return querymethods.getCountryId("select country from film where name = ?",filmname);
	}
	//����filmid��relation�в�ѯ�����е�type
	public ArrayList<Integer> getTypes(Object  filmid){
		return querymethods.allType(filmid);
	}
	//���ݵ�Ӱ����ѯ����Ӧ��id
	public Object getFilmid(String filmname) {
		return querymethods.getFilmid("select id from film where name = ?",filmname);
	}
	//�����û�����ѯ����Ӧ��״̬
	public int getStatus(String name) {
		return querymethods.getStatus("select status from user where username = ?", name);
	}
}
