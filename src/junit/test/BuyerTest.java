package junit.test;

import cn.itcast.bean.QueryResult;
import cn.itcast.bean.user.Buyer;
import cn.itcast.service.user.BuyerService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.LinkedHashMap;

/**
 * Desc:
 *
 * @author : weiguili(li5220008@gmail.com)
 *         Date: 13-8-25
 *         Time: 下午12:57
 */
public class BuyerTest {
    private static BuyerService buyerService;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        try {
            ApplicationContext act = new ClassPathXmlApplicationContext("beans.xml");
            buyerService = (BuyerService)act.getBean("buyerServiceBean");
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void save(){
        for(int i=40; i<100; i++){
            Buyer buyer = new Buyer();
            buyer.setUsername("limings"+i);
            buyer.setPassword("123456");
            buyer.setEmail("liming@sss.cn"+i);
            buyerService.save(buyer);
        }
    }

    @Test
    public void update(){
        Buyer buyer = buyerService.find("liming");
        buyer.setEmail("li@sstest.com");
        buyerService.update(buyer);
    }

    @Test
    public void find(){
        Buyer buyer = buyerService.find("liming");
        System.out.println(buyer.getEmail());
    }

    @Test
    public void getCount() {
        System.out.println(buyerService.getCount());
    }

    @Test
    public void getScrollData(){
        String where = "o.email=?1";
        Object[] params = new Object[]{"liming@sss.cn1"};

        LinkedHashMap<String,String> ordermap = new LinkedHashMap<String, String>();
        ordermap.put("email", "asc");
        ordermap.put("password", "desc");

        QueryResult<Buyer> qr = buyerService.getScrollData(0, 5, where, params, ordermap);
        for(Buyer buyer : qr.getResultlist()){
            System.out.println(buyer.getEmail());
        }

        System.out.println(qr.getRecordtotal());
    }
    @Test
    public void exit(){
        System.out.println(buyerService.exit("limings1sss"));
    }

}
