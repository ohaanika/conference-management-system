package model;

import java.util.List;
import java.util.UUID;

public class HotelRoom {
	private UUID id;
    private int roomNumber;
    private int capacity;
    private int numOccupied;
    private int numAvailable;
    
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
    public int getRoomNumber() {
        return roomNumber;
    }
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public int getNumAvailable() {
        return numAvailable;
    }
    public void setNumAvailable(int numAvailable) {
        this.numAvailable = numAvailable;
    }
    public int getNumOccupied() {
        return numOccupied;
    }
    public void setNumOccupied(int numOccupied) {
        this.numOccupied = numOccupied;
    }
}
