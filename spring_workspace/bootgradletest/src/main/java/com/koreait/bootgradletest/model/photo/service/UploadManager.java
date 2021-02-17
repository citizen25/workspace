package com.koreait.bootgradletest.model.photo.service;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.koreait.bootgradletest.exception.UploadException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UploadManager {

	public void save(MultipartFile multi) throws UploadException{
		log.debug("contex is "+this.getClass().getResource("/static/upload").getPath());
		
		String savePath = this.getClass().getResource("/static/upload").getPath()+"/"+multi.getOriginalFilename();
		log.debug("파일 이름 "+savePath);
		
		try {
			multi.transferTo(new File(savePath));
		} catch (IllegalStateException e) {
			e.printStackTrace();
			throw new UploadException("업로드 실패");
		} catch (IOException e) {
			e.printStackTrace();
			throw new UploadException("업로드 실패");
		}
	}
}
