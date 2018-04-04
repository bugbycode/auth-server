package com.util;

import java.util.Collection;

public class FortObjectUtil {
	
	@SuppressWarnings("unchecked")
	public static boolean isEmpty(Object obj) {
		Collection<Object> collection = null;
		if(obj == null) {
			return true;
		}else if(obj instanceof Collection) {
			collection = (Collection<Object>) obj;
		}else {
			return obj == null;
		}
		return collection == null || collection.isEmpty();
	}
	
	public static boolean isNotEmpty(Object obj) {
		return !isEmpty(obj);
	}
}
