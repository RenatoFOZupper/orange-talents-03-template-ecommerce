INSERT INTO usuarios(email, senha, data_de_criacao) VALUES('admin@email.com', '$2a$10$sFKmbxbG4ryhwPNx/l3pgOJSt.fW1z6YcUnuE2X8APA/Z3NI/oSpq', now());
INSERT INTO usuarios(email, senha, data_de_criacao) VALUES('user@email.com', '$2a$10$sFKmbxbG4ryhwPNx/l3pgOJSt.fW1z6YcUnuE2X8APA/Z3NI/oSpq', now());

INSERT INTO categorias(nome) VALUES ('Eletronicos');
INSERT INTO categorias(nome, categoria_mae_id) VALUES ('Computador', 1);

INSERT INTO produto (nome, valor, quantidade, descricao, criado_em, categoria_id, dono_id) VALUES ('computador', 1000.0, 100, 'computador muito rapido', now(), 2, 1); 
INSERT INTO produto (nome, valor, quantidade, descricao, criado_em, categoria_id, dono_id) VALUES ('notebook', 2000.0, 50, 'notebook da tectoy', now(), 2, 2); 

INSERT INTO caracteristica_produto (nome, descricao, produto_id) VALUES ('processador', 'i5-2.5GHz', 1);
INSERT INTO caracteristica_produto (nome, descricao, produto_id) VALUES ('HDD', '01 terabyte', 1);
INSERT INTO caracteristica_produto (nome, descricao, produto_id) VALUES ('RAM', '16 gigabytes', 1);

INSERT INTO caracteristica_produto (nome, descricao, produto_id) VALUES ('processador', 'i3-2.1GHz', 2);
INSERT INTO caracteristica_produto (nome, descricao, produto_id) VALUES ('HDD', '500 gigabytes', 2);
INSERT INTO caracteristica_produto (nome, descricao, produto_id) VALUES ('RAM', '04 gigabytes', 2);

INSERT INTO imagem_produto (link, produto_id) VALUES ('http://bucket.io/checkout-extendido-15-04.txt', 1);
INSERT INTO imagem_produto (link, produto_id) VALUES ('http://bucket.io/desafio-final.rtf', 2);

INSERT INTO pergunta (titulo, criado_em, produto_id, usuario_da_pergunta_id) VALUES ('Funciona mesmo?', now(), 1, 1);
INSERT INTO pergunta (titulo, criado_em, produto_id, usuario_da_pergunta_id) VALUES ('É novo?', now(), 1, 1);

INSERT INTO opiniao_produto (nota, titulo, descricao, produto_id, usuario_logado_id) VALUES (1, 'Ruim', 'trava muito', 1, 2); 
INSERT INTO opiniao_produto (nota, titulo, descricao, produto_id, usuario_logado_id) VALUES (5, 'Otimo', 'Perfeito!!', 1, 2); 