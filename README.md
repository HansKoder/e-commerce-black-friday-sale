# E-commerce (Black Friday Sale)

This Side Project has as goal to simulate the popular event called Black Friday Sale

## Goals

- Service build with Spring Boot - Nest.js and FastAPI.
- Build a scalable application
- Explore and expand the knowledge about distributed systems using caching distributed like redis/valkey and scyllaDB or MongoDB.


# Stack 

## Backend 

- Spring Boot (Now)
- Nest.js (next iteration)

## Front end
- Vite
- React
- Tailwind

## Load Testing

- Artillery (Now)
- Migrate to K6 (Next Goal)

## Monitoring with prometheus and grafana

Located this path
```bash
cd ./monitoring
```

### Run docker-compose 

```bash
docker-compose up -d
```

### Prometheus

Website is `localhost:9000`

### Grafana 

Website is `localhost:3000`

### Credentials Grafana
After accesing, you need to put your credentials for example admin - Admin2025

### Grafana Datasource

You need to add prometheus as datasource.
Save and test.

## Run Load Testing with Artillery

### Install dependencies

Located this path
```bash
cd ./load-testing
npm install
```
### Load Testing shopping cart

```bash
npm run shopping-cart
```

## Redis Cluster

Located the folder `redis-cluster`


### Running docker-compsoe

```bash
docker-compose up -d
```

### Create Cluster Redis

```bash
docker exec -it redis-cluster-redis-node-1-1 redis-cli --cluster create \
  redis-cluster-redis-node-1-1:6379 \
  redis-cluster-redis-node-2-1:6379 \
  redis-cluster-redis-node-3-1:6379 \
  redis-cluster-redis-node-4-1:6379 \
  redis-cluster-redis-node-5-1:6379 \
  redis-cluster-redis-node-6-1:6379 \
  --cluster-replicas 1
```

Type `'yes'`

### Test Redis Cluster

```bash
docker exec -it redis-cluster-redis-node-1-1 redis-cli -c
cluster info 
cluster nodes
```

### This should be the possible output

```text
cluster_state:ok
cluster_slots_assigned:16384
cluster_slots_ok:16384
cluster_known_nodes:6
cluster_size:3
```
