#language: pt

@get_account
Funcionalidade: Consultar usuários
  Eu como usuário autenticado,
  Quero consultar as informações da minha conta,
  Para verificar meus dados pessoais e detalhes da conta.

  Cenário: Consultar conta com sucesso
    Dado que tenho um endpoint de consulta
    Quando faço a requisição com URL
    Então o status code 200 é retornado
    E as informações da conta