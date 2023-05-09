package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Offre;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TriOffreForm extends Form {
    private List<Offre> offres;
    private ComboBox<String> catOffreSelect;

    public TriOffreForm(List<Offre> offres) {
        this.offres = offres;
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        // Create the scrollable select list for CatOffreId
        catOffreSelect = new ComboBox<>();
        add(catOffreSelect);

        // Populate the select list with distinct CatOffreId values
        populateCatOffreSelect();

        // Add an action listener to the select list
        catOffreSelect.addActionListener(e -> {
            // Get the selected CatOffreId
            String selectedCatOffreId = catOffreSelect.getSelectedItem();

            // Sort the offres list based on the selected CatOffreId
            sortOffresByCatOffreId(selectedCatOffreId);

            // Update the UI to reflect the sorted offres
            updateUI();
        });

        // Initially, sort the offres list based on the default selected CatOffreId
        String defaultSelectedCatOffreId = catOffreSelect.getSelectedItem();
        sortOffresByCatOffreId(defaultSelectedCatOffreId);

        // Update the UI to reflect the initial sorted offres
        updateUI();
    }

    private void populateCatOffreSelect() {
        Map<Integer, String> catOffreMap = new HashMap<>();

        // Fetch the CatOffreId and corresponding category name from the CategorieOffre entities
        for (Offre offre : offres) {
            int catOffreId = offre.getCatOffreId();
            String categoryName = getCategoryName(catOffreId);
            catOffreMap.put(catOffreId, categoryName);
        }

        // Sort the CatOffreIds in ascending order
        List<Integer> sortedCatOffreIds = new ArrayList<>(catOffreMap.keySet());
        Collections.sort(sortedCatOffreIds);

        // Add the sorted CatOffreIds to the select list
        for (int catOffreId : sortedCatOffreIds) {
            catOffreSelect.addItem(String.valueOf(catOffreId));
        }
    }

    private String getCategoryName(int catOffreId) {
        // Fetch the category name based on the catOffreId using your CategorieOffre service or logic
        // Replace the return statement below with your implementation
        return "Category " + catOffreId;
    }

    private void sortOffresByCatOffreId(String selectedCatOffreId) {
        int catOffreId = Integer.parseInt(selectedCatOffreId);
        Collections.sort(offres, new OffreComparator(catOffreId));
    }



    private void updateUI() {
        // Clear the current UI components
        getContentPane().removeAll();

        // Add the sorted offres as SpanLabels to the UI
        for (Offre offre : offres) {
            SpanLabel offreLabel = new SpanLabel(offre.toString());
            getContentPane().add(offreLabel);
        }

        // Revalidate the form to update the UI
        revalidate();
    }

  
}