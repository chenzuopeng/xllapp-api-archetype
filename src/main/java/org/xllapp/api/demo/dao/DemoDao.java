package org.xllapp.api.demo.dao;

import java.util.List;
import java.util.Map;

import org.xllapp.api.demo.entity.Demo;
import org.xllapp.mybatis.MyBatisRepository;


/**
 *
 * 此接口中的方法名即为DemoMapper.xml文件中定义的SQL语句的id.
 *
 * @author dylan.chen Feb 24, 2013
 * 
 */
@MyBatisRepository
public interface DemoDao {

	public List<Demo> search(Map<String, Object> parameters);
	
	public Demo get(Long id);
 
	public void save(Demo demo);
	
	public void update(Demo demo);

	public void delete(Long id);
	
}
