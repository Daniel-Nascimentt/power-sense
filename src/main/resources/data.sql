-- Inserir registros na tabela CONTRATANTE
INSERT INTO contratante (nome, cpf, data_nascimento, sexo)
VALUES ("João Contratante", "123.456.789-00", "1990-01-15", "MASCULINO"),
       ("Maria Contratante", "987.654.321-00", "1985-03-20", "FEMININO"),
       ("Carlos Contratante", "555.555.555-55", "1978-11-10", "MASCULINO"),
       ("Ana Contratante", "111.222.333-44", "2000-07-05", "FEMININO"),
       ("Pedro Contratante", "999.888.777-66", "1992-09-30", "MASCULINO");

-- Inserir registros na tabela RESIDENTE
INSERT INTO residente (nome, cpf, data_nascimento, sexo, parentesco_com_contratante, contratante_id)
VALUES ("Lucas Residente", "111.222.333-55", "2010-05-05", "MASCULINO", "Filho", 1),
       ("Isabela Residente", "444.555.666-77", "2005-09-10", "FEMININO", "Filha", 2),
       ("Mateus Residente", "777.888.999-11", "2002-02-25", "MASCULINO", "Filho", 3),
       ("Amanda Residente", "222.333.444-88", "2012-12-15", "FEMININO", "Filha", 4),
       ("Rafael Residente", "888.777.666-55", "2014-07-20", "MASCULINO", "Filho", 5);

-- Inserir registros na tabela ENDERECO
INSERT INTO endereco (rua, numero, bairro, cidade, estado, cep, contratante_id)
VALUES ("Rua A", 123, "Centro", "Cidade A", "SP", "12345-678", 1),
       ("Rua B", 456, "Bairro X", "Cidade B", "RJ", "56789-012", 2),
       ("Rua C", 789, "Bairro Y", "Cidade C", "MG", "23456-789", 3),
       ("Rua D", 101, "Bairro Z", "Cidade D", "RS", "34567-890", 4),
       ("Rua E", 202, "Bairro W", "Cidade E", "SC", "45678-901", 5);

-- Inserir registros na tabela ELETRODOMESTICO
INSERT INTO eletrodomestico (nome, modelo, potencia, voltagem, contratante_utiliza_id)
VALUES ("Geladeira", "ABC123", 150, "V220", 1),
       ("Fogão", "XYZ456", 800, "V220", 2),
       ("Máquina de Lavar", "123XYZ", 500, "V110", 3),
       ("Televisão", "LG789", 100, "V220", 4),
       ("Computador", "Dell321", 200, "V110", 5);

-- Inserir registros na tabela CONSUMO
INSERT INTO consumo (watts_total_consumido, eletrodomestico_id, horas_consumo)
VALUES (600, 1, 4),
       (2400, 2, 3),
       (1000, 3, 2),
       (600, 4, 6),
       (1000, 5, 8);

INSERT INTO eletrodomestico_residentes_utilizam (eletros_utilizados_id, residentes_utilizam_id)
VALUES (1, 1),
       (2, 1),
       (3, 1),
       (4, 1),
       (2, 2),
       (3, 2),
       (4, 2),
       (2, 3),
       (3, 3),
       (4, 3),
       (2, 4),
       (3, 4),
       (4, 5),
       (5, 5),
       (5, 5);