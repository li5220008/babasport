package cn.itcast.web.action.user;

import cn.itcast.service.user.BuyerService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * desc: 客户登录
 * User: weiguili(li5220008@gmail.com)
 * Date: 13-8-30
 * Time: 上午10:10
 */
@Controller("/user/logon")
public class BuyerLogonAction extends Action {
    @Resource BuyerService buyerService;
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BuyerForm formbean = (BuyerForm)form;
        if(formbean.getUsername()!=null && !"".equals(formbean.getUsername().trim())&&
                formbean.getPassword()!=null && !"".equals(formbean.getPassword().trim())
                ){
            if(buyerService.validate(formbean.getUsername().trim(), formbean.getPassword().trim())){
                request.getSession().setAttribute("user",buyerService.find(formbean.getUsername().trim()));
                request.setAttribute("message", "用户登录成功");
                request.setAttribute("urladdress","/");
                return mapping.findForward("message");
            }else {
                request.setAttribute("message", "用户名或密码有误");
            }
        }
        return mapping.findForward("logon");
    }
}
