-- Q1: Stored Procedure [returns a table containing names and capacity of free locations inthe given time frame]

CREATE OR REPLACE FUNCTION FREE_LOCATIONS (some_date DATE, start_time TIME, end_time TIME)
	RETURNS void AS $$
DECLARE
	C1 CURSOR FOR
	SELECT L.lname AS room, L.capacity AS cap, E.startTime AS startT, E.endTime AS endT, E.eventDate AS eventD
	FROM Event E, Location L
	WHERE E.locationID = L.lname;
	event_row RECORD;
BEGIN 
	OPEN C1;
	CREATE TABLE freelocations (lname VARCHAR(20), capacity INT, avail_sT TIME, avail_eT TIME);
	LOOP
		FETCH C1 INTO event_row;
		EXIT WHEN NOT FOUND;
		IF event_row.eventD = some_date THEN
			IF (event_row.startT < start_time AND event_row.endT < start_time)
			OR (event_row.startT > end_time AND event_row.endT > end_time) THEN
			INSERT INTO freelocations VALUES (event_row.room, event_row.cap);
			END IF;
		END IF;
	END LOOP;
	CLOSE C1;	
	RETURN;
END;
$$ LANGUAGE plpgsql;

SELECT FREE_LOCATIONS ('2019-01-11', '13:00:00', '21:00:00');
Select lname, capacity FROM freelocations GROUP BY lname, capacity ORDER BY lname


