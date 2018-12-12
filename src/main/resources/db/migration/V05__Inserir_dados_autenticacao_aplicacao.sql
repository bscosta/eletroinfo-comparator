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
VALUES('Comparator', NULL, NULL, 'fas fa-search', 'comparator', 0, 'comparator', @user_dev_id, NOW(), @user_dev_id, NOW(), 'America/Sao_Paulo', 'America/Sao_Paulo', '127.0.0.1', '127.0.0.1', 0);

SET @pm_cptr_id = LAST_INSERT_ID();

INSERT INTO auth.menu
(name, url, menu_top_id, icon, tag, internal_menu, internationalization_code, user_register, date_register, user_last_update, date_last_update, zone_register, zone_last_update, ip_register, ip_last_update, deleted)
VALUES('Menu e Feature', '/menu', @pm_adm_id, 'far fa-list-alt', 'administracao, menus, features', 0, 'menu.feature', @user_dev_id, NOW(), @user_dev_id, NOW(), 'America/Sao_Paulo', 'America/Sao_Paulo', '127.0.0.1', '127.0.0.1', 0);

SET @cm_menu_id = LAST_INSERT_ID();

INSERT INTO auth.menu
(name, url, menu_top_id, icon, tag, internal_menu, internationalization_code, user_register, date_register, user_last_update, date_last_update, zone_register, zone_last_update, ip_register, ip_last_update, deleted)
VALUES('Usuário', '/usuario', @pm_adm_id, 'fa  fa-fw  fa-user', 'administracao, usuario', 0, 'usuario', @user_dev_id, NOW(), @user_dev_id, NOW(), 'America/Sao_Paulo', 'America/Sao_Paulo', '127.0.0.1', '127.0.0.1', 0);

SET @cm_usuario_id = LAST_INSERT_ID();

INSERT INTO auth.menu
(name, url, menu_top_id, icon, tag, internal_menu, internationalization_code, user_register, date_register, user_last_update, date_last_update, zone_register, zone_last_update, ip_register, ip_last_update, deleted)
VALUES('Grupo Usuários', '/grupoUsuarios', @pm_adm_id, 'fas fa-users', 'administracao, grupo, usuarios', 0, 'grupo.usuarios', @user_dev_id, NOW(), @user_dev_id, NOW(), 'America/Sao_Paulo', 'America/Sao_Paulo', '127.0.0.1', '127.0.0.1', 0);

SET @cm_gpUsuarios_id = LAST_INSERT_ID();

INSERT INTO auth.menu
(name, url, menu_top_id, icon, tag, internal_menu, internationalization_code, user_register, date_register, user_last_update, date_last_update, zone_register, zone_last_update, ip_register, ip_last_update, deleted)
VALUES('Marca', '/marca', @pm_cptr_id, 'fas fa-shopping-cart', 'comparator, marca', 0, 'marca', @user_dev_id, NOW(), @user_dev_id, NOW(), 'America/Sao_Paulo', 'America/Sao_Paulo', '127.0.0.1', '127.0.0.1', 0);

SET @cm_marca_id = LAST_INSERT_ID();

INSERT INTO auth.menu
(name, url, menu_top_id, icon, tag, internal_menu, internationalization_code, user_register, date_register, user_last_update, date_last_update, zone_register, zone_last_update, ip_register, ip_last_update, deleted)
VALUES('Produto', '/produto', @pm_cptr_id, 'fa fa-product-hunt', 'comparator, produto', 0, 'produto', @user_dev_id, NOW(), @user_dev_id, NOW(), 'America/Sao_Paulo', 'America/Sao_Paulo', '127.0.0.1', '127.0.0.1', 0);

SET @cm_produto_id = LAST_INSERT_ID();

INSERT INTO auth.menu
(name, url, menu_top_id, icon, tag, internal_menu, internationalization_code, user_register, date_register, user_last_update, date_last_update, zone_register, zone_last_update, ip_register, ip_last_update, deleted)
VALUES('Vendedor', '/vendedor', @pm_cptr_id, 'fa fa-user', 'comparator, vendedor', 0, 'vendedor', @user_dev_id, NOW(), @user_dev_id, NOW(), 'America/Sao_Paulo', 'America/Sao_Paulo', '127.0.0.1', '127.0.0.1', 0);

SET @cm_vendedor_id = LAST_INSERT_ID();

INSERT INTO auth.menu
(name, url, menu_top_id, icon, tag, internal_menu, internationalization_code, user_register, date_register, user_last_update, date_last_update, zone_register, zone_last_update, ip_register, ip_last_update, deleted)
VALUES('Fornecedor', '/fornecedor', @pm_cptr_id, 'fas fa-people-carry', 'comparator, fornecedor', 0, 'fornecedor', @user_dev_id, NOW(), @user_dev_id, NOW(), 'America/Sao_Paulo', 'America/Sao_Paulo', '127.0.0.1', '127.0.0.1', 0);

SET @cm_fornecedor_id = LAST_INSERT_ID();

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

INSERT INTO auth.feature
(name, code, user_register, date_register, user_last_update, date_last_update, zone_register, zone_last_update, ip_register, ip_last_update, deleted)
VALUES('Cadastrar Usuário', 'cadastrar.usuario', @user_dev_id, NOW(), @user_dev_id, NOW(), 'America/Sao_Paulo', 'America/Sao_Paulo', '127.0.0.1', '127.0.0.1', 0);

SET @ft_CdtrUsr_id = LAST_INSERT_ID();

INSERT INTO auth.menu_feature
(menu_id, feature_id) VALUES (@cm_usuario_id, @ft_CdtrUsr_id);

INSERT INTO auth.feature
(name, code, user_register, date_register, user_last_update, date_last_update, zone_register, zone_last_update, ip_register, ip_last_update, deleted)
VALUES('Editar Usuário', 'editar.usuario', @user_dev_id, NOW(), @user_dev_id, NOW(), 'America/Sao_Paulo', 'America/Sao_Paulo', '127.0.0.1', '127.0.0.1', 0);

SET @ft_EdtUsr_id = LAST_INSERT_ID();

INSERT INTO auth.menu_feature
(menu_id, feature_id) VALUES (@cm_usuario_id, @ft_EdtUsr_id);

INSERT INTO auth.feature
(name, code, user_register, date_register, user_last_update, date_last_update, zone_register, zone_last_update, ip_register, ip_last_update, deleted)
VALUES('Buscar Usuário', 'buscar.usuario', @user_dev_id, NOW(), @user_dev_id, NOW(), 'America/Sao_Paulo', 'America/Sao_Paulo', '127.0.0.1', '127.0.0.1', 0);

SET @ft_BscrUsr_id = LAST_INSERT_ID();

INSERT INTO auth.menu_feature
(menu_id, feature_id) VALUES (@cm_usuario_id, @ft_BscrUsr_id);

INSERT INTO auth.feature
(name, code, user_register, date_register, user_last_update, date_last_update, zone_register, zone_last_update, ip_register, ip_last_update, deleted)
VALUES('Buscar Todos Usuarios', 'buscar.todos.usuarios', @user_dev_id, NOW(), @user_dev_id, NOW(), 'America/Sao_Paulo', 'America/Sao_Paulo', '127.0.0.1', '127.0.0.1', 0);

SET @ft_BscrTdsUsr_id = LAST_INSERT_ID();

INSERT INTO auth.menu_feature
(menu_id, feature_id) VALUES (@cm_usuario_id, @ft_BscrTdsUsr_id);

INSERT INTO auth.feature
(name, code, user_register, date_register, user_last_update, date_last_update, zone_register, zone_last_update, ip_register, ip_last_update, deleted)
VALUES('Deletar Usuário', 'deletar.usuario', @user_dev_id, NOW(), @user_dev_id, NOW(), 'America/Sao_Paulo', 'America/Sao_Paulo', '127.0.0.1', '127.0.0.1', 0);

SET @ft_DltUsr_id = LAST_INSERT_ID();

INSERT INTO auth.menu_feature
(menu_id, feature_id) VALUES (@cm_usuario_id, @ft_DltUsr_id);

INSERT INTO auth.feature
(name, code, user_register, date_register, user_last_update, date_last_update, zone_register, zone_last_update, ip_register, ip_last_update, deleted)
VALUES('Cadastrar Grupo Usuarios', 'cadastrar.grupo.usuarios', @user_dev_id, NOW(), @user_dev_id, NOW(), 'America/Sao_Paulo', 'America/Sao_Paulo', '127.0.0.1', '127.0.0.1', 0);

SET @ft_CdstGpUsr_id = LAST_INSERT_ID();

INSERT INTO auth.menu_feature
(menu_id, feature_id) VALUES (@cm_gpUsuarios_id, @ft_CdstGpUsr_id);

INSERT INTO auth.feature
(name, code, user_register, date_register, user_last_update, date_last_update, zone_register, zone_last_update, ip_register, ip_last_update, deleted)
VALUES('Editar Grupo Usuarios', 'editar.grupo.usuarios', @user_dev_id, NOW(), @user_dev_id, NOW(), 'America/Sao_Paulo', 'America/Sao_Paulo', '127.0.0.1', '127.0.0.1', 0);

SET @ft_EdtGpUsr_id = LAST_INSERT_ID();

INSERT INTO auth.menu_feature
(menu_id, feature_id) VALUES (@cm_gpUsuarios_id, @ft_EdtGpUsr_id);

INSERT INTO auth.feature
(name, code, user_register, date_register, user_last_update, date_last_update, zone_register, zone_last_update, ip_register, ip_last_update, deleted)
VALUES('Buscar Todos Grupos Usuários', 'buscar.todos.grupos.usuarios', @user_dev_id, NOW(), @user_dev_id, NOW(), 'America/Sao_Paulo', 'America/Sao_Paulo', '127.0.0.1', '127.0.0.1', 0);

SET @ft_BscTdsGpUsr_id = LAST_INSERT_ID();

INSERT INTO auth.menu_feature
(menu_id, feature_id) VALUES (@cm_gpUsuarios_id, @ft_BscTdsGpUsr_id);

INSERT INTO auth.feature
(name, code, user_register, date_register, user_last_update, date_last_update, zone_register, zone_last_update, ip_register, ip_last_update, deleted)
VALUES('Buscar Grupo Usuário', 'buscar.grupo.usuario', @user_dev_id, NOW(), @user_dev_id, NOW(), 'America/Sao_Paulo', 'America/Sao_Paulo', '127.0.0.1', '127.0.0.1', 0);

SET @ft_BscGpUsr_id = LAST_INSERT_ID();

INSERT INTO auth.menu_feature
(menu_id, feature_id) VALUES (@cm_gpUsuarios_id, @ft_BscGpUsr_id);

INSERT INTO auth.feature
(name, code, user_register, date_register, user_last_update, date_last_update, zone_register, zone_last_update, ip_register, ip_last_update, deleted)
VALUES('Deletar Grupo  Usuários', 'deletar.grupo.usuarios', @user_dev_id, NOW(), @user_dev_id, NOW(), 'America/Sao_Paulo', 'America/Sao_Paulo', '127.0.0.1', '127.0.0.1', 0);

SET @ft_DltGpUsr_id = LAST_INSERT_ID();

INSERT INTO auth.menu_feature
(menu_id, feature_id) VALUES (@cm_gpUsuarios_id, @ft_DltGpUsr_id);

INSERT INTO auth.feature
(name, code, user_register, date_register, user_last_update, date_last_update, zone_register, zone_last_update, ip_register, ip_last_update, deleted)
VALUES('Cadastrar Marca', 'cadastrar.marca', @user_dev_id, NOW(), @user_dev_id, NOW(), 'America/Sao_Paulo', 'America/Sao_Paulo', '127.0.0.1', '127.0.0.1', 0);

SET @ft_CdstrMrc_id = LAST_INSERT_ID();

INSERT INTO auth.menu_feature
(menu_id, feature_id) VALUES (@cm_marca_id, @ft_CdstrMrc_id);

INSERT INTO auth.feature
(name, code, user_register, date_register, user_last_update, date_last_update, zone_register, zone_last_update, ip_register, ip_last_update, deleted)
VALUES('Editar Marca', 'editar.marca', @user_dev_id, NOW(), @user_dev_id, NOW(), 'America/Sao_Paulo', 'America/Sao_Paulo', '127.0.0.1', '127.0.0.1', 0);

SET @ft_EdtMrc_id = LAST_INSERT_ID();

INSERT INTO auth.menu_feature
(menu_id, feature_id) VALUES (@cm_marca_id, @ft_EdtMrc_id);

INSERT INTO auth.feature
(name, code, user_register, date_register, user_last_update, date_last_update, zone_register, zone_last_update, ip_register, ip_last_update, deleted)
VALUES('Buscar Marca', 'buscar.marca', @user_dev_id, NOW(), @user_dev_id, NOW(), 'America/Sao_Paulo', 'America/Sao_Paulo', '127.0.0.1', '127.0.0.1', 0);

SET @ft_BscMrc_id = LAST_INSERT_ID();

INSERT INTO auth.menu_feature
(menu_id, feature_id) VALUES (@cm_marca_id, @ft_BscMrc_id);

INSERT INTO auth.feature
(name, code, user_register, date_register, user_last_update, date_last_update, zone_register, zone_last_update, ip_register, ip_last_update, deleted)
VALUES('Buscar Todas Marcas', 'buscar.todas.marcas', @user_dev_id, NOW(), @user_dev_id, NOW(), 'America/Sao_Paulo', 'America/Sao_Paulo', '127.0.0.1', '127.0.0.1', 0);

SET @ft_BscTdsMrc_id = LAST_INSERT_ID();

INSERT INTO auth.menu_feature
(menu_id, feature_id) VALUES (@cm_marca_id, @ft_BscTdsMrc_id);

INSERT INTO auth.feature
(name, code, user_register, date_register, user_last_update, date_last_update, zone_register, zone_last_update, ip_register, ip_last_update, deleted)
VALUES('Deletar Marca', 'deletar.marca', @user_dev_id, NOW(), @user_dev_id, NOW(), 'America/Sao_Paulo', 'America/Sao_Paulo', '127.0.0.1', '127.0.0.1', 0);

SET @ft_DltMrc_id = LAST_INSERT_ID();

INSERT INTO auth.menu_feature
(menu_id, feature_id) VALUES (@cm_marca_id, @ft_DltMrc_id);

INSERT INTO auth.feature
(name, code, user_register, date_register, user_last_update, date_last_update, zone_register, zone_last_update, ip_register, ip_last_update, deleted)
VALUES('Cadastrar Produto', 'cadastrar.produto', @user_dev_id, NOW(), @user_dev_id, NOW(), 'America/Sao_Paulo', 'America/Sao_Paulo', '127.0.0.1', '127.0.0.1', 0);

SET @ft_CdstPrdt_id = LAST_INSERT_ID();

INSERT INTO auth.menu_feature
(menu_id, feature_id) VALUES (@cm_produto_id, @ft_CdstPrdt_id);

INSERT INTO auth.feature
(name, code, user_register, date_register, user_last_update, date_last_update, zone_register, zone_last_update, ip_register, ip_last_update, deleted)
VALUES('Editar Produto', 'editar.produto', @user_dev_id, NOW(), @user_dev_id, NOW(), 'America/Sao_Paulo', 'America/Sao_Paulo', '127.0.0.1', '127.0.0.1', 0);

SET @ft_EdtPrdt_id = LAST_INSERT_ID();

INSERT INTO auth.menu_feature
(menu_id, feature_id) VALUES (@cm_produto_id, @ft_EdtPrdt_id);

INSERT INTO auth.feature
(name, code, user_register, date_register, user_last_update, date_last_update, zone_register, zone_last_update, ip_register, ip_last_update, deleted)
VALUES('Buscar Produto', 'buscar.produto', @user_dev_id, NOW(), @user_dev_id, NOW(), 'America/Sao_Paulo', 'America/Sao_Paulo', '127.0.0.1', '127.0.0.1', 0);

SET @ft_BscPrdt_id = LAST_INSERT_ID();

INSERT INTO auth.menu_feature
(menu_id, feature_id) VALUES (@cm_produto_id, @ft_BscPrdt_id);

INSERT INTO auth.feature
(name, code, user_register, date_register, user_last_update, date_last_update, zone_register, zone_last_update, ip_register, ip_last_update, deleted)
VALUES('Buscar Todos Produtos', 'buscar.todos.produtos', @user_dev_id, NOW(), @user_dev_id, NOW(), 'America/Sao_Paulo', 'America/Sao_Paulo', '127.0.0.1', '127.0.0.1', 0);

SET @ft_BscTdsPrdt_id = LAST_INSERT_ID();

INSERT INTO auth.menu_feature
(menu_id, feature_id) VALUES (@cm_produto_id, @ft_BscTdsPrdt_id);

INSERT INTO auth.feature
(name, code, user_register, date_register, user_last_update, date_last_update, zone_register, zone_last_update, ip_register, ip_last_update, deleted)
VALUES('Deletar Produto', 'deletar.produto', @user_dev_id, NOW(), @user_dev_id, NOW(), 'America/Sao_Paulo', 'America/Sao_Paulo', '127.0.0.1', '127.0.0.1', 0);

SET @ft_DltPrdt_id = LAST_INSERT_ID();

INSERT INTO auth.menu_feature
(menu_id, feature_id) VALUES (@cm_produto_id, @ft_DltPrdt_id);

INSERT INTO auth.feature
(name, code, user_register, date_register, user_last_update, date_last_update, zone_register, zone_last_update, ip_register, ip_last_update, deleted)
VALUES('Cadastrar Vendedor', 'cadastrar.vendedor', @user_dev_id, NOW(), @user_dev_id, NOW(), 'America/Sao_Paulo', 'America/Sao_Paulo', '127.0.0.1', '127.0.0.1', 0);

SET @ft_CdstVddr_id = LAST_INSERT_ID();

INSERT INTO auth.menu_feature
(menu_id, feature_id) VALUES (@cm_vendedor_id, @ft_CdstVddr_id);

INSERT INTO auth.feature
(name, code, user_register, date_register, user_last_update, date_last_update, zone_register, zone_last_update, ip_register, ip_last_update, deleted)
VALUES('Editar Vendedor', 'editar.vendedor', @user_dev_id, NOW(), @user_dev_id, NOW(), 'America/Sao_Paulo', 'America/Sao_Paulo', '127.0.0.1', '127.0.0.1', 0);

SET @ft_EdtVddr_id = LAST_INSERT_ID();

INSERT INTO auth.menu_feature
(menu_id, feature_id) VALUES (@cm_vendedor_id, @ft_EdtVddr_id);

INSERT INTO auth.feature
(name, code, user_register, date_register, user_last_update, date_last_update, zone_register, zone_last_update, ip_register, ip_last_update, deleted)
VALUES('Buscar Vendedor', 'buscar.vendedor', @user_dev_id, NOW(), @user_dev_id, NOW(), 'America/Sao_Paulo', 'America/Sao_Paulo', '127.0.0.1', '127.0.0.1', 0);

SET @ft_BscVddr_id = LAST_INSERT_ID();

INSERT INTO auth.menu_feature
(menu_id, feature_id) VALUES (@cm_vendedor_id, @ft_BscVddr_id);

INSERT INTO auth.feature
(name, code, user_register, date_register, user_last_update, date_last_update, zone_register, zone_last_update, ip_register, ip_last_update, deleted)
VALUES('Buscar Todos Vendedores', 'buscar.todos.vendedores', @user_dev_id, NOW(), @user_dev_id, NOW(), 'America/Sao_Paulo', 'America/Sao_Paulo', '127.0.0.1', '127.0.0.1', 0);

SET @ft_BscTdsVddr_id = LAST_INSERT_ID();

INSERT INTO auth.menu_feature
(menu_id, feature_id) VALUES (@cm_vendedor_id, @ft_BscTdsVddr_id);

INSERT INTO auth.feature
(name, code, user_register, date_register, user_last_update, date_last_update, zone_register, zone_last_update, ip_register, ip_last_update, deleted)
VALUES('Deletar Vendedor', 'deletar.vendedor', @user_dev_id, NOW(), @user_dev_id, NOW(), 'America/Sao_Paulo', 'America/Sao_Paulo', '127.0.0.1', '127.0.0.1', 0);

SET @ft_DltVddr_id = LAST_INSERT_ID();

INSERT INTO auth.menu_feature
(menu_id, feature_id) VALUES (@cm_vendedor_id, @ft_DltVddr_id);

INSERT INTO auth.feature
(name, code, user_register, date_register, user_last_update, date_last_update, zone_register, zone_last_update, ip_register, ip_last_update, deleted)
VALUES('Adicionar Contato Vendedor', 'adicionar.contato.vendedor', @user_dev_id, NOW(), @user_dev_id, NOW(), 'America/Sao_Paulo', 'America/Sao_Paulo', '127.0.0.1', '127.0.0.1', 0);

SET @ft_AdcCttVddr_id = LAST_INSERT_ID();

INSERT INTO auth.menu_feature
(menu_id, feature_id) VALUES (@cm_vendedor_id, @ft_AdcCttVddr_id);

INSERT INTO auth.feature
(name, code, user_register, date_register, user_last_update, date_last_update, zone_register, zone_last_update, ip_register, ip_last_update, deleted)
VALUES('Remover Contato Vendedor', 'remover.contato.vendedor', @user_dev_id, NOW(), @user_dev_id, NOW(), 'America/Sao_Paulo', 'America/Sao_Paulo', '127.0.0.1', '127.0.0.1', 0);

SET @ft_DelCttVddr_id = LAST_INSERT_ID();

INSERT INTO auth.menu_feature
(menu_id, feature_id) VALUES (@cm_vendedor_id, @ft_DelCttVddr_id);

INSERT INTO auth.feature
(name, code, user_register, date_register, user_last_update, date_last_update, zone_register, zone_last_update, ip_register, ip_last_update, deleted)
VALUES('Cadastrar Fornecedor', 'cadastrar.fornecedor', @user_dev_id, NOW(), @user_dev_id, NOW(), 'America/Sao_Paulo', 'America/Sao_Paulo', '127.0.0.1', '127.0.0.1', 0);

SET @ft_CdstrFndr_id = LAST_INSERT_ID();

INSERT INTO auth.menu_feature
(menu_id, feature_id) VALUES (@cm_fornecedor_id, @ft_CdstrFndr_id);

INSERT INTO auth.feature
(name, code, user_register, date_register, user_last_update, date_last_update, zone_register, zone_last_update, ip_register, ip_last_update, deleted)
VALUES('Editar Fornecedor', 'editar.fornecedor', @user_dev_id, NOW(), @user_dev_id, NOW(), 'America/Sao_Paulo', 'America/Sao_Paulo', '127.0.0.1', '127.0.0.1', 0);

SET @ft_EdtFndr_id = LAST_INSERT_ID();

INSERT INTO auth.menu_feature
(menu_id, feature_id) VALUES (@cm_fornecedor_id, @ft_EdtFndr_id);

INSERT INTO auth.feature
(name, code, user_register, date_register, user_last_update, date_last_update, zone_register, zone_last_update, ip_register, ip_last_update, deleted)
VALUES('Buscar Fornecedor', 'buscar.fornecedor', @user_dev_id, NOW(), @user_dev_id, NOW(), 'America/Sao_Paulo', 'America/Sao_Paulo', '127.0.0.1', '127.0.0.1', 0);

SET @ft_BscrFndr_id = LAST_INSERT_ID();

INSERT INTO auth.menu_feature
(menu_id, feature_id) VALUES (@cm_fornecedor_id, @ft_BscrFndr_id);

INSERT INTO auth.feature
(name, code, user_register, date_register, user_last_update, date_last_update, zone_register, zone_last_update, ip_register, ip_last_update, deleted)
VALUES('Buscar Todos Fornecedores', 'buscar.todos.fornecedores', @user_dev_id, NOW(), @user_dev_id, NOW(), 'America/Sao_Paulo', 'America/Sao_Paulo', '127.0.0.1', '127.0.0.1', 0);

SET @ft_BscrTdsFndr_id = LAST_INSERT_ID();

INSERT INTO auth.menu_feature
(menu_id, feature_id) VALUES (@cm_fornecedor_id, @ft_BscrTdsFndr_id);

INSERT INTO auth.feature
(name, code, user_register, date_register, user_last_update, date_last_update, zone_register, zone_last_update, ip_register, ip_last_update, deleted)
VALUES('Adicionar Endereço Fornecedor', 'adicionar.endereco.fornecedor', @user_dev_id, NOW(), @user_dev_id, NOW(), 'America/Sao_Paulo', 'America/Sao_Paulo', '127.0.0.1', '127.0.0.1', 0);

SET @ft_AdcEndFndr_id = LAST_INSERT_ID();

INSERT INTO auth.menu_feature
(menu_id, feature_id) VALUES (@cm_fornecedor_id, @ft_AdcEndFndr_id);

INSERT INTO auth.feature
(name, code, user_register, date_register, user_last_update, date_last_update, zone_register, zone_last_update, ip_register, ip_last_update, deleted)
VALUES('Remover Endereço Fornecedor', 'remover.endereco.fornecedor', @user_dev_id, NOW(), @user_dev_id, NOW(), 'America/Sao_Paulo', 'America/Sao_Paulo', '127.0.0.1', '127.0.0.1', 0);

SET @ft_RmvEndFndr_id = LAST_INSERT_ID();

INSERT INTO auth.menu_feature
(menu_id, feature_id) VALUES (@cm_fornecedor_id, @ft_RmvEndFndr_id);

INSERT INTO auth.feature
(name, code, user_register, date_register, user_last_update, date_last_update, zone_register, zone_last_update, ip_register, ip_last_update, deleted)
VALUES('Adicionar Contato Fornecedor', 'adicionar.contato.fornecedor', @user_dev_id, NOW(), @user_dev_id, NOW(), 'America/Sao_Paulo', 'America/Sao_Paulo', '127.0.0.1', '127.0.0.1', 0);

SET @ft_AdcCttFndr_id = LAST_INSERT_ID();

INSERT INTO auth.menu_feature
(menu_id, feature_id) VALUES (@cm_fornecedor_id, @ft_AdcCttFndr_id);

INSERT INTO auth.feature
(name, code, user_register, date_register, user_last_update, date_last_update, zone_register, zone_last_update, ip_register, ip_last_update, deleted)
VALUES('Remover Contato Fornecedor', 'remover.contato.fornecedor', @user_dev_id, NOW(), @user_dev_id, NOW(), 'America/Sao_Paulo', 'America/Sao_Paulo', '127.0.0.1', '127.0.0.1', 0);

SET @ft_RmvCttFndr_id = LAST_INSERT_ID();

INSERT INTO auth.menu_feature
(menu_id, feature_id) VALUES (@cm_fornecedor_id, @ft_RmvCttFndr_id);