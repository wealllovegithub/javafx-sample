package org.saltations;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StockEvt {

	SIX_WEEKS_OUT            ( "Six Weeks Out"),
	FIVE_WEEKS_OUT           ( "Five Weeks Out"),
	FOUR_WEEKS_OUT           ( "Four Weeks Out"),
	THREE_WEEKS_OUT          ( "Three Weeks Out"),
	TWO_WEEKS_OUT            ( "Two Weeks Out"),
	ONE_WEEKS_OUT            ( "One Weeks Out"),
	COACHES_TRAINING         ( "Coaches Training"),
	WORK_DAY_I               ( "Work Day I"),
	CLASSROOM_1              ( "Classroom 1"),
	CLASSROOM_2              ( "Classroom 2"),
	CLASSROOM_3              ( "Classroom 3"),
	CLASSROOM_4              ( "Classroom 4"),
	WORK_DAY_II              ( "Work Day II"),
	ALT_WORK_DAY_II          ( "Alternate Work Day II"),
	CLASSROOM_5              ( "Classroom 5"),
	CLASSROOM_6              ( "Classroom 6"),
	CLASSROOM_7              ( "Classroom 7"),
	CLASSROOM_8              ( "Classroom 8"),
	FUTURES_MEETING_TRAINING ( "Futures Meeting Training"),
	WORK_DAY_III             ( "Work Day III"),
	ALT_WORK_DAY_III         ( "Alternate Work Day III"),
	CLASSROOM_9              ( "Classroom 9"),
	CLASSROOM_10             ( "Classroom 10"),
	CLASSROOM_11             ( "Classroom 11"),
	CLASSROOM_12             ( "Classroom 12");
	
	private String value;
	
	@Override
	public String toString() {
		return value;
	}
	
}

