DROP DATABASE IF EXISTS drugstoredb;
CREATE DATABASE IF NOT EXISTS drugstoredb;

CREATE TABLE IF NOT EXISTS `drugstoredb`.`patient` (
  `passport` VARCHAR(8) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `birthday` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`passport`));

CREATE TABLE IF NOT EXISTS `drugstoredb`.`doctor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `surname` VARCHAR(45) NOT NULL,
  `speciality` VARCHAR(45) NOT NULL,
  `experience` INT NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE IF NOT EXISTS `drugstoredb`.`recipe` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATE NOT NULL,
  `FK_patient_recipe` VARCHAR(8) NOT NULL,
  `FK_doctor_recipe` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `doctor_id_idx` (`FK_doctor_recipe` ASC),
  INDEX `patient_passport_idx` (FK_patient_recipe ASC),
  CONSTRAINT `patient_passport`
    FOREIGN KEY (`FK_patient_recipe`)
    REFERENCES `drugstoredb`.`patient` (`passport`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `doctor_id`
    FOREIGN KEY (`FK_doctor_recipe`)
    REFERENCES `drugstoredb`.`doctor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS `drugstoredb`.`drug` (
  `id` INT NOT NULL auto_increment,
  `name` VARCHAR(45) NOT NULL,
  `producer` VARCHAR(45) NOT NULL,
  `package_price` FLOAT NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE IF NOT EXISTS `drugstoredb`.`drugstore` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `address` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE IF NOT EXISTS `drugstoredb`.`recipe_drug` (
  `FK_recipe_drug` INT NOT NULL,
  `FK_drug` INT NOT NULL,
  `amount` INT NOT NULL,
  PRIMARY KEY (`FK_recipe_drug`, `FK_drug`),
  INDEX `drug_id_idx` (`FK_drug` ASC),
  CONSTRAINT `recipe_id`
    FOREIGN KEY (`FK_recipe_drug`)
    REFERENCES `drugstoredb`.`recipe` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `drug_recipe_drug`
    FOREIGN KEY (`FK_drug`)
    REFERENCES `drugstoredb`.`drug` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS `drugstoredb`.`drugstore_drug` (
  `FK_drugstore_id` INT NOT NULL,
  `FK_drug_drugstore_drug` INT NOT NULL,
  `date` DATE NOT NULL,
  `price` FLOAT NOT NULL,
  PRIMARY KEY (`FK_drugstore_id`, `FK_drug_drugstore_drug`),
  INDEX `drug_id_idx` (`FK_drug_drugstore_drug` ASC),
  CONSTRAINT `drugstore_id`
    FOREIGN KEY (FK_drugstore_id)
    REFERENCES `drugstoredb`.`drugstore` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `drug_drugstore_drug`
    FOREIGN KEY (`FK_drug_drugstore_drug`)
    REFERENCES `drugstoredb`.`drug` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS `drugstoredb`.`purchase` (
  `idpurchase` INT NOT NULL,
  `date` VARCHAR(45) NOT NULL,
  `payment_status` VARCHAR(25) NOT NULL,
  `FK_drug_store_purchase` INT NOT NULL,
  `FK_prescription_purchase` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idpurchase`),
  INDEX `FK_drug_store_purchase_idx` (`FK_drug_store_purchase` ASC),
  CONSTRAINT `FK_drug_store_purchase`
    FOREIGN KEY (`FK_drug_store_purchase`)
    REFERENCES `drugstoredb`.`drugstore` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
 CONSTRAINT `FK_prescription_purchase`
    FOREIGN KEY (`FK_prescription_purchase`)
    REFERENCES `drugstoredb`.`recipe` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS `drugstoredb`.`purchase-drugs` (
  `FK_purchase_purchase_drugs` INT NOT NULL,
  `FK_drugs_purchase_drugs` INT NOT NULL,
  `number` INT NOT NULL,
  PRIMARY KEY (`FK_purchase_purchase_drugs`, `FK_drugs_purchase_drugs`),
  INDEX `FK_drugs_purchase_drugs_idx` (`FK_drugs_purchase_drugs` ASC),
  CONSTRAINT `FK_purchase_purchase_drugs`
    FOREIGN KEY (`FK_purchase_purchase_drugs`)
    REFERENCES `drugstoredb`.`purchase` (`idpurchase`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_drugs_purchase_drugs`
    FOREIGN KEY (`FK_drugs_purchase_drugs`)
    REFERENCES `drugstoredb`.`drug` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
