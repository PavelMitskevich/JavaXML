package com.mitskevich.task2.builder;

import com.mitskevich.task2.creator.CreatorEntityTest;
import com.mitskevich.task2.entity.*;
import com.mitskevich.task2.exception.CustomParserXmlException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.*;
import static org.testng.AssertJUnit.fail;

public class MedicineStaxBuilderTest {
    Set<AbstractMedicine> expected = new HashSet<>();

    @Test
    public void testBuildSetMedicines() {
        MedicineStaxBuilder builder = new MedicineStaxBuilder();
        expected = CreatorEntityTest.createEntities();
        Set<AbstractMedicine> actual;
        builder.buildSetMedicines("C:\\Users\\Иван\\IdeaProjects\\JavaXML\\src\\test\\java\\resources\\medicinesTest.xml");
        actual = builder.getMedicines();
        assertEquals(expected, actual);
    }
}