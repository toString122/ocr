package com.liwj.ocr.utils;



import com.liwj.ocr.pojo.TextPro;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public  class WebOCR {
	// OCR webapi 接口地址
	private static final String WEBOCR_URL = "https://webapi.xfyun.cn/v1/service/v1/ocr/general";
	// 应用ID (必须为webapi类型应用，并印刷文字识别服务，参考帖子如何创建一个webapi应用：http://bbs.xfyun.cn/forum.php?mod=viewthread&tid=36481)
	private static final String APPID = "d845e644";
	// 接口密钥(webapi类型应用开通印刷文字识别服务后，控制台--我的应用---印刷文字识别---服务的apikey)
	private static final String API_KEY = "c952293c733a9ab81ca6672396dfa2a0";
	// 是否返回位置信息
	private static final String LOCATION = "false";
	// 语种(可选值：en（英文），cn|en（中文或中英混合)
	private static final String LANGUAGE = "cn|en";
	// 图片地址,图片最短边至少15px，最长边最大4096px，格式jpg、png、bmp
//	private static final String PIC_PATH = "C:\\Users\\liwj\\Desktop\\wx.jpg";

	/**
	 * OCR WebAPI
	 * @throws IOException
	 */
	public static TextPro  getTextPro(String PIC_PATH) throws IOException {
		Map<String, String> header = buildHttpHeader();
		byte[] imageByteArray = FileUtil.read(PIC_PATH);
		String imageBase64 = new String(Base64.encodeBase64(imageByteArray), StandardCharsets.UTF_8);
		String result = HttpUtil.doPost1(WEBOCR_URL, header, "image=" + URLEncoder.encode(imageBase64, "UTF-8"));
		JSONObject obj = JSONObject.fromObject(result);
		return (TextPro)JSONObject.toBean(obj,TextPro.class);
	}

	/**
	 * 组装http请求头
	 */
	private static Map<String, String> buildHttpHeader() throws UnsupportedEncodingException {
		String curTime = System.currentTimeMillis() / 1000L + "";
		String param = "{\"location\":\"" + LOCATION + "\",\"language\":\"" + LANGUAGE + "\"}";
		String paramBase64 = new String(Base64.encodeBase64(param.getBytes(StandardCharsets.UTF_8)));
		String checkSum = DigestUtils.md5Hex(API_KEY + curTime + paramBase64);
		Map<String, String> header = new HashMap<String, String>();
		header.put("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
		header.put("X-Param", paramBase64);
		header.put("X-CurTime", curTime);
		header.put("X-CheckSum", checkSum);
		header.put("X-Appid", APPID);
		return header;
	}
}