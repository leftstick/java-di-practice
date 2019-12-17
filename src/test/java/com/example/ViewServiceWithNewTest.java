package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.example.entities.User;
import com.example.services.ViewService2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class ViewServiceWithNewTest {

	@Autowired
	ViewService2 viewService;

	/**
	 * 测试：user数据都是合理正确的，并且有6条数据，并取出全部数据进行验证
	 */
	@Test
	public void testWithAllValidData() {

		List<User> users = viewService.getUsersWithPhoneMarked(null);

		assertSame(6, users.size());
		assertTrue(users.get(0).getMobile().endsWith("******"));
	}

	/**
	 * 测试：user数据都是合理正确的，并且有6条数据，并取出前1条数据进行验证
	 */
	@Test
	public void testWithAllValidDataButLimited() {

		List<User> users = viewService.getUsersWithPhoneMarked(1);

		assertSame(1, users.size());
		assertTrue(users.get(0).getMobile().endsWith("******"));
	}

	/**
	 * 测试：user数据返回null时，结果应该转换成EmptyList
	 */
	@Test
	public void testWithNullData() {

		List<User> users = viewService.getUsersWithPhoneMarked(null);

		assertSame(0, users.size());
	}

	/**
	 * 测试：user数据返回第1条的mobile字段为不合法的156时，结果应该没有*，依旧是156
	 */
	@Test
	public void testWithInvalidData() {

		List<User> users = viewService.getUsersWithPhoneMarked(null);

		assertSame(1, users.size());
		assertSame("156", users.get(0).getMobile());

	}

	/**
	 * 测试：user数据返回第1条的mobile字段为不合法的null时，结果应该也是null
	 */
	@Test
	public void testWithNullPhoneData() {

		List<User> users = viewService.getUsersWithPhoneMarked(null);

		assertSame(1, users.size());
		assertNull(users.get(0).getMobile());

	}

	/**
	 * 测试：user数据返回仅有1条，但期望取前3条时，结果也应该只有1条，且mobile正确处理
	 */
	@Test
	public void testWithValidButNoEnoughData() {

		List<User> users = viewService.getUsersWithPhoneMarked(3);

		assertSame(1, users.size());
		assertEquals("15618******", users.get(0).getMobile());

	}

}