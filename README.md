# Scan.AI Backend

This repository contains the Spring Boot backend that powers the Scan.AI platform. The application ships with a Docker-based development workflow so you can get started without installing Java or MySQL locally.

## ✅ Prerequisites

- [Docker Engine](https://docs.docker.com/engine/install/) 20.10+
- [Docker Compose](https://docs.docker.com/compose/install/) v2+
- (Optional) A copy of the `.env.example` file renamed to `.env` if you need to override default environment variables.

## 🚀 Running the application

1. Make sure Docker is running.
2. From the repository root, build and start the API service together with its MySQL dependency:

   ```bash
   docker compose up --build app
   ```

   This command:
   - Builds the backend image using the local `Dockerfile`.
   - Starts the `app` container along with the `mysql` service declared in `compose.yml`.
   - Exposes the API at [http://localhost:8080](http://localhost:8080).
   - Documentation at [http://localhost:8080/wagger-ui/index.html](http://localhost:8080/wagger-ui/index.html).

   Press `Ctrl+C` to stop the stack when you're done, and run `docker compose down` if you want to remove the containers and network.

## 🧪 Running the integration test suite

Execute the database population integration test inside the dedicated `tests` service:

```bash
docker compose run --rm tests mvn test -Dtest=DatabasePopulationIntegrationTest
```

This command will:
- Build the test image (targeting the Maven `build` stage).
- Spin up a disposable container with the project sources mounted.
- Wait for the MySQL service to become healthy.
- Run only the `DatabasePopulationIntegrationTest` class.
- Remove the container after completion thanks to `--rm`.

If the database containers were left running from a previous session, you can bring them down with `docker compose down` before re-running the tests.

## ♻️ Useful extras

- View container logs: `docker compose logs -f app`
- Reset the database volume (removes all data!):

  ```bash
  docker compose down -v
  docker compose up --build app
  ```

- Run the full Maven test suite: `docker compose run --rm tests mvn test`

Feel free to tailor the provided commands to your workflow.
