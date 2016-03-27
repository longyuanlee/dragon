package com.longyuanlee.dragon.dao.mysql;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.longyuanlee.dragon.dao.AccountDao;

@Repository
public class AccountDaoMysqlImpl implements AccountDao {
	
	@Resource(name = "fairyShareUrl")
	private String fairyShareUrl;

	@Override
	public String get() {
		System.out.println("accountDaoGet");
		System.out.println(fairyShareUrl);
		return "";
	}

}
