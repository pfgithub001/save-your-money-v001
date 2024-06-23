INSERT INTO amount_frequencies (id, frequency_name, divider, leap_year_divider)
VALUES
    (1,'Diario',1,1),
    (2,'Semanal',7,7),
    (3,'Mensual',30.40,30.5),
    (4,'Trimestral',91.25,91.5),
    (5,'Semestral',182.5,183),
    (6,'Anual',365,366)
;
INSERT INTO fixed_movements (id, description, amount_frequency_id)
VALUES
    (1,'Sueldo',6),
    (2,'Gastos casa',3)
;
INSERT INTO amount_date_ranges (id, amount, start_date, end_date, fixed_movement_id)
VALUES
    (1,22000, NOW() - INTERVAL '60 days', NULL, 1),
    (2,600, NOW() - INTERVAL '60 days', NULL, 2)
;

INSERT INTO expected_movements (id, description, amount, amount_frequency_id)
VALUES
    (1,'Ocio',100,2),
    (2,'Ropa',60,3),
    (3,'Viajes',2500,6)
;

INSERT INTO movements (id, description, amount, date, expected_movement_id)
VALUES
    (1,'Cervezas',-5,NOW() - INTERVAL '60 days',1),
    (2,'Cena',-60,NOW() - INTERVAL '59 days',1),
    (3,'Bizums Cena',40,NOW() - INTERVAL '59 days',1),
    (4, 'Almuerzo trabajo', -15, NOW() - INTERVAL '56 days', 1),
    (5, 'Libros', -30, NOW() - INTERVAL '55 days', 1),
    (6, 'Café', -5, NOW() - INTERVAL '54 days', 1),
    (7, 'Gimnasio', -50, NOW() - INTERVAL '53 days', 1),
    (8, 'Cine', -20, NOW() - INTERVAL '52 days', 1),
    (9, 'Pizza', -25, NOW() - INTERVAL '51 days', 1),
    (10, 'Regalo cumpleaños', -30, NOW() - INTERVAL '50 days', 1),
    -- Movimientos adicionales para el expected_movement_id = 2
    (11, 'Farmacia', -15, NOW() - INTERVAL '49 days', 2),
    (12, 'Suscripción streaming', -12, NOW() - INTERVAL '48 days', 2),
    (13, 'Parking', -8, NOW() - INTERVAL '47 days', 2),
    (14, 'Comida rápida', -18, NOW() - INTERVAL '46 days', 2),
    (15, 'Electrodoméstico', -200, NOW() - INTERVAL '45 days', 2),
    (16, 'Servicio internet', -50, NOW() - INTERVAL '44 days', 2),
    (17, 'Compras online', -120, NOW() - INTERVAL '43 days', 2),
    (18, 'Alquiler', -1000, NOW() - INTERVAL '42 days', 2),
    (19, 'Concierto', -80, NOW() - INTERVAL '41 days', 2),
    (20, 'Decoración casa', -150, NOW() - INTERVAL '40 days', 2),
    -- Movimientos adicionales para el expected_movement_id = 3
    (21, 'Transporte público', -30, NOW() - INTERVAL '39 days', 3),
    (22, 'Gastos médicos', -90, NOW() - INTERVAL '38 days', 3),
    (23, 'Mantenimiento coche', -150, NOW() - INTERVAL '37 days', 3),
    (24, 'Comida gourmet', -100, NOW() - INTERVAL '36 days', 3),
    (25, 'Seguro', -200, NOW() - INTERVAL '35 days', 3),
    (26, 'Compra electrónica', -500, NOW() - INTERVAL '34 days', 3),
    (27, 'Entradas espectáculo', -70, NOW() - INTERVAL '33 days', 3),
    (28, 'Salida amigos', -40, NOW() - INTERVAL '32 days', 3),
    (29, 'Juegos', -50, NOW() - INTERVAL '31 days', 3),
    (30, 'Imprevistos', -100, NOW() - INTERVAL '30 days', 3)
;