package com.asiainfo.omp.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.omp.dao.secondary.AttachmentDao;
import com.asiainfo.omp.model.Attachment;
import com.asiainfo.omp.service.AttachmentService;
@Service
public class AttachmentServiceImpl implements AttachmentService {
	
	@Autowired
	AttachmentDao dao;

	@Override
	public void saveAttachmentInfo(Attachment att) {
		dao.saveAttachmentInfo(att);
	}

	@Override
	public void deleteAttachmentInfo(int attId) {
		dao.deleteAttachmentInfo(attId);
	}

	@Override
	public void updateAttachment(Map<String, Object> map) {
		dao.updateAttachment(map);
	}

	@Override
	public void deleteAttByFaultId(int faultId) {
		dao.deleteAttByFaultId(faultId);
	}

}
