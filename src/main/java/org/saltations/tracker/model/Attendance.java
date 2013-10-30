/**
 * 
 */
package org.saltations.tracker.model;

import java.util.Map;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.joda.time.LocalDate;

import com.google.common.collect.Maps;

/**
 * Responsible for tracking and evaluating attendance 
 * 
 * @author jmochel
 */

@Data
@NoArgsConstructor
public class Attendance {

	Map<LocalDate, AttendanceCommunication> communicated = Maps.newHashMap();
	
	Map<LocalDate, AttendanceResult> actual = Maps.newHashMap();
	
}
