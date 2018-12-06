INSERT INTO auth.`user`
(name, email, login, password, user_type, activated, deleted, user_register, date_register, user_last_update, date_last_update, zone_register, zone_last_update, ip_register, ip_last_update)
VALUES('Developer', 'developer@system.com', 'developer', '$2a$10$AdMkUTPbYqIux0dhBnXY3Ois6XJfgxe4i9d4BrbpYtxakmp1KIlFW', 0, 1, 0, 1, NOW(), 1, NOW(), 'America/Sao_Paulo', 'America/Sao_Paulo', '127.0.0.1', '127.0.0.1');

SET @user_dev_id = LAST_INSERT_ID();

INSERT INTO auth.menu
(name, url, menu_top_id, icon, tag, internal_menu, internationalization_code, user_register, date_register, user_last_update, date_last_update, zone_register, zone_last_update, ip_register, ip_last_update, deleted)
VALUES('Administração', NULL, NULL, 'fas fa-cog', 'administracao', 0, 'administracao', @user_dev_id, NOW(), @user_dev_id, NOW(), 'America/Sao_Paulo', 'America/Sao_Paulo', '127.0.0.1', '127.0.0.1', 0);

SET @pm_adm_id = LAST_INSERT_ID();

INSERT INTO auth.menu
(name, url, menu_top_id, icon, tag, internal_menu, internationalization_code, user_register, date_register, user_last_update, date_last_update, zone_register, zone_last_update, ip_register, ip_last_update, deleted)
VALUES('Menu e Feature', '/menu', @pm_adm_id, 'far fa-list-alt', 'administracao, menus, features', 0, 'menu.feature', @user_dev_id, NOW(), @user_dev_id, NOW(), 'America/Sao_Paulo', 'America/Sao_Paulo', '127.0.0.1', '127.0.0.1', 0);

SET @cm_menu_id = LAST_INSERT_ID();

INSERT INTO auth.feature
(name, code, user_register, date_register, user_last_update, date_last_update, zone_register, zone_last_update, ip_register, ip_last_update, deleted)
VALUES('Buscar Menu', 'buscar.menu', @user_dev_id, NOW(), @user_dev_id, NOW(), 'America/Sao_Paulo', 'America/Sao_Paulo', '127.0.0.1', '127.0.0.1', 0);

SET @ft_bscrMenu_id = LAST_INSERT_ID();

INSERT INTO auth.menu_feature
(menu_id, feature_id) VALUES (@cm_menu_id, @ft_bscrMenu_id);

INSERT INTO auth.feature
(name, code, user_register, date_register, user_last_update, date_last_update, zone_register, zone_last_update, ip_register, ip_last_update, deleted)
VALUES('Buscar Todos Menus', 'buscar.todos.menus', @user_dev_id, NOW(), @user_dev_id, NOW(), 'America/Sao_Paulo', 'America/Sao_Paulo', '127.0.0.1', '127.0.0.1', 0);

SET @ft_bscrTdsMenus_id = LAST_INSERT_ID();

INSERT INTO auth.menu_feature
(menu_id, feature_id) VALUES (@cm_menu_id, @ft_bscrTdsMenus_id);

INSERT INTO auth.feature
(name, code, user_register, date_register, user_last_update, date_last_update, zone_register, zone_last_update, ip_register, ip_last_update, deleted)
VALUES('Cadastrar Menu', 'cadastrar.menu', @user_dev_id, NOW(), @user_dev_id, NOW(), 'America/Sao_Paulo', 'America/Sao_Paulo', '127.0.0.1', '127.0.0.1', 0);

SET @ft_CdtrMenu_id = LAST_INSERT_ID();

INSERT INTO auth.menu_feature
(menu_id, feature_id) VALUES (@cm_menu_id, @ft_CdtrMenu_id);

INSERT INTO auth.feature
(name, code, user_register, date_register, user_last_update, date_last_update, zone_register, zone_last_update, ip_register, ip_last_update, deleted)
VALUES('Editar Menu', 'editar.menu', @user_dev_id, NOW(), @user_dev_id, NOW(), 'America/Sao_Paulo', 'America/Sao_Paulo', '127.0.0.1', '127.0.0.1', 0);

SET @ft_EdtMenu_id = LAST_INSERT_ID();

INSERT INTO auth.menu_feature
(menu_id, feature_id) VALUES (@cm_menu_id, @ft_EdtMenu_id);

INSERT INTO auth.feature
(name, code, user_register, date_register, user_last_update, date_last_update, zone_register, zone_last_update, ip_register, ip_last_update, deleted)
VALUES('Adicionar Feature', 'adicionar.feature', @user_dev_id, NOW(), @user_dev_id, NOW(), 'America/Sao_Paulo', 'America/Sao_Paulo', '127.0.0.1', '127.0.0.1', 0);

SET @ft_AdcFt_id = LAST_INSERT_ID();

INSERT INTO auth.menu_feature
(menu_id, feature_id) VALUES (@cm_menu_id, @ft_AdcFt_id);

INSERT INTO auth.feature
(name, code, user_register, date_register, user_last_update, date_last_update, zone_register, zone_last_update, ip_register, ip_last_update, deleted)
VALUES('Remover Feature', 'remover.feature', @user_dev_id, NOW(), @user_dev_id, NOW(), 'America/Sao_Paulo', 'America/Sao_Paulo', '127.0.0.1', '127.0.0.1', 0);

SET @ft_RmvFt_id = LAST_INSERT_ID();

INSERT INTO auth.menu_feature
(menu_id, feature_id) VALUES (@cm_menu_id, @ft_RmvFt_id);

INSERT INTO auth.feature
(name, code, user_register, date_register, user_last_update, date_last_update, zone_register, zone_last_update, ip_register, ip_last_update, deleted)
VALUES('Deletar Menu', 'deletar.menu', @user_dev_id, NOW(), @user_dev_id, NOW(), 'America/Sao_Paulo', 'America/Sao_Paulo', '127.0.0.1', '127.0.0.1', 0);

SET @ft_DltMenu_id = LAST_INSERT_ID();

INSERT INTO auth.menu_feature
(menu_id, feature_id) VALUES (@cm_menu_id, @ft_DltMenu_id);