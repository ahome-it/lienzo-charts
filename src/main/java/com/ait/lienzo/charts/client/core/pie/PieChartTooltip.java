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

package com.ait.lienzo.charts.client.core.pie;

import com.ait.lienzo.charts.shared.core.types.palettes.PatternFlyPalette;
import com.ait.lienzo.client.core.shape.Group;
import com.ait.lienzo.client.core.shape.Rectangle;
import com.ait.lienzo.client.core.shape.Text;
import com.ait.lienzo.client.core.shape.Triangle;
import com.ait.lienzo.client.core.types.BoundingBox;
import com.ait.lienzo.client.core.types.Point2D;
import com.ait.lienzo.client.core.types.Shadow;
import com.ait.lienzo.shared.core.types.ColorName;
import com.ait.lienzo.shared.core.types.IColor;
import com.ait.lienzo.shared.core.types.TextAlign;
import com.ait.lienzo.shared.core.types.TextBaseLine;

public class PieChartTooltip extends Group
{
    private final IColor m_tooltipColor;

    private final IColor m_fontColor;
    
    private IColor m_dataColor;
    
    private static final String FONT_FAMILY            = "Verdana";

    private static final String CATEGORIES_FONT_STYLE  = "";

    private static final String VALUES_FONT_STYLE      = "bold";

    private static final int    FONT_SIZE              = 10;

    private Rectangle m_dataRectangle;
    
    private Rectangle           m_rectangle;

    private Text                m_categoriesTextObject;

    private Text                m_valuesTextObject;
    
    private Text                m_valuesPercentageTextObject;

    private String              m_categoriesText;

    private String              m_valuesText;

    private String              m_valuesPercentageText;
    
    private static final int MARGINS = 10;
 

    public PieChartTooltip()
    {
        PatternFlyPalette palette = new PatternFlyPalette();
        m_tooltipColor = palette.getTooltipBackgroundColor();
        m_fontColor = palette.getTooltipTextColor(); 
        build();
    }

    protected PieChartTooltip build()
    {
        m_dataRectangle = new Rectangle(10,10);
        m_rectangle = new Rectangle(1, 1).setFillColor(m_tooltipColor);
            
        m_valuesPercentageTextObject = createStandardTextObject();        
        m_categoriesTextObject = createStandardTextObject();        
        m_valuesTextObject = createStandardTextObject();  
        
        add(m_rectangle);
        add(m_dataRectangle);
        add(m_valuesTextObject);
        add(m_categoriesTextObject);        
        add(m_valuesPercentageTextObject);
                
        m_valuesTextObject.moveToTop();
        m_categoriesTextObject.moveToTop();        
        m_valuesPercentageTextObject.moveToTop();
        
        setVisible(false);
        setListening(false);
        return this;
    }
    
    private Text createStandardTextObject()
    {
        return new Text("", FONT_FAMILY, VALUES_FONT_STYLE, FONT_SIZE)
                .setFillColor(m_fontColor)
                .setTextAlign(TextAlign.LEFT)
                .setTextBaseLine(TextBaseLine.MIDDLE);
    }

    public PieChartTooltip show(final double x, final double y)
    {
        double tooltipHeight = (MARGINS*2) + 10;
        m_dataRectangle.setFillColor(m_dataColor);
        m_dataRectangle.setX(MARGINS).setY(MARGINS);        
        
        m_valuesTextObject.setText(m_valuesText);
        BoundingBox bb = m_valuesTextObject.getBoundingBox();
        final double valuesWidth = bb.getWidth();
        m_valuesTextObject.setX(m_dataRectangle.getX() + m_dataRectangle.getWidth() + 5);    
        m_valuesTextObject.setY(14);
                
        m_categoriesTextObject.setText(m_categoriesText);
        bb = m_categoriesTextObject.getBoundingBox();
        final double categoriesWidth = bb.getWidth();                
        m_categoriesTextObject.setX(MARGINS + m_dataRectangle.getWidth() + 10 + valuesWidth);                
        m_categoriesTextObject.setY(14);
        
        m_valuesPercentageTextObject.setText(m_valuesPercentageText);
        bb = m_valuesPercentageTextObject.getBoundingBox();        
        final double valuesPercentageWidth = bb.getWidth();
        m_valuesPercentageTextObject.setX(categoriesWidth + m_categoriesTextObject.getX() + 40);                
        m_valuesPercentageTextObject.setY(14);
        
        m_rectangle.setWidth((MARGINS*2) + categoriesWidth + valuesWidth + valuesPercentageWidth + m_dataRectangle.getWidth() + 5 + 5 + 40);
        m_rectangle.setHeight(tooltipHeight);
        
        setX(x);
        setY(y);
        moveToTop();
        setVisible(true);
        getLayer().batch();
        return this;
    }

    public PieChartTooltip hide()
    {
        setVisible(false);

        getLayer().batch();

        return this;
    }

    public PieChartTooltip setValues(
            String categoriesText,
            String valuesText,
            IColor color,
            String valuesPercentageText)
    {
        m_dataColor = color;
        m_categoriesText = categoriesText;
        m_valuesText = valuesText;
        m_valuesPercentageText = valuesPercentageText;

        return this;
    }
}
