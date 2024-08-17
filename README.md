MAM (Maintenance Anomaly Management)

Descrição
MAM (Maintenance Anomaly Management) é uma API RESTful desenvolvida em Java utilizando o Spring Boot. Esta aplicação é projetada para gerenciar cadastros de anomalias de manutenção, incluindo usuários, anomalias, equipamentos, departamentos e setores.

Funcionalidades
Cadastro e gerenciamento de usuários
Registro de anomalias de manutenção
Controle de equipamentos e suas localizações
Definição de departamentos responsáveis pelo tratamento de anomalias
Configuração de prazos para resolução com base na prioridade das anomalias

Tecnologias Utilizadas
Java 21
Spring Boot
Maven
MySQL

Pré-requisitos
Certifique-se de que você tem as seguintes ferramentas instaladas em sua máquina:

Java 21 ou superior
Maven
MySQL
IDE (IntelliJ IDEA, Eclipse, etc.)

Como Rodar o Projeto
Clone o repositório:
bash
Copiar código
git clone https://github.com/lucianolelespadilha/MAM.git
Importe o projeto em sua IDE de preferência.
Configure o banco de dados MySQL:
Crie um banco de dados com o nome mam_db.
Atualize as credenciais do banco de dados no arquivo application.properties.
Execute o comando Maven para compilar e iniciar o projeto:
bash
Copiar código
mvn spring-boot:run

Contribuições
Contribuições são bem-vindas! Para contribuir:

Faça um fork do projeto.
Crie uma nova branch para sua feature (git checkout -b feature/nome-da-feature).
Commit suas alterações (git commit -m 'Adiciona nova feature').
Faça o push para a branch (git push origin feature/nome-da-feature).
Abra um Pull Request.

Licença
Este projeto está licenciado sob a MIT License - veja o arquivo LICENSE.md para mais detalhes.

Contato
Se tiver dúvidas ou sugestões, entre em contato:
Email: luciano.leles.padilha@gmail.com
