package com.asiainfo.omp.dao.secondary;

import java.util.List;
import java.util.Map;

import com.asiainfo.omp.model.FaultFLow;

public interface KnowledgeDao {
	/**
	 * 故障列表查询
	 * @return
	 */
	public List<FaultFLow> queryKnowledgeInfoList(Map<String,Object> map);
	
	/**
	 * 查询故障上传附件
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryAttachByFaultId(Map<String,Object> map);
	
	/**
	 * 保存
	 * @param map
	 */
	public void saveFaultInfo(FaultFLow fault); 
	
	/**
	 * 修改
	 * @param map
	 */
	public void updateFaultInfo(FaultFLow fault);
	
	/**
	 * 删除
	 * @param faultId
	 */
	public void deleteFaultInfo(int faultId);
}
