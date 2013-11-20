/**
 * 
 */
package org.saltations.tracker.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author jmochel
 *
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class AbstractPartaker extends AbstractGraduate {

	private Boolean handedInAgreementAndWaiver = false;

	private Boolean handedInAttendanceCommunication = false;
	
	private Boolean handedInParticipationInformationForm  = false;
	
	private Boolean handedInParticipationWorksheet  = false;
	
	private Boolean scheduledCoachingCall  = false;
	
	private Boolean scheduledFuturesMeeting  = false;
	
	private Boolean scheduledGroupCall  = false;
	
	private Boolean scheduledIntro  = false;
	
	private Boolean handedInCommunityProjectShortForm  = false;
	
	private Boolean handedInCommunityProjectLongForm  = false;

	Attendance attendance = new Attendance();
	
	private static final long serialVersionUID = 1L;

	public AbstractPartaker(RoleType type, Person person) {
		super(type, person);
	}
}
