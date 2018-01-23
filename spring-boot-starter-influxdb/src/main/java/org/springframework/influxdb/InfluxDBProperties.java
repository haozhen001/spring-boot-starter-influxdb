package org.springframework.influxdb;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="spring.influxdb")
public class InfluxDBProperties {
	
	private String ip="127.0.0.1";
	private String port="8086";
	private String username="root";
	private String password="root";
	private String dupport="8089";
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDupport() {
		return dupport;
	}
	public void setDupport(String dupport) {
		this.dupport = dupport;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	
	
}
