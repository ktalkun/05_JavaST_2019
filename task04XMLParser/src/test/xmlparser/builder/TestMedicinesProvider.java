package test.xmlparser.builder;

import by.tolkun.xmlparser.builder.ObjectFactory;
import by.tolkun.xmlparser.entity.medicine.Company;
import by.tolkun.xmlparser.entity.medicine.Currency;
import by.tolkun.xmlparser.entity.medicine.Dosage;
import by.tolkun.xmlparser.entity.medicine.Group;
import by.tolkun.xmlparser.entity.medicine.Medicine;
import by.tolkun.xmlparser.entity.medicine.Medicines;
import by.tolkun.xmlparser.entity.medicine.Package;
import by.tolkun.xmlparser.entity.medicine.PackageType;
import by.tolkun.xmlparser.entity.medicine.Period;
import by.tolkun.xmlparser.entity.medicine.PeriodType;
import by.tolkun.xmlparser.entity.medicine.Price;
import by.tolkun.xmlparser.entity.medicine.Version;
import by.tolkun.xmlparser.entity.medicine.VersionType;
import by.tolkun.xmlparser.entity.medicine.Versions;

/**
 * Class to expected provide {@code Medicines}.
 *
 * @author Kirill Tolkun
 */
public class TestMedicinesProvider {

    /**
     * Factory to create entity objects.
     */
    private ObjectFactory objectFactory;

    /**
     * Default constructor.
     */
    public TestMedicinesProvider() {
        objectFactory = new ObjectFactory();
    }

    /**
     * Create first expected {@code Medicines}.
     *
     * @return {code Medicines}
     */
    public Medicines createExpectedMedicines1() {
        Medicines expectedMedicines1 = objectFactory.createMedicines();
        Medicine medicine = objectFactory.createMedicine();
        medicine.setName("Антиоксикапс");
        medicine.setGroup(Group.VITAMINS);
        Versions versions = objectFactory.createVersions();
        Version version = objectFactory.createVersion();
        Company company = objectFactory.createCompany();

        company.setName("БЕЛМЕДПРЕПАРАТЫ");

        Package _package = objectFactory.createPackage();
        _package.setType(PackageType.BOX);
        _package.setQuantity(20);
        Price price = objectFactory.createPrice();
        price.setCurrency(Currency.BYN);
        price.setValue(3.29);
        _package.setPrice(price);
        company.setPackage(_package);

        Dosage dosage = objectFactory.createDosage();
        dosage.setSize(3);
        Period period = objectFactory.createPeriod();
        period.setType(PeriodType.DAY);
        period.setValue(1);
        dosage.setPeriod(period);
        company.setDosage(dosage);

        version.setType(VersionType.CAPSULES);
        version.getCompanyOrCompanyCertificate().add(company);
        versions.getVersion().add(version);
        medicine.setVersions(versions);
        expectedMedicines1.getMedicine().add(medicine);
        return expectedMedicines1;
    }

    /**
     * Create second expected {@code Medicines}.
     *
     * @return {code Medicines}
     */
    public Medicines createExpectedMedicines2() {
        Medicines expectedMedicines2 = objectFactory.createMedicines();
        Medicine medicine = objectFactory.createMedicine();
        medicine.setGroup(Group.SEDATIVES);
        medicine.setName("Валериана");
        medicine.getAnalogs().add("Валериана Форте");
        medicine.getAnalogs().add("Валерианы настойка");
        Versions versions = objectFactory.createVersions();
        Version version = objectFactory.createVersion();
        Company company = objectFactory.createCompany();

        company.setName("БЕЛМЕДПРЕПАРАТЫ");

        Package _package = objectFactory.createPackage();
        _package.setType(PackageType.BOTTLE);
        _package.setQuantity(1);
        Price price = objectFactory.createPrice();
        price.setCurrency(Currency.BYN);
        price.setValue(2.99);
        _package.setPrice(price);

        company.setPackage(_package);

        Dosage dosage = objectFactory.createDosage();
        dosage.setSize(3);
        Period period = objectFactory.createPeriod();
        period.setType(PeriodType.DAY);
        period.setValue(1);
        dosage.setPeriod(period);
        company.setDosage(dosage);

        version.getCompanyOrCompanyCertificate().add(company);

        company = objectFactory.createCompany();

        company.setName("Apotex");

        _package = objectFactory.createPackage();
        _package.setType(PackageType.BOTTLE);
        _package.setQuantity(1);
        price = objectFactory.createPrice();
        price.setCurrency(Currency.BYN);
        price.setValue(5.20);
        _package.setPrice(price);
        company.setPackage(_package);

        dosage = objectFactory.createDosage();
        dosage.setSize(3);
        period = objectFactory.createPeriod();
        period.setType(PeriodType.DAY);
        period.setValue(1);
        dosage.setPeriod(period);
        company.setDosage(dosage);

        version.setType(VersionType.LIQUID);
        version.getCompanyOrCompanyCertificate().add(company);
        versions.getVersion().add(version);
        medicine.setVersions(versions);
        expectedMedicines2.getMedicine().add(medicine);
        return expectedMedicines2;
    }

    /**
     * Create third expected {@code Medicines}.
     *
     * @return {code Medicines}
     */
    public Medicines createExpectedMedicines3() {
        Medicines expectedMedicines3 = objectFactory.createMedicines();
        Medicine medicine = objectFactory.createMedicine();
        medicine.setGroup(Group.ANTIDEPRESSANTS);
        medicine.setName("Амитриптилин");
        medicine.getAnalogs().add("Имипрамин");
        medicine.getAnalogs().add("Мапротилин");
        medicine.getAnalogs().add("Кломипрамин");
        medicine.getAnalogs().add("Миансерин");
        Versions versions = objectFactory.createVersions();
        Version version = objectFactory.createVersion();
        Company company = objectFactory.createCompany();

        company.setName("БЕЛМЕДПРЕПАРАТЫ");

        Package _package = objectFactory.createPackage();
        _package.setType(PackageType.BOX);
        _package.setQuantity(20);
        Price price = objectFactory.createPrice();
        price.setCurrency(Currency.BYN);
        price.setValue(22.90);
        _package.setPrice(price);

        company.setPackage(_package);

        Dosage dosage = objectFactory.createDosage();
        dosage.setSize(2);
        Period period = objectFactory.createPeriod();
        period.setType(PeriodType.DAY);
        period.setValue(1);
        dosage.setPeriod(period);
        company.setDosage(dosage);

        version.setType(VersionType.CAPSULES);
        version.getCompanyOrCompanyCertificate().add(company);
        versions.getVersion().add(version);

        version = objectFactory.createVersion();
        company = objectFactory.createCompany();

        company.setName("Solvay Farma");

        _package = objectFactory.createPackage();
        _package.setType(PackageType.BOX);
        _package.setQuantity(30);
        price = objectFactory.createPrice();
        price.setCurrency(Currency.BYN);
        price.setValue(21.35);
        _package.setPrice(price);

        company.setPackage(_package);

        dosage = objectFactory.createDosage();
        dosage.setSize(1);
        period = objectFactory.createPeriod();
        period.setType(PeriodType.DAY);
        period.setValue(1);
        dosage.setPeriod(period);
        company.setDosage(dosage);

        version.setType(VersionType.TABLETS);
        version.getCompanyOrCompanyCertificate().add(company);
        versions.getVersion().add(version);
        medicine.setVersions(versions);
        expectedMedicines3.getMedicine().add(medicine);
        return expectedMedicines3;
    }
}
