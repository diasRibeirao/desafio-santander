CREATE TABLE TRANSACOES (
		ID BIGINT NOT NULL AUTO_INCREMENT,		
        CLIENTE_ID BIGINT NOT NULL,
        TIPO  VARCHAR(10),
        VALOR NUMERIC(38,2),      
        TAXA NUMERIC(38,2),      
        DATA DATE,  
        
         PRIMARY KEY(ID),
         
         FOREIGN KEY (CLIENTE_ID) REFERENCES CLIENTES(ID)
)