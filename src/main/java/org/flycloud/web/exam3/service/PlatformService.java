package org.flycloud.web.exam3.service;

import org.flycloud.web.exception.PasswordErrorException;
import org.flycloud.web.exception.UserNotExistsException;

public interface PlatformService {

	public void login(String name, String pass) throws UserNotExistsException,
			PasswordErrorException;

}
