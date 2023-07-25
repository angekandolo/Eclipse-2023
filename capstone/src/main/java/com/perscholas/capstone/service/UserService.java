package com.perscholas.capstone.service;
import com.perscholas.capstone.dto.*;
import com.perscholas.capstone.entity.*;

import java.util.List;

public interface UserService {
	   void saveUser(UserDto userDto);

	    User findUserByEmail(String email);
	}
