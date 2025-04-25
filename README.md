# API Dual Database Project

This project is a dual-database application consisting of two Spring Boot APIs: one connected to a MongoDB database and another to a MySQL database. The services are orchestrated using Docker Compose, ensuring easy setup and deployment. The MongoDB API handles data stored in a MongoDB instance, while the MySQL API manages data in a MySQL database.

---

## Project Structure





- `api-mongodb`: Spring Boot application connected to MongoDB.



- `api-mysql`: Spring Boot application connected to MySQL.



- `docker-compose.yml`: Defines and orchestrates the MongoDB, MySQL, and API services.



- **Volumes**: Persistent storage for MongoDB (`mongodb_data`) and MySQL (`mysql_data`).



- **Network**: A bridge network (`app-network`) for communication between services.


---

## Prerequisites

Before running the project, ensure you have the following installed:





- [Docker]



- [Docker Compose]



- [Java 21 (for local development, if not using Docker)]



- [Maven (for building the Spring Boot applications, if not using Docker)]

---

# Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/your-repo-name.git
cd your-repo-name
```

### 2. Directory Structure

Ensure the following directories exist:





./api-mongodb: Contains the MongoDB API source code and Dockerfile.



./api-mysql: Contains the MySQL API source code and Dockerfile.

### 3. Configure Environment

The docker-compose.yml defines environment variables for both APIs:





MongoDB API: Connects to mongodb:27017/meu_banco_mongo.



MySQL API: Connects to mysql:3306/meu_banco_mysql with credentials root/root.

The application.properties for the MySQL API is overridden by environment variables in docker-compose.yml to ensure correct configuration.

### 4. Build and Run with Docker Compose

Run the following command to build and start all services:

docker-compose up -d

This will:





Pull the latest mongo and mysql images.



Build the api-mongodb and api-mysql images using their respective Dockerfiles.



Start the services with persistent volumes and a shared network.

### 5. Access the APIs





- `MongoDB API`: Available at http://localhost:8080



- `MySQL API`: Available at http://localhost:8081

### 6. Verify Database Connections





MongoDB:

```bash
docker exec -it mongodb mongosh meu_banco_mongo
```


MySQL:

```bash
docker exec -it mysql mysql -uroot -proot -e "SHOW DATABASES;"
```

### 7. Stop the Services

To stop and remove the containers:

```bash
docker-compose down
```

To also remove volumes (data will be lost):

```
docker-compose down -v
```

## Services Overview

| Service       | Description                 | Port (Host) | Port (Container) | Database           |
|---------------|-----------------------------|-------------|------------------|--------------------|
| `mongodb`     | MongoDB database            | 27017       | 27017            | `meu_banco_mongo`  |
| `mysql`       | MySQL database              | 3306        | 3306             | `meu_banco_mysql`  |
| `api-mongodb` | Spring Boot API for MongoDB | 8080        | 8080             | Connects to MongoDB|
| `api-mysql`   | Spring Boot API for MySQL   | 8081        | 8081             | Connects to MySQL  |

## Configuration Details

### MongoDB API:

- Configured via `SPRING_DATA_MONGODB_URI` in `docker-compose.yml`.
- Connects to `mongodb:27017/meu_banco_mongo`.

### MySQL API:

- Configured via environment variables in `docker-compose.yml` (overrides `application.properties`).
- Connects to `mysql:3306/meu_banco_mysql` with credentials `root/root`.
- Hibernate DDL is set to `update` for automatic schema updates.

### Docker Compose:

- Uses version `3.8`.
- Defines a bridge network (`app-network`) for service communication.
- Persists data using volumes: `mongodb_data`, `mysql_data`.


## Troubleshooting

### API fails to connect to database:


- Ensure the `mongodb` and `mysql` services are running before the APIs start. The `depends_on` directive helps, but you may add health checks for robustness:

```yaml
mysql:
  healthcheck:
    test: ["CMD", "mysqladmin", "ping", "-h", "localhost", "-uroot", "-proot"]
    interval: 10s
    timeout: 5s
    retries: 5

mongodb:
  healthcheck:
    test: ["CMD", "mongo", "--eval", "db.adminCommand('ping')"]
    interval: 10s
    timeout: 5s
    retries: 5
```

- Check logs:

```bash
docker logs api-mysql
docker logs api-mongodb
```


### Port Conflicts:

- If ports `27017`, `3306`, `8080`, or `8081` are in use, modify the host ports in `docker-compose.yml`, for example:

```yaml
3307:3306
```



## Contributing

1. Fork the repository.
2. Create a new branch:  
   `git checkout -b feature/your-feature`
3. Commit your changes:  
   `git commit -m 'Add your feature'`
4. Push to the branch:  
   `git push origin feature/your-feature`
5. Open a Pull Request.


## 👨‍💻 Authors

- **Vitu Felipe** | [@vitufelipe](https://github.com/vitufelipe)
- **Maria Eduarda** | 

## License

This project is licensed under the MIT License. See the LICENSE file for details.

## Contact

For questions or feedback, open an issue on GitHub or contact victor_felipe28@hotmail.com.
