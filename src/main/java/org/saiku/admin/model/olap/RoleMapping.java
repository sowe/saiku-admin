package org.saiku.admin.model.olap;

import java.util.Arrays;


public class RoleMapping {

	private String roleSpring;
	private String[] rolesSaiku;


	public String getRoleSpring() {
		return roleSpring;
	}

	public void setRoleSpring(String roleSpring) {
		this.roleSpring = roleSpring;
	}

	public String[] getRolesSaiku() {
		return rolesSaiku;
	}

	public void setRolesSaiku(String[] rolesSaiku) {
		this.rolesSaiku = rolesSaiku;
	}


	@Override
    public String toString() {
        return "RoleMapping{" + "roleSpring=" + roleSpring + ", rolesSaiku=" + Arrays.toString(rolesSaiku)+"}";
    }
}
