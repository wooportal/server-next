INSERT INTO media (id, mime_type, name) VALUES
('4a3717af-94b2-4a5d-b328-2d2754ea3cb5', 'image/jpg', 'profile picture'),
('34af774d-2774-427b-a005-a5f406b0f72b', 'image/jpg', 'profile picture'),
('89c7c32f-f53d-4730-bb48-ac5b02b2abd5', 'image/jpg', 'profile picture'),
('ab6a0bdc-c5bf-442a-a7ca-5f4b768d6513', 'image/jpg', 'profile picture'),
('ced2157e-5e4f-44bb-b9a7-a17cd3cdad98', 'image/jpg', 'profile picture'),
('9eaee436-86d7-467e-beb9-be884a9c291c', 'image/jpg', 'title image'),
('670d0b9d-ecfb-4dd2-8f07-ead633c7ef5b', 'image/jpg', 'title image'),
('6359d990-bcfc-400a-b685-fe3d7ff9d1c0', 'image/jpg', 'title image'),
('e302e041-fb51-47b6-99bc-47fea56de39e', 'image/jpg', 'title image'),
('af5c4d43-54f1-40d1-ba26-5895b91bb287', 'image/jpg', 'title image'),
('c96198d3-2bd6-48bf-9af4-d79fbf66a1a8', 'image/jpg', 'title image'),
('76460bc7-ed5c-4f70-a377-46e2cf49e86f', 'image/jpg', 'title image'),
('ceb59217-29f6-4a58-a8f1-776b698a9c5a', 'video/mp4', 'GetRolled');

INSERT INTO groups(id, name) VALUES
('f5983943-b1a3-4265-bf88-208ecbbd09a3', 'Metall'),
('1f8c55b2-4210-4eea-bc14-6a6d55cc538e', 'Holz');

INSERT INTO courses(id, name, group_id) VALUES
('2163ee48-8605-46eb-bbd0-c62497007b18', 'Metall 1', 'f5983943-b1a3-4265-bf88-208ecbbd09a3'),
('ab36cc61-a884-4da8-be52-db8f19abcbd1', 'Metall 2', 'f5983943-b1a3-4265-bf88-208ecbbd09a3'),
('dce3746a-6109-45fd-8bfa-09bb99378d37', 'Holz1', '1f8c55b2-4210-4eea-bc14-6a6d55cc538e');

INSERT INTO users (id, fullname, email, profile_picture_id, password, phone, evaluateCourse) VALUES
('36808f63-4b6b-40e7-b2ee-a91f657e4e58', 'Student', 'student@example.com', '34af774d-2774-427b-a005-a5f406b0f72b', '$2a$10$FpJxzuxpXKUIDQMuLNARGOb5Ocz2YoiW4WwgWOoM4uaB7P6iNM.SW', '22222222', 'false'),
('e14e5d92-0b4a-47b3-991d-ac18dd6923b3', 'Student2', 'student2@example.com', '34af774d-2774-427b-a005-a5f406b0f72b', '$2a$10$FpJxzuxpXKUIDQMuLNARGOb5Ocz2YoiW4WwgWOoM4uaB7P6iNM.SW', '33333333', 'false'),
('528250c0-e0e3-4166-aa71-0f150eb2453f', 'Superviser', 'superviser@example.com', '89c7c32f-f53d-4730-bb48-ac5b02b2abd5', '$2a$10$FpJxzuxpXKUIDQMuLNARGOb5Ocz2YoiW4WwgWOoM4uaB7P6iNM.SW', '444444', 'false'),
('dd939b40-be89-41ca-b031-fef2dc82a536', 'Not Veried User', 'notverified@example.com', 'ab6a0bdc-c5bf-442a-a7ca-5f4b768d6513', '$2a$10$FpJxzuxpXKUIDQMuLNARGOb5Ocz2YoiW4WwgWOoM4uaB7P6iNM.SW', '55555555', 'false'),
('71a857db-dd35-4d08-bb0f-20115a584ee5', 'Not Approved User', 'notapproved@example.com', 'ced2157e-5e4f-44bb-b9a7-a17cd3cdad98', '$2a$10$FpJxzuxpXKUIDQMuLNARGOb5Ocz2YoiW4WwgWOoM4uaB7P6iNM.SW', '66666666', 'false');

INSERT INTO user_roles (id, user_id, role_id) VALUES
('cfae19fc-ee80-4462-ac53-72631e5a52c1', '36808f63-4b6b-40e7-b2ee-a91f657e4e58', 'bde2722b-c45e-4256-b81a-721982d75f5b'),
('c87fae61-beab-4785-b728-6483e1ce123c', '36808f63-4b6b-40e7-b2ee-a91f657e4e58', 'cf5090d0-dfeb-4bf8-a242-33a2522fe765'),
('7a8d49f7-d1db-40b2-a653-3997a0439218', '528250c0-e0e3-4166-aa71-0f150eb2453f', 'bde2722b-c45e-4256-b81a-721982d75f5b'),
('4f649fac-e63d-48ba-a7c8-0de62c0b0fac', '528250c0-e0e3-4166-aa71-0f150eb2453f', 'f5bfa44f-2d19-4ebd-b40b-9112c6b9a996'),
('a5a2a082-1472-4b00-b19d-45b0612295f6', '528250c0-e0e3-4166-aa71-0f150eb2453f', 'cf5090d0-dfeb-4bf8-a242-33a2522fe765'),
('64d47bfa-f34d-49bb-8bae-9e3ff7b2e1d4', '71a857db-dd35-4d08-bb0f-20115a584ee5', 'cf5090d0-dfeb-4bf8-a242-33a2522fe765');

INSERT INTO organizers (id, name, mail, phone, website) VALUES
('2295ed87-3f46-428a-a179-e7b7093d2f05', 'IHK-Ulm', 'Musterfrau.Mandy@example.com', '0176-3138557', 'https://www.ihk-ausbildungsmesse.de/'),
('ead07153-6e4b-45ae-abd4-562fc126e92f', 'Bowling-Center-Ulm', 'Mustermann.Max@example.com', '0173-7717645', 'https://wp.neu-ulm-inside.de/new-bowling-center/'),
('d2f041f1-4817-47be-a49a-b6e494a4a8a6', 'Jugendwerkstatt alpha e.V.', 'Susanne@example.com', '0176-5646541', 'https://www.alphaev.de');

INSERT INTO event_categories (id, name, icon) VALUES
('555f2324-87bf-4510-ac08-893076444dbb', 'Education and Training', 'Education and Training'),
('a464edde-2e2f-455e-a5ca-84fd28f8ad4a', 'Sports', 'Sports'),
('320e2686-a4bc-4990-9b95-8d6f3ddf56ba', 'Information', 'Information'),
('00db3dbf-b23d-4fb1-a1ff-5e7b321d8301', 'Social', 'Social'),
('c777f9a8-2061-4fc4-9cae-63130641e4d5', 'Other', 'Other');

INSERT INTO addresses (id, house_number, latitude, longitude, place, postal_code, street) VALUES
('ff656406-89af-4835-a9bf-571fd978f78f', '27', '48.397340', '9.992980', 'Ulm', '89073', 'ihk-street'),
('b1b84b9d-a30c-4921-8d20-af21058bec79', '22', '48.397340', '9.992980', 'Ulm', '89073', 'müller-street'),
('4ff89e9a-2751-4a15-845e-8283194e81fa', '28', '48.397340', '9.992980', 'Ulm', '89073', 'bowling-street'),
('5dc8809e-8890-4262-b334-a0dff5b8ffd8', '33', '48.397340', '9.992980', 'Ulm', '89073', 'claus-street'),
('4b2589d5-fc11-4cfd-be8a-c2471277b73d', '100', '48.333333', '9.888888', 'Wuppertal', '42285', 'Siegesstrasse'),
('4966fe7a-6770-4b32-aa73-046c64fc6b51', '10', '48.444444', '9.777777', 'Ulm', '89077', 'Blaubeurerstrasse'),
('0fbf3399-9265-40b4-8050-1cba034c2fe8', '21', '42.999999', '8.442342', 'Stuttgart', '70565', 'Ruppmannstrasse');

INSERT INTO events (id, name, description, category_id, organizer_id, title_image_id, address_id) VALUES
('440a6eec-be4d-47e8-b38d-3a819bc4624e', 'Vocational-Training-Fair','First touch with companys','555f2324-87bf-4510-ac08-893076444dbb',
'2295ed87-3f46-428a-a179-e7b7093d2f05','9eaee436-86d7-467e-beb9-be884a9c291c','ff656406-89af-4835-a9bf-571fd978f78f'),
('3e9b15fb-02ce-4d09-bfcd-40751eaee1b7', 'Team-Bowling', 'Team-Bowling in Ulm','a464edde-2e2f-455e-a5ca-84fd28f8ad4a',
'ead07153-6e4b-45ae-abd4-562fc126e92f','c96198d3-2bd6-48bf-9af4-d79fbf66a1a8', '4ff89e9a-2751-4a15-845e-8283194e81fa'),
('4d2a2a1f-1c36-471b-8483-ae18338a5fd6', 'Opening Event', 'Information and guidance', '320e2686-a4bc-4990-9b95-8d6f3ddf56ba',
'd2f041f1-4817-47be-a49a-b6e494a4a8a6', '670d0b9d-ecfb-4dd2-8f07-ead633c7ef5b', '4b2589d5-fc11-4cfd-be8a-c2471277b73d'),
('8b66b8d7-dd54-460b-b1f1-4204cc660872', 'Game-Night', 'Monopoly, Chess, Uno and more', '00db3dbf-b23d-4fb1-a1ff-5e7b321d8301',
'd2f041f1-4817-47be-a49a-b6e494a4a8a6', 'af5c4d43-54f1-40d1-ba26-5895b91bb287', '4b2589d5-fc11-4cfd-be8a-c2471277b73d'),
('fa30c3b2-a14d-4627-a11b-d2704ee496eb', 'Christmas-Party', 'Celebration of the year 2022', 'c777f9a8-2061-4fc4-9cae-63130641e4d5',
'd2f041f1-4817-47be-a49a-b6e494a4a8a6', '6359d990-bcfc-400a-b685-fe3d7ff9d1c0', '4b2589d5-fc11-4cfd-be8a-c2471277b73d');

INSERT INTO schedules (id, start_date, end_date, event_id) VALUES
('2f2f8064-43e3-4e11-a157-d88d4f357e4c', '2022-10-19 10:00:00+02', '2022-10-19 16:00:00+02', '440a6eec-be4d-47e8-b38d-3a819bc4624e'),
('4d43606f-1338-4e61-baec-e080d9e55400', '2022-05-05 18:00:00+02', '2022-05-05 20:00:00+02', '3e9b15fb-02ce-4d09-bfcd-40751eaee1b7'),
('58fa6c11-de2d-4c54-b85c-dab0d546473a', '2022-05-12 18:00:00+02', '2022-05-12 20:00:00+02', '3e9b15fb-02ce-4d09-bfcd-40751eaee1b7'),
('fc7965c6-ce88-4ecc-a455-045237370b9a', '2022-05-19 18:00:00+02', '2022-05-19 20:00:00+02', '3e9b15fb-02ce-4d09-bfcd-40751eaee1b7'),
('fdfbfd8e-a940-433e-bdd9-fe91194131a7', '2022-09-15 10:00:00+02', '2022-09-15 14:00:00+02', '4d2a2a1f-1c36-471b-8483-ae18338a5fd6'),
('6cd32c9d-ac17-4e87-8a13-f8634bf8c069', '2022-07-10 19:00:00+02', '2022-07-10 22:00:00+02', '8b66b8d7-dd54-460b-b1f1-4204cc660872'),
('f4db1b24-8f48-464f-9565-8d2057bf5bc2', '2022-05-23 19:00:00+02', '2022-05-23 22:00:00+02', 'fa30c3b2-a14d-4627-a11b-d2704ee496eb');

INSERT INTO job_types (id, name, color) VALUES
('430d33c4-f0f2-42b1-927f-5270d91c7f32', 'metal trade', '#0000FF'),
('1a773b24-de53-4ccc-9daa-e9261590f88b', 'carpentry', '#00FF00'),
('b840ae1f-f9b8-4223-b50a-1643fb82a674', 'craft', '#FF0000'),
('be24e3e7-b08b-4792-be4e-df34cf8e9851', 'property', '#FFFF00');

INSERT INTO companies (id, name, mail, phone, website, address_id) VALUES
('89aaa55a-168d-4165-85fc-abde073d6732', 'Carpentry Müller GmbH', 'müller@example.com', '11830', 'müllergmbh.de', 'b1b84b9d-a30c-4921-8d20-af21058bec79'),
('f476ed5a-c5c6-429b-bcf0-85fcf8363be3', 'Metal Claus GmbH', 'claus@example.com', '112', 'clausmetal.de', '5dc8809e-8890-4262-b334-a0dff5b8ffd8'),
('f36d8f40-e387-4282-8dca-30e29d644600', 'Ikea Deutschland', 'Fynn@example.com', '0877-252525', 'ikea.com', '4966fe7a-6770-4b32-aa73-046c64fc6b51'),
('bd2c9144-1517-42e5-a35c-503793ccb51d', 'Regierungspräsidium Tübingen', 'mareike@example.com', '0588-545', 'rp.baden-wuerttemberg.de', '0fbf3399-9265-40b4-8050-1cba034c2fe8');

INSERT INTO job_ads(id, title, start_date, due_date, company_id, type_id) VALUES
('39cdb5c0-aa0b-4665-a732-e51cb0986e6d', 'metal worker', '2022-10-1 10:00:00+02', '2022-09-15 10:00:00+02', '89aaa55a-168d-4165-85fc-abde073d6732', '430d33c4-f0f2-42b1-927f-5270d91c7f32'),
('e7c48d91-851c-4542-ac7b-e8efb66d0f8e', 'carpetry worker', '2022-04-01 10:00:00+02', '2022-01-31 00:00:00+02', 'f476ed5a-c5c6-429b-bcf0-85fcf8363be3', '1a773b24-de53-4ccc-9daa-e9261590f88b'),
('10bee224-3c01-40cf-8598-1cce46269206', 'craftsman', '2022-06-01 10:00:00+02', '2022-05-20 09:08:00+02', '89aaa55a-168d-4165-85fc-abde073d6732', 'b840ae1f-f9b8-4223-b50a-1643fb82a674'),
('9ff39911-533a-4420-9149-f261de7d569c', 'interior designer', '2022-09-01 10:00:00+02', '2022-05-31 10:00:00+02','f36d8f40-e387-4282-8dca-30e29d644600', 'be24e3e7-b08b-4792-be4e-df34cf8e9851'),
('0ae19f9c-94d6-4fdc-92f4-81595f3d6916', 'housekeeper', '2022-11-01 10:00:00+02', '2022-09-30 10:00:00+02', 'bd2c9144-1517-42e5-a35c-503793ccb51d', 'be24e3e7-b08b-4792-be4e-df34cf8e9851');

INSERT INTO assignment_states (id, name) VALUES
('aad3dd2b-f29d-4a87-8056-f72b0aae3dc0', 'ASSIGNED'),
('577d7e21-f994-433f-8f7b-c7ac3887d692', 'DONE');

INSERT INTO questionnaires(id, name) VALUES
('0e0fc8f9-7271-48a1-95dd-74d1fa8bab3f', 'Metal-Group-Evaluation'),
('0e1a2d66-5629-4988-b45f-d207a3b9d4f4', 'Carpentry-Group-Evaluation');

INSERT INTO questions(id, item, questionnaire_id) VALUES
('7c6d47ab-f4fe-46c6-869c-0538c1d2c2c4', 'question1', '0e0fc8f9-7271-48a1-95dd-74d1fa8bab3f'),
('4100e7c3-e648-4b68-b937-89d9fec60880', 'question2', '0e0fc8f9-7271-48a1-95dd-74d1fa8bab3f'),
('90f4880d-dae8-4290-922c-216c48ce249d', 'question1', '0e1a2d66-5629-4988-b45f-d207a3b9d4f4'),
('90a8772d-f551-4111-9601-069e453ef770', 'question2', '0e1a2d66-5629-4988-b45f-d207a3b9d4f4');

INSERT INTO assignments(id, assignment_state_id, questionnaire_id, user_id) VALUES
('5eb2d309-b5f7-45d5-9ed9-aa731cc6c4fd', 'aad3dd2b-f29d-4a87-8056-f72b0aae3dc0', '0e0fc8f9-7271-48a1-95dd-74d1fa8bab3f', '36808f63-4b6b-40e7-b2ee-a91f657e4e58'),
('89cece0b-8068-49d3-ad74-e882386c5c8b', '577d7e21-f994-433f-8f7b-c7ac3887d692', '0e1a2d66-5629-4988-b45f-d207a3b9d4f4', '36808f63-4b6b-40e7-b2ee-a91f657e4e58');

INSERT INTO answers(id, rating, question_id, assignment_id) VALUES
('892d91b3-e73b-4bd1-9874-99f810d30d56', '5', '7c6d47ab-f4fe-46c6-869c-0538c1d2c2c4', '5eb2d309-b5f7-45d5-9ed9-aa731cc6c4fd'),
('7c8e86c7-4049-42ad-89b6-6c3da2bd8a38', '3', '4100e7c3-e648-4b68-b937-89d9fec60880', '5eb2d309-b5f7-45d5-9ed9-aa731cc6c4fd'),
('9963bfd5-877c-4e50-8cca-3d3f3a094857', '2', '90f4880d-dae8-4290-922c-216c48ce249d', '89cece0b-8068-49d3-ad74-e882386c5c8b'),
('ad0f00a3-ef45-4b14-9ecf-5b5d78930a81', '2', '90a8772d-f551-4111-9601-069e453ef770', '89cece0b-8068-49d3-ad74-e882386c5c8b');

INSERT INTO link_categories(id, name) VALUES
('ff6340c7-e973-45c2-b1a0-1ff9cd70a861', 'Was will ich? Was kann ich?'),
('a5cb2e7f-baa2-4d28-a4a8-9106b2024049', 'Welche Ausbildungen gibt es?');

INSERT INTO links(id, url, title, category_id) VALUES
('077d22e5-1e37-463b-bd91-2bf84873ad46', 'https://planet-beruf.de/schuelerinnen/video/video-how-to-bewerbungsvideos-drehen', 'How to .. Bewerbungsvideo drehen', 'ff6340c7-e973-45c2-b1a0-1ff9cd70a861'),
('61a37d3e-e72a-4504-8766-2eb2176c17ed', 'https://planet-beruf.de/schuelerinnen/welche-ausbildungen-gibt-es/welche-berufe-gibt-es', 'Welche Berufe gibt es?', 'a5cb2e7f-baa2-4d28-a4a8-9106b2024049');

INSERT INTO template_types(id, name) VALUES
('e99c1aec-a2ce-4c10-8532-43d518551330', 'Deckblatt'),
('5852aa11-4e5c-4d8d-bc41-9fa44cb6ca1a', 'Anschreiben'),
('31d957ee-7b0b-4118-b470-ed08719df47f', 'Lebenslauf');

INSERT INTO templates(id, name, content, template_type_id) VALUES
('2f51d668-eab1-478b-8b0d-b2b16d38d64a', 'Vorlage1', 'document', '5852aa11-4e5c-4d8d-bc41-9fa44cb6ca1a'),
('4f27f7cc-f849-4438-83fe-1b8fffccb2c5', 'Vorlage2', 'document', '5852aa11-4e5c-4d8d-bc41-9fa44cb6ca1a');

INSERT INTO user_templates(id, name, content, template_type_id, user_id) VALUES
('0c132c71-d17c-4d1e-b473-f6f0fa085c6a', 'Firma1', 'document', '5852aa11-4e5c-4d8d-bc41-9fa44cb6ca1a', '36808f63-4b6b-40e7-b2ee-a91f657e4e58'),
('d5ef1f29-56f8-4d89-bc77-e963c6a7bb3e', 'Firma2', 'document', '5852aa11-4e5c-4d8d-bc41-9fa44cb6ca1a', '36808f63-4b6b-40e7-b2ee-a91f657e4e58');

INSERT INTO feedbacks(id, rating, course_id, user_id) VALUES
('566458a2-fe16-4850-8e68-44c1cd5fd8cd', '3', '2163ee48-8605-46eb-bbd0-c62497007b18', '36808f63-4b6b-40e7-b2ee-a91f657e4e58'),
('50bb5dd5-e180-4cf4-9f30-f16bea9acfa8', '5', 'ab36cc61-a884-4da8-be52-db8f19abcbd1', '36808f63-4b6b-40e7-b2ee-a91f657e4e58');

INSERT INTO subscription_types(id, name, description) VALUES
('a3d3c3ce-6908-42e6-97f7-ddaddae8deb3', 'Events', 'Bowling'),
('e7e34f9d-31fb-472e-aba7-15b14dcef865', 'JobAds', 'metal worker');

INSERT INTO subscriptions(id, device_token, subscription_type_id, user_id) VALUES
('1038e9e5-a82f-4d00-83fe-6ea0910d2c8d', 'secretkey', 'a3d3c3ce-6908-42e6-97f7-ddaddae8deb3', '36808f63-4b6b-40e7-b2ee-a91f657e4e58'),
('92557db8-b5fd-4bee-920c-ba2fe348713e', 'secretkey2', 'e7e34f9d-31fb-472e-aba7-15b14dcef865', '36808f63-4b6b-40e7-b2ee-a91f657e4e58');

INSERT INTO user_events(id, user_id, event_id) VALUES
('18c923a3-8160-4beb-bcc6-738b1fe5ad00', '36808f63-4b6b-40e7-b2ee-a91f657e4e58', '440a6eec-be4d-47e8-b38d-3a819bc4624e'),
('ef115c0f-acc5-4700-b1e8-bb3ccfe2b194', '36808f63-4b6b-40e7-b2ee-a91f657e4e58', '3e9b15fb-02ce-4d09-bfcd-40751eaee1b7');

INSERT INTO user_job_ads(id, user_id, job_ad_id) VALUES
('c1f7570c-96ff-4e83-95e2-b86a7691f3a2', '36808f63-4b6b-40e7-b2ee-a91f657e4e58', '39cdb5c0-aa0b-4665-a732-e51cb0986e6d'),
('3afe8863-71e7-4488-8925-7d453eb2dc2a', '36808f63-4b6b-40e7-b2ee-a91f657e4e58', 'e7c48d91-851c-4542-ac7b-e8efb66d0f8e');

INSERT INTO pages(id, slug, content, title_image_id, video_id) VALUES
('8e65e9a7-f980-4be2-b412-9ea0c085df04', 'https://jugendwerkstatt/titleimage/slug', 'image', '76460bc7-ed5c-4f70-a377-46e2cf49e86f', null),
('3c918357-244d-4a6c-8b87-223da7364cdf', 'https://jugendwerkstatt/openingvideo/slug', 'video', null, 'ceb59217-29f6-4a58-a8f1-776b698a9c5a');

INSERT INTO chats(id, name, admin) VALUES
('41bf7460-1a1a-41cb-8b2f-a89688671b33', 'Holz1Groupchat', false),
('b2cb2479-1c75-4bf6-9543-38c53a55a70f', 'Student', true);

INSERT INTO participants(id, chat_id, user_id) VALUES
('87c048f9-26fb-4b3f-9d37-f03556c08edd', '41bf7460-1a1a-41cb-8b2f-a89688671b33', '36808f63-4b6b-40e7-b2ee-a91f657e4e58'),
('75cd4f05-816a-45d0-b242-e12b00499e7f', 'b2cb2479-1c75-4bf6-9543-38c53a55a70f', '36808f63-4b6b-40e7-b2ee-a91f657e4e58'),
('824d97ce-a60c-477d-9dc8-d48e8a5ba2d3', '41bf7460-1a1a-41cb-8b2f-a89688671b33', 'e14e5d92-0b4a-47b3-991d-ac18dd6923b3'),
('36ea3a0f-31e7-4db0-b2f5-d9b42b5139c3', '41bf7460-1a1a-41cb-8b2f-a89688671b33', '528250c0-e0e3-4166-aa71-0f150eb2453f'),
('30932b23-9b0a-472b-b16a-a6ae1c80740d', 'b2cb2479-1c75-4bf6-9543-38c53a55a70f', '528250c0-e0e3-4166-aa71-0f150eb2453f');

INSERT INTO messages(id, content, chat_id, user_id, media_id, parent_id) VALUES
('456588b4-f512-4d78-953c-53dee35b18c0', 'Hello fellow woodworkers!', '41bf7460-1a1a-41cb-8b2f-a89688671b33', '528250c0-e0e3-4166-aa71-0f150eb2453f', null, null),
('125b98f8-0310-4455-ba31-7a43a6cdc7b0', 'Hello all!', '41bf7460-1a1a-41cb-8b2f-a89688671b33', '36808f63-4b6b-40e7-b2ee-a91f657e4e58', null, null),
('c25cca5d-3f2c-4297-bf41-50a9f98a59fa', 'Hello Supervisor, here is my form!', 'b2cb2479-1c75-4bf6-9543-38c53a55a70f', '36808f63-4b6b-40e7-b2ee-a91f657e4e58', 'ceb59217-29f6-4a58-a8f1-776b698a9c5a', null),
('569f2578-177e-4e08-a206-9f2626b8c258', 'This is not a form!', 'b2cb2479-1c75-4bf6-9543-38c53a55a70f', '528250c0-e0e3-4166-aa71-0f150eb2453f', null, 'c25cca5d-3f2c-4297-bf41-50a9f98a59fa');

INSERT INTO read_receipts(id, message_id, participant_id) VALUES
('2ce49f62-f74f-480a-b43e-f27232b8dc29', '456588b4-f512-4d78-953c-53dee35b18c0', '87c048f9-26fb-4b3f-9d37-f03556c08edd'),
('4c524fd6-f7ae-481c-89c8-2b16ddd1ca48', 'c25cca5d-3f2c-4297-bf41-50a9f98a59fa', '75cd4f05-816a-45d0-b242-e12b00499e7f'),
('af00cf23-a10e-45b0-8c5f-2ae481764d1d', 'c25cca5d-3f2c-4297-bf41-50a9f98a59fa', '30932b23-9b0a-472b-b16a-a6ae1c80740d');

INSERT INTO notifications(id, title, content, read, user_id) VALUES
('af3c1da6-585d-47fd-ab18-d0c987be8872', 'ThisIsTheTitle', 'ThisIsTheContent', true, '36808f63-4b6b-40e7-b2ee-a91f657e4e58'),
('794560a6-76da-41b3-a5f3-f868f764caad', 'SecondTitle', 'SecondContent', false, '36808f63-4b6b-40e7-b2ee-a91f657e4e58');
