INSERT INTO `CRM`.`priority` (`id`, `is_active`, `name`) VALUES ('1', 1, 'Urgent');
INSERT INTO `CRM`.`priority` (`id`, `is_active`, `name`) VALUES ('2', 1, 'Normal');
INSERT INTO `CRM`.`priority` (`id`, `is_active`, `name`) VALUES ('3', 1, 'Important');
INSERT INTO `CRM`.`priority` (`id`, `is_active`, `name`) VALUES ('4', 1, 'Deadline');


INSERT INTO `CRM`.`status` (`id`, `is_active`, `name`, `sorting_order_number`) VALUES ('1', 1, 'New project', '5');
INSERT INTO `CRM`.`status` (`id`, `is_active`, `name`, `sorting_order_number`) VALUES ('2', 1, 'Production', '1');
INSERT INTO `CRM`.`status` (`id`, `is_active`, `name`, `sorting_order_number`) VALUES ('3', 1, 'Testing', '2');


INSERT INTO `CRM`.`app_user` (`id`, `first_name`, `last_name`, `login`, `password`, `user_role`) VALUES ('1', 'Jan', 'Kowalski', 'kowal', '$2a$10$AKHUuVgn6dZMte3vNar7Sus2nS0ELUkcu0iXEt0/vgjgQWrUXwC6K', 'ADMIN');
INSERT INTO `CRM`.`app_user` (`id`, `first_name`, `last_name`, `login`, `password`, `user_role`) VALUES ('2', 'Marcin', 'Kosztrzewa', 'kos', '$2a$10$G/unD.wnKGgAAMvxdOebIetbHhh3p4hxaEc3z/Zz6/6RNN1j7mcbS', 'ADMIN');
INSERT INTO `CRM`.`app_user` (`id`, `first_name`, `last_name`, `login`, `password`, `user_role`) VALUES ('3', 'Marian', 'Nowak', 'nowak', '$2a$10$I3gzDkje0II.aL8jBvJXG.MUPJPi0si6J7VnIuo12wp9Xg6sJP9oK', 'USER');
