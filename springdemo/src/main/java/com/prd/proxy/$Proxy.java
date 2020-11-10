package com.prd.proxy;
import com.prd.proxy.UserDao;
public class $Proxy implements UserDao{
	private UserDao target;
	public $Proxy (UserDao target){
		this.target =target;
	}
	public void query() {
		System.out.println("log");
		target.query();
	}
}