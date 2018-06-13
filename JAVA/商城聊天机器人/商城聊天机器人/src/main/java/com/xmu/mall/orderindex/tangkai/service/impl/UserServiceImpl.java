package com.xmu.mall.orderindex.tangkai.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmu.mall.orderindex.tangkai.mapper.UserMapper;
import com.xmu.mall.orderindex.tangkai.model.NewUser;
import com.xmu.mall.orderindex.tangkai.model.User;
import com.xmu.mall.orderindex.tangkai.model.UserAddress;
import com.xmu.mall.orderindex.tangkai.service.UserService;

/**
 * @author tangkai
 * @data 2017年6月2日--下午4:14:50
 */
@Service("tangkai_UserService")
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService{
	@Autowired (required=false) 
	private UserMapper userMapper;
	
	@Override
	public User getUserByUserId(long user_id) {
		// TODO Auto-generated method stub
		return userMapper.getUserByUserId(user_id);
	}

	@Override
	public void addUserAddress(UserAddress address) {
		// TODO Auto-generated method stub
		userMapper.addUserAddress(address);
	}

	@Override
	public List<UserAddress> getUserAddressesByUserId(long user_id) {
		// TODO Auto-generated method stub
		return userMapper.getUserAddressesByUserId(user_id);
	}

	@Override
	public List<UserAddress> getNotDefaultAddress(long user_id) {
		// TODO Auto-generated method stub
		return userMapper.getNotDefaultAddress(user_id);
	}

	@Override
	public UserAddress getDefaultAddress(long user_id) {
		// TODO Auto-generated method stub
		return userMapper.getDefaultAddress(user_id);
	}

	@Override
	public UserAddress getAddressById(long address_id) {
		// TODO Auto-generated method stub
		return userMapper.getAddressById(address_id);
	}

	@Override
	public int checkUser(String username, String psw) {
		// TODO Auto-generated method stub
		return userMapper.checkUser(username, psw);
	}

	@Override
	public String getUserIdByName(String username) {
		// TODO Auto-generated method stub
		return userMapper.getUserIdByName(username);
	}

	@Override
	public void addUser(NewUser user) {
		// TODO Auto-generated method stub
		userMapper.addUser(user);
	}

	@Override
	public User getUserByUserName(String user_name) {
		// TODO Auto-generated method stub
		return userMapper.getUserByUserName(user_name);
	}

	@Override
	public int checkManager(String manager_id, String psw) {
		// TODO Auto-generated method stub
		return userMapper.checkManager(manager_id, psw);
	}
	

}
