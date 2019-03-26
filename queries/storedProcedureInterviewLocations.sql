SELECT Event.eid, tier,Event.locationid
FROM (Select *
	 	FROM (Select * 
			  	FROM Sponsor 
			  	JOIN Interview ON interview.sponsorid=sponsor.aid
			 )t1 
		JOIN Company 
		ON t1.companyid=Company.cname
	 )t2
JOIN Event ON Event.eid=t2.eid;