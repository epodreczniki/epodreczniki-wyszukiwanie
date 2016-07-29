package pl.psnc.epub.parser.jaxb.module;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author spychala
 */
@XmlType(namespace="http://epodreczniki.pl/")
public class EPDateElement extends ModuleElement {
    
    protected String year;
    protected String month;
    protected String day;
    
    @XmlElement(name="year", namespace="http://epodreczniki.pl/")
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        if (year.startsWith("-"))
            this.year = String.format("%s p.n.e.", year.substring(1));
        else
            this.year = year;
    }

    @XmlElement(name="month", namespace="http://epodreczniki.pl/")
    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    @XmlElement(name="day", namespace="http://epodreczniki.pl/")
    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @Override
    public String toString() {
        String strDate = null;
        
        if ((day != null) && !"".equals(day))
            strDate = day;
        if ((month != null) && !"".equals(month))
            if (strDate != null)
                strDate = String.format("%s-%s", strDate, month);
            else
                strDate = month;
        if ((year != null) && !"".equals(year))
            if (strDate != null)
                strDate = String.format("%s-%s", strDate, year);
            else
                strDate = year;
        return strDate;
    }


}
