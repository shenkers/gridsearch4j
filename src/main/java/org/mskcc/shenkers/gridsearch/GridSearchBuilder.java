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
public class GridSearchBuilder {

	@SuppressWarnings("rawtypes")
	Map<String,List> fieldGrid;
	
	@SuppressWarnings("rawtypes")
	public GridSearchBuilder(Map<String, List> fieldGrid) {
		this.fieldGrid = fieldGrid;
	}

        public GridSearch build(Object obj) throws SecurityException, NoSuchFieldException{
            return new GridSearch(fieldGrid, obj);
        }
}
