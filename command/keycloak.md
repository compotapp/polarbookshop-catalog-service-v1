docker exec -it pbs-keycloak bash

cd /opt/keycloak/bin

./kcadm.sh config credentials \
--server http://localhost:8080 \
--realm master \
--user user \
--password password

#http://localhost:8080/admin

./kcadm.sh create realms -s realm=PBS -s enabled=true
#create realms - команда создания новой области
#-s realm=PBS - устанавливает имя realm как "PBS"
#-s enabled=true - включает realm (делает его активным)

./kcadm.sh create roles -r PBS -s name=employee
./kcadm.sh create roles -r PBS -s name=customer
#Создаётся роль "employee" в realm PBS
#Создаётся роль "customer" в realm PBS

./kcadm.sh create users -r PBS \
-s username=admin \
-s firstName=Admin \
-s lastName=Admin \
-s enabled=true

./kcadm.sh add-roles -r PBS \
--uusername admin \
--rolename employee \
--rolename customer

#Имя пользователя для нового пользователя. Он будет использоваться для входа в систему.
#Пользователь должен быть активным.
#Изабель одновременно сотрудник и клиент.

./kcadm.sh create users -r PBS \
-s username=user \
-s firstName=User \
-s lastName=User \
-s enabled=true

./kcadm.sh add-roles -r PBS \
--uusername user \
--rolename customer
#Бьорн — клиент.

./kcadm.sh get users -r PBS -q username=admin
#Посмотреть информацию о пользователе

./kcadm.sh get-roles -r PBS --uusername admin
#Посмотреть роли пользователя

./kcadm.sh set-password -r PBS --username admin --new-password password

./kcadm.sh set-password -r PBS --username user --new-password password
#Установить пароль для пользователя

./kcadm.sh create clients -r PBS \
-s clientId=pbs-edge-service \
-s enabled=true \
-s publicClient=false \
-s secret=pbs-keycloak-secret \
-s 'redirectUris=["http://localhost:9000", "http://localhost:9000/login/oauth2/code/*"]'
#clientId=pbs-edge-service - уникальный идентификатор клиента
#enabled=true - клиент активен
#publicClient=false - это конфиденциальный клиент (с секретом)
#secret=pbs-keycloak-secret - секрет для аутентификации клиента
#redirectUris - разрешённые URL для перенаправления после аутентификации

#PowerShell
$tokenResponse = Invoke-RestMethod -Uri "http://localhost:8080/realms/PBS/protocol/openid-connect/token" `
>>   -Method POST `
>>   -Headers @{"Content-Type"="application/x-www-form-urlencoded"} `
>>   -Body "username=admin&password=password&grant_type=password&client_id=pbs-edge-service&client_secret=pbs-keycloak-secret"

$newToken = $tokenResponse.access_token

Write-Host "New Token: $newToken"

PS C:\Users\user> Invoke-RestMethod -Uri "http://localhost:9001/books" `
>>   -Method POST `
>>   -Headers @{
>>     "Content-Type"="application/json"
>>     "Authorization"="Bearer $newToken"
>>   } `
>>   -Body '{"isbn":"1234567890", "title":"Test Book", "author":"Author", "price":9.99}'

Invoke-RestMethod -Uri "http://localhost:9001/books" -Method GET

