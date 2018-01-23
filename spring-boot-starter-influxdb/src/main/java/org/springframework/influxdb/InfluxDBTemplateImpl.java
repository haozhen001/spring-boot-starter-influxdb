package org.springframework.influxdb;

import java.util.List;

import org.influxdb.InfluxDB;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;


public class InfluxDBTemplateImpl implements InfluxDBTemplate{

	private String[] udp;
	
	private InfluxDB influxDB;

	@Override
	public String[] getUdp() {
		return udp;
	}

	
	public void setUdp(String[] udp) {
		this.udp = udp;
	}

	@Override
	public InfluxDB getInfluxDB() {
		return influxDB;
	}

	
	public void setInfluxDB(InfluxDB influxDB) {
		this.influxDB = influxDB;
	}
	@Override
	public String getInfluxDBVersion() {
		return influxDB.ping().getVersion();
	}
	@Override
	public void insertBatchPoints(BatchPoints points) {
		influxDB.write(points);
	}
	@Override
	public void insertData(String databaseName, String policyName,
			String insertStr) {
		influxDB.write(databaseName, policyName,InfluxDB.ConsistencyLevel.ONE,insertStr);
	}


	@Override
	public void insertData(String databaseName, String policyName, Point point) {
		influxDB.write(databaseName, policyName,point);
	}


	@Override
	public void insertDataAsArray(String databaseName, String policyName,
			List<String> list) {
		influxDB.write(databaseName, policyName,InfluxDB.ConsistencyLevel.ONE,list);
		
	}


	@Override
	public void closeInfluxDB() {
		influxDB.close();
	}
	
	


	@Override
	public boolean isExistsDatabase(String databaseName) {
		if(databaseName!=null){
			List<String> databaseNames	=	influxDB.describeDatabases();
			 for(String name:databaseNames){
				 if(name.equals(databaseName)){
					 return true;
				 }
			 }
			}
		return false;
	}


	@Override
	public void createDatabaseName(String databaseName) {
//		if(!isExistsDatabase(databaseName))
			influxDB.createDatabase(databaseName);
	}


	@Override
	public void createRetentionPolicyWithINF(String databaseName, String policy) {
        String command = String.format("CREATE RETENTION POLICY \"%s\" ON \"%s\" DURATION %s REPLICATION %s DEFAULT",
        		policy, databaseName, "INF", "168h");
        query(command, databaseName);
	}
	
	public void query(String command,String databaseName){
		  influxDB.query(new Query(command, databaseName));
	}


	@Override
	public void reConnect() {
		
	}
}
