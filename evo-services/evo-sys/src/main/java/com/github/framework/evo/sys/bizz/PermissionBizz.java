package com.github.framework.evo.sys.bizz;

import com.github.framework.evo.sys.condition.PermissionCondition;
import com.github.framework.evo.sys.dto.PermissionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: Kyll
 * Date: 2019-01-02 14:50
 */
@Slf4j
@Service
public class PermissionBizz {
	private static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();
	private static final String REGEX = "\\{(.*?)\\}";

	@Autowired
	private FuncBizz funcBizz;

	public boolean check(PermissionCondition condition) {
		boolean result = false;
		for (PermissionDto permissionDto : funcBizz.findByPermission(condition)) {
			result = ANT_PATH_MATCHER.match(convertToAntPath(permissionDto.getPrefix() + permissionDto.getUri()), condition.getUri());
			if (result) {
				break;
			}
		}
		return result;
	}

	/**
	 * 将 /xxxx/yyyy/{id} 替换为 /xxxx/yyyy/**
	 * @param path URI
	 * @return ANT 路径
	 */
	private String convertToAntPath(String path) {
		Matcher matcher = Pattern.compile(REGEX).matcher(path);

		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, "**");
		}
		matcher.appendTail(sb);

		return sb.toString();
	}
}
