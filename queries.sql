
SELECT firstname,lastname,email
FROM (Attendee 
JOIN (SELECT aID FROM Delegate WHERE university="McGill")
ON  Attendee.aID=Delegate.aID)
 )


SELECT firstname,lastname,email
FROM (Attendee 
JOIN(SELECT aID FROM (Sponsor) WHERE companyID="Kinaxis")
ON  Attendee.aID=Sponsor.aID))


SELECT description,deadlineTime FROM Task WHERE (status="false"&&deadlineDate="2019-01-09")

SELECT title FROM Event WHERE locationID = NULL

SELECT hID FROM HotelBooking WHERE HotelBooking.capacity != 0



DELETE FROM Task WHERE deadlineDate=x

SELECT hID FROM HotelBooking WHERE capacity= (SELECT MIN (capacity) FROM HotelBooking)


UPDATE HotelBooking H, (HotelBooking X JOIN AttendeeHotelBookings Y ON X.hID=Y.hID ) Z 
SET H.capacity = x  
WHERE H.hid=(SELECT hID FROM   Z  WHERE Z.aid= b )).hID



UPDATE HotelBooking H
SET H.capacity = x  
WHERE H.hid=(SELECT hID FROM   AttendeeHotelBookings  WHERE AttendeeHotelBookings.aid= b )).hID            //tis dont make sense to ramsha



CREATE VIEW Interveiws (DelegateName,SponsorName)
AS(
	SELECT D.firstname, S.firstname
 (SELECT ( eID, firstname)
	FROM(
		(Interveiw  I
		JOIN Attendee A  ON A.aID=I.dID)) D )

	JOIN

 (SELECT ( eID, firstname)
	FROM(
		(Interveiw  I
		(JOIN Attendee B ON B.aID=I.sID))  S)
	
	ON D.eID=S.eID)




CREATE VIEW  tasktoppl (OrganizerName,numberOfTasks)
 AS(
 	SELECT COUNT(*)
 	FROM OrganizerTasks
 	WHERE OrganizerID=x)












