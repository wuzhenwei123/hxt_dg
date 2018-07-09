package com.hxt.hCompany.model;

import java.util.Date;

import com.base.model.BaseModel;
/**
 * @author	yy
 * @time	2015年11月17日 23:59:31
 */
public class HCompany extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private Integer id;
		private String name;
		private Integer user_id;
		private String contact_name;
		private String contact_phone;
		private String contact_mail;
		private String fp_name;
		private String fp_phone;
		private String fp_telephone;
		private String fp_address;
		private String fax;
		private String fax_ext;
		private Integer ywyId;
		private Integer status;
		private Date create_time;
		private String verify_reason;
		private Integer verify_user_id;
		private String com_bank_code;
		private Integer verify_status;
		private String com_license_no;
		private String com_license_img;
		private String com_tax_no;
		private String com_tax_img;
		private String com_dept_code;
		private String com_dept_img;
		private String com_duty_no;
		private String com_bank_name;
		private String com_account_name;
		private String com_account_no;
		private String remark1;
		private String remark2;
		private String remark3;
		private String remark4;
		private String remark5;
		private String remark6;
		private String ywyNick;
		
		private String faxFlag;
		private Integer notid;
		//2016-8-7 新增
		private String credit_code;//企业统一社会信用代码 可空
		private Date com_license_start;//营业执照有效期开始日期 可空
		private Date com_license_end;//营业执照有效期结束日期 可空
		private String province_code;//省代码
		private String city_code;//市代码
		private String area_code;//地区代码
		private String com_address;//详细营业地址
		private String com_zip_code;//营业地址邮政编码
		private String zp_code;//增值税发票纳税人识别号信息
		private String 	zp_province_code;//增票省
		private String 	zp_city_code;//增票市
		private String 	zp_area_code;//增票地区
		private String zp_address;//增票开票信息地址详细信息
		private String zp_area_number;//增票开票信息电话区号
		private String zp_phone;//增票开票信息电话
		private String zp_bank_code;//增票开票信息开户行
		private String zp_bank_account;//增票开票信息开户行账号
		private Integer zp_verify_status;//增票开票信息资料是否已审核1 通过 2 不通过 0 审核中
		private Date verify_status_time;//证件资料最后一次审核时间
		private Date zp_verify_time;//增票开票资料最后一次审核时间
		private Integer zp_verify_creater_id;//增票开票资料审核人 ID
		private Date update_time;//最后一次变更时间
		private Integer update_operator_id;//最后一次变更人 ID
		private Integer com_business_doc_type;//营业证件类型 1 三证合一 2 非三证合一
		
		
		private String oneAgentOpenId;//一级代理openID
		private String oneAgentName;//一级代理名称
		private String twoAgentOpenID;//二级代理openID
		private String twoAgentName;//二级代理名称
		private Integer servicerId;//服务人员ID（adminID）
		private String servicerName;//服务人员姓名
		
		private Date startTime;
		private Date endTime;
		
		private Integer searchType;//查询类型 0：全部  1：自己  2 代理
		
		private String pay_status;
		private String paymentNo;//缴费号
		
		private String pay_flage;//是否制单，1有订单，2无订单
		
		private String rec_phone;//注册时候填写的推荐人手机号
		
		private Date startTimeOrder;
		private Date endTimeOrder;
		
		private Integer rateFlag;//
		
		public Integer getRateFlag() {
			return rateFlag;
		}

		public void setRateFlag(Integer rateFlag) {
			this.rateFlag = rateFlag;
		}

		public Date getStartTimeOrder() {
			return startTimeOrder;
		}

		public void setStartTimeOrder(Date startTimeOrder) {
			this.startTimeOrder = startTimeOrder;
		}

		public Date getEndTimeOrder() {
			return endTimeOrder;
		}

		public void setEndTimeOrder(Date endTimeOrder) {
			this.endTimeOrder = endTimeOrder;
		}

		public String getRec_phone() {
			return rec_phone;
		}

		public void setRec_phone(String rec_phone) {
			this.rec_phone = rec_phone;
		}

		public String getPay_flage() {
			return pay_flage;
		}

		public void setPay_flage(String pay_flage) {
			this.pay_flage = pay_flage;
		}

		public String getPaymentNo() {
			return paymentNo;
		}

		public void setPaymentNo(String paymentNo) {
			this.paymentNo = paymentNo;
		}

		public String getPay_status() {
			return pay_status;
		}

		public void setPay_status(String pay_status) {
			this.pay_status = pay_status;
		}

		public Integer getSearchType() {
			return searchType;
		}

		public void setSearchType(Integer searchType) {
			this.searchType = searchType;
		}

		public Date getStartTime() {
			return startTime;
		}

		public void setStartTime(Date startTime) {
			this.startTime = startTime;
		}

		public Date getEndTime() {
			return endTime;
		}

		public void setEndTime(Date endTime) {
			this.endTime = endTime;
		}

		public Integer getServicerId() {
			return servicerId;
		}

		public void setServicerId(Integer servicerId) {
			this.servicerId = servicerId;
		}

		public String getOneAgentOpenId() {
			return oneAgentOpenId;
		}

		public void setOneAgentOpenId(String oneAgentOpenId) {
			this.oneAgentOpenId = oneAgentOpenId;
		}

		public String getOneAgentName() {
			return oneAgentName;
		}

		public void setOneAgentName(String oneAgentName) {
			this.oneAgentName = oneAgentName;
		}

		public String getTwoAgentOpenID() {
			return twoAgentOpenID;
		}

		public void setTwoAgentOpenID(String twoAgentOpenID) {
			this.twoAgentOpenID = twoAgentOpenID;
		}

		public String getTwoAgentName() {
			return twoAgentName;
		}

		public void setTwoAgentName(String twoAgentName) {
			this.twoAgentName = twoAgentName;
		}

		public String getServicerName() {
			return servicerName;
		}

		public void setServicerName(String servicerName) {
			this.servicerName = servicerName;
		}

		public Integer getCom_business_doc_type() {
			return com_business_doc_type;
		}

		public void setCom_business_doc_type(Integer com_business_doc_type) {
			this.com_business_doc_type = com_business_doc_type;
		}

		public String getCredit_code() {
			return credit_code;
		}

		public void setCredit_code(String credit_code) {
			this.credit_code = credit_code;
		}

		public Date getCom_license_start() {
			return com_license_start;
		}

		public void setCom_license_start(Date com_license_start) {
			this.com_license_start = com_license_start;
		}

		public Date getCom_license_end() {
			return com_license_end;
		}

		public void setCom_license_end(Date com_license_end) {
			this.com_license_end = com_license_end;
		}

		public String getProvince_code() {
			return province_code;
		}

		public void setProvince_code(String province_code) {
			this.province_code = province_code;
		}

		public String getCity_code() {
			return city_code;
		}

		public void setCity_code(String city_code) {
			this.city_code = city_code;
		}

		public String getArea_code() {
			return area_code;
		}

		public void setArea_code(String area_code) {
			this.area_code = area_code;
		}

		public String getCom_address() {
			return com_address;
		}

		public void setCom_address(String com_address) {
			this.com_address = com_address;
		}

		public String getCom_zip_code() {
			return com_zip_code;
		}

		public void setCom_zip_code(String com_zip_code) {
			this.com_zip_code = com_zip_code;
		}

		public String getZp_code() {
			return zp_code;
		}

		public void setZp_code(String zp_code) {
			this.zp_code = zp_code;
		}

		public String getZp_province_code() {
			return zp_province_code;
		}

		public void setZp_province_code(String zp_province_code) {
			this.zp_province_code = zp_province_code;
		}

		public String getZp_city_code() {
			return zp_city_code;
		}

		public void setZp_city_code(String zp_city_code) {
			this.zp_city_code = zp_city_code;
		}

		public String getZp_area_code() {
			return zp_area_code;
		}

		public void setZp_area_code(String zp_area_code) {
			this.zp_area_code = zp_area_code;
		}

		public String getZp_address() {
			return zp_address;
		}

		public void setZp_address(String zp_address) {
			this.zp_address = zp_address;
		}

		public String getZp_area_number() {
			return zp_area_number;
		}

		public void setZp_area_number(String zp_area_number) {
			this.zp_area_number = zp_area_number;
		}

		public String getZp_phone() {
			return zp_phone;
		}

		public void setZp_phone(String zp_phone) {
			this.zp_phone = zp_phone;
		}

		public String getZp_bank_code() {
			return zp_bank_code;
		}

		public void setZp_bank_code(String zp_bank_code) {
			this.zp_bank_code = zp_bank_code;
		}

		public String getZp_bank_account() {
			return zp_bank_account;
		}

		public void setZp_bank_account(String zp_bank_account) {
			this.zp_bank_account = zp_bank_account;
		}

		public Integer getZp_verify_status() {
			return zp_verify_status;
		}

		public void setZp_verify_status(Integer zp_verify_status) {
			this.zp_verify_status = zp_verify_status;
		}

		public Date getVerify_status_time() {
			return verify_status_time;
		}

		public void setVerify_status_time(Date verify_status_time) {
			this.verify_status_time = verify_status_time;
		}

		public Date getZp_verify_time() {
			return zp_verify_time;
		}

		public void setZp_verify_time(Date zp_verify_time) {
			this.zp_verify_time = zp_verify_time;
		}

		public Integer getZp_verify_creater_id() {
			return zp_verify_creater_id;
		}

		public void setZp_verify_creater_id(Integer zp_verify_creater_id) {
			this.zp_verify_creater_id = zp_verify_creater_id;
		}

		public Date getUpdate_time() {
			return update_time;
		}

		public void setUpdate_time(Date update_time) {
			this.update_time = update_time;
		}

		public Integer getUpdate_operator_id() {
			return update_operator_id;
		}

		public void setUpdate_operator_id(Integer update_operator_id) {
			this.update_operator_id = update_operator_id;
		}

		public Integer getNotid() {
			return notid;
		}

		public void setNotid(Integer notid) {
			this.notid = notid;
		}

		public String getFaxFlag() {
			return faxFlag;
		}

		public void setFaxFlag(String faxFlag) {
			this.faxFlag = faxFlag;
		}

		public String getYwyNick() {
			return ywyNick;
		}

		public void setYwyNick(String ywyNick) {
			this.ywyNick = ywyNick;
		}

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
		public Integer getUser_id() {
			return user_id;
		}
	
		public void setUser_id(Integer user_id) {
			this.user_id = user_id;
		}
		public String getContact_name() {
			return contact_name;
		}
	
		public void setContact_name(String contact_name) {
			this.contact_name = contact_name;
		}
		public String getContact_phone() {
			return contact_phone;
		}
	
		public void setContact_phone(String contact_phone) {
			this.contact_phone = contact_phone;
		}
		public String getContact_mail() {
			return contact_mail;
		}
	
		public void setContact_mail(String contact_mail) {
			this.contact_mail = contact_mail;
		}
		public String getFp_name() {
			return fp_name;
		}
	
		public void setFp_name(String fp_name) {
			this.fp_name = fp_name;
		}
		public String getFp_phone() {
			return fp_phone;
		}
	
		public void setFp_phone(String fp_phone) {
			this.fp_phone = fp_phone;
		}
		public String getFp_telephone() {
			return fp_telephone;
		}
	
		public void setFp_telephone(String fp_telephone) {
			this.fp_telephone = fp_telephone;
		}
		public String getFp_address() {
			return fp_address;
		}
	
		public void setFp_address(String fp_address) {
			this.fp_address = fp_address;
		}
		public String getFax() {
			return fax;
		}
	
		public void setFax(String fax) {
			this.fax = fax;
		}
		public String getFax_ext() {
			return fax_ext;
		}
	
		public void setFax_ext(String fax_ext) {
			this.fax_ext = fax_ext;
		}
		public Integer getYwyId() {
			return ywyId;
		}
	
		public void setYwyId(Integer ywyId) {
			this.ywyId = ywyId;
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
		public String getVerify_reason() {
			return verify_reason;
		}
	
		public void setVerify_reason(String verify_reason) {
			this.verify_reason = verify_reason;
		}
		public Integer getVerify_user_id() {
			return verify_user_id;
		}
	
		public void setVerify_user_id(Integer verify_user_id) {
			this.verify_user_id = verify_user_id;
		}
		public String getCom_bank_code() {
			return com_bank_code;
		}
	
		public void setCom_bank_code(String com_bank_code) {
			this.com_bank_code = com_bank_code;
		}
		public Integer getVerify_status() {
			return verify_status;
		}
	
		public void setVerify_status(Integer verify_status) {
			this.verify_status = verify_status;
		}
		public String getCom_license_no() {
			return com_license_no;
		}
	
		public void setCom_license_no(String com_license_no) {
			this.com_license_no = com_license_no;
		}
		public String getCom_license_img() {
			return com_license_img;
		}
	
		public void setCom_license_img(String com_license_img) {
			this.com_license_img = com_license_img;
		}
		public String getCom_tax_no() {
			return com_tax_no;
		}
	
		public void setCom_tax_no(String com_tax_no) {
			this.com_tax_no = com_tax_no;
		}
		public String getCom_tax_img() {
			return com_tax_img;
		}
	
		public void setCom_tax_img(String com_tax_img) {
			this.com_tax_img = com_tax_img;
		}
		public String getCom_dept_code() {
			return com_dept_code;
		}
	
		public void setCom_dept_code(String com_dept_code) {
			this.com_dept_code = com_dept_code;
		}
		public String getCom_dept_img() {
			return com_dept_img;
		}
	
		public void setCom_dept_img(String com_dept_img) {
			this.com_dept_img = com_dept_img;
		}
		public String getCom_duty_no() {
			return com_duty_no;
		}
	
		public void setCom_duty_no(String com_duty_no) {
			this.com_duty_no = com_duty_no;
		}
		public String getCom_bank_name() {
			return com_bank_name;
		}
	
		public void setCom_bank_name(String com_bank_name) {
			this.com_bank_name = com_bank_name;
		}
		public String getCom_account_name() {
			return com_account_name;
		}
	
		public void setCom_account_name(String com_account_name) {
			this.com_account_name = com_account_name;
		}
		public String getCom_account_no() {
			return com_account_no;
		}
	
		public void setCom_account_no(String com_account_no) {
			this.com_account_no = com_account_no;
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
		public String getRemark4() {
			return remark4;
		}
	
		public void setRemark4(String remark4) {
			this.remark4 = remark4;
		}
		public String getRemark5() {
			return remark5;
		}
	
		public void setRemark5(String remark5) {
			this.remark5 = remark5;
		}
		public String getRemark6() {
			return remark6;
		}
	
		public void setRemark6(String remark6) {
			this.remark6 = remark6;
		}
}