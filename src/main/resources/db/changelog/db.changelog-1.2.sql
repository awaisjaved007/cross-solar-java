--liquibase formatted sql

--changeset javeda:001 dbms:mysql
ALTER TABLE panel ADD CONSTRAINT PANAL_SERIAL_SIZE CHECK (LENGTHB(serial) = 16);