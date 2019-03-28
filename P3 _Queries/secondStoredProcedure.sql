-- Q5 Stored procedure 

CREATE OR REPLACE FUNCTION REPLACE_LOCATION (tier1 VARCHAR(20), lname VARCHAR(20))
	RETURNS void AS $$
DECLARE
	C1 CURSOR FOR
	SELECT Event.eid, tier,Event.locationid FROM (Select *
		FROM (Select * 
			  	FROM Sponsor 
			  	JOIN Interview ON interview.sponsorid=sponsor.aid
			 )t1 
		JOIN Company 
		ON t1.companyid=Company.cname
	 	) t2
		JOIN Event ON Event.eid=t2.eid;
	interview RECORD;
BEGIN 
	OPEN C1;
	LOOP
		FETCH C1 INTO interview;
		EXIT WHEN NOT FOUND;
		IF interview.tier = tier1 THEN
			UPDATE Event  
			SET locationid = lname
			WHERE Event.eid = interview.eid;																  
		END IF;
	END LOOP;
	CLOSE C1;	
	RETURN;
END;
$$ LANGUAGE plpgsql;

																			  
SELECT REPLACE_LOCATION ('gold', 'Viger');
SELECT Event.eid, tier,Event.locationid FROM (Select *
		FROM (Select * 
			  	FROM Sponsor 
			  	JOIN Interview ON interview.sponsorid=sponsor.aid
			 )t1 
		JOIN Company 
		ON t1.companyid=Company.cname
	 	) AS t2
		JOIN Event ON Event.eid=t2.eid;

									
 																			  





