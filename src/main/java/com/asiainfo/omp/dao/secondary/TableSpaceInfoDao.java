package com.asiainfo.omp.dao.secondary;

import java.util.List;
import java.util.Map;

import com.asiainfo.omp.model.TableSpaceInfo;

/**
 * @Description 表空间Dao
 * @author yangbin
 * @date 2017.6.26
 *
 */
public interface TableSpaceInfoDao {

	/**
	 * 表空间列表查询
	 * @return
	 */
	public List<TableSpaceInfo> queryTableSpaceInfoList(Map<String,Object> map);
	/**
	 * 通过表空间Id查询对象
	 * @return
	 */
	public TableSpaceInfo getTableSpaceInfoById(int id);
	
	/**
	 * 查询所有表空间数量
	 * @return
	 */
	public Map<String,Object> queryAllTableSpaceNum();
	
	/**
	 * 查询数量
	 * @param map
	 * @return
	 */
	public Map<String, Object> queryTableSpaceNum(Map<String, Object> map);
	
	/**
	 * 获取详情信息，查询同一表空间的其他节点
	 * @param map
	 * @return
	 */
	public List<TableSpaceInfo> queryAllByTableName(Map<String,Object> map);
	
}
