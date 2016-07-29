package pl.psnc.epub.parser.jaxb.module;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author spychala
 */
@XmlRootElement(name="core-curriculum-entry", namespace="http://epodreczniki.pl/")
public class CoreCurriculumEntry {
    
    private String educationLevel;
    private String coreCurrirulumSubject;
    private String coreCurriculumCode;
    private String coreCurriculumKeyword;

    @XmlElement(name="education-level", namespace="http://cnx.rice.edu/mdml")
    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    @XmlElement(name="core-curriculum-subject", namespace="http://epodreczniki.pl/")
    public String getCoreCurrirulumSubject() {
        return coreCurrirulumSubject;
    }

    public void setCoreCurrirulumSubject(String coreCurrirulumSubject) {
        this.coreCurrirulumSubject = coreCurrirulumSubject;
    }

    @XmlElement(name="core-curriculum-code", namespace="http://epodreczniki.pl/")
    public String getCoreCurriculumCode() {
        return coreCurriculumCode;
    }

    public void setCoreCurriculumCode(String coreCurriculumCode) {
        this.coreCurriculumCode = coreCurriculumCode;
    }

    @XmlElement(name="core-curriculum-keyword", namespace="http://epodreczniki.pl/")
    public String getCoreCurriculumKeyword() {
        return coreCurriculumKeyword;
    }

    public void setCoreCurriculumKeyword(String coreCurriculumKeyword) {
        this.coreCurriculumKeyword = coreCurriculumKeyword;
    }

    @Override
    public String toString() {
        //return "CoreCurriculumEntry{" + "educationLevel=" + educationLevel + ", coreCurrirulumSubject=" + coreCurrirulumSubject + ", coreCurriculumCode=" + coreCurriculumCode + ", coreCurriculumKeyword=" + coreCurriculumKeyword + '}';
        return String.format("Poziom: %s; Temat: %s, Kod: %s, Klucz: %s", educationLevel, coreCurrirulumSubject, coreCurriculumCode, coreCurriculumKeyword);
    }
    
}
