package org.saiku.admin.model.mysql;

public class ExplainData {

	private String id;
	private String selectType;
	private String table;
	private String type;
	private String possibleKeys;
	private String key;
	private String keyLen;
	private String ref;
	private String rows;
	private String extra;


	public ExplainData(String id, String selectType, String table, String type,
			String possibleKeys, String key, String keyLen, String ref,
			String rows, String extra) {
		super();
		this.id = id;
		this.selectType = selectType;
		this.table = table;
		this.type = type;
		this.possibleKeys = possibleKeys;
		this.key = key;
		this.keyLen = keyLen;
		this.ref = ref;
		this.rows = rows;
		this.extra = extra;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSelectType() {
		return selectType;
	}
	public void setSelectType(String selectType) {
		this.selectType = selectType;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPossibleKeys() {
		return possibleKeys;
	}
	public void setPossibleKeys(String possibleKeys) {
		this.possibleKeys = possibleKeys;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getKeyLen() {
		return keyLen;
	}
	public void setKeyLen(String keyLen) {
		this.keyLen = keyLen;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public String getRows() {
		return rows;
	}
	public void setRows(String rows) {
		this.rows = rows;
	}
	public String getExtra() {
		return extra;
	}
	public void setExtra(String extra) {
		this.extra = extra;
	}
	
	
	
}
