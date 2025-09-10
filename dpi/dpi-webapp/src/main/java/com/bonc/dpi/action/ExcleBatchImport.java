package com.bonc.dpi.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.bonc.dpi.service.DomainRuleService;
import com.bonc.dpi.utils.DpiUtils;


/**
 * excle批量导入
 * @author BONC-XUXL
 *
 */
@Controller
@RequestMapping(value = "/excleBatchImport")
public class ExcleBatchImport {
	
	@Resource
	private DomainRuleService drService;
	
	/**
	 * 问题数据查看页面
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/falseDataViewFromExcle")
	public String falseDataViewFromExcle(HttpServletRequest request) throws Exception{
		
		//列表信息
		request.setAttribute("invalidDataInExcle_repeatPrimaryKey", drService.invalidDataInExcle_repeatPrimaryKey);//主键验证
		request.setAttribute("invalidDataInExcle_empty", drService.invalidDataInExcle_empty);//非空验证
		request.setAttribute("invalidDataInExcle_tooLong", drService.invalidDataInExcle_tooLong);//属性过长验证
		request.setAttribute("invalidDataInExcle_notTrue_prodId", drService.invalidDataInExcle_notTrue_prodId);//业务验证
		request.setAttribute("list_false_cache", drService.list_false_cache);//excle中与库中主键过长
		
		//提示信息
		DpiUtils.excleFalseData_Info(request);
		
		return "pages/jsp/dpi/domainRule/falseDataViewFromExcle";
	}

}
