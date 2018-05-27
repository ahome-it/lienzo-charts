
package com.ait.lienzo.charts.client.core.model.actions;

import com.ait.lienzo.charts.client.core.model.DataTable;

public class DeleteDataAction extends DataAction
{
    private String m_rowLabel;

    public DeleteDataAction(String column, String row)
    {
        setColumn(column);
        m_rowLabel = row;
    }

    public DeleteDataAction(String column)
    {
        this(column, null);
    }

    @Override
    public void apply(DataTable dataTable)
    {
        if (m_rowLabel != null)
        {
            int index = dataTable.getRowIndex(m_rowLabel);
            // TODO: this
        }
        //
        // TODO Auto-generated method stub

    }
}