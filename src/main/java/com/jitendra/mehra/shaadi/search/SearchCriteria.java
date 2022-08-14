package com.jitendra.mehra.shaadi.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class SearchCriteria {
	
	private String key;
    private String operation;
    private Object value;
	public boolean isOrPredicate() { 
		return false;
	}
	 

}
