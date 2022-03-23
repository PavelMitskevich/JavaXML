package com.mitskevich.task2.builder;

import com.mitskevich.task2.creator.CreatorEntityTest;
import com.mitskevich.task2.entity.AbstractMedicine;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

import static org.testng.Assert.*;

public class MedicineDomBuilderTest {
    Set<AbstractMedicine> expected = new HashSet<>();

    @Test
    public void testBuildSetMedicines() {
        MedicineDomBuilder builder = new MedicineDomBuilder();
        expected = CreatorEntityTest.createEntities();
        Set<AbstractMedicine> actual;
        builder.buildSetMedicines("C:\\Users\\Иван\\IdeaProjects\\JavaXML\\src\\test\\java\\resources\\medicinesTest.xml");
        actual = builder.getMedicines();
        assertEquals(expected, actual);
    }
}