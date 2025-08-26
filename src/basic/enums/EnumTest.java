package enums;

public class EnumTest {
    public static void main(String[] args)
    {
        Size size = Size.MEDIUM;
        System.out.println("size="+size);
        System.out.println("abbreviation="+size.getAbbreviation());
        size = Size.valueOf("LARGE");
        System.out.println("size="+size);
        System.out.println("abbreviation="+size.getAbbreviation());
    }
}
enum Size{
    SMALL("S"),MEDIUM("M"),LARGE("L"),EXTRALARGE("XL");
    private String abbreviation;
    private Size(String abbreviation){
        this.abbreviation = abbreviation;
    }
    public String getAbbreviation(){
        return abbreviation;
    }
}