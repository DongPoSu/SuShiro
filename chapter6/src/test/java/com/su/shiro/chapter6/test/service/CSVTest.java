package com.su.shiro.chapter6.test.service;

import com.opencsv.CSVWriter;
import com.opencsv.bean.BeanToCsv;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.MappingStrategy;
import junit.framework.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: suzheng
 * @Version:
 * @Package: com.su.shiro.chapter6.test.service
 * @Company: SIBU_KANGER
 * @Description:
 * @Date: 2016/05/27
 */
public class CSVTest {
    @Test
    public void writerCsv() throws IOException {
        File file = new File("write.csv");
        Writer writer = new FileWriter(file);
        CSVWriter csvWriter = new CSVWriter(writer, ',');
        String[] strs = {"abc", "abc", "abc"};
        csvWriter.writeNext(strs);
        csvWriter.close();
    }

    @Test
    public void beanToCsv() {
//        String[] strHeads = {"序号", "推送号", "订单号", "快递公司", "当前快递单号", "原始快递单号", "推送时间", "发货时间", "收件人", "收件人地址", "产品名称", "产品数量", "买家备注", "卖家备注"};
        String[] strs = {"password", "name","orderCode"};
        File file = new File("beantocsv.csv");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        List<User> list = new ArrayList<User>();
        for (int i = 0; i < 100; i++) {
            list.add(new User("su", "0asdjflajsdlfja","03513156161"));
        }
        ColumnPositionMappingStrategy<User> mappingStrategy = new ColumnPositionMappingStrategy<User>();
        mappingStrategy.setType(User.class);
        mappingStrategy.setColumnMapping(strs);
        try {
            Writer writer = new FileWriter(file);
            CSVWriter csvWriter = new CSVWriter(writer);
            MyBeanToCsv beanToCsv = new MyBeanToCsv();
            beanToCsv.write(mappingStrategy, csvWriter, list);
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void beanToCsv2() throws Exception{
        List<User> list = new ArrayList<User>();
        for (int i = 0; i < 10; i++) {
            list.add(new User("su", null, null));
        }
        List<String[]> strs = new ArrayList<String[]>();
        for (User user : list) {
            strs.add(user.convertToStrings());
        }
        File file = new File("test.csv");
        Writer writer = new FileWriter(file);
        CSVWriter csvWriter = new CSVWriter(writer, ',');
        csvWriter.writeAll(strs);
        csvWriter.close();
    }
}

