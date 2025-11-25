#!/bin/bash

# Script para rodar o backend localmente com hot-reload
# Antes de executar, certifique-se de que o MySQL está rodando:
# docker compose up -d mysql

echo "🚀 Iniciando Scan.AI Backend em modo de desenvolvimento..."
echo ""
echo "✅ Hot-reload habilitado via Spring Boot DevTools"
echo "📡 LiveReload estará disponível na porta 35729"
echo ""

# Carregar SDKMAN para usar Java 21
source "$HOME/.sdkman/bin/sdkman-init.sh"

# Verificar se MySQL está rodando
if ! docker ps | grep -q mysql; then
    echo "⚠️  MySQL não está rodando. Iniciando container..."
    docker compose up -d mysql
    echo "⏳ Aguardando MySQL inicializar..."
    sleep 5
fi

# Exportar variáveis de ambiente
export PROFILE=dev
export DB_URL_DEV=jdbc:mysql://localhost:3307/scanAiDev
export DB_USERNAME=root
export DB_PASSWORD=root
export SECRET=250c089a-5651-4e1c-82cd-7fe00a6190ee
export MATRICULA=123
export EMAIL=teste@empresa.com
export CPF=11111111111
export NOME=Admin
export SENHA=senha123

# Rodar a aplicação
mvn spring-boot:run
