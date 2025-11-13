FROM postgres

# Установка переменных окружения
ENV POSTGRES_USER=user
ENV POSTGRES_PASSWORD=password
ENV POSTGRES_DB=polardb_catalog

# Открываем порт PostgreSQL
EXPOSE 5432

#docker network create catalog-network  ; docker network ls

#docker build -t pbs-postgres docker/postgres
#docker run -d --name pbs-postgres -p 5432:5432 pbs-postgres
#docker run -d --name pbs-postgres --net catalog-network -p 5432:5432 pbs-postgres