package com.xmu.mall.orderindex.tangkai.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xmu.mall.orderindex.tangkai.model.NewUser;
import com.xmu.mall.orderindex.tangkai.model.User;
import com.xmu.mall.orderindex.tangkai.model.UserAddress;

/**
 * @author tangkai
 * @data 2017年6月2日--下午4:17:17
 */
@Repository
public interface UserMapper {
	
	public User getUserByUserId(long user_id);
	
	public String getUserIdByName(@Param("username") String username);
	
	public void addUserAddress(UserAddress address);
	
	public List<UserAddress> getUserAddressesByUserId(long user_id);
	
	public List<UserAddress> getNotDefaultAddress(long user_id);
	
	public UserAddress getDefaultAddress(long user_id);
	
	public UserAddress getAddressById(long address_id);
	
	public int checkUser(@Param("username") String username,@Param("psw") String psw);
	
	public int checkManager(@Param("manager_id") String username,@Param("psw") String psw);
	
	public void addUser(NewUser user);
	
	public User getUserByUserName(String user_name);
	
}
