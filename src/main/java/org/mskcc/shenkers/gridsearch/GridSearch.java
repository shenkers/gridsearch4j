/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mskcc.shenkers.gridsearch;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sol
 */
public class GridSearch {

    Map<String, List> fieldGrid;
    int nFields;

    Object obj;
    Class<? extends Object> clazz;

    boolean hasNext = true;

    List<String> fieldNames;
    List<Field> fields;
    List<List<Object>> values;
    List<Integer> sizes;
    List<Integer> indexes;

    long searchIndex;
    long gridSize = 1;

    public GridSearch(Map<String, List> fieldGrid, Object obj) throws SecurityException, NoSuchFieldException {
        this.fieldGrid = fieldGrid;
        nFields = fieldGrid.size();

        fieldNames = new ArrayList<String>(fieldGrid.keySet());
        fields = new ArrayList<Field>(nFields);
        values = new ArrayList<List<Object>>(nFields);
        sizes = new ArrayList<Integer>(nFields);
        indexes = new ArrayList<Integer>(nFields);

        this.obj = obj;
        clazz = obj.getClass();

        for (int i = 0; i < fieldNames.size(); i++) {
            String fieldName = fieldNames.get(i);
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            fields.add(field);
            List<Object> fieldValues = fieldGrid.get(fieldName);
            values.add(fieldValues);
            sizes.add(fieldValues.size());
            indexes.add(0);
            gridSize *= fieldValues.size();
        }
    }

    public boolean hasNext() {
        return hasNext;
    }

    public void next() throws IllegalArgumentException, IllegalAccessException {
        for (int i = 0; i < indexes.size(); i++) {
            int index = indexes.get(i);
            Object value = values.get(i).get(index);
            fields.get(i).set(obj, value);
        }
        searchIndex++;
        hasNext = increment();
    }

    private boolean increment() {
        boolean carry_over = false;
        int i = 0;
        do {
            if (indexes.get(i) == sizes.get(i) - 1) {
                indexes.set(i, 0);
                carry_over = true;
                i++;
            } else {
                indexes.set(i, indexes.get(i) + 1);
                carry_over = false;
                return true;
            }
        } while (carry_over && i < indexes.size());
        return false;
    }

    public long getSearchIndex() {
        return searchIndex;
    }

    public long getGridSize() {
        return gridSize;
    }

}
