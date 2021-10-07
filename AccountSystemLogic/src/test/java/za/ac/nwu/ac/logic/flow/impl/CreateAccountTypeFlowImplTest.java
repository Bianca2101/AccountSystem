package za.ac.nwu.ac.logic.flow.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import za.ac.nwu.ac.translator.AccountTypeTranslator;

@RunWith(MockitoJUnitRunner.class)
public class CreateAccountTypeFlowImplTest {

    @Mock
    private AccountTypeTranslator translator;

    @InjectMocks
    private CreateAccountTypeFlowImpl flow;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void create() {
        /*Mockito.doThrow(new RuntimeException()).when(translator).someMethod();
        try{
            flow.create(new AccountTypeDto());
            fail("Should throw an exception");
        }catch (Exception e){
        }
        Mockito.verify(translator, Mockito.times(1)).someMethod();
        Mockito.verify(translator, Mockito.never()).create(Mockito.any(AccountTypeDto.class));*/
    }
}