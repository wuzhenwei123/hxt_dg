package com.hxt.hSubCompany.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.base.model.BaseModel;
import com.hxt.hSubOrder.model.HSubOrder;

/**
 * @author wuzhenwei
 * @time 2015年11月24日 10:42:56
 */
public class HSubCompany extends BaseModel implements java.io.Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Integer s_id;
    private Integer c_id;
    private String sub_name;
    private String invoice_title;
    private String consignee;
    private String consignee_phone;
    private String consignee_tel1;
    private String consignee_tel2;
    private String consignee_address;
    private Date create_time;
    private Date update_time;

    private String c_name;//单位名称
    private String ammeter_no;//电表号
    private Integer yw_id;
    private Integer totalFee;//欠费总金额
    //新增2016-8-9 22:48:03
    private String zip_code;//收件人邮编
    private String province_code;//省
    private String city_code;//市
    private String area_code;//区
    private String province_name;
    private String city_name;
    private String area_name;
    private String oneName;//客户经理代理
    private String twoName;//代理名称
    private String servicerName;//服务人员名称
    
    private String contact_name;//业务联系人姓名
    private String contact_phone;//业务联系人手机（短信验证）
    
    private Date create_time1;
    private Date create_time2;
    
    private Integer servicerId;
    
    private String oneAgentOpenId;
    private String twoAgentOpenID;
    
    private String totalFeeStr;
    private Integer ammeterCount;
    
    public Integer getAmmeterCount() {
		return ammeterCount;
	}

	public void setAmmeterCount(Integer ammeterCount) {
		this.ammeterCount = ammeterCount;
	}

	public String getTotalFeeStr() {
		return totalFeeStr;
	}

	public void setTotalFeeStr(String totalFeeStr) {
		this.totalFeeStr = totalFeeStr;
	}

	public String getOneAgentOpenId() {
		return oneAgentOpenId;
	}

	public void setOneAgentOpenId(String oneAgentOpenId) {
		this.oneAgentOpenId = oneAgentOpenId;
	}

	public String getTwoAgentOpenID() {
		return twoAgentOpenID;
	}

	public void setTwoAgentOpenID(String twoAgentOpenID) {
		this.twoAgentOpenID = twoAgentOpenID;
	}

	public Integer getServicerId() {
		return servicerId;
	}

	public void setServicerId(Integer servicerId) {
		this.servicerId = servicerId;
	}

	public Date getCreate_time1() {
		return create_time1;
	}

	public void setCreate_time1(Date create_time1) {
		this.create_time1 = create_time1;
	}

	public Date getCreate_time2() {
		return create_time2;
	}

	public void setCreate_time2(Date create_time2) {
		this.create_time2 = create_time2;
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

	public String getOneName() {
		return oneName;
	}

	public void setOneName(String oneName) {
		this.oneName = oneName;
	}

	public String getTwoName() {
		return twoName;
	}

	public void setTwoName(String twoName) {
		this.twoName = twoName;
	}

	public String getServicerName() {
		return servicerName;
	}

	public void setServicerName(String servicerName) {
		this.servicerName = servicerName;
	}

	private Integer[] ids;
    
    public Integer[] getIds() {
		return ids;
	}

	public void setIds(Integer[] ids) {
		this.ids = ids;
	}

	private List<HSubOrder> subOrderList;//子订单列表
    
    public List<HSubOrder> getSubOrderList() {
		return subOrderList;
	}

	public void setSubOrderList(List<HSubOrder> subOrderList) {
		this.subOrderList = subOrderList;
	}

	public String getProvince_name() {
		return province_name;
	}

	public void setProvince_name(String province_name) {
		this.province_name = province_name;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public String getArea_name() {
		return area_name;
	}

	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}

	public String getZip_code() {
		return zip_code;
	}

	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
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

	public Integer getYw_id() {
        return yw_id;
    }

    public void setYw_id(Integer yw_id) {
        this.yw_id = yw_id;
    }

    public String getAmmeter_no() {
        return ammeter_no;
    }

    public void setAmmeter_no(String ammeter_no) {
        this.ammeter_no = ammeter_no;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public Integer getS_id() {
        return s_id;
    }

    public void setS_id(Integer s_id) {
        this.s_id = s_id;
    }

    public Integer getC_id() {
        return c_id;
    }

    public void setC_id(Integer c_id) {
        this.c_id = c_id;
    }

    public String getSub_name() {
        return sub_name;
    }

    public void setSub_name(String sub_name) {
        this.sub_name = sub_name;
    }

    public String getInvoice_title() {
        return invoice_title;
    }

    public void setInvoice_title(String invoice_title) {
        this.invoice_title = invoice_title;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getConsignee_phone() {
        return consignee_phone;
    }

    public void setConsignee_phone(String consignee_phone) {
        this.consignee_phone = consignee_phone;
    }

    public String getConsignee_tel1() {
        return consignee_tel1;
    }

    public void setConsignee_tel1(String consignee_tel1) {
        this.consignee_tel1 = consignee_tel1;
    }

    public String getConsignee_tel2() {
        return consignee_tel2;
    }

    public void setConsignee_tel2(String consignee_tel2) {
        this.consignee_tel2 = consignee_tel2;
    }

    public String getConsignee_address() {
        return consignee_address;
    }

    public void setConsignee_address(String consignee_address) {
        this.consignee_address = consignee_address;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }
}