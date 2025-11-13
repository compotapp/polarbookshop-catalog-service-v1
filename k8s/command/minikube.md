minikube start --cpus 2 --memory 4g --driver docker --profile pbs
minikube start --cpus 2 --memory 4g --nodes 2 --driver docker --profile pbs

minikube start --profile pbs
minikube stop --profile pbs

minikube delete --profile pbs --all
minikube delete --all

minikube image load pbs-catalog-service --profile pbs

