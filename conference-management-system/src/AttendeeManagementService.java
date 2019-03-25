import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import Model.Attendee;
import Model.Delegate;
import Model.Speaker;
import Model.Sponsor;

public class AttendeeManagementService {

	/**
	 * Registers an attendee of specific type. Initializes the properties, generates
	 * the ID.
	 *
	 * @param firstName
	 * @param lastName
	 * @param emailAddress
	 * @param shirtCut
	 * @param shirtSize
	 * @param dietaryRestrictions
	 */
	
	// An entry in the table is created for any attendee of any type due to ISA
			// relationship.

	public String createAttendee(String type, String firstName, String lastName, String emailAddress, String shirtCut,
			String shirtSize, String dietaryRestrictions, String major, String university, String province, String role,
			String cname, String tier) {
		
		UUID aid = UUID.randomUUID();
		
		Connection connection = ConnectionManager.getConnectionInstance();
		
		PreparedStatement attendee = null;
		
        String createAttendeeSQL = "INSERT INTO attendee VALUES('" + aid.toString() + "','" + firstName + "','" + lastName + "','" + emailAddress + "','" 
		+ shirtCut + "','" + shirtSize + "','" + dietaryRestrictions +"');";
	
        try {
            attendee = connection.prepareStatement(createAttendeeSQL);
            attendee.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
        if(type.toLowerCase().equals("delegate")) {
        	return createDelegate(aid, major, university, province);
        }
        
        else if(type.toLowerCase().equals("organizer")) {
        	return createOrganizer(aid, role);
        }
        
        else if (type.toLowerCase().equals("sponsor")) {
        	return createSponsor(aid, cname);
        }
        
        else {
        	return createCompany(cname, tier);
        }
        
	}
	
	private String createDelegate (UUID aid, String major, String university, String province) {
		Connection connection = ConnectionManager.getConnectionInstance();
		
		PreparedStatement delegate = null;
		
		String createDelegateSQL ="INSERT INTO delegate VALUES('" + aid.toString() + "','" + major + "','" + university + "','" + province + "');";
		
		try {
            delegate = connection.prepareStatement(createDelegateSQL);
            delegate.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
        return aid.toString();
		
	}
	
	private String createOrganizer (UUID aid, String role) {
		Connection connection = ConnectionManager.getConnectionInstance();
		
		PreparedStatement organizer = null;
		
		String createOrganizerSQL ="INSERT INTO organizer VALUES('" + aid.toString() + "','" + role + "');";
		
		try {
            organizer = connection.prepareStatement(createOrganizerSQL);
            organizer.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
        return aid.toString();
		
	}
	
	private String createSponsor (UUID aid, String cname) {
		
		Connection connection = ConnectionManager.getConnectionInstance();
		
		PreparedStatement sponsor = null;
		
		String createSponsorSQL ="INSERT INTO sponsor VALUES('" + aid + "','" + cname + "');";
		
		try {
            sponsor = connection.prepareStatement(createSponsorSQL);
            sponsor.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
        return aid.toString();
}
	
	private String createCompany (String cname, String tier) {
		Connection connection = ConnectionManager.getConnectionInstance();
		
		PreparedStatement company = null;
		
		String createCompanySQL ="INSERT INTO company VALUES('" + cname + "','" + tier + "');";
		
		try {
            company = connection.prepareStatement(createCompanySQL);
            company.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		return cname;
	}
	
	

	public List<Attendee> getAttendeeIDsFromName(String FirstName, String LastName) {
		Connection connection = ConnectionManager.getConnectionInstance();

		PreparedStatement basicGetting = null;
		String getAttendeeSQL = "SELECT aid,emailaddress FROM Attendee WHERE firstname= '" + FirstName
				+ "' AND lastname= '" + LastName + "';";
		List<Attendee> attendees = new ArrayList<Attendee>();
		// An entry in the table is created for any event of any type due to ISA
		// relationship.
		try {
			basicGetting = connection.prepareStatement(getAttendeeSQL);
			ResultSet rs = basicGetting.executeQuery();
			while (rs.next()) {
				UUID aid = UUID.fromString(rs.getString("aid"));

				String email = rs.getString("emailaddress");
				Attendee attendee = new Attendee();
				attendee.setEmail(email);
				attendee.setFn(FirstName);
				attendee.setLn(LastName);
				attendee.setId(aid);
				attendees.add(attendee);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return attendees;

	}

	public List<Sponsor> getSponsorIDsFromName(String firstName, String lastName){
		Connection connection = ConnectionManager.getConnectionInstance();

		PreparedStatement basicGetting = null;
		String getAttendeeSQL = "SELECT Attendee.aid,emailaddress,companyid FROM Attendee JOIN Sponsor ON Attendee.aid=Sponsor.aid WHERE firstname= '" + firstName
				+ "' AND lastname= '" + lastName + "';";
		List<Sponsor> sponsors= new ArrayList<>();
		try {
			basicGetting = connection.prepareStatement(getAttendeeSQL);
			ResultSet rs = basicGetting.executeQuery();
			while (rs.next()) {
				UUID aid = UUID.fromString(rs.getString("aid"));

				String email = rs.getString("emailaddress");
				Sponsor sponsor = new Sponsor();
				sponsor.setId(aid);
				sponsor.setEmail(email);
				sponsor.setFirstName(firstName);
				sponsor.setLastName(lastName);
				sponsors.add(sponsor);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return sponsors;

	}

	public List<Delegate> getDelegateIDsFromName(String firstName, String lastName){
		Connection connection = ConnectionManager.getConnectionInstance();

		PreparedStatement basicGetting = null;
		String getAttendeeSQL = "SELECT Attendee.aid,emailaddress,major,university FROM Attendee JOIN Delegate ON Attendee.aid=Delegate.aid WHERE firstname= '" + firstName
				+ "' AND lastname= '" + lastName + "';";
		List<Delegate> delegates= new ArrayList<>();
		try {
			basicGetting = connection.prepareStatement(getAttendeeSQL);
			ResultSet rs = basicGetting.executeQuery();
			while (rs.next()) {
				UUID aid = UUID.fromString(rs.getString("aid"));

				String email = rs.getString("emailaddress");
				String university = rs.getString("university");
				String major = rs.getString("major");
				Delegate delegate = new Delegate();
				delegate.setId(aid);
				delegate.setEmail(email);
				delegate.setFirstName(firstName);
				delegate.setLastName(lastName);
				delegate.setUniversity(university);
				delegate.setMajor(major);
				delegates.add(delegate);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return delegates;
	}

	public List<Speaker> getSpeakerIDsFromName(String firstName, String lastName){
		Connection connection = ConnectionManager.getConnectionInstance();

		PreparedStatement basicGetting = null;
		String getAttendeeSQL = "SELECT Attendee.aid,emailaddress FROM Attendee JOIN Speaker ON Attendee.aid=Speaker.aid WHERE firstname= '" + firstName
				+ "' AND lastname= '" + lastName + "';";
		List<Speaker> speakers= new ArrayList<>();
		try {
			basicGetting = connection.prepareStatement(getAttendeeSQL);
			ResultSet rs = basicGetting.executeQuery();
			while (rs.next()) {
				UUID aid = UUID.fromString(rs.getString("aid"));
				String email = rs.getString("emailaddress");
				Speaker speaker = new Speaker();
				speaker.setId(aid);
				speaker.setEmail(email);
				speakers.add(speaker);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return speakers;
	}

}
