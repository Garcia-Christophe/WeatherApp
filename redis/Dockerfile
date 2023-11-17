#https://r-future.github.io/post/how-to-fix-redis-warnings-with-docker/
FROM redis:3.2-alpine
WORKDIR /redis
COPY redis.conf /usr/local/etc/redis/redis.conf
COPY init.sh ./
RUN chmod +x init.sh
