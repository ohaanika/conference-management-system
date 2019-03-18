-- CHECK 1: restricting the of events

--ALTER

--TEST INSERT

--TEST UPDATE

-- CHECK 2: restricting shirt size of attendee

--ALTER
ALTER TABLE Attendee
ADD CHECK (shirtSize in ('XS','S','M','L','XL','XXL'))

--TEST INSERT
INSERT INTO Attendee
VALUES ('f470afaa-9a03-4ae7-a7e6-ac640b3611a9','First','Last','test@email.com','M','XXS','Diet')

--TEST UPDATE
UPDATE Attendee
SET shirtSize = 'ABC'
where aID = 'f470afaa-9a03-4ae7-a7e6-ac640b3611a9'
