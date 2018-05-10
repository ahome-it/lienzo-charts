/*
   Copyright (c) 2014,2015,2016 Ahome' Innovation Technologies. All rights reserved.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
   
   Author: Roger Martinez - Red Hat
 */

package com.ait.lienzo.charts.client.core;

import com.ait.lienzo.charts.client.core.json.validators.AxisValidator;
import com.ait.lienzo.charts.client.core.json.validators.PieChartDataValidator;
import com.ait.lienzo.charts.client.core.json.validators.XYChartSeriesValidator;
import com.ait.lienzo.charts.shared.core.types.*;
import com.ait.lienzo.client.core.AttributeType;
import com.ait.lienzo.client.core.shape.json.validators.ArrayValidator;
import com.ait.lienzo.client.core.shape.json.validators.EnumValidator;
import com.ait.lienzo.client.core.shape.json.validators.IAttributeTypeValidator;

public class ChartAttributeType extends AttributeType
{
    public static final ChartAttributeType ALIGN               = new ChartAttributeType(new EnumValidator<ChartAlign>("align", ChartAlign.values()));

    public static final ChartAttributeType DIRECTION           = new ChartAttributeType(new EnumValidator<ChartDirection>("direction", ChartDirection.values()));

    public static final ChartAttributeType ORIENTATION         = new ChartAttributeType(new EnumValidator<ChartOrientation>("orientation", ChartOrientation.values()));

    public static final ChartAttributeType LEGEND_POSITION     = new ChartAttributeType(new EnumValidator<LegendPosition>("LegendPosition", LegendPosition.values()));

    public static final ChartAttributeType LEGEND_ALIGN        = new ChartAttributeType(new EnumValidator<LegendAlign>("LegendAlign", LegendAlign.values()));

    public static final ChartAttributeType AXIS_TYPE           = new ChartAttributeType(AxisValidator.INSTANCE);

    public static final ChartAttributeType LABELS_POSITION     = new ChartAttributeType(new EnumValidator<LabelsPosition>("labelsPosition", LabelsPosition.values()));

    public static final ChartAttributeType XY_CHART_DATA_TYPE  = new ChartAttributeType(new ArrayValidator(XYChartSeriesValidator.INSTANCE));

    public static final ChartAttributeType PIE_CHART_DATA_TYPE = new ChartAttributeType(new ArrayValidator(PieChartDataValidator.INSTANCE));

    protected ChartAttributeType(IAttributeTypeValidator validator)
    {
        super(validator);
    }
}
