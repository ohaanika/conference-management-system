package managementservices;

import model.HotelRoom;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HotelBookingManagementService {
	
    public HotelRoom findHotelRoomByRoomNumber(int roomNumber, List<HotelRoom> listRooms) {
	    for(HotelRoom hotelRoom : listRooms) {
	        if(hotelRoom.getRoomNumber() == roomNumber) {
	            return hotelRoom;
	        }
	    }
	    return null;
    }
	
	public List<HotelRoom> findAvailableRooms(int numAttendees) {
		List<HotelRoom> availableHotelRooms = new ArrayList<>();
		Connection connection = ConnectionManager.getConnectionInstance();
		PreparedStatement basicRoom;
		// The SQL for finding all available rooms with specified space
		String findRoomsSQL = "SELECT * FROM (SELECT H.hID, H.roomNumber, H.capacity, count(*) as numOccupied, H.capacity-count(*) as numAvailable  FROM AttendeeHotelBookings as AH, HotelBooking as H WHERE AH.hID = H.hID GROUP BY H.hID, H.roomNumber, H.capacity UNION SELECT H.hID, H.roomNumber, H.capacity, 0 as numOccupied, H.capacity as numAvailable FROM HotelBooking as H WHERE H.hID NOT IN (SELECT DISTINCT hID FROM AttendeeHotelBookings) GROUP BY H.hID, H.roomNumber, H.capacity) AS T WHERE numAvailable >=" + Integer.toString(numAttendees) + "ORDER BY roomNumber";
		// Finding all available rooms with specified space (i.e. number of attendees to fit in a room)
		try {
			basicRoom = connection.prepareStatement(findRoomsSQL);
            ResultSet rs = basicRoom.executeQuery();
            while (rs.next()){
                HotelRoom hotelRoom = new HotelRoom();
                hotelRoom.setId(UUID.fromString(rs.getString("hID")));
                hotelRoom.setRoomNumber(Integer.parseInt(rs.getString("roomnumber")));
                hotelRoom.setCapacity(Integer.parseInt(rs.getString("capacity")));
                hotelRoom.setNumOccupied(Integer.parseInt(rs.getString("numoccupied")));
                hotelRoom.setNumAvailable(Integer.parseInt(rs.getString("numavailable")));
                availableHotelRooms.add(hotelRoom);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // return list of available rooms with specified space 
        return availableHotelRooms;
    }

    public void assignRoom(String aID, int size) {
    	

    }

}
