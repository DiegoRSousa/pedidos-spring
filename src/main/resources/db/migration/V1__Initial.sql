CREATE TABLE USUARIO (
	ID INT AUTO_INCREMENT,
	NOME VARCHAR(80),
	SENHA VARCHAR (1024),
	PRIMARY KEY(ID)
);

INSERT INTO USUARIO (NOME, SENHA) VALUES ('admin', '$2a$10$EUFZUU.49DnNeOWx9ZbuEubLuvlA0ggWrWSB.kSlpyrJc.5wBm/Rm');
INSERT INTO USUARIO (NOME, SENHA) VALUES ('padrao', '$2a$10$IA8v9WmHurHqpobNENaxOucggO5PtHQxaKcSMlzwZAck/xQWH/ftS');

CREATE TABLE PERFIS (
	USUARIO_ID INT,
	PERFIS INT
);

INSERT INTO PERFIS VALUES (1, 1);
INSERT INTO PERFIS VALUES (1, 0);
INSERT INTO PERFIS VALUES (2, 1);

CREATE TABLE CATEGORIA (
	ID INT AUTO_INCREMENT, 
	DESCRICAO VARCHAR(80) NOT NULL,
	PRIMARY KEY(ID)
);

INSERT INTO CATEGORIA (DESCRICAO) VALUES ('Informática');
INSERT INTO CATEGORIA (DESCRICAO) VALUES ('Escritorio');
INSERT INTO CATEGORIA (DESCRICAO) VALUES ('Esporte');

CREATE TABLE PRODUTO (
	ID INT AUTO_INCREMENT, 
	CODIGO VARCHAR(4) NOT NULL,
	DESCRICAO VARCHAR(80) NOT NULL,
	PRECO DECIMAL(7, 2) NOT NULL,
	CATEGORIA_ID INT NOT NULL,
	PRIMARY KEY(ID),
	FOREIGN KEY(CATEGORIA_ID) REFERENCES CATEGORIA (ID)
);

INSERT INTO PRODUTO (CODIGO, DESCRICAO, PRECO, CATEGORIA_ID) VALUES ('0001', 'Computador HP G240', 2500.00, 1);
INSERT INTO PRODUTO (CODIGO, DESCRICAO, PRECO, CATEGORIA_ID) VALUES ('0002', 'Dell XPS', 3500.00, 1);
INSERT INTO PRODUTO (CODIGO, DESCRICAO, PRECO, CATEGORIA_ID) VALUES ('0003', 'Mesa escritório', 300.00, 2);
INSERT INTO PRODUTO (CODIGO, DESCRICAO, PRECO, CATEGORIA_ID) VALUES ('0004', 'Bike Soul 29', 1700.00, 3);
INSERT INTO PRODUTO (CODIGO, DESCRICAO, PRECO, CATEGORIA_ID) VALUES ('0005', 'Bike Caloi 29', 16500.00, 3);

CREATE TABLE CLIENTE (
	ID INT AUTO_INCREMENT,
	NOME VARCHAR(480) NOT NULL,
	CPF VARCHAR(12) DEFAULT '',
	LOGRADOURO VARCHAR(480) DEFAULT '',
	NUMERO VARCHAR(10) DEFAULT '',
	BAIRRO VARCHAR(480) DEFAULT '',
	CRIADO_EM DATE,
	ATUALIZADO_EM DATE,
	PRIMARY KEY(ID)
);

INSERT INTO CLIENTE (NOME, CRIADO_EM) VALUES ('Gabriel Eduardo', '2020-08-15');
INSERT INTO CLIENTE (NOME, CPF, LOGRADOURO, CRIADO_EM) VALUES ('Paulo Campos', '112860072', 'Rua 105', '2020-08-15');
INSERT INTO CLIENTE (NOME, CRIADO_EM) VALUES ('Nair Campos', '2020-08-15');
INSERT INTO CLIENTE (NOME, CRIADO_EM) VALUES ('Tereza dos Santos', '2020-08-15');
INSERT INTO CLIENTE (NOME, CPF, LOGRADOURO, CRIADO_EM) VALUES ('Elaine Giovanna', '38642299606', 'Rua Preta', '2020-08-15');
INSERT INTO CLIENTE (NOME, CRIADO_EM) VALUES ('Yago dos Santos', '2020-08-15');
INSERT INTO CLIENTE (NOME, CRIADO_EM) VALUES ('Giovanni dos Santos', '2020-08-15');
INSERT INTO CLIENTE (NOME, CPF, LOGRADOURO, CRIADO_EM) VALUES ('Valentina Priscila Ester', '', 'Rua 105', '2020-08-15');
INSERT INTO CLIENTE (NOME, CRIADO_EM) VALUES ('Erick Matheus', '2020-08-15');
INSERT INTO CLIENTE (NOME, CRIADO_EM) VALUES ('Matheus Fernandes', '2020-08-15');
INSERT INTO CLIENTE (NOME, CPF, LOGRADOURO, CRIADO_EM) VALUES ('Priscila Ester', '82978199148', 'Rua 105', '2020-08-15');
INSERT INTO CLIENTE (NOME, CRIADO_EM) VALUES ('Pietra de Paula', '2020-08-15');
INSERT INTO CLIENTE (NOME, CRIADO_EM) VALUES ('Sônia de Paula', '2020-08-15');
INSERT INTO CLIENTE (NOME, CPF, LOGRADOURO, CRIADO_EM) VALUES ('Raul Galvão', '33988593214', 'Rua 105', '2020-08-15');
INSERT INTO CLIENTE (NOME, CRIADO_EM) VALUES ('Adriana Daiane Fabiana', '2020-08-15');
INSERT INTO CLIENTE (NOME, CRIADO_EM) VALUES ('Anderson Juan Manuel Galvão', '2020-08-15');
INSERT INTO CLIENTE (NOME, CPF, LOGRADOURO, CRIADO_EM) VALUES ('Laís Silva', '01255591862', 'Rua 105', '2020-08-15');
INSERT INTO CLIENTE (NOME, CPF, LOGRADOURO, NUMERO, BAIRRO, CRIADO_EM) 
VALUES ('Eliane Nicole Aragão', '54087598730', 'Rua 7', '156', 'Morada do Sol', '2020-08-15');

CREATE TABLE PEDIDO (
	ID INT AUTO_INCREMENT,
    NUMERO VARCHAR(11) NOT NULL,
    DATA DATE NOT NULL,
    CLIENTE_ID INT NOT NULL,
    SUB_TOTAL DECIMAL (7, 2) NOT NULL,
    ACRESCIMO DECIMAL (7, 2) NOT NULL,
    DESCONTO DECIMAL (7, 2) NOT NULL,
    TOTAL DECIMAL (7, 2) NOT NULL,
    CRIADO_EM DATE NOT NULL,
    ATUALIZADO_EM DATE,
    PRIMARY KEY(ID),
    FOREIGN KEY (CLIENTE_ID) REFERENCES CLIENTE (ID),
    CHECK (TOTAL = (SUB_TOTAL + ACRESCIMO - DESCONTO))
);

INSERT INTO PEDIDO (NUMERO, DATA, CLIENTE_ID, SUB_TOTAL, ACRESCIMO, DESCONTO, TOTAL, CRIADO_EM)
VALUES ('00321', '2020/08/15', 1, 100.00, 0, 0, 100.00, '2020/08/15');
INSERT INTO PEDIDO (NUMERO, DATA, CLIENTE_ID, SUB_TOTAL, ACRESCIMO, DESCONTO, TOTAL, CRIADO_EM)
VALUES ('01341', '2020/08/15', 1, 600.00, 0, 10, 590.00, '2020/08/15');
INSERT INTO PEDIDO (NUMERO, DATA, CLIENTE_ID, SUB_TOTAL, ACRESCIMO, DESCONTO, TOTAL, CRIADO_EM)
VALUES ('00019', '2020/08/15', 3, 400.00, 0, 0.10, 399.90, '2020/08/15');
INSERT INTO PEDIDO (NUMERO, DATA, CLIENTE_ID, SUB_TOTAL, ACRESCIMO, DESCONTO, TOTAL, CRIADO_EM)
VALUES ('00021', '2020/08/15', 2, 250.00, 5.00, 0, 255.00, '2020/08/15');
INSERT INTO PEDIDO (NUMERO, DATA, CLIENTE_ID, SUB_TOTAL, ACRESCIMO, DESCONTO, TOTAL, CRIADO_EM)
VALUES ('00521', '2020/08/15', 1, 100.00, 0, 1.00, 99.00, '2020/08/15');
INSERT INTO PEDIDO (NUMERO, DATA, CLIENTE_ID, SUB_TOTAL, ACRESCIMO, DESCONTO, TOTAL, CRIADO_EM)
VALUES ('00670', '2020/08/15', 2, 100.00, 0, 0, 100.00, '2020/08/15');
INSERT INTO PEDIDO (NUMERO, DATA, CLIENTE_ID, SUB_TOTAL, ACRESCIMO, DESCONTO, TOTAL, CRIADO_EM)
VALUES ('00100', '2020/08/15', 3, 100.00, 0, 0, 100.00, '2020/08/15');
INSERT INTO PEDIDO (NUMERO, DATA, CLIENTE_ID, SUB_TOTAL, ACRESCIMO, DESCONTO, TOTAL, CRIADO_EM)
VALUES ('00360', '2020/08/15', 7, 100.00, 0, 0, 100.00, '2020/08/15');

CREATE TABLE PRODUTOPEDIDO (
	ID INT AUTO_INCREMENT,
    QUANTIDADE DECIMAL (7, 2) NOT NULL,
    PRECO DECIMAL (7, 2) NOT NULL,
    SUB_TOTAL DECIMAL(7, 2),
    PRODUTO_ID INT NOT NULL,
    PEDIDO_ID INT NOT NULL,
    PRIMARY KEY(ID),
    FOREIGN KEY (PRODUTO_ID) REFERENCES PRODUTO(ID),
    FOREIGN KEY (PEDIDO_ID) REFERENCES PEDIDO(ID),
    CHECK (SUB_TOTAL = (PRECO * QUANTIDADE))
);

INSERT INTO PRODUTOPEDIDO (QUANTIDADE, PRECO, SUB_TOTAL, PRODUTO_ID, PEDIDO_ID) VALUES (3, 15.00, 45.00, 1, 1);
INSERT INTO PRODUTOPEDIDO (QUANTIDADE, PRECO, SUB_TOTAL, PRODUTO_ID, PEDIDO_ID) VALUES (1, 5.00, 5, 2, 1);
INSERT INTO PRODUTOPEDIDO (QUANTIDADE, PRECO, SUB_TOTAL, PRODUTO_ID, PEDIDO_ID) VALUES (2, 25.00, 50, 3, 1);

