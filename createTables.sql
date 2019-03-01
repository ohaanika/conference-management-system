--table for Attendee
CREATE TABLE Attendee
(
	aID UUID,
	firstName VARCHAR(50) NOT NULL,
	lastName VARCHAR(50) NOT NULL,
	emailAddress VARCHAR(50),
	shirtCut VARCHAR(1),
	shirtSize VARCHAR(3),
	dietaryRestrictions VARCHAR(10),
	PRIMARY KEY (aID)
);

--table for Delegate
CREATE TABLE Delegate
(
	aID UUID,
	major VARCHAR(50),
	university VARCHAR(100),
	province VARCHAR(20),
	PRIMARY KEY (aID),
	FOREIGN KEY (aID) REFERENCES Attendee(aID)
);

--table for Organizer
CREATE TABLE Organizer
(
	aID UUID,
	role VARCHAR(20),
	PRIMARY KEY (aID),
	FOREIGN KEY (aID) REFERENCES Attendee(aID)
);

--table for Speaker
CREATE TABLE Speaker
(
	aID UUID,
	PRIMARY KEY (aID),
	FOREIGN KEY (aID) REFERENCES Attendee(aID)
);

--table for Sponsor
CREATE TABLE Sponsor
(
	aID UUID,
	companyID VARCHAR(50) NOT NULL,
	PRIMARY KEY (aID),
	FOREIGN KEY (aID) REFERENCES Attendee(aID),
	FOREIGN KEY (companyID) REFERENCES Company(cname)
);

--table for Company
CREATE TABLE Company
(
	cname VARCHAR(50),
	tier VARCHAR(20) NOT NULL,
	PRIMARY KEY (cname)
);

--table for Location
CREATE TABLE Location
(
	lname VARCHAR(20),
	capacity int NOT NULL,
	PRIMARY KEY (lname)
);

--table for Events
CREATE TABLE Event
(
	eID UUID,
	title VARCHAR(30),
	size int NOT NULL,
	startTime time,
	endTime time,
	eventDate date,
	locationID VARCHAR(20),
	PRIMARY KEY (eID),
	FOREIGN KEY (locationID) references Location(lname)
);

--table for Interview
--An interview is must be exactly between one delegate and a sponsor (participation constraint) 
CREATE TABLE Interview
(
	eID UUID,
	delegateID UUID NOT NULL,
	sponsorID UUID NOT NULL,
	PRIMARY KEY (eID),
	FOREIGN KEY (eID) references Event(eID),
	FOREIGN KEY (delegateID) references Delegate(aID),
	FOREIGN KEY (sponsorID) references Sponsor(aID)
);

--table for Workshop
CREATE TABLE Workshop
(
	eID UUID,
	companyID UUID NOT NULL,
	PRIMARY KEY (eID),
	FOREIGN KEY (eID) references Event(eID),
	FOREIGN KEY (companyID) references Company(aID)
);

--table for Talk
CREATE TABLE Talk
(
	eID UUID,
	speakerID UUID,
	Primary Key (eID),
	FOREIGN KEY (eID) references Event(eID),
	FOREIGN KEY (speakerID) references Speaker(aID)
);

--table for Task
CREATE TABLE Task
(
	tID UUID,
	description VARCHAR(30) NOT NULL,
	deadlineDate DATE,
	deadlineTime TIME,
	complete BOOLEAN,
	PRIMARY KEY (tID)
);

--table for EventTasks
CREATE TABLE EventTasks
(
	eventID UUID,
	taskID UUID NOT NULL,
	FOREIGN KEY (eventID) references Event(aID),
	FOREIGN KEY (taskID) references Task(tID)
);

--table for OrganizerTasks
CREATE TABLE OrganizerTasks
(
	organizerID UUID,
	taskID UUID NOT NULL,
	FOREIGN KEY (organizerID) references Organizer(aID),
	FOREIGN KEY (taskID) references Task(tID)
);

--table for HotelBooking
CREATE TABLE HotelBooking
(
	hID UUID,
	roomNumber int,
	capacity int,
	PRIMARY KEY (hID)
);

--table for AttendeeHotelBookings
CREATE TABLE AttendeeHotelBookings
(
	aID UUID,
	hID UUID NOT NULL,
	FOREIGN KEY (hID) references HotelBooking(hID),
	FOREIGN KEY (aID) references Attendee(aID)
);


