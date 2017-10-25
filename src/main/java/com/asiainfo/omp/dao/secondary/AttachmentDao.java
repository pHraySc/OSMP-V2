package com.asiainfo.omp.dao.secondary;

import java.util.Map;

import com.asiainfo.omp.model.Attachment;

public interface AttachmentDao {

	/**
	 * 保存
	 * @param att
	 */
	public void saveAttachmentInfo(Attachment att);
	
	/**
	 * 删除
	 * @param attId
	 */
    public void deleteAttachmentInfo(int attId);
    
    /**
     * 保存故障id到文件数据
     * @param attId
     * @param flowId
     */
    public void updateAttachment(Map<String, Object> map);
    
    /**
     * 删除附件
     * @param faultId
     */
    public void deleteAttByFaultId(int faultId);
}
