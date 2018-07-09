package com.hxt.hProxySerial.controller;

import java.util.Date;

import org.apache.log4j.Logger;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hxt.hProxySerial.model.HProxySerial;
import com.hxt.hProxySerial.service.HProxySerialService;
import com.base.utils.FileUploadConstants;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.controller.BaseController;
/**
 * @author	wuzhenwei
 * @time	2016年09月08日 22:18:13
 */
@Controller
@RequestMapping("/hProxySerial")
public class HProxySerialController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HProxySerialController.class); //Logger
	
	@Autowired
	private HProxySerialService hproxyserialService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hProxySerial/hProxySerialIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hProxySerial/hProxySerialAdd";
	}
	@RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HProxySerial hproxyserial1 = new HProxySerial();
		hproxyserial1.setId(id);
		HProxySerial hproxyserial = hproxyserialService.getHProxySerial(hproxyserial1);
		model.addAttribute("hproxyserial", hproxyserial);
		
		return "/hProxySerial/hProxySerialUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getHProxySerialList", method = RequestMethod.GET)
	public String getHProxySerialList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HProxySerial hproxyserial = new HProxySerial();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hproxyserial.setId(id);
			
			String payBankNumber = RequestHandler.getString(request, "payBankNumber");
			hproxyserial.setPayBankNumber(payBankNumber);
			
			String userNumber = RequestHandler.getString(request, "userNumber");
			hproxyserial.setUserNumber(userNumber);
			
			String contractNumber = RequestHandler.getString(request, "contractNumber");
			hproxyserial.setContractNumber(contractNumber);
			
			String bank_number = RequestHandler.getString(request, "bank_number");
			hproxyserial.setBank_number(bank_number);
			

			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hproxyserial.setRowStart(from);
			hproxyserial.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hproxyserial.setSortColumn(sortColumn);
			
			int hproxyserialListCount = hproxyserialService.getHProxySerialListCount(hproxyserial);
			ResponseList<HProxySerial> hproxyserialList = null;
			if ( hproxyserialListCount > 0 )
			{
				hproxyserialList = hproxyserialService.getHProxySerialList(hproxyserial);
			} else
			{
				hproxyserialList = new ResponseList<HProxySerial>();
			}
			// 设置数据总数
			hproxyserialList.setTotalResults(hproxyserialListCount);
			
			writeSuccessMsg("ok", hproxyserialList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getHProxySerialBaseList", method = RequestMethod.GET)
	public String getHProxySerialBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HProxySerial hproxyserial = new HProxySerial();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hproxyserial.setId(id);
			
			String payBankNumber = RequestHandler.getString(request, "payBankNumber");
			hproxyserial.setPayBankNumber(payBankNumber);
			
			String userNumber = RequestHandler.getString(request, "userNumber");
			hproxyserial.setUserNumber(userNumber);
			
			String contractNumber = RequestHandler.getString(request, "contractNumber");
			hproxyserial.setContractNumber(contractNumber);
			
			String bank_number = RequestHandler.getString(request, "bank_number");
			hproxyserial.setBank_number(bank_number);
			
			List<HProxySerial> hproxyserialList = hproxyserialService.getHProxySerialBaseList(hproxyserial);
		
			writeSuccessMsg("ok", hproxyserialList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addHProxySerial", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			HProxySerial hproxyserial = new HProxySerial();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hproxyserial.setId(id);
			String payBankNumber = RequestHandler.getString(request, "payBankNumber");
			hproxyserial.setPayBankNumber(payBankNumber);
			String userNumber = RequestHandler.getString(request, "userNumber");
			hproxyserial.setUserNumber(userNumber);
			String contractNumber = RequestHandler.getString(request, "contractNumber");
			hproxyserial.setContractNumber(contractNumber);
			String bank_number = RequestHandler.getString(request, "bank_number");
			hproxyserial.setBank_number(bank_number);
				
			hproxyserialService.insertHProxySerial(hproxyserial);
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateHProxySerial", method = RequestMethod.POST)
	public String updateHProxySerial(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HProxySerial hproxyserial = new HProxySerial();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hproxyserial.setId(id);
			
			String payBankNumber = RequestHandler.getString(request, "payBankNumber");
			hproxyserial.setPayBankNumber(payBankNumber);
			
			String userNumber = RequestHandler.getString(request, "userNumber");
			hproxyserial.setUserNumber(userNumber);
			
			String contractNumber = RequestHandler.getString(request, "contractNumber");
			hproxyserial.setContractNumber(contractNumber);
			
			String bank_number = RequestHandler.getString(request, "bank_number");
			hproxyserial.setBank_number(bank_number);
			

			hproxyserialService.updateHProxySerial(hproxyserial);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeHProxySerial", method = RequestMethod.POST)
	public String removeHProxySerial(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HProxySerial hproxyserial = new HProxySerial();
			Integer id = RequestHandler.getInteger(request, "id");
			hproxyserial.setId(id);

			hproxyserialService.removeHProxySerial(hproxyserial);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllHProxySerial", method = RequestMethod.POST)
	public String removeAllHProxySerial(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						HProxySerial hProxySerial = new HProxySerial();
						hProxySerial.setId(Integer.valueOf(id));
						hproxyserialService.removeHProxySerial(hProxySerial);
					}
				}
			}
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/getNumber", method = RequestMethod.GET)
	public String getNumber(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String bank_number = RequestHandler.getString(request, "bank_number");
			String payBankNumber = RequestHandler.getString(request, "payBankNumber");
			HProxySerial hProxySerial = new HProxySerial();
			hProxySerial.setBank_number(bank_number);
			hProxySerial.setPayBankNumber(payBankNumber);
			HProxySerial hProxySerial1 = hproxyserialService.getHProxySerial(hProxySerial);
			if(hProxySerial1==null){
				
				String cityCode = bank_number.substring(3, 7);
				String bankCode = bank_number.substring(0, 3);
				
				String contractNumber = FileUploadConstants.LOAN_CODE + cityCode + FileUploadConstants.ORGANIZATION_CODE + FileUploadConstants.SERVICE_ELECTRICITY_CODE + bankCode + payBankNumber;
				
				hProxySerial.setContractNumber(contractNumber);
				int id = hproxyserialService.insertHProxySerial(hProxySerial);
				Long userNumber = 100000000000000000L + id;
				hProxySerial.setId(id);
				hProxySerial.setUserNumber(userNumber+"");
				hproxyserialService.updateHProxySerial(hProxySerial);
				writeSuccessMsg("ok", hProxySerial, response);
			}else{
				writeSuccessMsg("ok", hProxySerial1, response);
			}
		} catch (Exception e){
			e.printStackTrace();
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
}
