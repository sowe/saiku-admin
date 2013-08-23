package org.saiku.admin.model;

public class QueryBBDD {

	private String query;

	public QueryBBDD(String _query) {
		this.query = _query;
	}
	
	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}


	@Override
	public String toString() {
		return "QueryMysql{query="+query+"}";
	}
}
