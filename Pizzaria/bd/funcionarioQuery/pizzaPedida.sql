CREATE TABLE pizzaPedida(

    numeroPedido INTEGER REFERENCES pedido(numeroPedido) ON DELETE CASCADE,
    codigoPizza INTEGER REFERENCES pizza(codigoPizza) ON DELETE CASCADE,
    borda varchar(15),
    quantidade int,
    
    PRIMARY KEY(numeroPedido, codigoPizza)   

)