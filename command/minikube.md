minikube start --cpus 2 --memory 4g --driver docker --profile pbs
minikube start --cpus 2 --memory 2g --nodes 2 --driver docker --profile pbs

minikube start --profile pbs
minikube stop --profile pbs

minikube dashboard --profile pbs

minikube delete --profile pbs --all
minikube delete --all

minikube addons enable ingress --profile pbs
kubectl get pods -n ingress-nginx

minikube image load pbs-catalog-service --profile pbs

minikube addons enable ingress --profile pbs

minikube tunnel --profile pbs
http://127.0.0.1/books

minikube ip




