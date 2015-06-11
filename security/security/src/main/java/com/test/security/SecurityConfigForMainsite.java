package com.test.security;

import java.util.Map;
import java.util.ResourceBundle;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.test.framework.util.ResourceTextUtil;

@Configuration
public class SecurityConfigForMainsite extends BaseSecurityConfig {

	private static final ResourceBundle whiteListBundle = ResourceTextUtil
			.getResourceBundleByName("config.whitelistOfMainsite");

	private static final String whiteUrls = ResourceTextUtil.getTextFromResourceByKey(whiteListBundle, "urls");

	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean getShiroFilterFactoryBean() {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(getDefaultWebSecurityManager());
		shiroFilterFactoryBean.setLoginUrl("/login");

		this.doInitWhiteList(filterChainDefinitionMap);

		filterChainDefinitionMap.put("/**", "authc");

		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	private void doInitWhiteList(Map<String, String> filterChainDefinitionMap) {
		// whitelist
		String[] whiteList = this.getWhiteListArr();
		if (whiteList == null || whiteList.length <= 0) {
			return;
		}
		for (String url : whiteList) {
			filterChainDefinitionMap.put(url, "anon");
		}
	}

	private String[] getWhiteListArr() {
		if (StringUtils.isEmpty(whiteUrls)) {
			return null;
		}
		return whiteUrls.split("\\|");
	}

}
