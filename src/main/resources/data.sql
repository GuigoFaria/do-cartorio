INSERT INTO endereco (bairro, cep, cidade, complemento, estado, numero, rua) VALUES ('Centro', '13569-290', 'Osasco', 'n/a', 'SP', '312b', 'Rua João Batista');
INSERT INTO cartorio (endereco_id, nome) VALUES (1, 'Cartório Civil de Osasco');
INSERT INTO certidao (nome) VALUES ('2° Via de Certidão de Casamento');
INSERT INTO cartorio_certidao_list  (cartorio_id, certidao_list_id) VALUES(1,1);

