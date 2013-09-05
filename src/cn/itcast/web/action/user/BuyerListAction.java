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
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

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
        StringBuilder sb = new StringBuilder();
        List<Object> params = new ArrayList<Object>();
        if("true".equals(formbean.getQuery())){
            if(formbean.getUsername() != null && !"".equals(formbean.getUsername().trim())){
                params.add("%"+formbean.getUsername().trim()+"%");
                sb.append("o.username like ?").append(params.size());
            }

            if(formbean.getRealname() != null && !"".equals(formbean.getRealname().trim())){
                if(params.size()>0) sb.append(" and ");
                params.add("%"+formbean.getRealname().trim()+"%");
                sb.append("o.realname like ?").append(params.size());
            }

            if(formbean.getEmail() != null && !"".equals(formbean.getEmail().trim())){
                if((params.size()>0)) sb.append(" and ");
                params.add("%"+formbean.getEmail().trim()+"%");
                sb.append("o.email like ?").append(params.size());
            }
            pageView.setQueryResult(buyerService.getScrollData(pageView.getFirstResult(), pageView.getMaxresult(), sb.toString(), params.toArray() ,orderby));
        }else {
            pageView.setQueryResult(buyerService.getScrollData(pageView.getFirstResult(), pageView.getMaxresult(),orderby));
        }
        request.setAttribute("pageView", pageView);
        return mapping.findForward("list");
    }

}
