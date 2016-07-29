package pl.psnc.epub.parser.jaxb.collection;

import java.util.Date;

/**
 *
 * @author spychala
 */
public class CollectionFactory {

    public static Collection createCollection() {
        return new Collection(new Date());
    }

    public static Metadata createMetadata() {
        return new Metadata(new Date());
    }

    public static Parameters createParameters() {
        return new Parameters(new Date());
    }

    public static Parameter createParameter() {
        return new Parameter(new Date());
    }

    public static Content createContent() {
        return new Content(new Date());
    }

    public static Role createRole() {
        return new Role(new Date());
    }

    public static Roles createRoles() {
        return new Roles(new Date());
    }

    public static License createLicense() {
        return new License(new Date());
    }

    public static Keywords createKeywords() {
        return new Keywords();
    }

    public static Subjects createSubjects() {
        return new Subjects();
    }

    public static EducationLevels createEducationLevels() {
        return new EducationLevels();
    }
}
