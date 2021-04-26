Run app using Docker:

Build image:
```
docker build -t nanox/w2m-superheroes:1.0.0 .
```

Run image:
```
docker run -p 8080:8080 -t nanox/w2m-superheroes:1.0.0
```

Documentation:
```
http://localhost:8080/swagger-ui/
```

API docs:
```
http://localhost:8080/v2/api-docs
```