package org.epam.final_project.other;

import org.epam.final_project.model.Faculty;

import java.util.Comparator;
import java.util.List;

public class Sort {
    private static Comparator<Faculty> sortByName= Comparator.comparing(Faculty::getNameFaculty);
    private static Comparator<Faculty> sortByAllPlaces=Comparator.comparing((Faculty::getAllPlaces)).reversed();
    private static Comparator<Faculty> sortByBudgetPlaces=Comparator.comparing(Faculty::getFundedPlaces).reversed();

    public static void sortByName(List<Faculty> facultyList){
        facultyList.sort(sortByName);
    }
    public static void sortByALLPlaces(List<Faculty> facultyList){
        facultyList.sort(sortByAllPlaces);
    }
    public static void sortByBudgetPlaces(List<Faculty> facultyList){
        facultyList.sort(sortByBudgetPlaces);
    }
}
