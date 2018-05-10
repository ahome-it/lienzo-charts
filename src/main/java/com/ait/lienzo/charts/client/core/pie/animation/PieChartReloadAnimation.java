package com.ait.lienzo.charts.client.core.pie.animation;

import com.ait.lienzo.charts.client.core.animation.StackedTweeningAnimation;
import com.ait.lienzo.charts.client.core.model.DataTable;
import com.ait.lienzo.charts.client.core.model.PieChartData;
import com.ait.lienzo.charts.client.core.pie.PieChart;
import com.ait.lienzo.charts.client.core.pie.PieChart.PieSlice;
import com.ait.lienzo.charts.client.core.pie.PieChartTooltip;
import com.ait.lienzo.client.core.animation.AnimationTweener;
import com.ait.lienzo.client.core.animation.IAnimationCallback;
import com.ait.lienzo.client.core.shape.Text;
import com.google.gwt.core.shared.GWT;

public class PieChartReloadAnimation extends AbstractPieChartAnimation 
{

    public PieChartReloadAnimation(PieChart pieChart, PieChartData data, double chartWidth, double chartHeight, AnimationTweener tweener,
            double duration, IAnimationCallback callback) {
        super(pieChart, chartWidth, chartHeight, tweener, duration, callback);
        add(pieChart, buildAnimationProperties(null, null, chartWidth, chartHeight));
        pieChart.setData(data);        
    }
    
    @Override
    protected boolean apply(final StackedTweeningAnimation.NodeAnimation nodeAnimation, final double percent)
    {
        GWT.log("PieChartReloadAnimation.apply percent: " + percent);
        boolean apply = super.apply(nodeAnimation, percent);

        final double w = getPieChart().getChartWidth();

        final double h = getPieChart().getChartHeight();
        GWT.log("[w,h]" + "["+w+","+h+"]");
        
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
        
       // percent = 1 - percent;
        double radius = getPieChart().getRadius(w, h);

        PieChartData data = getPieChart().getData();

        if (radius <= 0 || (null == data) || (data.size() < 1))
        {
            return;
        }
        
        moveGroups(w, h);

        final DataTable dataTable = data.getDataTable();

        final Double[] values = dataTable.getColumn(data.getValuesProperty()).getNumericValues();
                     
        //double sofar = 0;

        double total = 0;

        for (int i = 0; i < values.length; i++)
        {
            total += values[i];
        }
        getPieChart().getLabels().setListening(false);

       // sofar = 0;
        // total = 360
        // value[i] = x
        double expectedStarts[] = new double[values.length];
        double expectedEnds[] = new double[values.length];
        for (int i = 0; i < values.length; i++)
        {
            
                double thisTotal =values[i] / total;
                if (i == 0)
                {
                    expectedStarts[i] = 0;
                    expectedEnds[i] = thisTotal;
                }
                else
                {
                    expectedStarts[i] = expectedEnds[i-1];
                    expectedEnds[i] = expectedStarts[i] + thisTotal;
                }
            
        }
        
        for (int i = 0; i < values.length; i++)
        {   
            if (i >= getPieChart().getPieSlices().size()) 
            {
                GWT.log("PICE OF PIE NOT FOUND at position "+i+" (maybe the cat eated)");
                getPieChart().addSlice(i, values[i]/total);
                final PieChart.PieSlice newSlice = getPieChart().getPieSlices().get(i);
                final PieChart.PieSlice previousSlice = getPieChart().getPieSlices().get(i-1);
                newSlice.setStartAngle(previousSlice.getEndAngle());
                newSlice.setEndAngle( Math.PI * (-0.5 + 2 * (expectedEnds[i])));

            }
            
            final PieChart.PieSlice slice = getPieChart().getPieSlices().get(i); 
            
            double expectedStartAngle =  Math.PI * (-0.5 + 2 * (expectedStarts[i]));
            double expectedEndAngle =   Math.PI * (-0.5 + 2 * (expectedEnds[i]));
            
            
            /*final double value = calculateCurrentValue(slice, values[i], total, percent);*/ 
            
            double currentEndAngle = slice.getEndAngle();
            double diff = currentEndAngle - expectedEndAngle;
            double diffToApply = diff * percent;
            currentEndAngle -= diffToApply;                                
            
            double currentStartAngle = slice.getStartAngle();
            double diffStart = currentStartAngle - expectedStartAngle;
            double diffToApplyStart = diffStart * percent;
            currentStartAngle -= diffToApplyStart;
                        
            // a área do slice tem q agora ir pro seu novo tamanho 
            
           // double endAngle = PieChart.PieSlice.buildEndAngle(sofar, value);
            
            doAnimatePieSlice(slice, radius, currentStartAngle, currentEndAngle);    
            
            calculateText(w, h, 1, radius, i, slice, expectedStartAngle, expectedEndAngle);
            
            //calculateTooltip(w, h, percent, radius, sofar, i, ((values[i]/total)*percent), slice); 
            //sofar += ((values[i]/total)*percent);         
        }
        
        getPieChart().getLabels().moveToTop();
    }
    
    @Override
    protected double calculateCurrentValue(final PieSlice slice, final double value, final double total, final double percent)
    {
        //if(percent == 0) {
          //  return value/total;
        //}
        return (value / total);// +  ; //* percent; //-   * percent; 
        // got to shrink. Percent 1 =  value / total. Percent 0 = old value
        //double currentEnd = slice.getEndAngle();
        //double targetEnd = value / total;
         
               
        //return targetEnd;
       
    }
    
    @Override
    protected double buildStartAngle(double sofar, PieSlice slice, Double value, double total, double percent)
    {
        
        return PieChart.PieSlice.buildStartAngle(sofar); 
        // sofar 100% = 1; 50% = 0.5
        // value = how % of the total we got
        
        //double startAngle = slice.getStartAngle();
     //return startAngle;
         // value = currentValue which is % of the total
        //double targetStartAngle = PieChart.PieSlice.buildStartAngle(sofar - value);
        //return targetStartAngle ;
        // unboundSpace = if it have more free space, the slices will shrink (we have added some slice)
        // unboundSpace = if it have less free space, the slices will GROW (we have REMOVED some slice)
        
        //return PieChart.PieSlice.buildStartAngle(sofar); 
    }

    @Override
    protected double buildEndAngle(double sofar, PieSlice slice, Double value, double total, double percent) 
    { 
        return Math.PI * (-0.5 + 2 * (sofar - value)); //PieChart.PieSlice.buildEndAngle(sofar, value);  
        //return slice.getEndAngle();
        //return PieChart.PieSlice.buildEndAngle(sofar, value / total);
        
        
        //return PieChart.PieSlice.buildEndAngle(sofar, value);  
        /*double targetAngle = PieChart.PieSlice.buildEndAngle(0, value / total);
        double currentEnd = slice.getEndAngle();       
        
        return targetAngle;*/
//        double diff = Math.abs(currentEnd) - targetAngle;
//        
//        return currentEnd - Math.abs(diff * percent);
    }
    
    @Override
    protected void doAnimateToolTip(PieChartTooltip tooltip, double x, double y)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void doAnimatePieSlice(PieChart.PieSlice slice, double radius, double startAngle, double endAngle)
    {     
        setShapeCircularAttributes(slice, radius, startAngle, endAngle);
    }

    @Override
    protected void doAnimateText(Text text, double x, double y, double alpha, double scale)
    {
        setShapeAttributes(text, x, y, alpha, scale);
    }

}
