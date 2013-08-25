package junit.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Desc:
 *
 * @author : weiguili(li5220008@gmail.com)
 *         Date: 13-8-25
 *         Time: 下午12:57
 */
public class BuyerTest {
    //private static BuyerService buyerService;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }


    @Test
    public void save(){
        ApplicationContext act = new ClassPathXmlApplicationContext("beans.xml");
    }
}
