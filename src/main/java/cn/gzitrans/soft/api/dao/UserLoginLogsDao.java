package cn.gzitrans.soft.api.dao;

import org.springframework.data.repository.CrudRepository;

import cn.gzitrans.soft.api.entity.UserLoginLogsEntity;

public interface UserLoginLogsDao extends CrudRepository<UserLoginLogsEntity, Long>{

}
