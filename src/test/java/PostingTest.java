import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by xopher on 28/05/2018.
 */
public class PostingTest {

    @Test
    public void automatedTestUsingOpenPojo_GettersAndSetters_ProperFieldAccess() {
        // All Getters and Setters should access proper field
        // https://github.com/OpenPojo/openpojo/wiki
        PojoTestUtility.validateGettersAndSetters(Posting.class);
    }

}