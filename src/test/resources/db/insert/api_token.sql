INSERT INTO api_tokens(user_id, code, name)
VALUES (1, '5a8666ba-fcf9-4f3f-89b0-5cc9d522fe40', 'CI');

INSERT INTO api_tokens(user_id, code, name)
VALUES (1, '158c058d-84da-4ca0-bd4a-b9c19e625140', 'CI2');

INSERT INTO api_token_permissions(user_id, code, permission)
VALUES (1, '158c058d-84da-4ca0-bd4a-b9c19e625140', 'file.edit');