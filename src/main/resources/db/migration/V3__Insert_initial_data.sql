INSERT INTO TB_USUARIO (id_usuario, nome_usuario, email_usuario, senha_usuario, role) VALUES (1, 'João Silva', 'joao@gmail.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ADMIN');
INSERT INTO TB_USUARIO (id_usuario, nome_usuario, email_usuario, senha_usuario, role) VALUES (2, 'Maria Souza', 'maria@gmail.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ADMIN');
INSERT INTO TB_USUARIO (id_usuario, nome_usuario, email_usuario, senha_usuario, role) VALUES (3, 'Carlos Lima', 'carlos@gmail.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'USER');
INSERT INTO TB_USUARIO (id_usuario, nome_usuario, email_usuario, senha_usuario, role) VALUES (4, 'Ana Costa', 'ana@gmail.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'USER');
INSERT INTO TB_USUARIO (id_usuario, nome_usuario, email_usuario, senha_usuario, role) VALUES (5, 'Fernanda Rocha', 'fernanda@gmail.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'USER');

INSERT INTO TB_PATIO (id_patio, id_usuario, cep_patio, numero_patio, cidade_patio, estado_patio, capacidade_patio) VALUES (1, 1, '01001-000', '100', 'São Paulo', 'SP', 50);
INSERT INTO TB_PATIO (id_patio, id_usuario, cep_patio, numero_patio, cidade_patio, estado_patio, capacidade_patio) VALUES (2, 2, '20010-000', '200', 'Rio de Janeiro', 'RJ', 60);
INSERT INTO TB_PATIO (id_patio, id_usuario, cep_patio, numero_patio, cidade_patio, estado_patio, capacidade_patio) VALUES (3, 3, '30110-000', '300', 'Belo Horizonte', 'MG', 40);
INSERT INTO TB_PATIO (id_patio, id_usuario, cep_patio, numero_patio, cidade_patio, estado_patio, capacidade_patio) VALUES (4, 4, '40020-000', '400', 'Salvador', 'BA', 30);
INSERT INTO TB_PATIO (id_patio, id_usuario, cep_patio, numero_patio, cidade_patio, estado_patio, capacidade_patio) VALUES (5, 5, '50030-000', '500', 'Recife', 'PE', 35);
INSERT INTO TB_PATIO (id_patio, id_usuario, cep_patio, numero_patio, cidade_patio, estado_patio, capacidade_patio) VALUES (6, 5, '50030-090', '510', 'Recife', 'PE', 40);
