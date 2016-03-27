package com.longyuanlee.dragon.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.longyuanlee.dragon.service.AccountService;

/**
 * @ClassName: AccountController
 * @Description:
 * @Company:YY
 * @Copyright: 2016
 * @author ly
 * @date 2016-3-24
 * 
 */
@Controller
@RequestMapping(value = AccountController.DIR)
public class AccountController {
	public static final String DIR = "/account";

	@Autowired
	private AccountService accountService;

	@RequestMapping("test")
	@ResponseBody
	public String test() {
		System.out.println("123");
		return accountService.get();
	}

}
