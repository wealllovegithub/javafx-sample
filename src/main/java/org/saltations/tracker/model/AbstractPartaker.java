/**
 * 
 */
package org.saltations.tracker.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.saltations.tracker.infra.Display;

/**
 * @author jmochel
 *
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class AbstractPartaker extends AbstractGraduate {

	@Display(name="Agreement and Waiver In")
	private Boolean handedInAgreementAndWaiver = false;

	@Display(name="Attendance Form In")
	private Boolean handedInAttendanceCommunication = false;
	
	@Display(name="Participation Information In")
	private Boolean handedInParticipationInformationForm  = false;
	
	@Display(name="Participation Worksheet In")
	private Boolean handedInParticipationWorksheet  = false;
	
	@Display(name="Scheduled Coaching Call")
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
