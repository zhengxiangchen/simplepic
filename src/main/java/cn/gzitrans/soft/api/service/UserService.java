package cn.gzitrans.soft.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.gzitrans.soft.api.dao.UserDao;
import cn.gzitrans.soft.api.entity.UserEntity;


@Service
public class UserService{

	@Autowired
	private UserDao userDao;
	
	/**
	 * 保存用户信息
	 * @param user
	 */
	public void save(UserEntity user){
		userDao.save(user);
    }


	/**
	 * 通过openid获取用户对象
	 * @param openid
	 * @return
	 */
	public UserEntity findUserByOpenId(String openid) {
		return userDao.findUserByOpenId(openid);
	}

}
