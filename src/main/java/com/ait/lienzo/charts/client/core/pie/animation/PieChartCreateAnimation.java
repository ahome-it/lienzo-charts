package com.ait.lienzo.charts.client.core.pie.animation;

import com.ait.lienzo.charts.client.core.animation.StackedTweeningAnimation;
import com.ait.lienzo.charts.client.core.pie.PieChart;
import com.ait.lienzo.charts.client.core.pie.PieChart.PieSlice;
import com.ait.lienzo.charts.client.core.pie.PieChartTooltip;
import com.ait.lienzo.client.core.animation.AnimationTweener;
import com.ait.lienzo.client.core.animation.IAnimationCallback;
import com.ait.lienzo.client.core.shape.Text;
import com.google.gwt.core.shared.GWT;

public class PieChartCreateAnimation extends AbstractPieChartAnimation
{

    public PieChartCreateAnimation(PieChart pieChart, double chartWidth, double chartHeight, AnimationTweener tweener,
            double duration, IAnimationCallback callback) 
    {
        super(pieChart, chartWidth, chartHeight, tweener, duration, callback);

        add(pieChart, buildAnimationProperties(null, null, chartWidth, chartHeight));
    }

    @Override
    protected boolean apply(final StackedTweeningAnimation.NodeAnimation nodeAnimation, final double percent)
    {
        GWT.log("PieChartCreateAnimation.apply percent: " + percent);
        boolean apply = super.apply(nodeAnimation, percent);

        final double w = getPieChart().getChartWidth();

        final double h = getPieChart().getChartHeight();
        GWT.log("[w,h]" + "["+w+","+h+"]");
        
        calculate(w, h);

        return apply;
    }

    @Override
    protected void doAnimateToolTip(PieChartTooltip tooltip, double x, double y)
    {
        tooltip.show(x, y);
    }

    @Override
    protected void doAnimatePieSlice(PieChart.PieSlice slice, double radius, double startAngle, double endAngle)
    {
        GWT.log("setShapeCircularAttributes slice color:" + slice.getColorKey() + "; radius: "+radius+"; startAngle: "+ startAngle+ "endAngle:"+endAngle);
        setShapeCircularAttributes(slice, radius, startAngle, endAngle);
    }

    @Override
    protected void doAnimateText(Text text, double x, double y, double alpha, double scale)
    {
        setShapeAttributes(text, x, y, alpha, scale);
    }

}
