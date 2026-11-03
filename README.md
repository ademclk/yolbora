# Commands

```bash
./gradlew build
./gradlew build -PjavaVersion=25
./gradlew :arch:test
./gradlew :api:bootRun
curl -fsS localhost:8080/actuator/health
docker compose config -q
docker compose up -d --wait
docker compose ps
docker compose exec postgres pg_isready -U ideate -d ideate
docker compose exec redis redis-cli ping
docker compose exec kafka /opt/kafka/bin/kafka-topics.sh --bootstrap-server localhost:29092 --list
./gradlew :api:smokeTest
```
