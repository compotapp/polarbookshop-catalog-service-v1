# Запустите Docker Desktop перед началом

# Запустите Minikube с достаточными ресурсами
minikube start --cpus=3 --memory=4096 --disk-size=20g --driver=docker --profile jenkins

# Включите необходимые аддоны
minikube addons enable ingress --profile jenkins
#minikube addons enable registry --profile jenkins

minikube addons disable registry --profile jenkins
# registry proxy не поднимается пробросить порты
kubectl port-forward -n kube-system service/registry 5000:80

# Проверьте статус
minikube status --profile jenkins

minikube update-context --profile jenkins

# Получите kubeconfig и создайте ConfigMap powershell
minikube kubectl -- get secrets -o jsonpath="{.items[?(@.metadata.annotations['kubernetes\.io/service-account\.name']=='default')].data.ca}" -n default | Out-File -FilePath ca.crt -Encoding ASCII --profile jenkins

minikube kubectl -- create configmap kube-config --from-file=$HOME/.kube/config -n jenkins

# 2й вариант 

# Настраиваем Docker CLI для работы с Minikube
minikube docker-env
# Вернет команду типа 
& minikube -p minikube docker-env --shell powershell | Invoke-Expression

# Проверяем что Docker работает с Minikube
docker images

# Получаем пароль администратора Jenkins
minikube kubectl -- exec -n jenkins deployment/jenkins -- cat /var/jenkins_home/secrets/initialAdminPassword

# Открываем Jenkins в браузере, пробросит порт
minikube service jenkins -n jenkins --url

###################################################

#Создайте пространство имён для Jenkins
kubectl create namespace devops-tools

#Получить сведения о развертывании
kubectl describe deployments --namespace=devops-tools

#Пароль можно найти в конце журнала
kubectl logs jenkins-deployment-2539456353-j00w5 --namespace=devops-tools