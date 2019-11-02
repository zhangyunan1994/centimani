package com.github.zyndev.centimani;

import com.github.zyndev.centimani.report.IReport;
import com.github.zyndev.centimani.report.NewReportImpl;
import com.github.zyndev.centimani.report.OldReportImpl;
import org.junit.Assert;
import org.junit.Test;

public class AppTest {

    @Test
    public void test() {
        Experiment<Double> experiment = new Experiment<>("测试double", (c1, c2) -> c1.intValue() == c2.intValue());
        Result result = experiment.run(() -> 1D, () -> 1D);
        System.out.println(result);
        Assert.assertTrue(result.isMatch());
    }

    @Test
    public void testReport() {
        IReport oldReport = new OldReportImpl();
        IReport newReport = new NewReportImpl();

        Experiment<Long> experiment = new Experiment<>("测试报表", (c1, c2) -> c1.intValue() == c2.intValue());
        Result result = experiment.run(oldReport::getMaxAge, newReport::getMaxAge);
        System.out.println(result);
        Assert.assertTrue(result.isMatch());
    }
}
