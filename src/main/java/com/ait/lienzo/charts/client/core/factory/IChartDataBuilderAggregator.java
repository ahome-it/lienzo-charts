
package com.ait.lienzo.charts.client.core.factory;

import java.util.Date;

public interface IChartDataBuilderAggregator
{
    IChartDataBuilderAggregator and(Double value, String header);

    IChartDataBuilderAggregator and(String value, String header);

    IChartDataBuilderAggregator and(Date value, String header);
}