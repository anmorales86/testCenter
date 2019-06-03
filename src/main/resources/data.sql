/**
 * Created by Anderson Q. on 3/06/2019.
 */

DROP TABLE IF EXISTS employee;

CREATE TABLE employee (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  nameComplete VARCHAR(250) NOT NULL,
  status VARCHAR(250) NOT NULL,
  position VARCHAR(250)
);

INSERT INTO employee (nameComplete, status, position) values ('ANDERSON QUINTERO','VACANT','DIRECTOR');
INSERT INTO employee (nameComplete, status, position) values ('PEDRO SUAREZ','VACANT','SUPERVISOR');
INSERT INTO employee (nameComplete, status, position) values ('CRISTIANO RONALDO','VACANT','DIRECTOR');
INSERT INTO employee (nameComplete, status, position) values ('SERGIO RAMOS','VACANT','SUPERVISOR');
INSERT INTO employee (nameComplete, status, position) values ('JAMES RODRIGUEZ','VACANT','OPERATOR');
INSERT INTO employee (nameComplete, status, position) values ('PABLO TORRES','VACANT','OPERATOR');
INSERT INTO employee (nameComplete, status, position) values ('ANDREA LOPEZ','VACANT','OPERATOR');
INSERT INTO employee (nameComplete, status, position) values ('SHIRLEY RAMIREZ','VACANT','OPERATOR');
INSERT INTO employee (nameComplete, status, position) values ('OSCAR JARAMILLO','VACANT','SUPERVISOR');
INSERT INTO employee (nameComplete, status, position) values ('FEDERICO CONTRERAS','VACANT','OPERATOR');