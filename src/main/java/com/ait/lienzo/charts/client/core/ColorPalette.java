
package com.ait.lienzo.charts.client.core;

import com.ait.lienzo.shared.core.types.IColor;

public abstract class ColorPalette
{
    //public abstract IColor getNextColor();

    public abstract IColor getBordersColor();
    
    public abstract IColor getColor(int colorIndex);
}    
