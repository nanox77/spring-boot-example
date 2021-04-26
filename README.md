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

Use endpoint:

At first, you have to get token using /login endpoint:

```
curl -X POST \
  http://localhost:8080/login \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 15345bd5-8ee5-4d7c-9ebc-7deaef9cd5bd' \
  -d '{
    "username": "nanox"
}'
```

Response:
```
{
    "username": "nanox",
    "token": "Bearer $TOKEN"
}
```

Get all superheros:
```
curl -X GET \
  http://localhost:8080/api/superheroes \
  -H 'Authorization: Bearer $TOKEN' \
  -H 'Content-Type: application/json'
```

Get superhero by id:
```
curl -X GET \
  http://localhost:8080/api/superhero/30875b39-371f-4f43-bf46-7bebcb2f0f14 \
  -H 'Authorization: Bearer $TOKEN' \
  -H 'Content-Type: application/json'
```

Search superhero by name:
```
curl -X GET \
  'http://localhost:8080/api/superheroes/search?name=man' \
  -H 'Authorization: Bearer $TOKEN' \
  -H 'Content-Type: application/json'
```

Update superhero:
```
curl -X PUT \
  http://localhost:8080/api/superhero/30875b39-371f-4f43-bf46-7bebcb2f0f14 \
  -H 'Authorization: Bearer $TOKEN' \
  -H 'Content-Type: application/json' \
  -d '{
	"name": "Super Manolito"
}'
```

Delete superhero:
```
curl -X DELETE \
  http://localhost:8080/api/superhero/30875b39-371f-4f43-bf46-7bebcb2f0f14 \
  -H 'Authorization: Bearer $TOKEN' \
  -H 'Content-Type: application/json'
```