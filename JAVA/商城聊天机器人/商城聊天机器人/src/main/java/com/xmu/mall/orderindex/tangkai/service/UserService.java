package com.xmu.mall.orderindex.tangkai.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmu.mall.orderindex.tangkai.model.NewUser;
import com.xmu.mall.orderindex.tangkai.model.User;
import com.xmu.mall.orderindex.tangkai.model.UserAddress;

/**
 * @author tangkai
 * @data 2017年6月2日--下午4:03:59
 */
public interface UserService {
	public User getUserByUserId(long user_id);
	
	public String getUserIdByName(String username);
	
	public void addUserAddress(UserAddress address);
	
	public List<UserAddress> getUserAddressesByUserId(long user_id);
	
	public List<UserAddress> getNotDefaultAddress(long user_id);
	
	public UserAddress getDefaultAddress(long user_id);
	
	public UserAddress getAddressById(long address_id);
	
	public int checkUser(String username, String psw);
	
	public int checkManager(String manager_id, String psw);
	
	public void addUser(NewUser user);
	
	public User getUserByUserName(String user_name);
}
