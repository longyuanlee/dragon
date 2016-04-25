package com.longyuanlee.dragon.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.longyuanlee.dragon.model.constants.BaseResultModel;
import com.longyuanlee.dragon.mvc.JsonView;
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
	public BaseResultModel test() {
		System.out.println("123");
		accountService.get();
		BaseResultModel baseResultModel = new BaseResultModel();
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "value");
		baseResultModel.setValue(map);

		return baseResultModel;
	}

	@RequestMapping("test1")
	@ResponseBody
	public JsonView test1() {
		System.out.println("123");
		accountService.get();
		JsonView jsonView = new JsonView();

		return jsonView;
	}

}
