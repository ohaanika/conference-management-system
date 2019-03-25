--Q4 Counting number of Interviews per day
SELECT COUNT(e.eid), eventdate FROM Interview i join Event e on i.eid = e.eid 
where eventdate is not null
group by eventdate
order by eventdate

--Q4 Average number of attendee per university
SELECT university FROM delegate



