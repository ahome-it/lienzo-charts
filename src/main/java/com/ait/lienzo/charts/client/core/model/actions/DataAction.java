
package com.ait.lienzo.charts.client.core.model.actions;

import java.util.Date;

import com.ait.lienzo.charts.client.core.animation.AbstractChartAnimation;
import com.ait.lienzo.charts.client.core.model.DataTable;
import com.ait.lienzo.charts.client.core.model.DataTableColumn.DataTableColumnType;

/**
 * Defines a action that performs against a DataTable.
 * @author Daniel José dos Santos
 *
 */
public abstract class DataAction
{
    private String                          m_column;

    private int                             m_rowIndex;

    private DataTableColumnType             m_columnType;

    private AbstractChartAnimation          m_animation;
    
    protected DataAction()
    {
        m_rowIndex = -1;
    }

    protected DataAction(String column, int rowIndex, DataTableColumnType columnType)
    {
        m_column = column;
        m_rowIndex = rowIndex;
        m_columnType = columnType;
    }

    protected DataAction(String column, int rowIndex)
    {
        this(column, rowIndex, DataTableColumnType.STRING);
    }

    protected <T> void setDataType(T data)
    {
        if (data instanceof String)
        {
            setColumnType(DataTableColumnType.STRING);
        }
        else if (data instanceof Date)
        {
            setColumnType(DataTableColumnType.DATE);
        }
        else if (data instanceof Number)
        {
            setColumnType(DataTableColumnType.NUMBER);
        }
        else
        {
            throw new IllegalArgumentException("The specified type is not supported.");
        }
    }

    protected void setColumn(String column)
    {
        m_column = column;
    }

    protected void setRowIndex(int rowIndex)
    {
        m_rowIndex = rowIndex;
    }

    protected int getRowIndex()
    {
        return m_rowIndex;
    }

    protected void setColumnType(DataTableColumnType columnType)
    {
        m_columnType = columnType;
    }

    protected DataTableColumnType getColumnType()
    {
        return m_columnType;
    }

    protected String getColumn()
    {
        return m_column;
    }

    public AbstractChartAnimation getAnimation()
    {
        return m_animation;
    }
    
    public void setAnimation(AbstractChartAnimation animation)
    {
        m_animation = animation;
    }
    
    protected boolean hasAnimation()
    {
        return m_animation != null;
    }
    
    /**
     * Apply the pending change.
     * @param dataTable The DataTable to be changed.
     */
    public abstract void apply(DataTable dataTable);
}