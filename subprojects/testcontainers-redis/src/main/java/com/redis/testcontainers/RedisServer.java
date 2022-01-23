package com.redis.testcontainers;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.lifecycle.Startable;
import org.testcontainers.shaded.org.apache.commons.lang.ClassUtils;
import org.testcontainers.shaded.org.apache.commons.lang.StringUtils;

public interface RedisServer extends Startable {

	String getRedisURI();

	boolean isCluster();

	boolean isEnabled();

	static String redisURI(String host, int port) {
		return "redis://" + host + ":" + port;
	}

	static String redisURI(GenericContainer<?> container) {
		return redisURI(container.getHost(), container.getFirstMappedPort());
	}

	static String toString(RedisServer server) {
		return ClassUtils.getShortClassName(server.getClass());
	}

	static boolean isEnabled(String suffix) {
		String value = System.getenv("TESTCONTAINERS_" + suffix);
		// Containers are enabled by default
		if (StringUtils.isEmpty(value)) {
			return true;
		}
		return !value.toLowerCase().matches("disabled|off|false|no");
	}

}
