package com.pass.service;

import com.pass.model.Media;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
	
	List<Media> upload(MultipartFile[] file, String source);
	
	String update(MultipartFile file, String path);
}
