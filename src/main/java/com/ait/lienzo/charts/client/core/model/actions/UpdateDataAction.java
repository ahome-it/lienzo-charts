
package com.ait.lienzo.charts.client.core.model.actions;

import java.util.Date;

import com.ait.lienzo.charts.client.core.model.DataTable;

public class UpdateDataAction<T> extends DataAction
{
    private T      m_data;

    private String m_rowLabel;

    public UpdateDataAction(String column, int rowIndex, T data)
    {
        setColumn(column);
        setRowIndex(rowIndex);
        setDataType(data);
        m_data = data;
    }

    public UpdateDataAction(String column, String rowLabel, T data)
    {
        setColumn(column);
        setDataType(data);
        m_rowLabel = rowLabel;
        m_data = data;
    }

    @Override
    public void apply(DataTable dataTable)
    {
        if (m_rowLabel != null)
        {
            setRowIndex(dataTable.getRowIndex(m_rowLabel));
        }

        switch (getColumnType())
        {
            case DATE:
                dataTable.setValue(getColumn(), getRowIndex(), (Date) m_data);
                break;

            case NUMBER:
                dataTable.setValue(getColumn(), getRowIndex(), (Double) m_data);
                break;

            case STRING:
                dataTable.setValue(getColumn(), getRowIndex(), (String) m_data);
                break;

            default:
                break;
        }
    }
}