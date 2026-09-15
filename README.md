# NetObserver

## Local environment

Create the local environment file once:

```shell
cp .env.example .env
```

Start the services from the repository root. The explicit `--env-file` is
required because the Compose file is stored in `infra/`, while `.env` is stored
in the repository root.

```shell
docker compose --env-file .env -f infra/docker-compose.yaml up -d
```

Stop the services with:

```shell
docker compose --env-file .env -f infra/docker-compose.yaml down
```
