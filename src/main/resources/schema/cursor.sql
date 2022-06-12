DELIMITER $$
CREATE PROCEDURE sp_load_bills()
BEGIN
	DECLARE @vCallsQuantity INTEGER DEFAULT 0;
    DECLARE @vIdLine INTEGER DEFAULT 0;
    DECLARE @vTotalPrice FLOAT DEFAULT 0;
    DECLARE @vPrice_x_minute FLOAT DEFAULT 0;

    DECLARE vTotalCost INTEGER DEFAULT 0;
    DECLARE vIdBill INTEGER DEFAULT 0;
    
	DECLARE vFinished INTEGER DEFAULT 0;
	DECLARE cur_lines CURSOR FOR SELECT	id_line FROM phones_line group by id_line;/*ACA TAMBIEN CUENTAN LAS LINEAS QUE ESTAN DESACTIVADAS, LO QUE PODEMOS HACER ES UN WHERE = AVAILABLE <> 0 Y QUE AL MOMENTO DE SETEAR EN 0 UN AVAILABLE QUE LE GENERE LA FACTURA EN ESE MOMENTO?*/
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET vFinished = 1;
    OPEN cur_lines;

    get_lines : LOOP
		FETCH cur_lines set vIdLine;
        IF vFinished = 1 THEN
			LEAVE get_lines;
		END IF;

        SET @@vCallsQuantity := SELECT count(*) FROM calls where id_line_origin=@vIdLine AND invoice = 0;

        SET @@vTotalPrice := SELECT SUM(total_price)from calls where id_line_origin=@vIdLine  AND invoice=0;

        SET @@vPrice_x_minute := SELECT SUM(price_x_minute) FROM calls WHERE id_line_origin =@vIdLine  AND invoice = 0;
		
        INSERT INTO bills(amount_calls,bill_date,total_price, cost, expiration_date, paid,phone_line_id_line, client_id_client)
        VALUES (@vCallsQuantity,CURRENT_DATE(),@vTotalPrice,@vTotalCost,(CURDATE() + INTERVAL 15 day),0,@vIdLine ,1);
		
       set vIdBill = last_insert_id();
        update calls SET invoice = 1 WHERE id_line_origin = @vIdLine AND invoice = 0;
        
    END LOOP get_lines;
    CLOSE cur_lines;
END
$$
DELIMITER ;


CALL sp_load_bills;