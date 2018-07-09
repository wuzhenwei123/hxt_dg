package com.hxt.hAgent.model;

import java.util.Date;

import com.base.model.BaseModel;
/**
 * @author	zlb
 * @time	2016年08月07日 21:47:54
 */
public class HAgent extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private Integer id;
		private String openId;
		private String name;
		private String licence_url;
		private String licence_code;
		private String tax_url;
		private String legal_person;
		private Integer card_style;
		private String card_no;
		private Integer sex;
		private String mobile1;
		private String mobile2;
		private Integer style;
		private Integer status;
		private Date create_time;
		private String create_openId;
		private Integer check_status;
		private String contact_person;
		private String tax_code;
		private String bank_name;
		private String bank_number;
		private String bank_account;
		private String contract_number;
		private Date contract_start_time;
		private Date contract_end_time;
		private String remark1;
		private String remark2;
		private String remark3;
		private String user_name;//登录账号
		
		private Integer gl_yl_id;//银联鼓励金
		private String gl_yl_name;//银联鼓励金
		
		
		private Integer gl_sj_id;//手机鼓励金
		private String gl_sj_name;//手机鼓励金
		
		private Integer gl_yl_type;//银联鼓励金
		private Double gl_yl_fee;//银联鼓励金
		private Double gl_yl_rate;//银联鼓励金
		private Integer gl_sj_type;//手机鼓励金
		private Double gl_sj_fee;//手机鼓励金
		private Double gl_sj_rate;//手机鼓励金
		
		private Integer gl_yl_id1;//银联鼓励金
		private Integer gl_sj_id1;//手机鼓励金
		
		public Integer getGl_yl_id1() {
			return gl_yl_id1;
		}

		public void setGl_yl_id1(Integer gl_yl_id1) {
			this.gl_yl_id1 = gl_yl_id1;
		}

		public Integer getGl_sj_id1() {
			return gl_sj_id1;
		}

		public void setGl_sj_id1(Integer gl_sj_id1) {
			this.gl_sj_id1 = gl_sj_id1;
		}

		public Integer getGl_yl_type() {
			return gl_yl_type;
		}

		public void setGl_yl_type(Integer gl_yl_type) {
			this.gl_yl_type = gl_yl_type;
		}

		public Double getGl_yl_fee() {
			return gl_yl_fee;
		}

		public void setGl_yl_fee(Double gl_yl_fee) {
			this.gl_yl_fee = gl_yl_fee;
		}

		public Double getGl_yl_rate() {
			return gl_yl_rate;
		}

		public void setGl_yl_rate(Double gl_yl_rate) {
			this.gl_yl_rate = gl_yl_rate;
		}

		public Integer getGl_sj_type() {
			return gl_sj_type;
		}

		public void setGl_sj_type(Integer gl_sj_type) {
			this.gl_sj_type = gl_sj_type;
		}

		public Double getGl_sj_fee() {
			return gl_sj_fee;
		}

		public void setGl_sj_fee(Double gl_sj_fee) {
			this.gl_sj_fee = gl_sj_fee;
		}

		public Double getGl_sj_rate() {
			return gl_sj_rate;
		}

		public void setGl_sj_rate(Double gl_sj_rate) {
			this.gl_sj_rate = gl_sj_rate;
		}

		public Integer getGl_yl_id() {
			return gl_yl_id;
		}

		public void setGl_yl_id(Integer gl_yl_id) {
			this.gl_yl_id = gl_yl_id;
		}

		public String getGl_yl_name() {
			return gl_yl_name;
		}

		public void setGl_yl_name(String gl_yl_name) {
			this.gl_yl_name = gl_yl_name;
		}

		public Integer getGl_sj_id() {
			return gl_sj_id;
		}

		public void setGl_sj_id(Integer gl_sj_id) {
			this.gl_sj_id = gl_sj_id;
		}

		public String getGl_sj_name() {
			return gl_sj_name;
		}

		public void setGl_sj_name(String gl_sj_name) {
			this.gl_sj_name = gl_sj_name;
		}

		public String getUser_name() {
			return user_name;
		}

		public void setUser_name(String user_name) {
			this.user_name = user_name;
		}

		public Integer getId() {
			return id;
		}
	
		public void setId(Integer id) {
			this.id = id;
		}
		public String getOpenId() {
			return openId;
		}
	
		public void setOpenId(String openId) {
			this.openId = openId;
		}
		public String getName() {
			return name;
		}
	
		public void setName(String name) {
			this.name = name;
		}
		public String getLicence_url() {
			return licence_url;
		}
	
		public void setLicence_url(String licence_url) {
			this.licence_url = licence_url;
		}
		public String getLicence_code() {
			return licence_code;
		}
	
		public void setLicence_code(String licence_code) {
			this.licence_code = licence_code;
		}
		public String getTax_url() {
			return tax_url;
		}
	
		public void setTax_url(String tax_url) {
			this.tax_url = tax_url;
		}
		public String getLegal_person() {
			return legal_person;
		}
	
		public void setLegal_person(String legal_person) {
			this.legal_person = legal_person;
		}
		public Integer getCard_style() {
			return card_style;
		}
	
		public void setCard_style(Integer card_style) {
			this.card_style = card_style;
		}
		public String getCard_no() {
			return card_no;
		}
	
		public void setCard_no(String card_no) {
			this.card_no = card_no;
		}
		public Integer getSex() {
			return sex;
		}
	
		public void setSex(Integer sex) {
			this.sex = sex;
		}
		public String getMobile1() {
			return mobile1;
		}
	
		public void setMobile1(String mobile1) {
			this.mobile1 = mobile1;
		}
		public String getMobile2() {
			return mobile2;
		}
	
		public void setMobile2(String mobile2) {
			this.mobile2 = mobile2;
		}
		public Integer getStyle() {
			return style;
		}
	
		public void setStyle(Integer style) {
			this.style = style;
		}
		public Integer getStatus() {
			return status;
		}
	
		public void setStatus(Integer status) {
			this.status = status;
		}
		public Date getCreate_time() {
			return create_time;
		}
	
		public void setCreate_time(Date create_time) {
			this.create_time = create_time;
		}
		public String getCreate_openId() {
			return create_openId;
		}
	
		public void setCreate_openId(String create_openId) {
			this.create_openId = create_openId;
		}
		public Integer getCheck_status() {
			return check_status;
		}
	
		public void setCheck_status(Integer check_status) {
			this.check_status = check_status;
		}
		public String getContact_person() {
			return contact_person;
		}
	
		public void setContact_person(String contact_person) {
			this.contact_person = contact_person;
		}
		public String getTax_code() {
			return tax_code;
		}
	
		public void setTax_code(String tax_code) {
			this.tax_code = tax_code;
		}
		public String getBank_name() {
			return bank_name;
		}
	
		public void setBank_name(String bank_name) {
			this.bank_name = bank_name;
		}
		public String getBank_number() {
			return bank_number;
		}
	
		public void setBank_number(String bank_number) {
			this.bank_number = bank_number;
		}
		public String getBank_account() {
			return bank_account;
		}
	
		public void setBank_account(String bank_account) {
			this.bank_account = bank_account;
		}
		public String getContract_number() {
			return contract_number;
		}
	
		public void setContract_number(String contract_number) {
			this.contract_number = contract_number;
		}
		public Date getContract_start_time() {
			return contract_start_time;
		}
	
		public void setContract_start_time(Date contract_start_time) {
			this.contract_start_time = contract_start_time;
		}
		public Date getContract_end_time() {
			return contract_end_time;
		}
	
		public void setContract_end_time(Date contract_end_time) {
			this.contract_end_time = contract_end_time;
		}
		public String getRemark1() {
			return remark1;
		}
	
		public void setRemark1(String remark1) {
			this.remark1 = remark1;
		}
		public String getRemark2() {
			return remark2;
		}
	
		public void setRemark2(String remark2) {
			this.remark2 = remark2;
		}
		public String getRemark3() {
			return remark3;
		}
	
		public void setRemark3(String remark3) {
			this.remark3 = remark3;
		}
}