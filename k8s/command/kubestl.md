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