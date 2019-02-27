--table for Attendee
CREATE TABLE Attendee (
	aID UUID NOT NULL,
	firstName VARCHAR(50) NOT NULL,
	LastName VARCHAR(50) NOT NULL,
	dietaryRestrictions VARCHAR (10),
	shirtCut VARCHAR(1),
	shirtSize VARCHAR(3),
	PRIMARY KEY (aID)
);

--table for Delegate
CREATE TABLE Delegate (
	aID UUID NOT NULL,
	university VARCHAR(100),
	major VARCHAR(50),
	province VARCHAR(20),
	PRIMARY KEY (aID),
	FOREIGN KEY (aID) REFERENCES Attendee(aID)
);

--table for Organizer
CREATE TABLE Organizer (
	aID UUID NOT NULL,
	role VARCHAR(20),  
	PRIMARY KEY (aID),
	FOREIGN KEY (aID) REFERENCES Attendee(aID)
);

--table for Speaker
CREATE TABLE Speaker (
	aID UUID NOT NULL,
	orgName VARCHAR(20),  
	talkID UUID NOT NULL,
	PRIMARY KEY (aID),
	FOREIGN KEY (aID) REFERENCES Attendee(aID),
	FOREIGN KEY (talkID) REFERENCES Talk(eID)
);

--table for Sponsor
CREATE TABLE Sponsor (
	aID UUID NOT NULL,  
	companyID VARCHAR(50) NOT NULL,
	PRIMARY KEY (aID, companyID),
	FOREIGN KEY (aID) REFERENCES Attendee(aID),
	FOREIGN KEY (companyID) REFERENCES Company(cname)
);

--table for Company
CREATE TABLE Company (
	cname VARCHAR(50) NOT NULL,
	tier VARCHAR(20),  
	workshopID UUID NOT NULL,
	PRIMARY KEY (cname),
	FOREIGN KEY (workshopID) REFERENCES Workshop(eID)
);

--table for CompanySponsors
/*
CREATE TABLE CompanySponsors(
	companyID VARCHAR(50) NOT NULL,
	sponsorID UUID NOT NULL, 
	FOREIGN KEY (companyID) REFERENCES Company(cname),
	FOREIGN KEY (sponsorID) REFERENCES Sponsors(aID)
);
*/

--table for TimeSlot
CREATE TABLE TimeSlot (
	startTime TIME, 
	endTime TIME, 
	slotDate DATE,
	PRIMARY KEY (startTime, endTime, slotDate)
);

--table for Location
CREATE TABLE Location(
	timeSlotID VARCHAR(50) NOT NULL UNIQUE,
	lname VARCHAR (20) NOT NULL,
	capacity int,
	PRIMARY KEY (lname)
);

--table for Events
CREATE TABLE Event (
	eID int NOT NULL, 
	title VARCHAR (30),
	timeSlotID VARCHAR(50),
	locationID int,
	PRIMARY KEY (eID),
	FOREIGN KEY (timeSlotID) references TimeSlot(timeSlotID),
	FOREIGN KEY (locationID) references Location(lname)
);

--table for LocationAtTimeSlot
CREATE TABLE LocationAtTimeSlot (
	locationID VARCHAR (20), 
	timeSlotID VARCHAR(50),
	FOREIGN KEY (locationID) references Location(lname),
	FOREIGN KEY (timeSlotID) references TimeSlot(timeSlotID)
);

--table for Interview
--An interview is must be exactly between one delegate and a sponsor (participation constraint) 
CREATE TABLE Interview (
	eID int NOT NULL, 
	delegateID UUID, 
	sponsorID UUID, 
	PRIMARY KEY (eID),
	FOREIGN KEY (eID) references Events(eID),
	FOREIGN KEY (delegateID) references Delegate(aID),
	FOREIGN KEY (sponsorID) references Sponsor(aID)
);

--table for Workshop
CREATE TABLE Workshop (
	eID int NOT NULL, 
	title VARCHAR(30), 
	sponsorID UUID, 
	PRIMARY KEY (eID),
	FOREIGN KEY (eID) references Event(eID),
	FOREIGN KEY (sponsorID) references Sponsor(aID)
);

--table for Talk
CREATE TABLE Talk (
	eID int NOT NULL, 
	title VARCHAR (30), 
	speakerID UUID,
	Primary Key (eID),
	FOREIGN KEY (eID) references Event(eID),
	FOREIGN KEY (speakerID) references Speaker(aID)
);

--table for Task
CREATE TABLE Task (
	tID int NOT NULL, 
	description VARCHAR(30), 
	deadlineDate DATE,
	deadlineTime TIME,
	status BOOLEAN,
	PRIMARY KEY (tID)
);

--table for OrganizerTasks
CREATE TABLE OrganizerTasks(
	organizerID UUID, 
	taskID int,
	FOREIGN KEY (organizerID) references Organizer(aID),
	FOREIGN KEY (taskID) references Task(tID)
);

--table for HotelBooking
CREATE TABLE HotelBooking (
	hID VARCHAR(100) NOT NULL, 
	roomNumber int, 
	capacity int,
	PRIMARY KEY (hID)
);

--table for AttendeeHotelBookings
CREATE TABLE AttendeeHotelBookings(
	aID int,
	roomNumber int, 
	PRIMARY KEY (aID),
	FOREIGN KEY (roomNumber) references HotelBooking(hID),
	FOREIGN KEY (aID) references Attendee(aID)
);


