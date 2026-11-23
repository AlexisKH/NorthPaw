INSERT INTO pet_type (name) VALUES ('Dog'), ('Cat'), ('Bird'), ('Fish'), ('Small Animal');

INSERT INTO brand (name) VALUES ('Orijen'), ('Royal Canin'), ('Hill''s Science Diet'), ('Kong'), ('Tetra'), ('Oxbow'), ('Kaytee'), ('Greenies'), ('Fluval');

INSERT INTO category (name, parent_category_id) VALUES ('Food', NULL), ('Treats', NULL), ('Toys', NULL), ('Aquarium Supplies', NULL);
INSERT INTO category (name, parent_category_id) VALUES ('Dry Food', (SELECT category_id FROM category WHERE name = 'Food'));

INSERT INTO item (brand_id, category_id, pet_type_id, name, description, created_at) VALUES
((SELECT brand_id FROM brand WHERE name='Orijen'), (SELECT category_id FROM category WHERE name='Dry Food'), (SELECT pet_type_id FROM pet_type WHERE name='Dog'), 'Original Dry Dog Food', 'Grain-free kibble made with fresh chicken and turkey.', DATEADD('DAY', -9, CURRENT_TIMESTAMP)),
((SELECT brand_id FROM brand WHERE name='Royal Canin'), (SELECT category_id FROM category WHERE name='Dry Food'), (SELECT pet_type_id FROM pet_type WHERE name='Cat'), 'Indoor Adult Cat Food', 'Supports digestion and reduces litter box odour for indoor cats.', DATEADD('DAY', -8, CURRENT_TIMESTAMP)),
((SELECT brand_id FROM brand WHERE name='Hill''s Science Diet'), (SELECT category_id FROM category WHERE name='Dry Food'), (SELECT pet_type_id FROM pet_type WHERE name='Dog'), 'Puppy Healthy Development', 'Balanced nutrition for growing puppies, with DHA for brain development.', DATEADD('DAY', -7, CURRENT_TIMESTAMP)),
((SELECT brand_id FROM brand WHERE name='Kong'), (SELECT category_id FROM category WHERE name='Toys'), (SELECT pet_type_id FROM pet_type WHERE name='Dog'), 'Classic Dog Toy', 'Durable rubber chew toy that can be stuffed with treats.', DATEADD('DAY', -6, CURRENT_TIMESTAMP)),
((SELECT brand_id FROM brand WHERE name='Tetra'), (SELECT category_id FROM category WHERE name='Food'), (SELECT pet_type_id FROM pet_type WHERE name='Fish'), 'TetraMin Tropical Flakes', 'Complete staple food for all tropical fish.', DATEADD('DAY', -5, CURRENT_TIMESTAMP)),
((SELECT brand_id FROM brand WHERE name='Oxbow'), (SELECT category_id FROM category WHERE name='Food'), (SELECT pet_type_id FROM pet_type WHERE name='Small Animal'), 'Timothy Hay', 'High-fibre hay for rabbits, guinea pigs and chinchillas.', DATEADD('DAY', -4, CURRENT_TIMESTAMP)),
((SELECT brand_id FROM brand WHERE name='Kaytee'), (SELECT category_id FROM category WHERE name='Food'), (SELECT pet_type_id FROM pet_type WHERE name='Bird'), 'Forti-Diet Pro Health Parrot Food', 'Seed, fruit and vegetable blend with added vitamins.', DATEADD('DAY', -3, CURRENT_TIMESTAMP)),
((SELECT brand_id FROM brand WHERE name='Greenies'), (SELECT category_id FROM category WHERE name='Treats'), (SELECT pet_type_id FROM pet_type WHERE name='Dog'), 'Dental Treats for Dogs', 'Chewy treats that help reduce tartar build-up.', DATEADD('DAY', -2, CURRENT_TIMESTAMP)),
((SELECT brand_id FROM brand WHERE name='Greenies'), (SELECT category_id FROM category WHERE name='Treats'), (SELECT pet_type_id FROM pet_type WHERE name='Cat'), 'Feline Dental Treats', 'Crunchy treats that clean teeth and freshen breath.', DATEADD('DAY', -1, CURRENT_TIMESTAMP)),
((SELECT brand_id FROM brand WHERE name='Fluval'), (SELECT category_id FROM category WHERE name='Aquarium Supplies'), (SELECT pet_type_id FROM pet_type WHERE name='Fish'), 'Aqua Clear Power Filter', 'Three-stage filtration for freshwater and saltwater aquariums.', CURRENT_TIMESTAMP);

INSERT INTO item_variant (item_id, sku, size, flavour, barcode, unit_price, created_at) VALUES
((SELECT item_id FROM item WHERE name='Original Dry Dog Food'), 'ORJ-DOG-2KG', '2 kg', 'Chicken & Turkey', '064992100011', 34.99, DATEADD('DAY', -9, CURRENT_TIMESTAMP)),
((SELECT item_id FROM item WHERE name='Original Dry Dog Food'), 'ORJ-DOG-114KG', '11.4 kg', 'Chicken & Turkey', '064992100028', 119.99, DATEADD('DAY', -9, CURRENT_TIMESTAMP)),
((SELECT item_id FROM item WHERE name='Indoor Adult Cat Food'), 'RC-CAT-15KG', '1.5 kg', 'Chicken', '030111446022', 24.99, DATEADD('DAY', -8, CURRENT_TIMESTAMP)),
((SELECT item_id FROM item WHERE name='Indoor Adult Cat Food'), 'RC-CAT-6KG', '6 kg', 'Chicken', '030111446039', 64.99, DATEADD('DAY', -8, CURRENT_TIMESTAMP)),
((SELECT item_id FROM item WHERE name='Puppy Healthy Development'), 'HSD-PUP-7KG', '7 kg', 'Chicken & Brown Rice', '052742909901', 72.50, DATEADD('DAY', -7, CURRENT_TIMESTAMP)),
((SELECT item_id FROM item WHERE name='Classic Dog Toy'), 'KONG-CL-M', 'Medium', NULL, '035585113029', 14.99, DATEADD('DAY', -6, CURRENT_TIMESTAMP)),
((SELECT item_id FROM item WHERE name='Classic Dog Toy'), 'KONG-CL-L', 'Large', NULL, '035585113036', 19.99, DATEADD('DAY', -6, CURRENT_TIMESTAMP)),
((SELECT item_id FROM item WHERE name='TetraMin Tropical Flakes'), 'TET-FLK-62G', '62 g', NULL, '046798161101', 8.99, DATEADD('DAY', -5, CURRENT_TIMESTAMP)),
((SELECT item_id FROM item WHERE name='TetraMin Tropical Flakes'), 'TET-FLK-200G', '200 g', NULL, '046798161118', 19.99, DATEADD('DAY', -5, CURRENT_TIMESTAMP)),
((SELECT item_id FROM item WHERE name='Timothy Hay'), 'OXB-HAY-1KG', '1 kg', '2nd Cutting', '744845101012', 16.99, DATEADD('DAY', -4, CURRENT_TIMESTAMP)),
((SELECT item_id FROM item WHERE name='Forti-Diet Pro Health Parrot Food'), 'KAY-PAR-25KG', '2.5 kg', 'Fruit & Veg', '046731273105', 21.99, DATEADD('DAY', -3, CURRENT_TIMESTAMP)),
((SELECT item_id FROM item WHERE name='Dental Treats for Dogs'), 'GRN-DOG-340G', '340 g', 'Original', '642863100012', 29.99, DATEADD('DAY', -2, CURRENT_TIMESTAMP)),
((SELECT item_id FROM item WHERE name='Feline Dental Treats'), 'GRN-CAT-130G', '130 g', 'Chicken', '642863200019', 7.99, DATEADD('DAY', -1, CURRENT_TIMESTAMP)),
((SELECT item_id FROM item WHERE name='Aqua Clear Power Filter'), 'FLV-AC30', '20-30 gal', NULL, '015561101304', 39.99, CURRENT_TIMESTAMP);

INSERT INTO distribution_centre (name, address, city, province) VALUES
('NorthPaw Toronto DC', '120 Industrial Pkwy', 'Mississauga', 'ON'),
('NorthPaw Calgary DC', '4500 54 Ave SE', 'Calgary', 'AB'),
('NorthPaw Montreal DC', '9800 Boul. Henri-Bourassa', 'Montreal', 'QC');

INSERT INTO customer (assigned_dc_id, store_name, contact_email, phone, address, city, province) VALUES
((SELECT dc_id FROM distribution_centre WHERE name='NorthPaw Toronto DC'), 'Happy Tails Pet Shop', 'orders@happytails.example', '416-555-0142', '22 Yonge St', 'Toronto', 'ON'),
((SELECT dc_id FROM distribution_centre WHERE name='NorthPaw Calgary DC'), 'Prairie Paws', 'hello@prairiepaws.example', '403-555-0188', '810 17 Ave SW', 'Calgary', 'AB'),
((SELECT dc_id FROM distribution_centre WHERE name='NorthPaw Montreal DC'), 'Le Chien Heureux', 'info@chienheureux.example', '514-555-0107', '55 Rue St-Denis', 'Montreal', 'QC');

INSERT INTO inventory (variant_id, dc_id, quantity_on_hand) VALUES
((SELECT variant_id FROM item_variant WHERE sku='ORJ-DOG-2KG'), (SELECT dc_id FROM distribution_centre WHERE name='NorthPaw Toronto DC'), 240),
((SELECT variant_id FROM item_variant WHERE sku='ORJ-DOG-2KG'), (SELECT dc_id FROM distribution_centre WHERE name='NorthPaw Calgary DC'), 120),
((SELECT variant_id FROM item_variant WHERE sku='ORJ-DOG-114KG'), (SELECT dc_id FROM distribution_centre WHERE name='NorthPaw Toronto DC'), 80),
((SELECT variant_id FROM item_variant WHERE sku='RC-CAT-15KG'), (SELECT dc_id FROM distribution_centre WHERE name='NorthPaw Montreal DC'), 150),
((SELECT variant_id FROM item_variant WHERE sku='RC-CAT-6KG'), (SELECT dc_id FROM distribution_centre WHERE name='NorthPaw Montreal DC'), 85),
((SELECT variant_id FROM item_variant WHERE sku='HSD-PUP-7KG'), (SELECT dc_id FROM distribution_centre WHERE name='NorthPaw Toronto DC'), 60),
((SELECT variant_id FROM item_variant WHERE sku='KONG-CL-M'), (SELECT dc_id FROM distribution_centre WHERE name='NorthPaw Toronto DC'), 300),
((SELECT variant_id FROM item_variant WHERE sku='KONG-CL-L'), (SELECT dc_id FROM distribution_centre WHERE name='NorthPaw Calgary DC'), 220),
((SELECT variant_id FROM item_variant WHERE sku='TET-FLK-62G'), (SELECT dc_id FROM distribution_centre WHERE name='NorthPaw Calgary DC'), 410),
((SELECT variant_id FROM item_variant WHERE sku='TET-FLK-200G'), (SELECT dc_id FROM distribution_centre WHERE name='NorthPaw Calgary DC'), 120),
((SELECT variant_id FROM item_variant WHERE sku='OXB-HAY-1KG'), (SELECT dc_id FROM distribution_centre WHERE name='NorthPaw Toronto DC'), 130),
((SELECT variant_id FROM item_variant WHERE sku='KAY-PAR-25KG'), (SELECT dc_id FROM distribution_centre WHERE name='NorthPaw Montreal DC'), 95),
((SELECT variant_id FROM item_variant WHERE sku='GRN-DOG-340G'), (SELECT dc_id FROM distribution_centre WHERE name='NorthPaw Toronto DC'), 175),
((SELECT variant_id FROM item_variant WHERE sku='GRN-CAT-130G'), (SELECT dc_id FROM distribution_centre WHERE name='NorthPaw Montreal DC'), 260),
((SELECT variant_id FROM item_variant WHERE sku='FLV-AC30'), (SELECT dc_id FROM distribution_centre WHERE name='NorthPaw Calgary DC'), 45);

INSERT INTO inventory (variant_id, dc_id, quantity_on_hand) VALUES
((SELECT variant_id FROM item_variant WHERE sku='RC-CAT-15KG'), (SELECT dc_id FROM distribution_centre WHERE name='NorthPaw Toronto DC'), 90),
((SELECT variant_id FROM item_variant WHERE sku='GRN-CAT-130G'), (SELECT dc_id FROM distribution_centre WHERE name='NorthPaw Toronto DC'), 110),
((SELECT variant_id FROM item_variant WHERE sku='TET-FLK-62G'), (SELECT dc_id FROM distribution_centre WHERE name='NorthPaw Toronto DC'), 200),
((SELECT variant_id FROM item_variant WHERE sku='ORJ-DOG-2KG'), (SELECT dc_id FROM distribution_centre WHERE name='NorthPaw Montreal DC'), 70),
((SELECT variant_id FROM item_variant WHERE sku='KONG-CL-M'), (SELECT dc_id FROM distribution_centre WHERE name='NorthPaw Montreal DC'), 140),
((SELECT variant_id FROM item_variant WHERE sku='GRN-DOG-340G'), (SELECT dc_id FROM distribution_centre WHERE name='NorthPaw Montreal DC'), 95),
((SELECT variant_id FROM item_variant WHERE sku='TET-FLK-62G'), (SELECT dc_id FROM distribution_centre WHERE name='NorthPaw Montreal DC'), 180),
((SELECT variant_id FROM item_variant WHERE sku='RC-CAT-6KG'), (SELECT dc_id FROM distribution_centre WHERE name='NorthPaw Calgary DC'), 40),
((SELECT variant_id FROM item_variant WHERE sku='GRN-DOG-340G'), (SELECT dc_id FROM distribution_centre WHERE name='NorthPaw Calgary DC'), 100),
((SELECT variant_id FROM item_variant WHERE sku='OXB-HAY-1KG'), (SELECT dc_id FROM distribution_centre WHERE name='NorthPaw Calgary DC'), 60);
