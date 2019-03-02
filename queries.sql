
SELECT firstname,lastname,email
FROM (Attendee 
JOIN (SELECT aID FROM Delegate WHERE university='McGill University')
ON  Attendee.aID=Delegate.aID)
 )


SELECT firstname,lastname,email
FROM (Attendee 
JOIN(SELECT aID FROM (Sponsor) WHERE companyID='Kinaxis')
ON  Attendee.aID=Sponsor.aID))


SELECT description,deadlineTime FROM Task WHERE (status="false" and deadlineDate='2019-01-09')

SELECT title FROM Event WHERE locationID = NULL

SELECT hID FROM HotelBooking WHERE HotelBooking.capacity != 0



DELETE FROM EventTasks WHERE taskID = 
	  ANY (SELECT (tID) FROM (Task t Join EventTasks ET on t.tID = ET.taskID) WHERE deadlineDate='2019-01-10')

SELECT hID FROM HotelBooking WHERE capacity= (SELECT MIN (capacity) FROM HotelBooking)


UPDATE HotelBooking
SET capacity = 5  
WHERE hID= (SELECT Y.hID FROM (HotelBooking X INNER JOIN AttendeeHotelBookings Y ON X.hID=Y.hID ) 
			WHERE Y.aID= '818ba2ab-51ee-4b12-afa4-f67fc6d852f3' );


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




CREATE VIEW  tasktoppl (numberOfTasks)
 AS(
 	SELECT COUNT(*)
 	FROM OrganizerTasks
 	WHERE OrganizerID='227b5c0b-709e-49c0-9842-7bbd82f78fa0')


Select * from tasktoppl

Update tasktoppl
Set numberoftasks = 2








