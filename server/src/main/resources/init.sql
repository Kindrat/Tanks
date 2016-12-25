CREATE TABLE role (
  id        INTEGER,
  user_role VARCHAR(20) CHECK user_role IN ('NONE', 'USER', 'ADMIN'),
  PRIMARY KEY (id)
);

CREATE TABLE profile (
  id       BIGINT      NOT NULL AUTO_INCREMENT,
  name     TEXT,
  nickname TEXT,
  login    VARCHAR(50) NOT NULL UNIQUE,
  password TEXT        NOT NULL,
  role     INTEGER     NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (role) REFERENCES role (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE units (
  id        BIGINT NOT NULL AUTO_INCREMENT,
  player_id BIGINT NOT NULL
);

CREATE TABLE rooms (
  id BIGINT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (id)
);