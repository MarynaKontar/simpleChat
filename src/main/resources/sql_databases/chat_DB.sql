CREATE DATABASE IF NOT EXISTS chat;

use chat;

-- 
CREATE TABLE IF NOT EXISTS users(
`login` VARCHAR(255) NOT NULL UNIQUE,
`password` VARCHAR(255) NOT NULL,
`username` VARCHAR(255) NOT NULL,
`registration_date` timestamp NOT NULL,
PRIMARY KEY(`login`)
);

CREATE TABLE IF NOT EXISTS `chat`(
`name` VARCHAR(255) NOT NULL,
`description` VARCHAR(255) NOT NULL,
PRIMARY KEY(`name`)
);

CREATE TABLE IF NOT EXISTS `message`(
`fk_message_user_login` VARCHAR(255) NOT NULL,
`fk_message_chat_name` VARCHAR(255) NOT NULL,
`text` varchar(255),
`date` timestamp NOT NULL,

FOREIGN KEY (`fk_message_user_login`) REFERENCES users(`login`),
FOREIGN KEY (`fk_message_chat_name`) REFERENCES chat(`name`),

PRIMARY KEY(`fk_message_user_login`,`fk_message_chat_name`)
);


-- ALTER TABLE `chat`.`message` 
-- ADD UNIQUE INDEX `fk_message_user_login_UNIQUE` (`fk_message_user_login` ASC);


CREATE TABLE `chat`.`groups` (
  `id` INT(11) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC));



CREATE TABLE `chat`.`user_groups` (
  `group_id` INT(11) NOT NULL,
  `user_login` VARCHAR(45) NOT NULL,
  INDEX `FK_ug_group_id_idx` (`group_id` ASC),
  INDEX `FK_ug_user_login_idx` (`user_login` ASC),
  CONSTRAINT `FK_ug_group_id`
    FOREIGN KEY (`group_id`)
    REFERENCES `chat`.`groups` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_ug_user_login`
    FOREIGN KEY (`user_login`)
    REFERENCES `chat`.`users` (`login`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
