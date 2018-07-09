package com.hxt.hRecRecord.model;

import java.util.Date;

import com.base.model.BaseModel;
/**
 * @author	wuzhenwei
 * @time	2016年09月05日 17:15:24
 */
public class HRecRecord extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private Integer id;
		private String ammeter_no;
		private String ammeter_name;
		private String ammeter_type;
		private Date create_time;
		private String ammeter_address;
		private Integer ammeterinfo_type;
		private String rec_phone;
		private String contact_phone;
		
		public String getContact_phone() {
			return contact_phone;
		}

		public void setContact_phone(String contact_phone) {
			this.contact_phone = contact_phone;
		}

		public Integer getId() {
			return id;
		}
	
		public void setId(Integer id) {
			this.id = id;
		}
		public String getAmmeter_no() {
			return ammeter_no;
		}
	
		public void setAmmeter_no(String ammeter_no) {
			this.ammeter_no = ammeter_no;
		}
		public String getAmmeter_name() {
			return ammeter_name;
		}
	
		public void setAmmeter_name(String ammeter_name) {
			this.ammeter_name = ammeter_name;
		}
		public String getAmmeter_type() {
			return ammeter_type;
		}
	
		public void setAmmeter_type(String ammeter_type) {
			this.ammeter_type = ammeter_type;
		}
		public Date getCreate_time() {
			return create_time;
		}
	
		public void setCreate_time(Date create_time) {
			this.create_time = create_time;
		}
		public String getAmmeter_address() {
			return ammeter_address;
		}
	
		public void setAmmeter_address(String ammeter_address) {
			this.ammeter_address = ammeter_address;
		}
		public Integer getAmmeterinfo_type() {
			return ammeterinfo_type;
		}
	
		public void setAmmeterinfo_type(Integer ammeterinfo_type) {
			this.ammeterinfo_type = ammeterinfo_type;
		}
		public String getRec_phone() {
			return rec_phone;
		}
	
		public void setRec_phone(String rec_phone) {
			this.rec_phone = rec_phone;
		}
}