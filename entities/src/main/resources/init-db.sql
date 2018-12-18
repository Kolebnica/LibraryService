INSERT INTO artists(id, creativename, fullname) VALUES (1, 'Robbie Rotten', 'Stefán Karl Stefánsson');
INSERT INTO artists(id, creativename) VALUES (2, 'Game over');
INSERT INTO artists(id, creativename, fullname) VALUES (3, 'Ceca', 'Svetlana Ražnatović');

INSERT INTO albums(id, release_year, title, artist_id) VALUES (1, 2014, 'We are number one', 1);
INSERT INTO albums(id, release_year, title, artist_id) VALUES (2, 2002, 'Igra za dva', 2);
INSERT INTO albums(id, release_year, title, artist_id) VALUES (3, 2003, 'Višja sila', 2);
INSERT INTO albums(id, release_year, title, artist_id) VALUES (4, 2006, 'Idealno loša', 3);
INSERT INTO albums(id, release_year, title, artist_id) VALUES (5, 2011, 'Ljubav živi', 3);
INSERT INTO albums(id, release_year, title, artist_id) VALUES (6, 2013, 'Poziv', 3);
INSERT INTO albums(id, release_year, title, artist_id) VALUES (7, 2016, 'Autogram', 3);

ALTER SEQUENCE artists_id_seq RESTART WITH 4;
ALTER SEQUENCE albums_id_seq RESTART WITH 8;