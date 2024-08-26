# Viticultor

## adicionarUva()

### Metodo Put

### URL:
```url
http://localhost:8080/uva
```
### Body
```JSON
{
	"datachegada": "2024-08-01",
	"numerotalao": 1,
	"sanidade": 3,
	"peso": 32,
	"so2": "so2",
	"numerolote": 1,
	"tipodevinho":"tinto",
	"casta": "branco"
}
```

## atualizarUva()

### Metodo Put

### URL:
```url
http://localhost:8080/uva
```
### Body
```JSON
{
	"datachegada": "2024-08-01",
	"numerotalao": 1,
	"sanidade": 3,
	"peso": 32,
	"so2": "so2",
	"numerolote": 1,
	"tipodevinho":"tinto",
	"casta": "branco"
}
```

## listarUvas()

### Metodo Get

### URL:
```url
http://localhost:8080/uva
```

## detalharUva()

### Metodo Get

### URL:
```url
http://localhost:8080/uva/{id}
```

## removerUva()

### Metodo Delete

### URL:
```url
http://localhost:8080/uva/{id}
```

## inativarUva()

### Metodo Delete

### URL:
```url
http://localhost:8080/uva/inativar{id}
```