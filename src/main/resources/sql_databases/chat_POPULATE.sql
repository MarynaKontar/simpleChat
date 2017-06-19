-- INSERT INTO chat.users (LOGIN, PASS, USERNAME) 
-- VALUES ('TEST', 'TEST','TEST');



INSERT INTO `chat`.`chat`
(`name`,
`description`)
VALUES
('PUBLIC', 'SDFFGYH'
);


INSERT INTO `chat`.`users` (`login`, `password`, `username`) VALUES ('test', 'test', 'test');
INSERT INTO `chat`.`users` (`login`, `password`, `username`) VALUES ('Login1', 'Pass1', 'userName1');
INSERT INTO `chat`.`users` (`login`, `password`, `username`) VALUES ('Vasya', '111', 'vasya username');
UPDATE `chat`.`users` SET `login`='TEST' WHERE `login`='test';


INSERT INTO `chat`.`message`
(`fk_message_user_login`,
`fk_message_chat_name`,
`text`)
VALUES
('test',
'PUBLIC',
'HELLO');

INSERT INTO `chat`.`groups` (`id`, `name`) VALUES ('1', 'group1');
INSERT INTO `chat`.`groups` (`id`, `name`) VALUES ('2', 'group2');

INSERT INTO `chat`.`user_groups` (`group_id`, `user_login`) VALUES ('1', 'Vasya');
INSERT INTO `chat`.`user_groups` (`group_id`, `user_login`) VALUES ('1', 'TEST');
INSERT INTO `chat`.`user_groups` (`group_id`, `user_login`) VALUES ('1', 'Login1');
INSERT INTO `chat`.`user_groups` (`group_id`, `user_login`) VALUES ('2', 'Login1');





-- ALTER TABLE `chat`.`users` 
-- ADD INDEX `INDEX_password` (`password` ASC);
-- 
-- ALTER TABLE `chat`.`users` 
-- ADD UNIQUE INDEX `login_UNIQUE` (`login` ASC);
-- 
-- 
-- ALTER TABLE `chat`.`message` 
-- ADD UNIQUE INDEX `fk_message_user_login_UNIQUE` (`fk_message_user_login` ASC);
