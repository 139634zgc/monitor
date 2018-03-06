package com.bitauto.bdc.modules.sys.controller;

import com.bitauto.bdc.common.utils.R;
import com.bitauto.bdc.common.utils.ShiroUtils;
import com.bitauto.bdc.modules.login.LoginResult;
import com.bitauto.bdc.modules.sys.entity.SysUserEntity;
import com.bitauto.bdc.modules.sys.service.SysUserService;
import com.bitauto.bdc.modules.sys.service.SysUserTokenService;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

import org.apache.commons.io.IOUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

/**
 * 登录相关
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月10日 下午1:15:31
 */
@RestController
public class SysLoginController extends AbstractController {
	@Autowired
	private Producer producer;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserTokenService sysUserTokenService;

	private static final Logger LOG = LoggerFactory.getLogger(SysLogController.class);


	@RequestMapping("captcha.jpg")
	public void captcha(HttpServletResponse response)throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-store, no-cache");
		response.setContentType("image/jpeg");

		//生成文字验证码
		String text = producer.createText();
		//生成图片验证码
		BufferedImage image = producer.createImage(text);
		//保存到shiro session
		ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);

		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);
		IOUtils.closeQuietly(out);
	}

	/**
	 * 登录
	 */
	@RequestMapping(value = "/sys/login", method = RequestMethod.POST)
	public Map<String, Object> login(String username, String password, String captcha)throws IOException {
		String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
		if(!captcha.equalsIgnoreCase(kaptcha)){
			return R.error("验证码不正确");
		}

		//用户信息
		SysUserEntity user = sysUserService.queryByUserName(username);

		//账号不存在、密码错误
		if(user == null) {
			return R.error("账号不存在");
		}

		if(username.equals("admin")) {
			if(!user.getPassword().equals(new Sha256Hash(password, user.getSalt()).toHex())) {
				return R.error("密码错误");
			}
		} else {
			LoginResult loginResult;
			try
			{
				loginResult = sysUserService.loginYiche(username, password);
			}
			catch (Exception e)
			{
				LOG.error("log yiche error, exception:{}", e);
				return R.error("登录异常");
			}

			if (loginResult != LoginResult.SUCCESS)
			{
				LOG.error("log yiche account or password error, account:{}, value:{}", username, loginResult.value());
				return R.error("账号或密码错误");
			}
		}

		//账号锁定
		if(user.getStatus() == 0){
			return R.error("账号已被锁定,请联系管理员");
		}

		//生成token，并保存到数据库
		R r = sysUserTokenService.createToken(user.getUserId());
		return r;
	}

	/**
	 * 退出
	 */
	@RequestMapping(value = "/sys/logout", method = RequestMethod.POST)
	public R logout() {
		sysUserTokenService.logout(getUserId());
		return R.ok();
	}
	
}
