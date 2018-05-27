package com.ait.lienzo.charts.client.core.factory;

import java.awt.List;
import java.util.ArrayList;

import javax.validation.constraints.NotNull;

import com.ait.lienzo.charts.client.core.AbstractChart;
import com.ait.lienzo.charts.client.core.model.DataTable;


/*abstract class Chart implements IChart, IChartAttributesSetter
{
    @NotNull
    static IChartFactory create()
    {
        return null;
    }  
 
    void setColorPallete(ColorPalette colorPallete) {}
    
    abstract Chart toPieChart();
}*/

/*class FadeInAnimation implements IChartAnimation{} 

class OtherCustomAnimation implements IChartAnimation{}
class Test
{
    void DoYourThing()
    {
        FadeInAnimation fadeInAnimation = new FadeInAnimation();
        OtherCustomAnimation customAnimation = new OtherCustomAnimation();
        DataTable data = new DataTable();
        Object someNewObject = new Object();
        Object otherObject = new Object();
        Object someOldItem = new Object();
        Object otherItem = new Object();
        
        // This is how user will create a barChart()
        Chart chart = Chart.create().barChart().using(data);
        
        // He can also create a pieChart or whatever type he wants to
        Chart otherChart = Chart.create().pieChart().using(data);
                        
        // User will be able to specify it's own animation or use our animations
        chart.add(someNewObject).add(otherObject).with(fadeInAnimation).applyChanges();
           
        // Can also queue changes
        chart.add(someNewObject);
        chart.remove(someOldItem);
        chart.add(otherObject);
     
        chart.applyChanges();
        
        // He can also specify what animation he wants for each action.
        chart.add(someNewObject).with(fadeInAnimation);
        chart.add(otherItem).with(customAnimation);       
        
        // If he wants to discard changes before applyChanges()
        chart.discardChanges();
        
        // It's also possible to change color palette...
        chart.setColorPallete(ColorsPalettes.MIXED);
        
        // ...or specify color palette at creation time
        Chart newBarChart = Chart.create().barChart().withColorPallete(ColorsPalettes.PATTERN_FLY).using(data);  
        
        // Also user should be able to change between one chart to another using the same data.
        // An animation for this could be cool too:
        Chart pieChart = newBarChart.toPieChart();
        
        /*
        Chart pieChart = Chart.create().pieChart().using(fullData);
        Chart chart = Chart.create().barChart().using(fullData);
        
        chart.add(someNewItem);
        chart.add(someNewItem).with(PieChartAnimation.COOL_ANIMATION_ONE);
        chart.delete(someOldItem);
        chart.update(someItem); // we'll figure it out inside what data is to update
        
        chart.draw();
        
        chart.create().barChart().usingColorsPallete(ColorsPallete.PATTERN_FLY);
        
        // can also use:
        chart.add(someNew).delete(someOld).delete(whatever).replace(somethingOld, somethingNew);
        // under the hoods, this will queue and pre-process the changes
        
        --- everything should be already done when we call draw().
            draw() has to be as fast as we can
        
        // or even:
        chart.convert().toBarChart();
 
        
    }
}
       */
