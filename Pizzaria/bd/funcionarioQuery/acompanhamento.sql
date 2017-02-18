CREATE TABLE acompanhamento(

    numeroPedido INTEGER REFERENCES pedido(numeroPedido) ON DELETE CASCADE,
    tipo varchar(15),
    int quantidade,
    float preco,
    
    PRIMARY KEY(numeroPedido, tipo)

)