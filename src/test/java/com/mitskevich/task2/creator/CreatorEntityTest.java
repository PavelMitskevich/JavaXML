package com.mitskevich.task2.creator;

import com.mitskevich.task2.entity.*;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CreatorEntityTest {
    public static Set<AbstractMedicine> createEntities() {
        Set<AbstractMedicine> medicines = new HashSet<>();

        Antibiotic antibiotic = new Antibiotic();
        antibiotic.setName("Amoxicillin");
        antibiotic.setPrescription(true);
        antibiotic.setPharm("Pharmtechnology");
        List<String> analogsOfAntibiotic = new ArrayList<>();
        analogsOfAntibiotic.add("Suprax");
        analogsOfAntibiotic.add("Amoxiclav");
        antibiotic.setAnalogs(analogsOfAntibiotic);
        List<Version> versionsOfAntibiotic = new ArrayList<>();
        Version versionOfAntibiotic = new Version();
        versionOfAntibiotic.setExecution("pill");
        Certificate certificateOfAntibiotic = new Certificate();
        certificateOfAntibiotic.setRegistrationNumber(1234567);
        certificateOfAntibiotic.setRegisteringOrganization("Belgroup");
        ExpirationDate expirationDateOfAntibiotic = new ExpirationDate(YearMonth.of(2010, 1), YearMonth.of(2025, 10));
        certificateOfAntibiotic.setExpirationDate(expirationDateOfAntibiotic);
        versionOfAntibiotic.setCertificate(certificateOfAntibiotic);
        PackageOfMedicine packageOfMedicineOfAntibiotic = new PackageOfMedicine("box", 100, 9.5);
        versionOfAntibiotic.setPackageOfMedicine(packageOfMedicineOfAntibiotic);
        Dosage dosageOfAntibiotic = new Dosage(1, 2);
        versionOfAntibiotic.setDosage(dosageOfAntibiotic);
        versionsOfAntibiotic.add(versionOfAntibiotic);
        antibiotic.setVersions(versionsOfAntibiotic);
        antibiotic.setExpirationDateOfMedicine(YearMonth.of(2022, 9));

        Painkiller painkiller = new Painkiller();
        painkiller.setName("Analgin");
        painkiller.setPower("strong");
        painkiller.setPharm("Ben");
        List<String> analogsOfPainkiller = new ArrayList<>();
        analogsOfPainkiller.add("Baralgin");
        antibiotic.setAnalogs(analogsOfPainkiller);
        List<Version> versionsOfPainkiller = new ArrayList<>();
        Version versionOfPainkiller = new Version();
        versionOfPainkiller.setExecution("pill");
        Certificate certificateOfPainkiller = new Certificate();
        certificateOfPainkiller.setRegistrationNumber(81359);
        certificateOfPainkiller.setRegisteringOrganization("Gin");
        ExpirationDate expirationDateOfPainkiller = new ExpirationDate(YearMonth.of(2010, 1), YearMonth.of(2030, 1));
        certificateOfPainkiller.setExpirationDate(expirationDateOfPainkiller);
        versionOfPainkiller.setCertificate(certificateOfPainkiller);
        PackageOfMedicine packageOfMedicineOfPainkiller = new PackageOfMedicine("box", 20, 10.21);
        versionOfPainkiller.setPackageOfMedicine(packageOfMedicineOfPainkiller);
        Dosage dosageOfPainkiller = new Dosage(1, 2);
        versionOfPainkiller.setDosage(dosageOfPainkiller);
        versionsOfPainkiller.add(versionOfPainkiller);
        painkiller.setVersions(versionsOfPainkiller);
        painkiller.setExpirationDateOfMedicine(YearMonth.of(2022, 10));

        medicines.add(antibiotic);
        medicines.add(painkiller);
        return medicines;
    }
}
