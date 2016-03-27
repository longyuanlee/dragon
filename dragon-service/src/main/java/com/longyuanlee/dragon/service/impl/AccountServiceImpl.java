package com.longyuanlee.dragon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longyuanlee.dragon.dao.AccountDao;
import com.longyuanlee.dragon.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountDao accountDao;

	@Override
	public String get() {
		System.out.println("accountGet");
		accountDao.get();
		return "";
	}

}
