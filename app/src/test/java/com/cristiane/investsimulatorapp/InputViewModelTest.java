package com.cristiane.investsimulatorapp;

import android.content.Context;

import com.cristiane.investsimulatorapp.ui.InputActivity;
import com.cristiane.investsimulatorapp.viewmodel.InputViewModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.mock;

/**
 * Created by cristiane on 12/07/2018.
 */

//@RunWith(MockitoJUnitRunner.class)
public class InputViewModelTest {

    InputViewModel viewModel;
    Context mMockContext;

    final String dummyValue = "32000.00";
    final String dummyDate = "2020-12-01";
    final String dummyRate = "123";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mMockContext = mock(InputActivity.class);
        viewModel = new InputViewModel();
        viewModel.updateInputData(dummyValue, "CDI", dummyRate, false, dummyDate);
    }

    @Test
    public void simulateWithEmptyInvestedAmount_showsInvestedAmountError() {
        viewModel.loadResult(mMockContext, "", "CDI", dummyRate, false, dummyDate);

        Mockito.verify((InputActivity) mMockContext).showValueInputError();
        Mockito.verify((InputActivity) mMockContext, Mockito.never()).showSimulationSuccessfully();
    }

    @Test
    public void simulateWithValidInvestedAmount_dontShowInvestedAmountError() {
        viewModel.loadResult(mMockContext, dummyValue, "CDI", dummyRate, false, dummyDate);

        Mockito.verify((InputActivity) mMockContext, Mockito.never()).showValueInputError();
    }

    @Test
    public void simulateWithEmptyMaturityDate_showsMaturityDateError() {
        viewModel.loadResult(mMockContext, dummyValue, "CDI", dummyRate, false, "");

        Mockito.verify((InputActivity) mMockContext).showDateError();
        Mockito.verify((InputActivity) mMockContext, Mockito.never()).showSimulationSuccessfully();
    }

    @Test
    public void simulateWithValidMaturityDate_dontShowMaturityDateError() {
        viewModel.loadResult(mMockContext, dummyValue, "CDI", dummyRate, false, dummyDate);

        Mockito.verify((InputActivity) mMockContext, Mockito.never()).showDateError();
    }

    @Test
    public void simulateWithEmptyRate_showsRateError() {
        viewModel.loadResult(mMockContext, dummyValue, "CDI", "", false, dummyDate);

        Mockito.verify((InputActivity) mMockContext).showCdiPercentageInputError();
        Mockito.verify((InputActivity) mMockContext, Mockito.never()).showSimulationSuccessfully();
    }

    @Test
    public void simulateWithValidRate_dontShowRateError() {
        viewModel.loadResult(mMockContext, dummyValue, "CDI", dummyRate, false, dummyDate);

        Mockito.verify((InputActivity) mMockContext, Mockito.never()).showCdiPercentageInputError();
    }
}
