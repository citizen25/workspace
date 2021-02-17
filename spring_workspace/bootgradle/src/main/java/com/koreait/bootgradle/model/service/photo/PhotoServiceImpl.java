package com.koreait.bootgradle.model.service.photo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.koreait.bootgradle.exception.FileUploadException;
import com.koreait.bootgradle.exception.PhotoUpdateException;
import com.koreait.bootgradle.model.common.UploadManager;
import com.koreait.bootgradle.model.domain.Photo;
import com.koreait.bootgradle.model.photo.repository.PhotoDAO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PhotoServiceImpl implements PhotoService{
	@Autowired
	private PhotoDAO photoDAO;
	
	@Autowired
	private UploadManager uploadManager;
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void regist(Photo photo) throws PhotoUpdateException,FileUploadException{
		log.debug("regist() 호출");
		uploadManager.save(photo.getMyFile()); //DB X
		photo.setFilename(photo.getMyFile().getOriginalFilename());//업로드된 파일명 대입
		photoDAO.insert(photo);
	}
}
