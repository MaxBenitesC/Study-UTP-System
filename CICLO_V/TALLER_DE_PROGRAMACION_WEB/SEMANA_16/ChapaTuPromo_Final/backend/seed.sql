INSERT INTO productos (
    nombre,
    categoria,
    descripcion,
    fecha_vencimiento,
    precio_original,
    stock,
    estado
) VALUES
-- Las fechas son relativas para que la demostracion siempre tenga ofertas vigentes.
-- El 16/07/2026 generan productos con vencimiento del 16 al 19 de julio.
('Torta de Chocolate San Antonio 1kg', 'Reposteria y Panaderia', 'Torta entera con relleno de manjar blanco y cobertura de chocolate bitter', CURRENT_DATE, 45.00, 2, 'Disponible'),
('Torta Tres Leches 800g', 'Reposteria y Panaderia', 'Torta clasica peruana banada en tres leches, sin conservantes artificiales', CURRENT_DATE + 1, 38.00, 4, 'Disponible'),
('Queque Marmoleado Donofrio 500g', 'Reposteria y Panaderia', 'Queque esponjoso sabor vainilla y chocolate, ideal para lonchera o desayuno', CURRENT_DATE + 2, 14.00, 6, 'Disponible'),
('Pan de Molde Bimbo Integral 500g', 'Reposteria y Panaderia', 'Pan integral sin corteza, alto en fibra, ideal para sanguches saludables', CURRENT_DATE + 3, 6.00, 10, 'Disponible'),
('Yogurt Gloria Fresa 1L', 'Lacteos', 'Yogurt bebible sabor fresa, sin lactosa, enriquecido con calcio y vitamina D', CURRENT_DATE, 8.50, 5, 'Disponible'),
('Queso Fresco La Preferida 250g', 'Lacteos', 'Queso fresco tradicional peruano, ideal para desayuno criollo con pan frances', CURRENT_DATE + 1, 9.00, 6, 'Disponible'),
('Crema de Leche Laive 200ml', 'Lacteos', 'Crema de leche fresca para cocinar, ideal para salsas y postres criollos', CURRENT_DATE + 2, 5.00, 8, 'Disponible'),
('Pollo Entero Refrigerado aprox. 1.8kg', 'Carnes y Embutidos', 'Pollo entero sin menudencia, refrigerado, listo para cocinar. Ideal para caldo o seco de pollo', CURRENT_DATE + 3, 22.00, 3, 'Disponible'),
('Jamon del Pais Suiza 200g', 'Carnes y Embutidos', 'Jamon del pais en lonchas estilo criollo, ideal para sanguches a la limenya', CURRENT_DATE, 12.00, 5, 'Disponible'),
('Salchicha estilo Huacho Suiza 500g', 'Carnes y Embutidos', 'Salchicha de cerdo y res estilo Huacho, para parrilla, sarten o con tacu tacu', CURRENT_DATE + 1, 15.00, 7, 'Disponible'),
('Filete de Perico refrigerado 500g', 'Carnes y Embutidos', 'Filete de perico fresco refrigerado, ideal para ceviche o sudado de pescado', CURRENT_DATE + 2, 18.00, 1, 'Disponible'),
('Ensalada Rusa Bells 250g', 'Preparados y Listos para comer', 'Ensalada rusa clasica con papa, zanahoria y betarraga, lista para servir', CURRENT_DATE + 3, 7.50, 3, 'Disponible'),
('Causa Rellena de Atun 300g', 'Preparados y Listos para comer', 'Causa limenya tradicional rellena de atun con mayonesa y palta. Lista para consumir', CURRENT_DATE, 12.00, 4, 'Disponible');
