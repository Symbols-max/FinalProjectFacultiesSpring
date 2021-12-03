package org.epam.final_project.other;

public class Helper {

   public static long getPageCount(long totalCount,int itemsPerPage) {
        return (totalCount / itemsPerPage) + ((totalCount % itemsPerPage > 0) ? 1 : 0);
    }

}
