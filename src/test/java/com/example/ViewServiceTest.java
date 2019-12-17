package com.example;

import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.example.entities.User;
import com.example.services.ViewService;
import com.example.services.atom.IUserService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = App.class)
public class ViewServiceTest {

	@InjectMocks
	ViewService viewService;

	@Mock
	IUserService userService;

	/**
	 * 测试：user数据都是合理正确的，并且有6条数据，并取出全部数据进行验证
	 */
	@Test
	public void testWithAllValidData() {

		List<User> originalData = new ArrayList<>();
		originalData.add(new User(1, "howardzuo@examplepace.com", "15618922978", "大都督", "码农"));
		originalData.add(new User(2, "tinazhang@examplepace.com", "18121001453", "市场部小姑娘", "市场经理"));
		originalData.add(new User(3, "hmwang@examplepace.com", "18726888330", "慧敏小姐姐", "程序媛"));
		originalData.add(new User(4, "cyren@examplepace.com", "13524695359", "马丁", "大Boss"));
		originalData.add(new User(5, "dzhang@examplepace.com", "13866861057", "张丹大哥哥", "设计大师"));
		originalData.add(new User(6, "hunterwen@examplepace.com", "17502125103", "猎手Hunter", "十万个为什么"));

		when(userService.list()).thenReturn(originalData);

		List<User> users = viewService.getUsersWithPhoneMarked(null);

		assertSame(6, users.size());
		assertTrue(users.get(0).getMobile().endsWith("******"));
	}

	/**
	 * 测试：user数据都是合理正确的，并且有6条数据，并取出前1条数据进行验证
	 */
	@Test
	public void testWithAllValidDataButLimited() {

		List<User> originalData = new ArrayList<>();
		originalData.add(new User(1, "howardzuo@examplepace.com", "15618922978", "大都督", "码农"));
		originalData.add(new User(2, "tinazhang@examplepace.com", "18121001453", "市场部小姑娘", "市场经理"));
		originalData.add(new User(3, "hmwang@examplepace.com", "18726888330", "慧敏小姐姐", "程序媛"));
		originalData.add(new User(4, "cyren@examplepace.com", "13524695359", "马丁", "大Boss"));
		originalData.add(new User(5, "dzhang@examplepace.com", "13866861057", "张丹大哥哥", "设计大师"));
		originalData.add(new User(6, "hunterwen@examplepace.com", "17502125103", "猎手Hunter", "十万个为什么"));

		when(userService.list()).thenReturn(originalData);

		List<User> users = viewService.getUsersWithPhoneMarked(1);

		assertSame(1, users.size());
		assertTrue(users.get(0).getMobile().endsWith("******"));
	}

	/**
	 * 测试：user数据返回null时，结果应该转换成EmptyList
	 */
	@Test
	public void testWithNullData() {

		when(userService.list()).thenReturn(null);

		List<User> users = viewService.getUsersWithPhoneMarked(null);

		assertSame(0, users.size());
	}

	/**
	 * 测试：user数据返回第1条的mobile字段为不合法的156时，结果应该没有*，依旧是156
	 */
	@Test
	public void testWithInvalidData() {

		List<User> originalData = new ArrayList<>();
		originalData.add(new User(1, "howardzuo@examplepace.com", "156", "大都督", "码农"));

		when(userService.list()).thenReturn(originalData);

		List<User> users = viewService.getUsersWithPhoneMarked(null);

		assertSame(1, users.size());
		assertSame("156", users.get(0).getMobile());

	}

	/**
	 * 测试：user数据返回第1条的mobile字段为不合法的null时，结果应该也是null
	 */
	@Test
	public void testWithNullPhoneData() {

		List<User> originalData = new ArrayList<>();
		originalData.add(new User(1, "howardzuo@examplepace.com", null, "大都督", "码农"));

		when(userService.list()).thenReturn(originalData);

		List<User> users = viewService.getUsersWithPhoneMarked(null);

		assertSame(1, users.size());
		assertNull(users.get(0).getMobile());

	}

	/**
	 * 测试：user数据返回仅有1条，但期望取前3条时，结果也应该只有1条，且mobile正确处理
	 */
	@Test
	public void testWithValidButNoEnoughData() {

		List<User> originalData = new ArrayList<>();
		originalData.add(new User(1, "howardzuo@examplepace.com", "15618922978", "大都督", "码农"));

		when(userService.list()).thenReturn(originalData);

		List<User> users = viewService.getUsersWithPhoneMarked(3);

		assertSame(1, users.size());
		assertEquals("15618******", users.get(0).getMobile());

	}

}