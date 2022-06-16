DELIMITER $$
drop procedure sp_load_bills
CREATE PROCEDURE sp_load_bills()
BEGIN
	DECLARE vCallsQuantity INTEGER DEFAULT 0;
    DECLARE vIdLine INTEGER DEFAULT 0;
    DECLARE vTotalPrice FLOAT DEFAULT 0;
    DECLARE vTotalCost FLOAT DEFAULT 0;
    DECLARE vIdClient INTEGER DEFAULT 0;

	DECLARE vFinished INTEGER DEFAULT 0;
	DECLARE cur_lines CURSOR FOR SELECT	id_line FROM phones_line group by id_line;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET vFinished = 1;
    OPEN cur_lines;

    get_lines : LOOP
		FETCH cur_lines INTO vIdLine;
        IF vFinished = 1 THEN
			LEAVE get_lines;
		END IF;

        select ifnull(count(*),0) INTO vCallsQuantity FROM calls WHERE id_line_origin = vIdLine AND invoice = 0;
        select ifnull(sum(total_price),0) INTO vTotalPrice FROM calls WHERE id_line_origin = vIdLine AND invoice = 0;
        select ifnull(sum(price_x_minute),0) INTO vTotalCost FROM calls WHERE id_line_origin = vIdLine AND invoice = 0;
        select c.id_client Into vIdClient FROM clients c WHERE phone_line_id_line = vIdLine;

        INSERT INTO bills(amount_calls,date,total_price,total_cost,expiration_date,paid,phone_line_id_line,client_id_client)
        VALUES (vCallsQuantity,CURRENT_DATE(),vTotalPrice,vTotalCost,(CURDATE() + INTERVAL 15 day),0,vIdLine,vIdClient);

        update calls SET invoice = 1 WHERE id_line_origin = vIdLine AND invoice = 0;

    END LOOP get_lines;
    CLOSE cur_lines;
END
$$
DELIMITER ;

	call sp_load_bills;