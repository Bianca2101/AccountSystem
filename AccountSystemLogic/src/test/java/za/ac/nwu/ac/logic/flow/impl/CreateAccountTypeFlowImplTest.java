package za.ac.nwu.ac.logic.flow.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//@RunWith(MockitoJUnitRunner.class)
public class CreateAccountTypeFlowImplTest {

   // @Mock
    //private AccountTypeTranslator translator;

   // @InjectMocks
   private CreateAccountTypeFlowImpl testAccountTypeFlowImplClass;

    @Before
    public void setUp() throws Exception {
        testAccountTypeFlowImplClass = new CreateAccountTypeFlowImpl(null);
    }

    @After
    public void tearDown() throws Exception {
        testAccountTypeFlowImplClass = null;
    }

    @Test
    public void create() {

    }
}