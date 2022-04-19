/*Usuários */
INSERT INTO USUARIO(id, nome, email, senha) VALUES (1, 'admin', 'instrutor@email.com', '$2a$10$sFKmbxbG4ryhwPNx/l3pgOJSt.fW1z6YcUnuE2X8APA/Z3NI/oSpq');
INSERT INTO USUARIO(id, nome, email, senha) VALUES (2, 'estagiario', 'estagiario@email.com', '$2a$10$sFKmbxbG4ryhwPNx/l3pgOJSt.fW1z6YcUnuE2X8APA/Z3NI/oSpq');
INSERT INTO USUARIO(id, nome, email, senha) VALUES (3, 'estagiario2', 'portalcompasstesteapie@outlook.com', '$2a$10$sFKmbxbG4ryhwPNx/l3pgOJSt.fW1z6YcUnuE2X8APA/Z3NI/oSpq');

/*Perfis */
INSERT INTO PERFIL(id, nome) VALUES (1, 'ROLE_ADMIN');
INSERT INTO PERFIL(id, nome) VALUES (2, 'ROLE_ESTAGIARIO');

/*Relacionamento*/
INSERT INTO USUARIO_PERFIS(usuario_id, perfis_id) VALUES (1, 1);
INSERT INTO USUARIO_PERFIS(usuario_id, perfis_id) VALUES (2, 2);
INSERT INTO USUARIO_PERFIS(usuario_id, perfis_id) VALUES (3, 2);