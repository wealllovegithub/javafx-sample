package org.saltations.tracker.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum State {

	MA("MA"),
	RI("RI"),
	NH("NH"),
	VT("VT"),
	ME("ME"),
	NY("NY"),
	AK("AK"),
	CT("CT");
	
    private String value;
    
    @Override
    public String toString() {
            return value;
    }
}
