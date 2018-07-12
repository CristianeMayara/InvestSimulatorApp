package com.cristiane.investsimulatorapp.viewmodel;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;

import com.cristiane.investsimulatorapp.model.Result;
import com.cristiane.investsimulatorapp.ui.InputActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Created by cristiane on 12/07/2018.
 */

//@RunWith(MockitoJUnitRunner.class)
@RunWith(JUnit4.class)
public class InputViewModelTest {

    InputViewModel viewModel;
    Context mMockContext;

    final String dummyValue = "32000.00";
    final String dummyDate = "2020-12-01";
    final String dummyRate = "123";

    MutableLiveData<Result> liveData;

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        liveData = new MutableLiveData<>();
        mMockContext = mock(InputActivity.class);
        viewModel = new InputViewModel();
        viewModel.updateInputData(dummyValue, "CDI", dummyRate, false, dummyDate);
    }

    @Test
    public void simulateWithEmptyInvestedAmount_showsInvestedAmountError() {
        viewModel.loadResult(mMockContext, "", "CDI", dummyRate, false, dummyDate);

        verify((InputActivity) mMockContext).showValueInputError();
        verify((InputActivity) mMockContext, never()).showSimulationSuccessfully();
    }

    @Test
    public void simulateWithValidInvestedAmount_dontShowInvestedAmountError() {
        viewModel.loadResult(mMockContext, dummyValue, "CDI", dummyRate, false, dummyDate);

        verify((InputActivity) mMockContext, never()).showValueInputError();
    }

    @Test
    public void simulateWithEmptyMaturityDate_showsMaturityDateError() {
        viewModel.loadResult(mMockContext, dummyValue, "CDI", dummyRate, false, "");

        verify((InputActivity) mMockContext).showDateError();
        verify((InputActivity) mMockContext, never()).showSimulationSuccessfully();
    }

    @Test
    public void simulateWithValidMaturityDate_dontShowMaturityDateError() {
        viewModel.loadResult(mMockContext, dummyValue, "CDI", dummyRate, false, dummyDate);

        verify((InputActivity) mMockContext, never()).showDateError();
    }

    @Test
    public void simulateWithEmptyRate_showsRateError() {
        viewModel.loadResult(mMockContext, dummyValue, "CDI", "", false, dummyDate);

        verify((InputActivity) mMockContext).showCdiPercentageInputError();
        verify((InputActivity) mMockContext, never()).showSimulationSuccessfully();
    }

    @Test
    public void simulateWithValidRate_dontShowRateError() {
        viewModel.loadResult(mMockContext, dummyValue, "CDI", dummyRate, false, dummyDate);

        verify((InputActivity) mMockContext, never()).showCdiPercentageInputError();
    }

    @Test
    public  void liveDataEmpty() {
        Observer<Result> result = mock(Observer.class);
        viewModel.getResult().observeForever(result);
        verifyNoMoreInteractions(mMockContext);
    }
}
