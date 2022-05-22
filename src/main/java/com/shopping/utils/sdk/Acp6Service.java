package com.shopping.utils.sdk;

import java.io.IOException;
import java.security.PublicKey;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;



/**
 * @ClassName Acp6Service
 * @Description 全渠道6.0接口服务类，接入商户集成请可以直接参考使用本类中的方法
 * @date 2020/03
 * 声明：以下代码只是为了方便接入方测试而提供的样例代码，商户可以根据自己需要，按照技术文档编写。该代码仅供参考，不提供编码，性能，规范性等方面的保障
 */
public class Acp6Service {


	/**
	 * 请求报文签名(使用配置文件中配置的私钥证书或者对称密钥签名)<br>
	 * 功能：对请求报文进行签名,并计算赋值certid,signature字段并返回<br>
	 * @param reqData 请求报文map<br>
	 * @param encoding 上送请求报文域encoding字段的值<br>
	 * @return　签名后的map对象<br>
	 */
	public static Map<String, String> sign(Map<String, String> reqData,String encoding) {
        return signByCertInfo(reqData, SDKConfig.getConfig().getSignCertPath(), SDKConfig.getConfig().getSignCertPwd(), encoding);
	}
	
	/**
	 * 多证书签名(通过传入私钥证书路径和密码签名）<br>
	 * 功能：如果有多个商户号接入银联,每个商户号对应不同的证书可以使用此方法:传入私钥证书和密码(并且在acp_sdk.properties中 配置 acpsdk.singleMode=false)<br>
	 * @param reqData 请求报文map<br>
	 * @param certPath 签名私钥文件（带路径）<br>
	 * @param certPwd 签名私钥密码<br>
	 * @param encoding 上送请求报文域encoding字段的值<br>
	 * @return　签名后的map对象<br>
	 */
	public static Map<String, String> signByCertInfo(Map<String, String> reqData, String certPath, 
			String certPwd, String encoding) {

        Map<String, String> data = SDKUtil.filterBlank(reqData);

        if (SDKUtil.isEmpty(encoding)) {
            encoding = "UTF-8";
        }
        
        if (SDKUtil.isEmpty(certPath) || SDKUtil.isEmpty(certPwd)) {
            return data;
        }
        
        try {
			data.put(SDKConstants.param_certId, CertUtil.getCertIdByKeyStoreMap(certPath, certPwd));
			data.put(SDKConstants.param_signature, SDKUtil.signRsa2(data, certPath, certPwd, encoding));
			return data;
        } catch (Exception e) {
            return data;
        }
	}

    /**
	 * 验证签名<br>
	 * @param data 返回报文数据<br>
	 * @param encoding 上送请求报文域encoding字段的值<br>
	 * @return true 通过 false 未通过<br>
	 */
	public static boolean validate(Map<String, String> data, String encoding) {
        if (SDKUtil.isEmpty(encoding)) {
            encoding = "UTF-8";
        }
        
        String certId = data.get(SDKConstants.param_certId);
        PublicKey verifyKey = CertUtil.getValidatePublicKey(certId);
        if(verifyKey == null) {
            return false;
        }
        
        try {
			boolean result = SDKUtil.verifyRsa2(data, verifyKey, encoding);
			return result;
        } catch (Exception e) {
            return false;
        }
	}

    /**
     * 获取应答报文中的加密公钥证书,并存储到本地,备份原始证书,并自动替换证书<br>
     * 更新成功则返回1，无更新返回0，失败异常返回-1<br>
     * @return
     */
    public static int updateEncryptCert(String strCert, String certType) {
        return SDKUtil.updateEncryptCert(strCert, certType);
    }

	/**
	 * 密码加密并做base64<br>
	 * @param accNo 卡号<br>
	 * @param pin 密码<br>
	 * @param encoding<br>
	 * @return 加密的内容<br>
	 */
	public static String encryptPin(String accNo, String pin, String encoding) {
		byte[] pinblock = SecureUtil.pinblock(accNo, pin);
		return Base64.encodeBase64String(SecureUtil.encrypt(CertUtil.getPinEncryptCert().pubKey, pinblock));
	}

//	/**
//	 * 密码加密并做base64<br>
//	 * @param accNo 卡号<br>
//	 * @param pin 密码<br>
//	 * @param encoding<br>
//	 * @return 加密的内容<br>
//	 */
//	public static String encryptPin(String pin, String encoding) {
//		byte[] pinblock = SecureUtil.pinblock(pin);
//		return Base64.encodeBase64String(SecureUtil.encrypt(CertUtil.getPinEncryptCert().pubKey, pinblock));
//	}
	
	/**
	 * 敏感信息加密并做base64(卡号，手机号，cvn2,有效期）<br>
	 * @param data 送 phoneNo,cvn2,有效期<br>
	 * @param encoding<br>
	 * @return 加密的密文<br>
	 */
	public static String encryptData(String data, String encoding) {
        return AcpService.encryptData(data, encoding);
    }

	
	/**
	 * @param data 明文<br>
	 * @return 加密的密文<br>
	 */
	public static String encryptData(byte[] data) {
        try {
            return Base64.encodeBase64String(SecureUtil.encrypt(CertUtil.getEncryptCert().pubKey, data));
        } catch (Exception e) {
            return null;
        }
    }

	
	/**
	 * @param data 明文<br>
	 * @return 加密的密文<br>
	 */
	public static String tripleDesEncryptECBPKCS5Padding(byte[] key, byte[] data) {
        try {
            return Base64.encodeBase64String(SecureUtil.tripleDesEncryptECBPKCS5Padding(key, SecureUtil.rightPadZero(data, 8)));
        } catch (Exception e) {
            return null;
        }
    }
	
	/**
	 * 敏感信息解密，使用配置文件acp_sdk.properties解密<br>
	 * @param base64EncryptedInfo 加密信息<br>
	 * @param encoding<br>
	 * @return 解密后的明文<br>
	 */
	public static String decryptData(String base64EncryptedInfo, String encoding) {
        return AcpService.decryptData(base64EncryptedInfo, encoding);
    }
	
	/**
	 * 敏感信息解密,通过传入的私钥解密<br>
	 * @param base64EncryptedInfo 加密信息<br>
	 * @param certPath 私钥文件（带全路径）<br>
	 * @param certPwd 私钥密码<br>
	 * @param encoding<br>
	 * @return
	 */
	public static String decryptData(String base64EncryptedInfo, String certPath, 
			String certPwd, String encoding) {
        return AcpService.decryptData(base64EncryptedInfo, certPath, certPwd, encoding);
	}
	
	/**
	 * 获取敏感信息加密证书的物理序列号<br>
	 * @return
	 */
	public static String getEncryptCertId(){
		return CertUtil.getEncryptCert().certId;
	}
	
	/**
	 * 获取敏感信息加密证书的物理序列号<br>
	 * @return
	 */
	public static String getPinEncryptCertId(){
		return CertUtil.getPinEncryptCert().certId;
	}
	
	
	/**
	 * 功能：后台交易提交请求报文并接收同步应答报文<br>
	 * @param reqData 请求报文<br>
	 * @param reqUrl  请求地址<br>
	 * @param encoding<br>
	 * @return 应答http 200返回true ,其他false<br>
	 */
	public static Map<String,String> post(Map<String, String> reqData, String reqUrl,String encoding) {
		if(reqData == null || reqUrl == null) {
			return null;
		}
		if(reqUrl.startsWith("https://") && !SDKConfig.getConfig().isIfValidateRemoteCert()) {
			reqUrl = "u" + reqUrl;
		}
		try{
			byte[] respBytes = HttpsUtil.post(reqUrl, SDKUtil.createLinkString(reqData, false, true, encoding).getBytes(encoding));
			if(respBytes == null) {
				return null;
			}
			Map<String,String> result = SDKUtil.parseQString(new String(respBytes, encoding), encoding);
			return result;
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 功能：后台交易提交请求报文并接收同步应答报文<br>
	 * @param reqData 请求报文<br>
	 * @param reqUrl  请求地址<br>
	 * @param encoding<br>
	 * @return 应答http 200返回true ,其他false<br>
	 */
	public static String postNotice(Map<String, String> reqData, String reqUrl,String encoding) {
		if(reqData == null || reqUrl == null) {
			return null;
		}
		if(reqUrl.startsWith("https://") && !SDKConfig.getConfig().isIfValidateRemoteCert()) {
			reqUrl = "u" + reqUrl;
		}
		try{
			byte[] respBytes = HttpsUtil.post(reqUrl, SDKUtil.createLinkString(reqData, false, true, encoding).getBytes(encoding));
			if(respBytes == null) {
				return null;
			}
			String result = new String(respBytes, encoding);
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 对字符串做base64<br>
	 * @param rawStr<br>
	 * @param encoding<br>
	 * @return<br>
	 * @throws IOException
	 */
	public static String base64Encode(String rawStr, String encoding){
		return AcpService.base64Encode(rawStr, encoding);
	}

	/**
	 * 对字符串做base64<br>
	 * @param base64Str<br>
	 * @param encoding<br>
	 * @return<br>
	 * @throws IOException
	 */
	public static String base64Decode(String base64Str, String encoding){
		return AcpService.base64Decode(base64Str, encoding);
	}
}
