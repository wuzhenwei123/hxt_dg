package com.hxt.openingBank.controller;

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

import com.hxt.openingBank.model.OpeningBank;
import com.hxt.openingBank.service.OpeningBankService;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.controller.BaseController;
/**
 * @author	wuzhenwei
 * @time	2016年09月09日 00:01:09
 */
@Controller
@RequestMapping("/openingBank")
public class OpeningBankController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(OpeningBankController.class); //Logger
	
	@Autowired
	private OpeningBankService openingbankService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/openingBank/openingBankIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/openingBank/openingBankAdd";
	}
	@RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		OpeningBank openingbank1 = new OpeningBank();
		openingbank1.setId(id);
		OpeningBank openingbank = openingbankService.getOpeningBank(openingbank1);
		model.addAttribute("openingbank", openingbank);
		
		return "/openingBank/openingBankUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getOpeningBankList", method = RequestMethod.GET)
	public String getOpeningBankList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			OpeningBank openingbank = new OpeningBank();
			
			Integer id = RequestHandler.getInteger(request, "id");
			openingbank.setId(id);
			
			String NBKCODE = RequestHandler.getString(request, "NBKCODE");
			openingbank.setNBKCODE(NBKCODE);
			
			String SABKCODE = RequestHandler.getString(request, "SABKCODE");
			openingbank.setSABKCODE(SABKCODE);
			
			String BNKCITY = RequestHandler.getString(request, "BNKCITY");
			openingbank.setBNKCITY(BNKCITY);
			
			String NBKNAME = RequestHandler.getString(request, "NBKNAME");
			openingbank.setNBKNAME(NBKNAME);
			
			String NBKSNAME = RequestHandler.getString(request, "NBKSNAME");
			openingbank.setNBKSNAME(NBKSNAME);
			
			String NBKADDRESS = RequestHandler.getString(request, "NBKADDRESS");
			openingbank.setNBKADDRESS(NBKADDRESS);
			
			String CNTTEL = RequestHandler.getString(request, "CNTTEL");
			openingbank.setCNTTEL(CNTTEL);
			
			String CNTPER = RequestHandler.getString(request, "CNTPER");
			openingbank.setCNTPER(CNTPER);
			
			String POSTCODE = RequestHandler.getString(request, "POSTCODE");
			openingbank.setPOSTCODE(POSTCODE);
			
			String NBKSTATE = RequestHandler.getString(request, "NBKSTATE");
			openingbank.setNBKSTATE(NBKSTATE);
			
			String BKEMAIL = RequestHandler.getString(request, "BKEMAIL");
			openingbank.setBKEMAIL(BKEMAIL);
			
			String CONTENT = RequestHandler.getString(request, "CONTENT");
			openingbank.setCONTENT(CONTENT);
			
			String PARTTYPE = RequestHandler.getString(request, "PARTTYPE");
			openingbank.setPARTTYPE(PARTTYPE);
			
			String BANKCATCODE = RequestHandler.getString(request, "BANKCATCODE");
			openingbank.setBANKCATCODE(BANKCATCODE);
			
			String HIGHPART = RequestHandler.getString(request, "HIGHPART");
			openingbank.setHIGHPART(HIGHPART);
			
			String BEARBANKCODE = RequestHandler.getString(request, "BEARBANKCODE");
			openingbank.setBEARBANKCODE(BEARBANKCODE);
			
			String CHARGEBANKCODE = RequestHandler.getString(request, "CHARGEBANKCODE");
			openingbank.setCHARGEBANKCODE(CHARGEBANKCODE);
			
			String NODECODE = RequestHandler.getString(request, "NODECODE");
			openingbank.setNODECODE(NODECODE);
			
			String SIGN = RequestHandler.getString(request, "SIGN");
			openingbank.setSIGN(SIGN);
			

			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			openingbank.setRowStart(from);
			openingbank.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			openingbank.setSortColumn(sortColumn);
			
			int openingbankListCount = openingbankService.getOpeningBankListCount(openingbank);
			ResponseList<OpeningBank> openingbankList = null;
			if ( openingbankListCount > 0 )
			{
				openingbankList = openingbankService.getOpeningBankList(openingbank);
			} else
			{
				openingbankList = new ResponseList<OpeningBank>();
			}
			// 设置数据总数
			openingbankList.setTotalResults(openingbankListCount);
			
			writeSuccessMsg("ok", openingbankList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getOpeningBankBaseList", method = RequestMethod.GET)
	public String getOpeningBankBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			OpeningBank openingbank = new OpeningBank();
			
			Integer id = RequestHandler.getInteger(request, "id");
			openingbank.setId(id);
			
			String NBKCODE = RequestHandler.getString(request, "NBKCODE");
			openingbank.setNBKCODE(NBKCODE);
			
			String SABKCODE = RequestHandler.getString(request, "SABKCODE");
			openingbank.setSABKCODE(SABKCODE);
			
			String BNKCITY = RequestHandler.getString(request, "BNKCITY");
			openingbank.setBNKCITY(BNKCITY);
			
			String NBKNAME = RequestHandler.getString(request, "NBKNAME");
			openingbank.setNBKNAME(NBKNAME);
			
			String NBKSNAME = RequestHandler.getString(request, "NBKSNAME");
			openingbank.setNBKSNAME(NBKSNAME);
			
			String NBKADDRESS = RequestHandler.getString(request, "NBKADDRESS");
			openingbank.setNBKADDRESS(NBKADDRESS);
			
			String CNTTEL = RequestHandler.getString(request, "CNTTEL");
			openingbank.setCNTTEL(CNTTEL);
			
			String CNTPER = RequestHandler.getString(request, "CNTPER");
			openingbank.setCNTPER(CNTPER);
			
			String POSTCODE = RequestHandler.getString(request, "POSTCODE");
			openingbank.setPOSTCODE(POSTCODE);
			
			String NBKSTATE = RequestHandler.getString(request, "NBKSTATE");
			openingbank.setNBKSTATE(NBKSTATE);
			
			String BKEMAIL = RequestHandler.getString(request, "BKEMAIL");
			openingbank.setBKEMAIL(BKEMAIL);
			
			String CONTENT = RequestHandler.getString(request, "CONTENT");
			openingbank.setCONTENT(CONTENT);
			
			String PARTTYPE = RequestHandler.getString(request, "PARTTYPE");
			openingbank.setPARTTYPE(PARTTYPE);
			
			String BANKCATCODE = RequestHandler.getString(request, "BANKCATCODE");
			openingbank.setBANKCATCODE(BANKCATCODE);
			
			String HIGHPART = RequestHandler.getString(request, "HIGHPART");
			openingbank.setHIGHPART(HIGHPART);
			
			String BEARBANKCODE = RequestHandler.getString(request, "BEARBANKCODE");
			openingbank.setBEARBANKCODE(BEARBANKCODE);
			
			String CHARGEBANKCODE = RequestHandler.getString(request, "CHARGEBANKCODE");
			openingbank.setCHARGEBANKCODE(CHARGEBANKCODE);
			
			String NODECODE = RequestHandler.getString(request, "NODECODE");
			openingbank.setNODECODE(NODECODE);
			
			String SIGN = RequestHandler.getString(request, "SIGN");
			openingbank.setSIGN(SIGN);
			
			List<OpeningBank> openingbankList = openingbankService.getOpeningBankBaseList(openingbank);
		
			writeSuccessMsg("ok", openingbankList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addOpeningBank", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			OpeningBank openingbank = new OpeningBank();
			
			Integer id = RequestHandler.getInteger(request, "id");
			openingbank.setId(id);
			String NBKCODE = RequestHandler.getString(request, "NBKCODE");
			openingbank.setNBKCODE(NBKCODE);
			String SABKCODE = RequestHandler.getString(request, "SABKCODE");
			openingbank.setSABKCODE(SABKCODE);
			String BNKCITY = RequestHandler.getString(request, "BNKCITY");
			openingbank.setBNKCITY(BNKCITY);
			String NBKNAME = RequestHandler.getString(request, "NBKNAME");
			openingbank.setNBKNAME(NBKNAME);
			String NBKSNAME = RequestHandler.getString(request, "NBKSNAME");
			openingbank.setNBKSNAME(NBKSNAME);
			String NBKADDRESS = RequestHandler.getString(request, "NBKADDRESS");
			openingbank.setNBKADDRESS(NBKADDRESS);
			String CNTTEL = RequestHandler.getString(request, "CNTTEL");
			openingbank.setCNTTEL(CNTTEL);
			String CNTPER = RequestHandler.getString(request, "CNTPER");
			openingbank.setCNTPER(CNTPER);
			String POSTCODE = RequestHandler.getString(request, "POSTCODE");
			openingbank.setPOSTCODE(POSTCODE);
			String NBKSTATE = RequestHandler.getString(request, "NBKSTATE");
			openingbank.setNBKSTATE(NBKSTATE);
			String BKEMAIL = RequestHandler.getString(request, "BKEMAIL");
			openingbank.setBKEMAIL(BKEMAIL);
			String CONTENT = RequestHandler.getString(request, "CONTENT");
			openingbank.setCONTENT(CONTENT);
			String PARTTYPE = RequestHandler.getString(request, "PARTTYPE");
			openingbank.setPARTTYPE(PARTTYPE);
			String BANKCATCODE = RequestHandler.getString(request, "BANKCATCODE");
			openingbank.setBANKCATCODE(BANKCATCODE);
			String HIGHPART = RequestHandler.getString(request, "HIGHPART");
			openingbank.setHIGHPART(HIGHPART);
			String BEARBANKCODE = RequestHandler.getString(request, "BEARBANKCODE");
			openingbank.setBEARBANKCODE(BEARBANKCODE);
			String CHARGEBANKCODE = RequestHandler.getString(request, "CHARGEBANKCODE");
			openingbank.setCHARGEBANKCODE(CHARGEBANKCODE);
			String NODECODE = RequestHandler.getString(request, "NODECODE");
			openingbank.setNODECODE(NODECODE);
			String SIGN = RequestHandler.getString(request, "SIGN");
			openingbank.setSIGN(SIGN);
				
			openingbankService.insertOpeningBank(openingbank);
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateOpeningBank", method = RequestMethod.POST)
	public String updateOpeningBank(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			OpeningBank openingbank = new OpeningBank();
			
			Integer id = RequestHandler.getInteger(request, "id");
			openingbank.setId(id);
			
			String NBKCODE = RequestHandler.getString(request, "NBKCODE");
			openingbank.setNBKCODE(NBKCODE);
			
			String SABKCODE = RequestHandler.getString(request, "SABKCODE");
			openingbank.setSABKCODE(SABKCODE);
			
			String BNKCITY = RequestHandler.getString(request, "BNKCITY");
			openingbank.setBNKCITY(BNKCITY);
			
			String NBKNAME = RequestHandler.getString(request, "NBKNAME");
			openingbank.setNBKNAME(NBKNAME);
			
			String NBKSNAME = RequestHandler.getString(request, "NBKSNAME");
			openingbank.setNBKSNAME(NBKSNAME);
			
			String NBKADDRESS = RequestHandler.getString(request, "NBKADDRESS");
			openingbank.setNBKADDRESS(NBKADDRESS);
			
			String CNTTEL = RequestHandler.getString(request, "CNTTEL");
			openingbank.setCNTTEL(CNTTEL);
			
			String CNTPER = RequestHandler.getString(request, "CNTPER");
			openingbank.setCNTPER(CNTPER);
			
			String POSTCODE = RequestHandler.getString(request, "POSTCODE");
			openingbank.setPOSTCODE(POSTCODE);
			
			String NBKSTATE = RequestHandler.getString(request, "NBKSTATE");
			openingbank.setNBKSTATE(NBKSTATE);
			
			String BKEMAIL = RequestHandler.getString(request, "BKEMAIL");
			openingbank.setBKEMAIL(BKEMAIL);
			
			String CONTENT = RequestHandler.getString(request, "CONTENT");
			openingbank.setCONTENT(CONTENT);
			
			String PARTTYPE = RequestHandler.getString(request, "PARTTYPE");
			openingbank.setPARTTYPE(PARTTYPE);
			
			String BANKCATCODE = RequestHandler.getString(request, "BANKCATCODE");
			openingbank.setBANKCATCODE(BANKCATCODE);
			
			String HIGHPART = RequestHandler.getString(request, "HIGHPART");
			openingbank.setHIGHPART(HIGHPART);
			
			String BEARBANKCODE = RequestHandler.getString(request, "BEARBANKCODE");
			openingbank.setBEARBANKCODE(BEARBANKCODE);
			
			String CHARGEBANKCODE = RequestHandler.getString(request, "CHARGEBANKCODE");
			openingbank.setCHARGEBANKCODE(CHARGEBANKCODE);
			
			String NODECODE = RequestHandler.getString(request, "NODECODE");
			openingbank.setNODECODE(NODECODE);
			
			String SIGN = RequestHandler.getString(request, "SIGN");
			openingbank.setSIGN(SIGN);
			

			openingbankService.updateOpeningBank(openingbank);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeOpeningBank", method = RequestMethod.POST)
	public String removeOpeningBank(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			OpeningBank openingbank = new OpeningBank();
			Integer id = RequestHandler.getInteger(request, "id");
			openingbank.setId(id);

			openingbankService.removeOpeningBank(openingbank);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllOpeningBank", method = RequestMethod.POST)
	public String removeAllOpeningBank(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						OpeningBank openingBank = new OpeningBank();
						openingBank.setId(Integer.valueOf(id));
						openingbankService.removeOpeningBank(openingBank);
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
}
