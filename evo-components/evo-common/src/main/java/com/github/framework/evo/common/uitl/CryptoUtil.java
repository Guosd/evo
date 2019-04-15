package com.github.framework.evo.common.uitl;

import com.github.framework.evo.common.Const;
import com.github.framework.evo.common.exception.CryptoOperateException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.crypto.cipher.CryptoCipher;
import org.apache.commons.crypto.utils.Utils;
import org.springframework.util.Base64Utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * User: Kyll
 * Date: 2019-04-12 09:33
 */
@Slf4j
public class CryptoUtil {
	private static final Map<String, SecretKeySpec> SECRET_KEY_SPEC_MAP = new HashMap<>();
	private static final Map<String, IvParameterSpec> IV_PARAMETER_SPEC_MAP = new HashMap<>();

	public static String encryptAes256(String content, String secretKey) {
		SecretKeySpec secretKeySpec = createAes256SecretKey(secretKey);
		AlgorithmParameterSpec algorithmParameterSpec = createAes256IvParameterSpec(Const.CRYPTO_AES256_IV_PARAMETER_SPEC);

		ByteBuffer outBuffer = ByteBuffer.allocateDirect(Const.BUFFER_SIZE_1024);
		int updateBytes;
		int finalBytes;
		try (CryptoCipher cryptoCipher = Utils.getCipherInstance(Const.CRYPTO_AES256_TRANSFORMATION, new Properties())) {
			ByteBuffer inBuffer = ByteBuffer.allocateDirect(Const.BUFFER_SIZE_1024);

			inBuffer.put(StringUtil.toUTF8Byte(content));
			inBuffer.flip();

			cryptoCipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, algorithmParameterSpec);

			updateBytes = cryptoCipher.update(inBuffer, outBuffer);
			finalBytes = cryptoCipher.doFinal(inBuffer, outBuffer);
		} catch (IOException | InvalidKeyException | ShortBufferException | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException e) {
			log.error("Aes256加密失败", e);

			throw new CryptoOperateException("Aes256加密失败", e);
		}

		byte[] encoded = new byte[updateBytes + finalBytes];
		outBuffer.flip();
		outBuffer.duplicate().get(encoded);
		return Base64Utils.encodeToString(encoded);
	}

	public static String decryptAes256(String content, String secretKey) {
		SecretKeySpec secretKeySpec = createAes256SecretKey(secretKey);
		AlgorithmParameterSpec algorithmParameterSpec = createAes256IvParameterSpec(Const.CRYPTO_AES256_IV_PARAMETER_SPEC);

		ByteBuffer outBuffer;
		ByteBuffer decoded = ByteBuffer.allocateDirect(Const.BUFFER_SIZE_1024);
		try (CryptoCipher cryptoCipher = Utils.getCipherInstance(Const.CRYPTO_AES256_TRANSFORMATION, new Properties())) {
			cryptoCipher.init(Cipher.DECRYPT_MODE, secretKeySpec, algorithmParameterSpec);

			outBuffer = ByteBuffer.allocateDirect(Const.BUFFER_SIZE_1024);
			outBuffer.put(Base64Utils.decode(StringUtil.toUTF8Byte(content)));
			outBuffer.flip();

			cryptoCipher.update(outBuffer, decoded);
			cryptoCipher.doFinal(outBuffer, decoded);

			decoded.flip();
		} catch (IOException | InvalidKeyException | ShortBufferException | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException e) {
			log.error("Aes256解密失败", e);
		}

		ByteBuffer copy = decoded.duplicate();
		byte[] bytes = new byte[copy.remaining()];
		copy.get(bytes);
		return new String(bytes, StandardCharsets.UTF_8);
	}

	private static SecretKeySpec createAes256SecretKey(String secretKey) {
		SecretKeySpec secretKeySpec = SECRET_KEY_SPEC_MAP.get(secretKey);
		if (secretKeySpec == null) {
			KeyGenerator keyGenerator;
			try {
				keyGenerator = KeyGenerator.getInstance("AES");
			} catch (NoSuchAlgorithmException e) {
				throw new CryptoOperateException("AES算法不存在", e);
			}
			keyGenerator.init(256, new SecureRandom(StringUtil.toUTF8Byte(secretKey)));
			secretKeySpec = new SecretKeySpec(keyGenerator.generateKey().getEncoded(), "AES");
		}
		return secretKeySpec;
	}

	private static IvParameterSpec createAes256IvParameterSpec(String iv) {
		IvParameterSpec ivParameterSpec = IV_PARAMETER_SPEC_MAP.get(iv);
		if (ivParameterSpec == null) {
			ivParameterSpec = new IvParameterSpec(StringUtil.toUTF8Byte(iv));
		}
		return ivParameterSpec;
	}

	public static void main(String[] args) {
		System.out.println(AlgorithmUtil.random(32));
	}
}
