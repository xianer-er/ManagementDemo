package Model;
import java.sql.SQLException;
import java.util.Vector;

import Control.Controller;
public class Judge {
	public int a ;
	public int n;
	Controller controller = new Controller();
	GetSet newUser = new GetSet();
	String name = "[a-zA-Z\\d\\u4e00-\\u9fa5] {0,20}";
	//对于电影查询的判断
	public Vector<Vector<Object>> judgeContent(String filmName, int type, int country, int number1) {
		Vector<Vector<Object>> content = new Vector();
		if((!filmName.equals(""))&&filmName.matches(name)) {  
				content = controller.getContent1(filmName);
			}
		else if(filmName.equals("")) {
			if(type==0&&country==0) {
				content = controller.getContent(number1, "film");
				a=0;
				}
			if(type!=0&&country!=0) {
				content = controller.getContent2(country,type,number1);
				a=1;
			}
			if(type!=0&&country==0) {
				content = controller.getContent2(country,type,number1);
				a=2;
			}
			if(type==0&&country!=0) {
				content = controller.getContent2(country,type,number1);
				a=3;
			}
		}
		return content;
	}
	//对于用户查询的判断
public Vector<Vector<Object>> getContent1(String userName ,int gender,int number1){
	Vector<Vector<Object>> content = new Vector();
	if((!userName.equals(""))&&userName.matches(name)) {
			content = controller.ugetContent1(userName);
		}
	else if(userName.equals("")) {
		if(gender ==2) {
			content = controller.getContent(number1, "user");
			}
		if(gender==1) {
			content = controller.ugetContent2(gender,number1);
		}
		if(gender==0) {
			content = controller.ugetContent2(gender,number1);
		}
	}
	return content;
}
}