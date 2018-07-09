package com.hxt.hSubOrder.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.base.model.BaseModel;
/**
 * @author	wuzhenwei
 * @time	2015年12月02日 00:01:04
 */
public class HSubOrder extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private Integer o_sub_id;
		private String o_id;
		private Integer c_id;
		private Integer sub_id;
		private Integer amount;
		private String electric;
		private String pay_status;
		private Date pay_time;
		private String tick_off_status;
		private Date tick_off_time;
		private Date create_time;
		
		private String sub_name;//子单位名称
		private String ammeter_name;//电表账户名
		private Integer totalFee;//金额小计
		private String c_name;//单位名称
		
		private String consignee;
	    private String consignee_phone;
	    private String consignee_address;
	    private String invoice_title;
	    private Integer fp_style;
	    
	    private String totalFeeStr;
		
	    private String electric_address;//用电地址
	    private String now_totalFee;//当前欠费
	    
	    private Integer isFP;
	    //新增
	    private BigDecimal oneRate;//一级代理分润比例
	    private BigDecimal twoRate;//二级代理分润比例
	    private BigDecimal personalRate;//服务人员分润比例
	    private Long oneFee;//一级分润金额
	    private Long twoFee;//二级分润金额
	    private Long personalFee;//服务人员分润金额
	    
	    private String express_name;//快递名
	    private String express_no;//快递号
	    
		public String getExpress_name() {
			return express_name;
		}

		public void setExpress_name(String express_name) {
			this.express_name = express_name;
		}

		public String getExpress_no() {
			return express_no;
		}

		public void setExpress_no(String express_no) {
			this.express_no = express_no;
		}

		public BigDecimal getOneRate() {
			return oneRate;
		}

		public void setOneRate(BigDecimal oneRate) {
			this.oneRate = oneRate;
		}

		public BigDecimal getTwoRate() {
			return twoRate;
		}

		public void setTwoRate(BigDecimal twoRate) {
			this.twoRate = twoRate;
		}

		public BigDecimal getPersonalRate() {
			return personalRate;
		}

		public void setPersonalRate(BigDecimal personalRate) {
			this.personalRate = personalRate;
		}
		
		public Long getOneFee() {
			return oneFee;
		}

		public void setOneFee(Long oneFee) {
			this.oneFee = oneFee;
		}

		public Long getTwoFee() {
			return twoFee;
		}

		public void setTwoFee(Long twoFee) {
			this.twoFee = twoFee;
		}

		public Long getPersonalFee() {
			return personalFee;
		}

		public void setPersonalFee(Long personalFee) {
			this.personalFee = personalFee;
		}

		public Integer getIsFP() {
			return isFP;
		}

		public void setIsFP(Integer isFP) {
			this.isFP = isFP;
		}

		public String getElectric_address() {
			return electric_address;
		}

		public void setElectric_address(String electric_address) {
			this.electric_address = electric_address;
		}

		public String getNow_totalFee() {
			return now_totalFee;
		}

		public void setNow_totalFee(String now_totalFee) {
			this.now_totalFee = now_totalFee;
		}

		public String getTotalFeeStr() {
			return totalFeeStr;
		}

		public void setTotalFeeStr(String totalFeeStr) {
			this.totalFeeStr = totalFeeStr;
		}

		public Integer getFp_style() {
			return fp_style;
		}

		public void setFp_style(Integer fp_style) {
			this.fp_style = fp_style;
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

		public String getConsignee_address() {
			return consignee_address;
		}

		public void setConsignee_address(String consignee_address) {
			this.consignee_address = consignee_address;
		}

		private Integer fp_order_id;
		
		public Integer getFp_order_id() {
			return fp_order_id;
		}

		public void setFp_order_id(Integer fp_order_id) {
			this.fp_order_id = fp_order_id;
		}

		public String getC_name() {
			return c_name;
		}

		public void setC_name(String c_name) {
			this.c_name = c_name;
		}

		private List<HSubOrder> listSubOrder;
		
		public List<HSubOrder> getListSubOrder() {
			return listSubOrder;
		}

		public void setListSubOrder(List<HSubOrder> listSubOrder) {
			this.listSubOrder = listSubOrder;
		}

		public String getSub_name() {
			return sub_name;
		}

		public void setSub_name(String sub_name) {
			this.sub_name = sub_name;
		}

		public String getAmmeter_name() {
			return ammeter_name;
		}

		public void setAmmeter_name(String ammeter_name) {
			this.ammeter_name = ammeter_name;
		}

		public Integer getTotalFee() {
			return totalFee;
		}

		public void setTotalFee(Integer totalFee) {
			this.totalFee = totalFee;
		}

		public Integer getO_sub_id() {
			return o_sub_id;
		}
	
		public void setO_sub_id(Integer o_sub_id) {
			this.o_sub_id = o_sub_id;
		}
		public String getO_id() {
			return o_id;
		}
	
		public void setO_id(String o_id) {
			this.o_id = o_id;
		}
		public Integer getC_id() {
			return c_id;
		}
	
		public void setC_id(Integer c_id) {
			this.c_id = c_id;
		}
		public Integer getSub_id() {
			return sub_id;
		}
	
		public void setSub_id(Integer sub_id) {
			this.sub_id = sub_id;
		}
		public Integer getAmount() {
			return amount;
		}
	
		public void setAmount(Integer amount) {
			this.amount = amount;
		}
		public String getElectric() {
			return electric;
		}
	
		public void setElectric(String electric) {
			this.electric = electric;
		}
		public String getPay_status() {
			return pay_status;
		}
	
		public void setPay_status(String pay_status) {
			this.pay_status = pay_status;
		}
		public Date getPay_time() {
			return pay_time;
		}
	
		public void setPay_time(Date pay_time) {
			this.pay_time = pay_time;
		}
		public String getTick_off_status() {
			return tick_off_status;
		}
	
		public void setTick_off_status(String tick_off_status) {
			this.tick_off_status = tick_off_status;
		}
		public Date getTick_off_time() {
			return tick_off_time;
		}
	
		public void setTick_off_time(Date tick_off_time) {
			this.tick_off_time = tick_off_time;
		}
		public Date getCreate_time() {
			return create_time;
		}
	
		public void setCreate_time(Date create_time) {
			this.create_time = create_time;
		}
}