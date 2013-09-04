package cn.itcast.web.action.user;

import cn.itcast.bean.PageView;
import cn.itcast.bean.QueryResult;
import cn.itcast.bean.user.Buyer;
import cn.itcast.service.user.BuyerService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;

/**
 * desc: 用户分页列表
 * User: weiguili(li5220008@gmail.com)
 * Date: 13-9-3
 * Time: 上午9:47
 */
@Controller("/control/user/list")
public class BuyerListAction extends Action {
    @Resource BuyerService buyerService;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception {
        BuyerForm formbean = (BuyerForm)form;
        PageView<Buyer> pageView = new PageView<Buyer>(10,formbean.getPage());
        LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
        orderby.put("regTime", "desc");
        pageView.setQueryResult(buyerService.getScrollData(pageView.getFirstResult(), pageView.getMaxresult()));
        request.setAttribute("pageView", pageView);
        return mapping.findForward("list");
    }
}
