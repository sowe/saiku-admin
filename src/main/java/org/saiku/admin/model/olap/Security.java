package org.saiku.admin.model.olap;

import java.util.Arrays;


public class Security {

	private String type;
	private String enable;
	private RoleMapping[] mapping;
	

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}
	public RoleMapping[] getMapping() {
		return mapping;
	}
	public void setMapping(RoleMapping[] mapping) {
		this.mapping = mapping;
	}
	
	
	@Override
    public String toString() {
        return "Security{" + "type=" + type + ", enable=" + enable+" mapping="+Arrays.toString(mapping)+"}";
    }
}
