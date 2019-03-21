CREATE PROCEDURE MIN_SALARY (IN deptnumberSMALLINT, IN minsalDOUBLE) 
	LANGUAGE SQL 
	BEGIN 
		DECLARE v_salary DOUBLE;
		DECLARE v_id SMALLINT;
		DECLARE at_end INT DEFAULT 0;
		DECLARE not_found CONDITION FOR SQLSTATE '02000';
		DECLARE C1 CURSOR FOR
			SELECT id, salary FROM staff WHERE did = deptnumber;
		DECLARE CONTINUE HANDLER FOR not_foundSET at_end= 1;
	OPEN C1;
	FETCH C1 INTO v_id, v_salary;
	WHILE at_end= 0 DO
		IF (v_salary< minsal)
			THEN UPDATE staff SET salary = minsalWHERE id = v_id;
		END IF;
		FETCH C1 INTO v_id, v_salary;
	END WHILE;
	CLOSE C1
END