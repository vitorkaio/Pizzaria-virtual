CREATE TABLE pedido(

    numeroPedido INTEGER PRIMARY KEY AUTOINCREMENT,
    cpf INTEGER REFERENCES cliente(cpf) ON DELETE CASCADE,
    data varchar(15),
    hora varchar(15),
    situacao varchar(15),
    precoTotal float,
    formaDePagamento varchar(15)   

)