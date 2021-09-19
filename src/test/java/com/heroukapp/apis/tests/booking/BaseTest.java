package com.heroukapp.apis.tests.booking;


import com.heroukapp.apis.config.Config;
import com.heroukapp.apis.steps.BookingsSteps;
import com.heroukapp.apis.utils.HeaderUtil;
import com.heroukapp.apis.utils.QueryParamUtil;


import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = Config.class)
@ExtendWith(SpringExtension.class)
@TestPropertySource({"classpath:endpoints.properties"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public abstract class BaseTest {
    @Autowired
    public BookingsSteps bookingsSteps;
    @Autowired
    public HeaderUtil headerUtil;
    @Autowired
    public QueryParamUtil queryParamUtil;
}
