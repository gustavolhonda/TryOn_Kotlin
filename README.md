# TryOn Kotlin

Aplicativo Android desenvolvido em Kotlin para Aluguel de roupas.

## Membros

811773 - Jakson Huang Zheng

811716 - Gustavo Lamin Honda

## Como Buildar

1. Clone o repositório
2. Abra o projeto no Android Studio
3. Sincronize o projeto com os arquivos Gradle
4. Execute o build usando:
   ```
   ./gradlew build
   ```
   ou apertando no botão de build

## Como Testar

### Testes Unitários
Para executar os testes unitários:
```
./gradlew test
```

### Testes de Instrumentação
Para executar os testes de instrumentação:
```
./gradlew connectedAndroidTest
```

## Estrutura do Projeto

- `app/src/main/java` - Código fonte principal
- `app/src/test/java` - Testes unitários
- `app/src/androidTest/java` - Testes de persistencia local
