package org.flycloud.web.exam3.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface ImportService {

	public void importUser(List<List<Object>> llo) throws Exception;

	public void importQuestion(MultipartFile file) throws Exception;

	public void importTextQuestion(MultipartFile file) throws Exception;

}
