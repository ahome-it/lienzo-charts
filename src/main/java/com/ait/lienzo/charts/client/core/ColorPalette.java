
package com.ait.lienzo.charts.client.core;

import com.ait.lienzo.shared.core.types.IColor;

public abstract class ColorPalette
{
    // For random colors. Remove?
    //public abstract IColor getNextColor();

    public abstract IColor getBordersColor();
    
    public abstract IColor getColor(int colorIndex);
    
    public abstract IColor getTooltipBackgroundColor();
    
    public abstract IColor getTooltipTextColor();
}    
