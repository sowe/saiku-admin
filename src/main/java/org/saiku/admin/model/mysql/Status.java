package org.saiku.admin.model.mysql;

public class Status {

	private Integer uptime;
	private Integer threads;
	private Integer questions;
	private Integer slowQueries;
	private Integer opens;
	private Integer flushTables;
	private Integer openTables;
	private Double queriesPerSecondAvg;


	public Integer getUptime() {
		return uptime;
	}
	public void setUptime(Integer uptime) {
		this.uptime = uptime;
	}
	public Integer getThreads() {
		return threads;
	}
	public void setThreads(Integer threads) {
		this.threads = threads;
	}
	public Integer getQuestions() {
		return questions;
	}
	public void setQuestions(Integer questions) {
		this.questions = questions;
	}
	public Integer getSlowQueries() {
		return slowQueries;
	}
	public void setSlowQueries(Integer slowQueries) {
		this.slowQueries = slowQueries;
	}
	public Integer getOpens() {
		return opens;
	}
	public void setOpens(Integer opens) {
		this.opens = opens;
	}
	public Integer getFlushTables() {
		return flushTables;
	}
	public void setFlushTables(Integer flushTables) {
		this.flushTables = flushTables;
	}
	public Integer getOpenTables() {
		return openTables;
	}
	public void setOpenTables(Integer openTables) {
		this.openTables = openTables;
	}
	public Double getQueriesPerSecondAvg() {
		return queriesPerSecondAvg;
	}
	public void setQueriesPerSecondAvg(Double queriesPerSecondAvg) {
		this.queriesPerSecondAvg = queriesPerSecondAvg;
	}


	@Override
	public String toString() {
		return 	"StatusMysql{uptime="+uptime+", threads="+threads+", questions="+questions+", slowQueries="+slowQueries+", opens="+opens+", " +
				"flushTables="+flushTables+", openTables="+openTables+", queriesPerSecondAvg="+queriesPerSecondAvg+"}";
	}
}
