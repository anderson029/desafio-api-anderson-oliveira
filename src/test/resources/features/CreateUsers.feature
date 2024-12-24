#language: pt

@create_users
Funcionalidade: Cadastro de Usuários
  Eu como visitante do sistema,
  Quero criar uma conta de usuário,
  Para acessar o sistema e gerenciar meus dados pessoais.

  Contexto: Autenticação do usuário
    Dado que estou autenticado.

  Esquema do Cenario: Cadastro de usuário
    E forneço todos os dados obrigatórios "<fieldName>", "<param>", "<fieldJob>", "<job>"
    Quando realizo uma requisição para o endpoint de cadastro,
    Então o sistema retorna o status code 201,
    E os dados do novo usuário são exibidos na resposta.

    Exemplos:
    |fieldName |param   |fieldJob |job    |
    |name      |Anderson|job      |Quality|
