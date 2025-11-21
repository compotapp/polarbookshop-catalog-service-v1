# Если поднято несколько кластеров
# Показать все доступные контексты
kubectl config get-contexts

#конфиг текущего контекста
kubectl config current-context

# Переключиться на контекст нужного кластера
kubectl config use-context pbs

# Теперь все команды kubectl выполняются для кластера pbs
kubectl get nodes

kubectl get pod

#Логи приложения
kubectl logs deployment/pbs-catalog-service

#Получить все объекты, созданные ingress-nginx пространство имен
kubectl get all -n ingress-nginx

kubectl get ingress

kubectl port-forward -n ingress-nginx service/ingress-nginx-controller 8080:80
http://127.0.0.1:8080/books

kubectl port-forward service/polar-postgres 5432:5432

kubectl get deploy -l app=catalog-service


#Secret
kubectl create secret generic test-credentials --from-literal=test.username=user --from-literal=test.password=password

kubectl get secret test-credentials

