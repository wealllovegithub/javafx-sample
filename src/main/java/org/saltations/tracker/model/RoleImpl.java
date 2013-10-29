/**
 * 
 */
package org.saltations.tracker.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Delegate;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import com.eaio.uuid.UUID;

/**
 * @author jmochel
 *
 */

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class RoleImpl implements Role, Serializable  {

	private static final long serialVersionUID = 1L;

	private UUID id = new UUID();
	
	@NonNull
	@Delegate
	private Person person;
	
    /**
     * Are they a graduate flag
     */

	@NonNull
    private Boolean graduate = true;
	

}
