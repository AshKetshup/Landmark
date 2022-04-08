package com.ashketshup;

import java.util.List;
import com.ashketshup.Navigation;

public class Pages {
    private static int maxAmountItems = 0;
    private int currentPage = 0;
    private int pagesAmount;

    public Pages(int contentSize) {
        this.currentPage = 0;
        this.pagesAmount = (int) Math.ceil((double) contentSize / (double) maxAmountItems);
    }

    public static void setMaxAmountItems(int maxItems) {
        Pages.maxAmountItems = maxItems;
    }

    public static int getMaxAmountItems() {
        return Pages.maxAmountItems;
    }

    public void setPagesAmount(int amountPages) {
        this.pagesAmount = amountPages;
    }

    public int getPagesAmount() {
        return pagesAmount;
    }

    /**
     * Gets current page.
     *
     * @return the current page
     */
    public int getCurrentPage() {
        return currentPage;
    }

    public void nextPage() {
        int nextIndex = currentPage + 1;

        if (0 <= (nextIndex) && (nextIndex) < pagesAmount)
            this.currentPage = nextIndex;
    }

    public void lastPage() {
        int nextIndex = currentPage - 1;

        if (0 <= (nextIndex) && (nextIndex) < pagesAmount)
            this.currentPage = nextIndex;
    }

    public <T> List<T> trimContent(List<T> content) {
        int start  = currentPage * maxAmountItems;
        int finish = start + maxAmountItems;

        if (finish >= content.size() || finish <= 0)
            finish = content.size();

        return content.subList(start, finish);
    }

    public String pageToString() {
        return (currentPage + 1) + "/" + pagesAmount;
    }
}
