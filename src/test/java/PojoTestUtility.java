import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;

/**
 * Utility for automated testing of getters/setters
 *
 * Created by xopher on 28/05/2018.
 */
public class PojoTestUtility {

    /* https://github.com/OpenPojo/openpojo */

    private static final Validator GET_SET_VALIDATOR = ValidatorBuilder.create()
        .with(new GetterTester())
        .with(new SetterTester())
        .build();

    public static void validateGettersAndSetters(final Class<?> classUnderTest) {
        GET_SET_VALIDATOR.validate(PojoClassFactory.getPojoClass(classUnderTest));
    }
}
