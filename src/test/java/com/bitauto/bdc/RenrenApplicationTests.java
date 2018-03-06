package com.bitauto.bdc;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bitauto.bdc.common.utils.RedisUtils;
import com.bitauto.bdc.modules.sys.entity.SysUserEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RenrenApplicationTests {
	@Autowired
	private RedisUtils redisUtils;

	@Test
	public void contextLoads() {
		SysUserEntity user = new SysUserEntity();
		user.setEmail("qqq@qq.com");
		redisUtils.set("user", user);

		System.out.println(ToStringBuilder.reflectionToString(redisUtils.get("user", SysUserEntity.class)));
	}

}
