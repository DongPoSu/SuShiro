package com.su.shiro.chapter6.test.service;

import com.opencsv.CSVWriter;
import com.opencsv.bean.BeanToCsv;
import com.opencsv.bean.MappingStrategy;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by suzheng on 2016/5/29.
 */
public class MyBeanToCsv<T> extends BeanToCsv<T> {

    public boolean write(MappingStrategy<T> mapper, CSVWriter csv, List<?> objects) {
        if (objects == null || objects.isEmpty()) {
            return false;
        }
        try {
            List<Method> getters = findGetters(mapper);
            processAndWriteObjects(csv, objects, getters);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error writing CSV !", e);
        }
    }

    private void processAndWriteObjects(CSVWriter csv, List<?> objects, List<Method> getters) throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        for (Object obj : objects) {
            String[] line = processObject(getters, obj);
            csv.writeNext(line);
        }
    }

    protected String[] processObject(List<Method> getters, Object bean) throws IntrospectionException,
            IllegalAccessException, InvocationTargetException {
        List<String> values = new ArrayList<String>();
        // retrieve bean values
        for (Method getter : getters) {
            Object value = getter.invoke(bean, (Object[]) null);
            if (value == null) {
                values.add("null");
            } else {
                if ("getOrderCode".equals(getter.getName())) {
                    values.add("\'" + value.toString());
                }else {
                    values.add(value.toString());
                }

            }
        }
        return values.toArray(new String[0]);
    }

    private List<Method> findGetters(MappingStrategy<T> mapper)
            throws IntrospectionException {
        int i = 0;
        PropertyDescriptor prop = mapper.findDescriptor(i);
        // build getters methods list
        List<Method> readers = new ArrayList<Method>();
        while (prop != null) {
            readers.add(prop.getReadMethod());
            i++;
            prop = mapper.findDescriptor(i);
        }
        return readers;
    }

    private List<Field> findFields(MappingStrategy<T> mapper) throws IntrospectionException {
        int i = 0;
        PropertyDescriptor prop = mapper.findDescriptor(i);
        // build getters methods list
        List<Field> readers = new ArrayList<Field>();
        while (prop != null) {
            i++;
            prop = mapper.findDescriptor(i);
        }
        return readers;
    }
}
