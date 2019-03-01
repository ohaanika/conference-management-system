#!/usr/bin/expect -f

# now connect using ssh
spawn ssh comp421.cs.mcgill.ca -l cs421g14;
expect "*?assword:*";
send "thiCeesah8dei\r";
set prompt_re {\$ $};
expect -re $prompt_re;
send "psql cs421\r";
expect "*?assword:*";
send "thiCeesah8dei\r";
set prompt_cs {cs421=>};
expect -re $prompt_cs;
send "COPY Attendee FROM '/home/cs421g14/Attendee.csv' DELIMITER ',' csv;\r"
send "SELECT * FROM ATTENDEE;\r"
expect elf;