import org.junit.Test;
import org.junit.Assert;
public class TheRavenTests {
    @Test
    public void testPrint() {
    var theRaven = new TheRaven();
        try {
            theRaven.print();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        int value = theRaven.result.get("the");
        Assert.assertEquals(56, value);
        int value2 = theRaven.result.get("and");
        Assert.assertEquals(30, value2);
    }
}
