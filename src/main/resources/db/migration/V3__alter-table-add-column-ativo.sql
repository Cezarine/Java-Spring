ALTER TABLE doctors ADD COLUMN active TINYINT(1) DEFAULT 1 NOT NULL AFTER city;
ALTER TABLE doctors CHANGE tell tell VARCHAR(20) NOT NULL AFTER especialidade;