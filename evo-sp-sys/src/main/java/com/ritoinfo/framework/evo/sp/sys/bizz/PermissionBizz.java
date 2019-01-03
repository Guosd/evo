package com.ritoinfo.framework.evo.sp.sys.bizz;

import com.ritoinfo.framework.evo.sp.sys.condition.PermissionCondition;
import com.ritoinfo.framework.evo.sp.sys.dto.PermissionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.AntPathMatcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: Kyll
 * Date: 2019-01-02 14:50
 */
@Slf4j
@Transactional(readOnly = true)
@Service
public class PermissionBizz {
	private static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();
	private static final String REGEX = "\\{(.*?)\\}";

	@Autowired
	private FuncBizz funcBizz;

	public boolean validate(PermissionCondition condition) {
		boolean result = false;
		for (PermissionDto permissionDto : funcBizz.findByPermission(condition)) {
			result = ANT_PATH_MATCHER.match(extractAntPath(permissionDto.getPrefix() + permissionDto.getUri()), condition.getUri());
			if (result) {
				break;
			}
		}
		return result;
	}

	private String extractAntPath(String path) {
		Matcher matcher = Pattern.compile(REGEX).matcher(path);

		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, "**");
		}
		matcher.appendTail(sb);

		return sb.toString();
	}
}
