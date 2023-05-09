package com.mycompany.myapp.gui;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Categorieoffre;
import com.mycompany.myapp.entities.Offre;
import com.mycompany.myapp.service.ServiceCategorieOffre;
import com.mycompany.myapp.service.ServiceOffre;
import java.util.ArrayList;
import java.util.HashMap;

public class StatistiquePieForm extends Form {
    public StatistiquePieForm(Resources res) {
        
        super("Categories", BoxLayout.y());
 Button retourButton = new Button("Retour");
        retourButton.addActionListener(e -> new CategorieOffreList(res).showBack());
        addComponent(retourButton);
        ArrayList<Offre> offres = ServiceOffre.getInstance().getAllOffre();
        HashMap<String, Integer> categoryCount = new HashMap<>();

        for (Offre offre : offres) {
            Categorieoffre categorie = ServiceCategorieOffre.getInstance().getCategorieOffreById(offre.getCatOffreId());
            if (categorie != null) {
                String categoryName = categorie.getTypeOffre();
               
if (categoryCount.containsKey(categoryName)) {
    int count = categoryCount.get(categoryName);
    categoryCount.put(categoryName, count + 1);
} else {
    categoryCount.put(categoryName, 1);
}            }
        }

        CategorySeries series = new CategorySeries("Catégories");
        DefaultRenderer renderer = new DefaultRenderer();

        int[] colors = new int[]{0xFF0000, 0x00FF00, 0x0000FF, 0xFFFF00}; // Define custom colors

        int colorIndex = 0;
        for (String category : categoryCount.keySet()) {
            series.add(category, categoryCount.get(category));
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(colors[colorIndex % colors.length]); // Set color for each category
            renderer.addSeriesRenderer(r);
            colorIndex++;
        }

        renderer.setLabelsTextSize(150); // Adjust label text size as needed
renderer.setLabelsColor(0x000000);
renderer.setChartTitle("statisques sur Categories");
renderer.setChartTitleTextSize(80);
        PieChart chart = new PieChart(series, renderer);
        ChartComponent chartComponent = new ChartComponent(chart);
        add(chartComponent);
             
    }
}
