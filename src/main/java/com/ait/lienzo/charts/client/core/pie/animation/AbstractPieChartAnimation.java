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

package com.ait.lienzo.charts.client.core.pie.animation;

import com.ait.lienzo.charts.client.core.animation.AbstractChartAnimation;
import com.ait.lienzo.charts.client.core.model.DataTable;
import com.ait.lienzo.charts.client.core.model.PieChartData;
import com.ait.lienzo.charts.client.core.pie.PieChart;
import com.ait.lienzo.charts.client.core.pie.PieChart.PieSlice;
import com.ait.lienzo.charts.client.core.pie.PieChartTooltip;
import com.ait.lienzo.client.core.Attribute;
import com.ait.lienzo.client.core.animation.AnimationProperties;
import com.ait.lienzo.client.core.animation.AnimationProperty;
import com.ait.lienzo.client.core.animation.AnimationTweener;
import com.ait.lienzo.client.core.animation.IAnimationCallback;
import com.ait.lienzo.client.core.event.NodeMouseEnterEvent;
import com.ait.lienzo.client.core.event.NodeMouseEnterHandler;
import com.ait.lienzo.client.core.shape.Group;
import com.ait.lienzo.client.core.shape.Shape;
import com.ait.lienzo.client.core.shape.Text;
import com.google.gwt.core.client.GWT;

public abstract class AbstractPieChartAnimation extends AbstractChartAnimation
{    
   // public static final double Turn = Math.PI * 2.0;
    
    public AbstractPieChartAnimation(final PieChart pieChart, final double chartWidth, final double chartHeight, AnimationTweener tweener, final double duration, final IAnimationCallback callback)
    {
        super(pieChart, chartWidth, chartHeight, tweener, duration, callback);
    }

    protected PieChart getPieChart()
    {
        return (PieChart) getNode();
    }

    protected void calculate(final double w, final double h)
    {
        double percent = getPercent();
        
        if (percent > 1) 
        {
            percent = 1;            
        }
        
        double radius = getPieChart().getRadius(w, h);

        PieChartData data = getPieChart().getData();

        if (radius <= 0 || (null == data) || (data.size() < 1))
        {
            return;
        }
        super.calculate(w, h);

        moveGroups(w, h);

        final DataTable dataTable = data.getDataTable();
        //final String[] categories = dataTable.getColumn(data.getCategoriesProperty()).getStringValues();
        final Double[] values = dataTable.getColumn(data.getValuesProperty()).getNumericValues();
                     
        double sofar = 0;

        double total = 0;

        for (int i = 0; i < values.length; i++)
        {
            total += values[i];
        }
        getPieChart().getLabels().setListening(false);

        // total = 360
        // value[i] = x
        
        for (int i = 0; i < values.length; i++)
        {
             
            
            if (i < getPieChart().getPieSlices().size()) 
            {
                
                final PieChart.PieSlice slice = getPieChart().getPieSlices().get(i); 
                final double value = calculateCurrentValue(slice, values[i], total, percent);
                
                double startAngle = buildStartAngle(sofar, slice, value, total, percent); 
                
                double endAngle = buildEndAngle(sofar, slice, value, total, percent);
               // double endAngle = PieChart.PieSlice.buildEndAngle(sofar, value);
                
                doAnimatePieSlice(slice, radius, startAngle, endAngle);    
                
                calculateText(w, h, percent, radius, sofar, i, value, slice);               
                sofar += value;
            }
            else
            {              
                // TODO: New data values added. 
            }
            
            
        }
        getPieChart().getLabels().moveToTop();
    }

    protected double buildStartAngle(double sofar, PieSlice slice, Double value, double total, double percent)
    {
        return PieChart.PieSlice.buildStartAngle(sofar); 
    }
    
    protected double buildEndAngle(double sofar, PieSlice slice, Double value, double total, double percent) 
    {
       return PieChart.PieSlice.buildEndAngle(sofar, value);       
    }

    protected double calculateCurrentValue(final PieSlice slice, final double value, final double total, final double percent)
    {
        return (value / total) * percent; 
    }
    
    protected void calculateText(final double w, final double h, double percent, double radius, double sofar, int i,  final double value, PieChart.PieSlice slice)
    {
        double startAngle = Math.PI * (2.0 * sofar);

        double endAngle = Math.PI * (2.0 * (sofar + value));

        calculateText(w, h, percent, radius, i, slice, startAngle, endAngle);
    }

    protected void calculateText(final double w, final double h, double percent, double radius, int i, PieChart.PieSlice slice, double startAngle, double endAngle) {
        double middleAngle = (startAngle + endAngle) / 2.0;

        if (middleAngle > (Math.PI * 2.0))
        {
            middleAngle = middleAngle - (Math.PI * 2.0);
        }
        else if (middleAngle < 0)
        {
            middleAngle = middleAngle + (Math.PI * 2.0);
        }
        double lx = Math.sin(middleAngle) * (radius / 2);

        double ly = 0 - Math.cos(middleAngle) * (radius / 2);

        //TextAlign align;

       if (middleAngle <= (Math.PI * 0.5))
        {
            lx += 2;

            //align = TextAlign.LEFT;
        }
        else if ((middleAngle > (Math.PI * 0.5)) && (middleAngle <= Math.PI))
        {
            lx += 2;

            //align = TextAlign.LEFT;
         }
        else if ((middleAngle > Math.PI) && (middleAngle <= (Math.PI * 1.5)))
        {
            lx -= 2;

            //align = TextAlign.RIGHT;
        }
        else
        {
            lx -= 2;

            //align = TextAlign.RIGHT;
        }
        final double xToolTip = lx;
        final double yToolTip = ly;
        
        if (slice != null)
        {
            slice.addNodeMouseEnterHandler(new NodeMouseEnterHandler()
            {
                @Override
                public void onNodeMouseEnter(NodeMouseEnterEvent event)
                {
                    // Position the tooltip.
                    doAnimateToolTip(getPieChart().getTooltip(), xToolTip + w / 2, yToolTip + h / 2);
                }
            });
        }
        
        if (i < getPieChart().getTexts().size())
        {
            Text text = getPieChart().getTexts().get(i);
   
            if (text != null)
            {                
                final double textWidth = text.getBoundingBox().getWidth();
                final double textHeight = text.getBoundingBox().getHeight();
                
                final double scale = percent; 
                        
                doAnimateText(text, lx - textWidth / 2, ly - textHeight / 2, 1d, scale);
            }
        }
        else
        {
            com.google.gwt.core.shared.GWT.log("this shouldn't happen....");
            
            // TODO: New data values added.
        }
    }

    protected void moveGroups(double w, double h)
    {
        final Group labels = getPieChart().getLabels();
        final Group slices = getPieChart().getSlices();
        final double x = w / 2;
        final double y = h / 2;
        labels.setX(x).setY(y);
        slices.setX(x).setY(y);
    }

    protected abstract void doAnimateToolTip(final PieChartTooltip tooltip, final double x, final double y);

    protected abstract void doAnimatePieSlice(final PieChart.PieSlice slice, final double radius, final double startAngle, final double endAngle);

    protected abstract void doAnimateText(final Text text, final double x, final double y, final double alpha, final double scale);

    protected static AnimationProperties buildAnimationProperties(Double x, Double y, Double w, Double h)
    {
        if (x == null && y == null && w == null && h == null) return null;
        AnimationProperties animationProperties = new AnimationProperties();
        if (x != null) animationProperties.push(AnimationProperty.Properties.X(x));
        if (y != null) animationProperties.push(AnimationProperty.Properties.Y(y));
        if (w != null) animationProperties.push(AnimationProperty.Properties.WIDTH(w));
        if (h != null) animationProperties.push(AnimationProperty.Properties.HEIGHT(h));
        return animationProperties;
    }

    protected void setShapeAttributes(final Shape<?> shape, final Double x, final Double y, final Double alpha)
    {
        if (shape != null)
        {
            if (x != null)
            {
                shape.setX(x);
            }
            
            if (y != null)
            {
                shape.setY(y);
            }
            
            if (alpha != null)
            {
                shape.setAlpha(alpha);
            }
        }
    }

    protected void setShapeAttributes(final Shape<?> shape, final Double x, final Double y, final Double alpha, final Double scale)
    {
        if (shape != null)
        {
            setShapeAttributes(shape, x, y, alpha);
            
            if (scale != null)
            {
               shape.setScale(scale);
            }
        }
    }
    
    protected void setShapeCircularAttributes(final Shape<?> shape, final Double radius, final Double startAngle, final Double endAngle)
    {
        if (shape != null)
        {
            if (radius != null)
            {
                shape.getAttributes().put(Attribute.RADIUS.getProperty(), radius);
            }
            if (startAngle != null) 
            {
                shape.getAttributes().put(Attribute.START_ANGLE.getProperty(), startAngle);
            }
            if (endAngle != null)
            {
                shape.getAttributes().put(Attribute.END_ANGLE.getProperty(), endAngle);
            }
        }
    }
}