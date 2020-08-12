package toby.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import toby.jwt.common.dto.VerifyImageDto;
import toby.jwt.common.utils.BytesUtil;
import toby.jwt.common.utils.HMACUtil;
import toby.jwt.common.utils.VerifyImageGenerator;
import toby.jwt.common.vo.VerifyVo;
import toby.jwt.helper.VerfiyRedisBizHelper;

@RestController
@RequestMapping("/verify")
public class VerifyController {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@Autowired
	private VerfiyRedisBizHelper verfiyRedisBizHelper;
	
	@GetMapping("/image")
	public VerifyVo image(@RequestParam String userCode) {
		
		VerifyImageDto verifyImageDto = VerifyImageGenerator.creatImageVerify(60, 45, 5);
		String sign = HMACUtil.encryptHmacSHA512(verifyImageDto.getImage(), "my secret".getBytes());
		String imageBase64String = BytesUtil.bytesToBase64Hex(verifyImageDto.getImage());
		verfiyRedisBizHelper.saveMobileSignupVerifyImageCode(userCode, verifyImageDto.getRandomString(), 1000L);
		
		return new VerifyVo(sign, imageBase64String);
	}
	
}
