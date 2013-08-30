package cn.itcast.web.formbean.user;

import cn.itcast.bean.user.Buyer;
import cn.itcast.service.user.BuyerService;
import cn.itcast.service.user.impl.BuyerServiceBean;
import cn.itcast.web.action.user.BuyerForm;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * desc:
 * User: weiguili(li5220008@gmail.com)
 * Date: 13-8-29
 * Time: 上午10:26
 */
@Controller("/user/reg")
public class BuyerRegAction extends DispatchAction {
    @Resource
    BuyerService buyerService;
    /**
     * 显示注册页面
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward regUI(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response) throws Exception {
        return mapping.findForward("regUI");
    }
    /**
     * 处理注册页面
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward reg(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response) throws Exception {
        BuyerForm formbean = (BuyerForm)form;
        if(buyerService.exit(formbean.getUsername().trim())){
            request.setAttribute("error", "该用户已经存在");
            return mapping.findForward("regUI");
        } else{
            Buyer buyer = new Buyer();
            buyer.setUsername(formbean.getUsername().trim());
            buyer.setPassword(formbean.getPassword().trim());
            buyer.setEmail(formbean.getEmail().trim());
            buyerService.save(buyer);
            request.setAttribute("message", "用户添加成功！");
            request.setAttribute("urladdress", "/");
            return mapping.findForward("message");
        }
    }

    /**
     * 检验用户名是否存在
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward isUserExsit(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response) throws Exception {
        BuyerForm formbean = (BuyerForm)form;
        request.setAttribute("exsit", buyerService.exit(formbean.getUsername().trim()));
        return mapping.findForward("checkResult");
    }
}
