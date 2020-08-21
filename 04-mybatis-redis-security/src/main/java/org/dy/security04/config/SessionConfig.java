package org.dy.security04.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author caiwl
 * @date 2020/8/21 16:42
 */
@Configurable
@EnableRedisHttpSession
public class SessionConfig {
}
