package org.springframework.influxdb;

import java.util.List;

import org.influxdb.InfluxDB;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;

public interface InfluxDBTemplate {

	String getInfluxDBVersion();
	
	public String[] getUdp();
	
	InfluxDB getInfluxDB();
	
	void insertBatchPoints(BatchPoints points);
	
	void insertData(String databaseName,String policyName,String insertStr);
	
	void insertData(String databaseName,String policyName,Point point);
	
	void insertDataAsArray(String databaseName,String policyName,List<String> list);
	
	void closeInfluxDB();
	
	boolean isExistsDatabase(String databaseName);
	
	void createDatabaseName(String databaseName);
	
	void createRetentionPolicyWithINF(String databaseName,String policy);
	
	void reConnect();
}
