INSERT INTO usuarios(email, senha, data_de_criacao) VALUES('admin@email.com', '$2a$10$sFKmbxbG4ryhwPNx/l3pgOJSt.fW1z6YcUnuE2X8APA/Z3NI/oSpq', now());
INSERT INTO usuarios(email, senha, data_de_criacao) VALUES('user@email.com', '$2a$10$sFKmbxbG4ryhwPNx/l3pgOJSt.fW1z6YcUnuE2X8APA/Z3NI/oSpq', now());

INSERT INTO categorias(nome) VALUES ('Eletronicos');
INSERT INTO categorias(nome, categoria_mae_id) VALUES ('Computador', 1);
