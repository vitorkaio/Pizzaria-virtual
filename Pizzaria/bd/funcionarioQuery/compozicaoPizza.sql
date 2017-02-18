CREATE TABLE pedido(

    codigoPizza INTEGER REFERENCES pizza(codigoPizza) ON DELETE CASCADE,
    codigoIngrediente INTEGER REFERENCES ingrediente(codigoIngrediente) ON DELETE CASCADE,
    
    PRIMARY KEY(codigoPizza, codigoIngrediente)

)