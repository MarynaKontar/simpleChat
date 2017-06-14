

 SELECT * FROM chat.users WHERE LOGIN = 'TEST';
 SELECT * FROM chat.users WHERE LOGIN LIKE '%TE%';
 SELECT * FROM chat.users WHERE LOGIN > 'T';
 SELECT * FROM chat.users WHERE LOGIN IN ('TEST');
 SELECT * FROM chat.users WHERE `password` IN ('TEST');



SELECT * FROM chat.message  JOIN chat.users 
ON (chat.message.fk_message_user_login = chat.users.login)
where chat.users.login = 'test';