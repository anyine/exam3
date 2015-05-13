package org.flycloud.web.exam3.service;

import java.util.List;

import javax.inject.Inject;

import org.flycloud.web.exam3.dao.UserDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ImportServiceImpl implements ImportService {

//	@Inject
//	private UserDao userDao;

	@Override
	public void importUser(List<List<Object>> llo) throws Exception {

	}
}
