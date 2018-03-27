package cn.gzitrans.soft.api.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import cn.gzitrans.soft.api.entity.UserEntity;

public interface UserDao extends CrudRepository<UserEntity, Long>{

	@Query("from UserEntity where openId = ?")
	UserEntity findUserByOpenId(String openid);

}
