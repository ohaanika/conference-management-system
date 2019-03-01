
--
SELECT firstName, lastName, emailAddress
FROM (Attendee JOIN (SELECT aID FROM Delegate WHERE university="McGill") ON Attendee.aID=Delegate.aID))

--
SELECT firstName, lastName, emailAddress
FROM (Attendee JOIN (SELECT aID FROM (Sponsor) WHERE companyID="Kinaxis") ON Attendee.aID=Sponsor.aID))

--
SELECT description,deadlineTime 
FROM Task
WHERE (status="false"&&deadlineDate="2019-01-09")

--
SELECT title 
FROM Event 
WHERE locationID = NULL

--
SELECT hID 
FROM HotelBooking
WHERE HotelBooking.capacity != 0

