package com.hxt.hPayurlCheck.model;

import java.util.Date;

import com.base.model.BaseModel;
/**
 * @author	wuzhenwei
 * @time	2015年11月28日 22:43:26
 */
public class HPayurlCheck extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private Integer id;
		private Integer c_id;
		private String check_no;
		private String check_url;
		private String pay_url;
		private String status;
		private Date create_date;
		private Date create_time;
		private Date check_time;
		private String open_ip;
		
		public Integer getId() {
			return id;
		}
	
		public void setId(Integer id) {
			this.id = id;
		}
		public Integer getC_id() {
			return c_id;
		}
	
		public void setC_id(Integer c_id) {
			this.c_id = c_id;
		}
		public String getCheck_no() {
			return check_no;
		}
	
		public void setCheck_no(String check_no) {
			this.check_no = check_no;
		}
		public String getCheck_url() {
			return check_url;
		}
	
		public void setCheck_url(String check_url) {
			this.check_url = check_url;
		}
		public String getPay_url() {
			return pay_url;
		}
	
		public void setPay_url(String pay_url) {
			this.pay_url = pay_url;
		}
		public String getStatus() {
			return status;
		}
	
		public void setStatus(String status) {
			this.status = status;
		}
		public Date getCreate_date() {
			return create_date;
		}
	
		public void setCreate_date(Date create_date) {
			this.create_date = create_date;
		}
		public Date getCreate_time() {
			return create_time;
		}
	
		public void setCreate_time(Date create_time) {
			this.create_time = create_time;
		}
		public Date getCheck_time() {
			return check_time;
		}
	
		public void setCheck_time(Date check_time) {
			this.check_time = check_time;
		}
		public String getOpen_ip() {
			return open_ip;
		}
	
		public void setOpen_ip(String open_ip) {
			this.open_ip = open_ip;
		}
}