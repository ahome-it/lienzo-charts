
package com.ait.lienzo.charts.client.core.model.actions;

import java.util.Date;

import com.ait.lienzo.charts.client.core.model.DataTable;
import com.ait.lienzo.charts.client.core.model.DataTableColumn.DataTableColumnType;

public final class AddDataAction<T> extends DataAction
{
    private T m_data;
    
    public AddDataAction(String column, T data)
    {        
        setColumn(column);
        setDataType(data);

        m_data = data;
    }

    public AddDataAction(String column, DataTableColumnType columnType)
    {     
        m_data = null;
        setColumn(column);
        setDataType(columnType);
    }

    protected AddDataAction(String column, int rowIndex, DataTableColumnType columnType)
    {
        super(column, rowIndex, columnType);
    }

    @Override
    public void apply(DataTable dataTable)
    {
        if (m_data == null)
        {
            dataTable.addColumn(getColumn(), getColumnType());
        }
        else
        {
            switch (getColumnType())
            {
                case DATE:
                    dataTable.addValue(getColumn(), (Date) m_data);
                    break;

                case NUMBER:
                    dataTable.addValue(getColumn(), (Double) m_data);
                    break;

                case STRING:
                    dataTable.addValue(getColumn(), (String) m_data);
                    break;

                default:
                    break;
            }
            
            if (hasAnimation())
            {
                getAnimation().run();
            }
        }
    }   
}