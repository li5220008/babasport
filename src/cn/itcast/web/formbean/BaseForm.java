package cn.itcast.web.formbean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class BaseForm extends ActionForm {
    /** 获取当前页 **/
    private int page;
    /** 设置是否进行查找 **/
    private String query;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
    public int getPage() {
        return page<1? 1 : page;
    }

    public void setPage(int page) {
        this.page = page;
    }


}
