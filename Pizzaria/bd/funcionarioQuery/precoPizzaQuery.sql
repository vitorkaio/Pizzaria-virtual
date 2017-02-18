CREATE TABLE precoPizza(

    codPizza INTEGER REFERENCES pizza(codPizza) ON DELETE CASCADE,
    tamanho char,
    data varchar(15),
    preco FLOAT,

    PRIMARY KEY(codPizza, tamanho, data)
)