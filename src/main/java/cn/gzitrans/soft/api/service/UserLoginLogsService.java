package cn.gzitrans.soft.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.gzitrans.soft.api.dao.UserLoginLogsDao;
import cn.gzitrans.soft.api.entity.UserLoginLogsEntity;

@Service
public class UserLoginLogsService {
	
	@Autowired
	private UserLoginLogsDao userLoginLogsDao;
	
	/**
	 * 保存登录记录
	 * @param user
	 */
	public void save(UserLoginLogsEntity userLoginLogs){
		userLoginLogsDao.save(userLoginLogs);
    }

}
