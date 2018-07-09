package com.hxt.hBank.controller;

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

import com.hxt.hBank.model.HBank;
import com.hxt.hBank.service.HBankService;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.controller.BaseController;
/**
 * @author	wuzhenwei
 * @time	2016年08月31日 00:17:10
 */
@Controller
@RequestMapping("/hBank")
public class HBankController extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(HBankController.class); //Logger
	
	@Autowired
	private HBankService hbankService = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hBank/hBankIndex";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/hBank/hBankAdd";
	}
	@RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		HBank hbank1 = new HBank();
		hbank1.setId(id);
		HBank hbank = hbankService.getHBank(hbank1);
		model.addAttribute("hbank", hbank);
		
		return "/hBank/hBankUpdate";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/getHBankList", method = RequestMethod.GET)
	public String getHBankList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HBank hbank = new HBank();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hbank.setId(id);
			
			String name = RequestHandler.getString(request, "name");
			hbank.setName(name);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hbank.setState(state);
			
			String bigImg = RequestHandler.getString(request, "bigImg");
			hbank.setBigImg(bigImg);
			
			String smallImg = RequestHandler.getString(request, "smallImg");
			hbank.setSmallImg(smallImg);
			
			String docUrl = RequestHandler.getString(request, "docUrl");
			hbank.setDocUrl(docUrl);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hbank.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hbank.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hbank.setRemark3(remark3);
			

			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			hbank.setRowStart(from);
			hbank.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			hbank.setSortColumn(sortColumn);
			
			int hbankListCount = hbankService.getHBankListCount(hbank);
			ResponseList<HBank> hbankList = null;
			if ( hbankListCount > 0 )
			{
				hbankList = hbankService.getHBankList(hbank);
			} else
			{
				hbankList = new ResponseList<HBank>();
			}
			// 设置数据总数
			hbankList.setTotalResults(hbankListCount);
			
			writeSuccessMsg("ok", hbankList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/getHBankBaseList", method = RequestMethod.GET)
	public String getHBankBaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HBank hbank = new HBank();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hbank.setId(id);
			
			String name = RequestHandler.getString(request, "name");
			hbank.setName(name);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hbank.setState(state);
			
			String bigImg = RequestHandler.getString(request, "bigImg");
			hbank.setBigImg(bigImg);
			
			String smallImg = RequestHandler.getString(request, "smallImg");
			hbank.setSmallImg(smallImg);
			
			String docUrl = RequestHandler.getString(request, "docUrl");
			hbank.setDocUrl(docUrl);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hbank.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hbank.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hbank.setRemark3(remark3);
			
			List<HBank> hbankList = hbankService.getHBankBaseList(hbank);
		
			writeSuccessMsg("ok", hbankList, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/addHBank", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			HBank hbank = new HBank();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hbank.setId(id);
			String name = RequestHandler.getString(request, "name");
			hbank.setName(name);
			Integer state = RequestHandler.getInteger(request, "state");
			hbank.setState(state);
			String bigImg = RequestHandler.getString(request, "bigImg");
			hbank.setBigImg(bigImg);
			String smallImg = RequestHandler.getString(request, "smallImg");
			hbank.setSmallImg(smallImg);
			String docUrl = RequestHandler.getString(request, "docUrl");
			hbank.setDocUrl(docUrl);
			String remark1 = RequestHandler.getString(request, "remark1");
			hbank.setRemark1(remark1);
			String remark2 = RequestHandler.getString(request, "remark2");
			hbank.setRemark2(remark2);
			String remark3 = RequestHandler.getString(request, "remark3");
			hbank.setRemark3(remark3);
				
			hbankService.insertHBank(hbank);
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/updateHBank", method = RequestMethod.POST)
	public String updateHBank(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HBank hbank = new HBank();
			
			Integer id = RequestHandler.getInteger(request, "id");
			hbank.setId(id);
			
			String name = RequestHandler.getString(request, "name");
			hbank.setName(name);
			
			Integer state = RequestHandler.getInteger(request, "state");
			hbank.setState(state);
			
			String bigImg = RequestHandler.getString(request, "bigImg");
			hbank.setBigImg(bigImg);
			
			String smallImg = RequestHandler.getString(request, "smallImg");
			hbank.setSmallImg(smallImg);
			
			String docUrl = RequestHandler.getString(request, "docUrl");
			hbank.setDocUrl(docUrl);
			
			String remark1 = RequestHandler.getString(request, "remark1");
			hbank.setRemark1(remark1);
			
			String remark2 = RequestHandler.getString(request, "remark2");
			hbank.setRemark2(remark2);
			
			String remark3 = RequestHandler.getString(request, "remark3");
			hbank.setRemark3(remark3);
			

			hbankService.updateHBank(hbank);
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/removeHBank", method = RequestMethod.POST)
	public String removeHBank(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			HBank hbank = new HBank();
			Integer id = RequestHandler.getInteger(request, "id");
			hbank.setId(id);

			hbankService.removeHBank(hbank);
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAllHBank", method = RequestMethod.POST)
	public String removeAllHBank(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ids = RequestHandler.getString(request, "ids");
			if(ids != null){
				String[] idStr = ids.split(",");
				if(idStr != null && idStr.length != 0){
					for (String id : idStr) {
						HBank hBank = new HBank();
						hBank.setId(Integer.valueOf(id));
						hbankService.removeHBank(hBank);
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
