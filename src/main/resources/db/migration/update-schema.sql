ALTER TABLE file_detail
DROP
CONSTRAINT fkokr8sppg5h6d1hwfxiyoeaph;

ALTER TABLE file_detail
DROP
COLUMN config_mapping_id;

ALTER TABLE config_mapping
DROP
COLUMN status;

ALTER TABLE config_mapping
    ADD status VARCHAR(255);
ALTER TABLE file_detail
    DROP CONSTRAINT fkokr8sppg5h6d1hwfxiyoeaph;

ALTER TABLE file_detail
    DROP COLUMN config_mapping_id;

ALTER TABLE config_mapping
    DROP COLUMN status;

ALTER TABLE config_mapping
    ADD status VARCHAR(255);