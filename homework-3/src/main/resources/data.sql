-- -----------------------------------------------------
-- Data for table `cash_register`.`operation_status`
-- -----------------------------------------------------

INSERT INTO `cash_register`.`operation_status` (`id`, `status_name`)
VALUES (1, 'created');
INSERT INTO `cash_register`.`operation_status` (`id`, `status_name`)
VALUES (2, 'closed');
INSERT INTO `cash_register`.`operation_status` (`id`, `status_name`)
VALUES (3, 'cancelled');
INSERT INTO `cash_register`.`operation_status` (`id`, `status_name`)
VALUES (4, 'fiscalised');

-- -----------------------------------------------------
-- Data for table `cash_register`.`role`
-- -----------------------------------------------------

INSERT INTO `cash_register`.`role` (`id`, `role_name`)
VALUES (1, 'admin');
INSERT INTO `cash_register`.`role` (`id`, `role_name`)
VALUES (2, 'cashier');
INSERT INTO `cash_register`.`role` (`id`, `role_name`)
VALUES (3, 'chief_cashier');
INSERT INTO `cash_register`.`role` (`id`, `role_name`)
VALUES (4, 'merchandiser');

-- -----------------------------------------------------
-- Data for table `cash_register`.`product`
-- -----------------------------------------------------

INSERT INTO `cash_register`.`product` (`product_id`, `amount`, `code`, `name`, `price`)
VALUES (1, 100.0, 'a123', 'apple', 24.0);
INSERT INTO `cash_register`.`product` (`product_id`, `amount`, `code`, `name`, `price`)
VALUES (2, 200.0, 'b234', 'apricot', 34.0);

-- -----------------------------------------------------
-- Data for table `cash_register`.`user`
-- -----------------------------------------------------

INSERT INTO `cash_register`.`user` (`id_user`, `login`, `surname`, `role_id`)
VALUES (1, 'mike', 'jonson', 1);
INSERT INTO `cash_register`.`user` (`id_user`, `login`, `surname`, `role_id`)
VALUES (2, 'jack', 'simpson', 2);
INSERT INTO `cash_register`.`user` (`id_user`, `login`, `surname`, `role_id`)
VALUES (3, 'karen', 'peterson', 3);
INSERT INTO `cash_register`.`user` (`id_user`, `login`, `surname`, `role_id`)
VALUES (4, 'adam', 'amundsen', 4);
INSERT INTO `cash_register`.`user` (`id_user`, `login`, `surname`, `role_id`)
VALUES (5, 'lily', 'smith', 2);
INSERT INTO `cash_register`.`user` (`id_user`, `login`, `surname`, `role_id`)
VALUES (6, 'jane', 'cake', 2);
