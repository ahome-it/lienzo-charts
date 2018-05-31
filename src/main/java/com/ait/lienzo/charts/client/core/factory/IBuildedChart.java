package com.ait.lienzo.charts.client.core.factory;

import com.ait.lienzo.charts.client.ChartData;
import com.ait.lienzo.charts.client.core.ColorPalette;

public interface IBuildedChart
{
    IBuildedChart using(ChartData data);
    IBuildedChart withColorPalette(ColorPalette pallete);
    IChartDataHandler getDataHandler();
    void applyDataChanges();
}