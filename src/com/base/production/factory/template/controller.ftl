package ${packageName}.${pak};

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

import ${packageName}.model.${domainName?cap_first};
import ${packageName}.service.${domainName?cap_first}Service;
import com.base.utils.RequestHandler;
import com.base.utils.ResponseList;
import com.base.controller.BaseController;
/**
 * ${description}
 * @time	${date}
 */
@Controller
@RequestMapping("/${domainName}")
public class ${domainName?cap_first}Controller extends BaseController
{
	
	//private static Logger logger = Logger.getLogger(${domainName?cap_first}Controller.class); //Logger
	
	@Autowired
	private ${domainName?cap_first}Service ${domainName?lower_case}Service = null;
	
	/*****************页面中转*********************/
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String init(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/${domainName}/${domainName}Index";
	}
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return "/${domainName}/${domainName}Add";
	}
	@RequestMapping(value = "/toUpdate/{${pkName}}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable Integer ${pkName}, HttpServletRequest request, HttpServletResponse response, Model model)
	{	
		
		${domainName?cap_first} ${domainName?lower_case}1 = new ${domainName?cap_first}();
		${domainName?lower_case}1.set${pkName?cap_first}(${pkName});
		${domainName?cap_first} ${domainName?lower_case} = ${domainName?lower_case}Service.get${domainName?cap_first}(${domainName?lower_case}1);
		model.addAttribute("${domainName?lower_case}", ${domainName?lower_case});
		
		return "/${domainName}/${domainName}Update";
	}

	/************* Public Methods *************/
	
	@RequestMapping(value = "/get${domainName?cap_first}List", method = RequestMethod.GET)
	public String get${domainName?cap_first}List(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			${domainName?cap_first} ${domainName?lower_case} = new ${domainName?cap_first}();
			
		<#list columns as item>
			${item.type} ${item.name} = RequestHandler.get${item.type}(request, "${item.name}");
			${domainName?lower_case}.set${item.name?cap_first}(${item.name});
			
		</#list>

			// 分页开始
			Integer pageNo = RequestHandler.getPageNo(request, "pageNo");
			Integer rowCount = RequestHandler.getPageSize(request, "rowCount");
			
			int from = RequestHandler.getFromByPage(pageNo, rowCount);
			${domainName?lower_case}.setRowStart(from);
			${domainName?lower_case}.setRowCount(rowCount);
			// 分页结束
			// 排序
			String sortColumn = RequestHandler.getString(request, "sortColumn");
			${domainName?lower_case}.setSortColumn(sortColumn);
			
			int ${domainName?lower_case}ListCount = ${domainName?lower_case}Service.get${domainName?cap_first}ListCount(${domainName?lower_case});
			ResponseList<${domainName?cap_first}> ${domainName?lower_case}List = null;
			if ( ${domainName?lower_case}ListCount > 0 )
			{
				${domainName?lower_case}List = ${domainName?lower_case}Service.get${domainName?cap_first}List(${domainName?lower_case});
			} else
			{
				${domainName?lower_case}List = new ResponseList<${domainName?cap_first}>();
			}
			// 设置数据总数
			${domainName?lower_case}List.setTotalResults(${domainName?lower_case}ListCount);
			
			writeSuccessMsg("ok", ${domainName?lower_case}List, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/get${domainName?cap_first}BaseList", method = RequestMethod.GET)
	public String get${domainName?cap_first}BaseList(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			${domainName?cap_first} ${domainName?lower_case} = new ${domainName?cap_first}();
			
		<#list columns as item>
			${item.type} ${item.name} = RequestHandler.get${item.type}(request, "${item.name}");
			${domainName?lower_case}.set${item.name?cap_first}(${item.name});
			
		</#list>
			List<${domainName?cap_first}> ${domainName?lower_case}List = ${domainName?lower_case}Service.get${domainName?cap_first}BaseList(${domainName?lower_case});
		
			writeSuccessMsg("ok", ${domainName?lower_case}List, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/add${domainName?cap_first}", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) 
	{
		try
		{
			
			${domainName?cap_first} ${domainName?lower_case} = new ${domainName?cap_first}();
			
		<#list columns as item>
			${item.type} ${item.name} = RequestHandler.get${item.type}(request, "${item.name}");
			<#if item.name == 'createTime'>
			${domainName?lower_case}.set${item.name?cap_first}(new Date());
        	</#if>
			<#if item.name != 'createTime'>
			${domainName?lower_case}.set${item.name?cap_first}(${item.name});
        	</#if>
		</#list>
				
			${domainName?lower_case}Service.insert${domainName?cap_first}(${domainName?lower_case});
			
			writeSuccessMsg("添加成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/update${domainName?cap_first}", method = RequestMethod.POST)
	public String update${domainName?cap_first}(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			${domainName?cap_first} ${domainName?lower_case} = new ${domainName?cap_first}();
			
		<#list columns as item>
			${item.type} ${item.name} = RequestHandler.get${item.type}(request, "${item.name}");
			${domainName?lower_case}.set${item.name?cap_first}(${item.name});
			
		</#list>

			${domainName?lower_case}Service.update${domainName?cap_first}(${domainName?lower_case});
			writeSuccessMsg("修改成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/remove${domainName?cap_first}", method = RequestMethod.POST)
	public String remove${domainName?cap_first}(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{
			${domainName?cap_first} ${domainName?lower_case} = new ${domainName?cap_first}();
			Integer ${pkName} = RequestHandler.getInteger(request, "${pkName}");
			${domainName?lower_case}.set${pkName?cap_first}(${pkName});

			${domainName?lower_case}Service.remove${domainName?cap_first}(${domainName?lower_case});
			writeSuccessMsg("删除成功", null, response);
		} catch (Exception e)
		{
			writeErrorMsg(e.getLocalizedMessage(), null, response);
		}
		return null;
	}
	@RequestMapping(value = "/removeAll${domainName?cap_first}", method = RequestMethod.POST)
	public String removeAll${domainName?cap_first}(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		try
		{	
			String ${pkName}s = RequestHandler.getString(request, "${pkName}s");
			if(${pkName}s != null){
				String[] ${pkName}Str = ${pkName}s.split(",");
				if(${pkName}Str != null && ${pkName}Str.length != 0){
					for (String ${pkName} : ${pkName}Str) {
						${domainName?cap_first} ${domainName} = new ${domainName?cap_first}();
						${domainName}.set${pkName?cap_first}(Integer.valueOf(${pkName}));
						${domainName?lower_case}Service.remove${domainName?cap_first}(${domainName});
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
