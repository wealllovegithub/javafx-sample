/**
 * 
 */
package org.saltations.tracker.model;

import lombok.AllArgsConstructor;

/**
 * @author jmochel
 *
 */

@AllArgsConstructor
public enum StockAgreements {
	
	AGREEMENT_AND_WAIVER("Handed in Agreement and Waiver"),
	ATTENDANCE_COMMUNICATION("Handed in Attendance Communication"),
	PARTICIPATION_INFO_FORM("Handed in Participation Information Form"),
	PARTICIPATION_WORKSHEET("Handed in Participation Worksheet"),
	SCHEDULED_COACHING_CALL("Scheduled their Coaching Call"),
	SCHEDULED_FUTURES_MEETING("Scheduled their Futures Meeting"),
	SCHEDULED_GROUP_CALL("Scheduled their Group Call"),
	SCHEDULED_ILF("Scheduled their Intro to the LM Forum"),
	HANDED_IN_SHORT_CPF("Handed in their Community Project Short Form"),
	HANDED_IN_LONG_CPF("Handed in their Community Project Long Form");
	
    private String value;
    
    @Override
    public String toString() {
            return value;
    }
}
