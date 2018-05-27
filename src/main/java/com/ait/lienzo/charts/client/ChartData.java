package com.ait.lienzo.charts.client;

import com.ait.lienzo.charts.client.core.model.DataTable;

public final class ChartData
{
    private final String                m_categoriesAxis;
    
    private final String                m_valuesAxis;
    
    private final DataTable             m_data;
    
    public ChartData(DataTable data, String categoriesAxis, String valuesAxis)
    {
        m_data = data;
        m_categoriesAxis = categoriesAxis;
        m_valuesAxis = valuesAxis;
    }
    
    public String getCategoriesAxis()
    {
        return m_categoriesAxis;
    }
    
    public String getValuesAxis()
    {
        return m_valuesAxis;
    }
    
    public DataTable getData()
    {
        return m_data;
    }
}

final class DataRow
{
    // chart.addDataRow("Juventude", 5, 2);
    // chart.addData("Juventude").with(5, "Goals").and(2,"against");

    // Country Capital    Population (millions)
    // Brazil  Brasilia   207
    // USA     Washington 325

    // chart.getData().add("Canada").with("Ottawa", "Capital").and(36, "Population")
    // chart.getData().add("Japan").with(127, "Population") //missing capital, but it's acceptable
    // OR just: chart.add("Argentina", "Buenos Aires", 43) 
}