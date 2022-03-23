package com.mitskevich.task2.validator;

import com.mitskevich.task2.exception.CustomParserXmlException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BaseValidatorTest {
    @Test
    public void validateXmlCorrectTest() throws CustomParserXmlException {
        String fileNameXml = "C:\\Users\\Иван\\IdeaProjects\\JavaXML\\src\\test\\java\\resources\\medicinesTest.xml";
        String fileNameSchema = "C:\\Users\\Иван\\IdeaProjects\\JavaXML\\src\\test\\java\\resources\\medicinesTest.xsd";
        BaseValidator baseValidator = new BaseValidator();
        boolean expected = true;
        boolean actual = baseValidator.isXmlValidXsd(fileNameXml, fileNameSchema);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void validateXmlWrongTest() throws CustomParserXmlException {
        String fileNameXml = "C:\\Users\\Иван\\IdeaProjects\\JavaXML\\src\\test\\java\\resources\\medicinesTestWrong.xml";
        String fileNameSchema = "C:\\Users\\Иван\\IdeaProjects\\JavaXML\\src\\test\\java\\resources\\medicinesTest.xsd";
        BaseValidator baseValidator = new BaseValidator();
        boolean actual = baseValidator.isXmlValidXsd(fileNameXml, fileNameSchema);
        Assert.assertFalse(actual);
    }
}