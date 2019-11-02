package com.github.zyndev.centimani.report;

public class OldReportImpl implements IReport {
    @Override
    public long getMaxAge() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
