/**
 * 
 */
package org.saltations.tracker.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author jmochel
 *
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class AbstractPartaker extends AbstractGraduate {

	private boolean handedInAgreementAndWaiver = false;
	private boolean handedInAttendanceCommunication = false;
	private boolean handedInParticipationInformationForm  = false;
	private boolean handedInParticipationWorksheet  = false;
	private boolean scheduledCoachingCall  = false;
	private boolean scheduledFuturesMeeting  = false;
	private boolean scheduledGroupCall  = false;
	private boolean scheduledIntro  = false;
	private boolean handedInCommunityProjectShortForm  = false;
	private boolean handedInCommunityProjectLongForm  = false;

	Attendance attendance = new Attendance();
	
	private static final long serialVersionUID = 1L;

	public AbstractPartaker(RoleType type, Person person) {
		super(type, person);
	}
}
