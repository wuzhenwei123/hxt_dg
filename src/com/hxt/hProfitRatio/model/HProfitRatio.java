package com.hxt.hProfitRatio.model;

import java.util.Date;

import com.base.model.BaseModel;
/**
 * @author	zlb
 * @time	2016年08月04日 21:43:46
 */
public class HProfitRatio extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private Integer id;
		private String name;
		private String manager_ratio;
		private String ont_agent_ratio;
		private String two_agent_ratio;
		private String personal_ratio;
		private Integer is_default;
		private Integer state;
		
		public Integer getId() {
			return id;
		}
	
		public void setId(Integer id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
	
		public void setName(String name) {
			this.name = name;
		}
		public String getManager_ratio() {
			return manager_ratio;
		}
	
		public void setManager_ratio(String manager_ratio) {
			this.manager_ratio = manager_ratio;
		}
		public String getOnt_agent_ratio() {
			return ont_agent_ratio;
		}
	
		public void setOnt_agent_ratio(String ont_agent_ratio) {
			this.ont_agent_ratio = ont_agent_ratio;
		}
		public String getTwo_agent_ratio() {
			return two_agent_ratio;
		}
	
		public void setTwo_agent_ratio(String two_agent_ratio) {
			this.two_agent_ratio = two_agent_ratio;
		}
		public String getPersonal_ratio() {
			return personal_ratio;
		}
	
		public void setPersonal_ratio(String personal_ratio) {
			this.personal_ratio = personal_ratio;
		}
		public Integer getIs_default() {
			return is_default;
		}
	
		public void setIs_default(Integer is_default) {
			this.is_default = is_default;
		}
		public Integer getState() {
			return state;
		}
	
		public void setState(Integer state) {
			this.state = state;
		}
}