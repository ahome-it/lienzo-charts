package com.ait.lienzo.charts.client.core.pie.animation;

import com.ait.lienzo.charts.client.core.animation.StackedTweeningAnimation;
import com.ait.lienzo.charts.client.core.model.DataTable;
import com.ait.lienzo.charts.client.core.model.PieChartData;
import com.ait.lienzo.charts.client.core.pie.PieChart;
import com.ait.lienzo.charts.client.core.pie.PieChart.PieSlice;
import com.ait.lienzo.charts.client.core.pie.PieChartTooltip;
import com.ait.lienzo.client.core.animation.AnimationTweener;
import com.ait.lienzo.client.core.animation.IAnimationCallback;
import com.ait.lienzo.client.core.event.NodeMouseEnterEvent;
import com.ait.lienzo.client.core.event.NodeMouseEnterHandler;
import com.ait.lienzo.client.core.shape.Text;

public class AddDataAnimation extends AbstractPieChartAnimation
{      
    public AddDataAnimation(
            PieChart pieChart,
            double chartWidth, 
            double chartHeight, 
            AnimationTweener tweener,
            double duration,
            IAnimationCallback callback)
    {
        super(pieChart, chartWidth, chartHeight, tweener, duration, callback);    
        add(pieChart, buildAnimationProperties(null, null, chartWidth, chartHeight));
    }

    @Override
    protected boolean apply(final StackedTweeningAnimation.NodeAnimation nodeAnimation, final double percent)
    {       
        boolean apply = super.apply(nodeAnimation, percent);
        final double w = getPieChart().getChartWidth();
        final double h = getPieChart().getChartHeight();       

        calculate(w, h);

        return apply;
    }
    
    @Override
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

        moveGroups(w, h);

        final DataTable dataTable = data.getDataTable();

        final Double[] values = dataTable.getColumn(data.getValuesProperty()).getNumericValues();

        double total = 0;

        for (int i = 0; i < values.length; i++)
        {
            total += values[i];
        }
        getPieChart().getLabels().setListening(false);

        double expectedStarts[] = new double[values.length];
        double expectedEnds[] = new double[values.length];
        for (int i = 0; i < values.length; i++)
        {
            double thisTotal = values[i] / total;
            if (i == 0)
            {
                expectedStarts[i] = 0;
                expectedEnds[i] = thisTotal;
            }
            else
            {
                expectedStarts[i] = expectedEnds[i - 1];
                expectedEnds[i] = expectedStarts[i] + thisTotal;
            }
        }

        for (int i = 0; i < values.length; i++)
        {
            if (i >= getPieChart().getPieSlices().size())
            {  
                //GWT.log("PICE OF PIE NOT FOUND at position " + i + " (maybe the cat eated)");
                getPieChart().addSlice(i, values[i] / total);
                final PieChart.PieSlice newSlice = getPieChart().getPieSlices().get(i);
                final PieChart.PieSlice previousSlice = getPieChart().getPieSlices().get(i - 1);
                newSlice.setStartAngle(previousSlice.getEndAngle());
                newSlice.setEndAngle(Math.PI * (-0.5 + 2 * (expectedEnds[i])));
            }

            final PieChart.PieSlice slice = getPieChart().getPieSlices().get(i);

            double expectedStartAngle = Math.PI * (-0.5 + 2 * (expectedStarts[i]));
            double expectedEndAngle = Math.PI * (-0.5 + 2 * (expectedEnds[i]));

            double currentEndAngle = slice.getEndAngle();
            double diff = currentEndAngle - expectedEndAngle;
            double diffToApply = diff * percent;
            currentEndAngle -= diffToApply;

            double currentStartAngle = slice.getStartAngle();
            double diffStart = currentStartAngle - expectedStartAngle;
            double diffToApplyStart = diffStart * percent;
            currentStartAngle -= diffToApplyStart;

            doAnimatePieSlice(slice, radius, currentStartAngle, currentEndAngle);

            calculateText(w, h, percent, radius, i, slice, currentStartAngle, currentEndAngle);         
        }

        getPieChart().getLabels().moveToTop();
    }
    
    @Override
    protected void calculateText(
            final double w,
            final double h,
            double percent, 
            double radius,
            int i,
            PieChart.PieSlice slice, 
            double startAngle,
            double endAngle)
    { 
        double middleAngle = (startAngle + endAngle) / 2.0;
 
        if (middleAngle > (Math.PI * 2.0))
        {
            middleAngle = middleAngle - (Math.PI * 2.0);
        }
        else if (middleAngle < 0)
        {
            middleAngle = middleAngle + (Math.PI * 2.0);
        }
        
        final double halfRadius = radius/2;
        final double lx = halfRadius *  Math.cos(middleAngle);
        final double ly = halfRadius * Math.sin(middleAngle);


        //TextAlign align;

        /*if (middleAngle <= (Math.PI * 0.5))
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
        }*/
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
                //final double scale = percent;

                // (0,0) is the center of the pie                

                doAnimateText(text, lx- textWidth / 2, ly - textHeight / 2, 1d, 1);              
            }
        }       
    }
    
    
    @Override
    protected double calculateCurrentValue(final PieSlice slice, final double value, final double total, final double percent)
    {     
        return (value / total);        
    }
   
    @Override
    protected double buildStartAngle(double sofar, PieSlice slice, Double value, double total, double percent)
    {
        return PieChart.PieSlice.buildStartAngle(sofar);      
    }

    @Override
    protected double buildEndAngle(double sofar, PieSlice slice, Double value, double total, double percent)
    {
        return Math.PI * (-0.5 + 2 * (sofar - value));
    }
    
    @Override
    protected void doAnimateToolTip(PieChartTooltip tooltip, double x, double y)
    {
        tooltip.setX(x).setY(y);       
    }

    @Override
    protected void doAnimatePieSlice(PieSlice slice, double radius, double startAngle, double endAngle)
    {
        setShapeCircularAttributes(slice, radius, startAngle, endAngle);        
    }

    @Override
    protected void doAnimateText(Text text, double x, double y, double alpha, double scale)
    {
        setShapeAttributes(text, x, y, alpha, scale);        
    }
}