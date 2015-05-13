package org.flycloud.web.exam3.service;

import javax.inject.Inject;

import org.flycloud.util.MD5Builder;
import org.flycloud.web.exam3.dao.UserDao;
import org.flycloud.web.exam3.model.User;
import org.flycloud.web.exception.PasswordErrorException;
import org.flycloud.web.exception.UserNotExistsException;
import org.springframework.stereotype.Service;

@Service
public class PlatformServiceImpl implements PlatformService {

	@Inject
	private UserDao userDao;

	@Override
	public void login(String name, String pass) throws UserNotExistsException,
			PasswordErrorException {
		User user = userDao.findOne(name);
		if (user == null) {
			throw new UserNotExistsException();
		}

		if (!MD5Builder.getMD5String(user.getPassword()).equals(pass)) {
			throw new PasswordErrorException();
		}
	}

}
