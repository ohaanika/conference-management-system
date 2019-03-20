-- Q1: Stored Procedure 1.1

CREATE PROCEDURE FREE_LOCATIONS (IN some_date DATE, IN start_time TIME, IN end_time TIME) 
	LANGUAGE SQL 
	BEGIN 
		DECLARE C1 CURSOR FOR
			SELECT L.lname, E.startTime, E.endTime, E.eventDate
			FROM Event E, Location L
			WHERE E.locationID = L.lname

-- Q1: Stored Procedure 1.2

CREATE PROCEDURE FREE_LOCATIONS (IN some_date DATE, IN start_time TIME, IN end_time TIME)
LANGUAGE SQL
DECLARE
	C1 CURSOR FOR
	SELECT L.lname AS room, L.capacity AS cap, E.startTime AS startT, E.endTime AS endT, E.eventDate AS eventD
	FROM Event E, Location L
	WHERE E.locationID = L.lname;
	event_row RECORD;
BEGIN 
	OPEN C1;
	CREATE TABLE freelocations (lname VARCHAR(20), capacity INT);
	LOOP
		FETCH C1 INTO event_row;
		EXIT WHEN NOT FOUND;
		IF event_row.eventD = some_date THEN
			IF (event_row.startT < start_time AND event_row.endT < start_time)
			OR (event_row.startT > end_time AND event_row.endT > end_time) THEN
			INSERT INTO freelocations VALUES (event_row.room, event_row.cap);
		END IF;
	END LOOP;
	CLOSE C1;	
	RETURN;
END;

EXEC SQL CALL FREE_LOCATIONS (2019-01-01, 08:00, 09:00);

-- Q1: Stored Procedure 1.3

CREATE FUNCTION FREE_LOCATIONS (some_date DATE, start_time TIME, end_time TIME)
	RETURNS void AS $$
DECLARE
	C1 CURSOR FOR
	SELECT L.lname AS room, L.capacity AS cap, E.startTime AS startT, E.endTime AS endT, E.eventDate AS eventD
	FROM Event E, Location L
	WHERE E.locationID = L.lname;
	event_row RECORD;
BEGIN 
	OPEN C1;
	CREATE TABLE freelocations (lname VARCHAR(20), capacity INT);
	LOOP
		FETCH C1 INTO event_row;
		EXIT WHEN NOT FOUND;
		IF event_row.eventD = some_date THEN
			IF (event_row.startT < start_time AND event_row.endT < start_time)
			OR (event_row.startT > end_time AND event_row.endT > end_time) THEN
			INSERT INTO freelocations VALUES (event_row.room, event_row.cap);
		END IF;
	END LOOP;
	CLOSE C1;	
	RETURN;
END;
$$ LANGUAGE plpgsql;

SELECT FREE_LOCATIONS ('2019-01-01', '08:00:00', '09:00:00');