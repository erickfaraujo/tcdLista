INSERT  INTO lista_db.tipo_lista (id_tipo_lista, desricao) 
SELECT  1, 'Minha Lista'
WHERE   NOT EXISTS 
        (   SELECT  1
            FROM    lista_db.tipo_lista 
            WHERE   desricao = 'Minha Lista' 
        );
        
INSERT  INTO lista_db.tipo_lista (id_tipo_lista, desricao) 
SELECT  2, 'Assitidos'
WHERE   NOT EXISTS 
        (   SELECT  1
            FROM    lista_db.tipo_lista 
            WHERE   desricao = 'Assistidos' 
        );

INSERT  INTO lista_db.tipo_lista (id_tipo_lista, desricao) 
SELECT  3, 'Curtidos'
WHERE   NOT EXISTS 
        (   SELECT  1
            FROM    lista_db.tipo_lista 
            WHERE   desricao = 'Curtidos' 
        );