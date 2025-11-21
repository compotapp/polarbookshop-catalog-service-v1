kubectl exec -it pbs-catalog-service-85765c965-pdsm4 -- /bin/bash

# Это как сделать уникальный ключ для твоего дома
openssl genrsa -out tls.key 2048

# Это как получить паспорт на дом
openssl req -new -x509 -key tls.key -out tls.crt -days 365 -subj "/CN=catalog-service.local"

-days 365 - паспорт действует 1 год
-subj "/CN=my-app.example.com" - имя твоего дома-сайта

# Создаем секрет одной командой
kubectl create secret tls catalog-tls \
--cert=tls.crt \
--key=tls.key

# Превращаем наш паспорт и ключ в специальный код base64
cat tls.crt | base64 | tr -d '\n'
cat tls.key | base64 | tr -d '\n'

#Добавьте запись в /etc/hosts (Linux/Mac) или C:\Windows\System32\drivers\etc\hosts

# Добавляем host в локальную машину
echo "127.0.0.1 catalog-service.local" | sudo tee -a /etc/hosts

# Запускаем port-forward до ingress
kubectl port-forward service/ingress-nginx-controller -n ingress-nginx 443:443

# Теперь можно зайти в браузере:
# https://catalog-service.local/actuator/health