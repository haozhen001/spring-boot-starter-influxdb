package org.springframework.influxdb;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * influxdb autoConfiguration
 * @author haozhen
 *
 */
@Configuration
@EnableConfigurationProperties(InfluxDBProperties.class)
@ConditionalOnClass(InfluxDB.class)
@ConditionalOnProperty(prefix="spring.influxdb",value="enabled",matchIfMissing=true)
public class InfluxDBAutoConfiguration {
	
	@Autowired
	private InfluxDBProperties influxdbProperties;
	
	@Bean
	@ConditionalOnMissingBean(InfluxDB.class)
	public InfluxDB getInfluxDB(){
		System.out.println("-------------------------------");
		System.out.println("create bean influxdb");
			
		InfluxDB influxdb = null;
		try {
			influxdb = InfluxDBFactory.connect(getUrl(), 
					influxdbProperties.getUsername(), influxdbProperties.getPassword());
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			System.out.println("catch exception error host in influxdb");
		}
		return influxdb;
	}
	private String getUrl(){
		return "http://"+influxdbProperties.getIp()+":"+influxdbProperties.getPort();
	}
	
	@Bean
	@ConditionalOnBean(InfluxDB.class)
	public InfluxDBTemplate getInfluxDBTemplate(InfluxDB influxDB){
		InfluxDBTemplateImpl influxDBTemplateImpl =  new InfluxDBTemplateImpl();
		influxDBTemplateImpl.setInfluxDB(influxDB);
		influxDBTemplateImpl.setUdp(influxdbProperties.getDupport().split(","));
		return influxDBTemplateImpl;
		
	}
	
	

}
