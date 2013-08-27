package cn.itcast.bean.user;

/**
 * desc: 性别
 * User: weiguili(li5220008@163.com)
 * Date: 13-8-27
 * Time: 下午3:23
 */
public enum Gender {
    MAN{
        public String getName(){return "男";};
    },
    WOMEN{
        public String getName(){return "女";}
    };
    public abstract String getName();
}
