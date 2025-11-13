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