import org.junit.Test;
import wanglijie.util.TimeUtil;

/**
 * Created by $Jason.Zhang on 1/5/16.
 */
public class UtilTest {
    @Test
    public void testTimeUtil(){
        assert new TimeUtil().toDateTimeFromUnixTimeStamp(1451965076L).equals("2016-01-04 19:37:56");
    }
}
