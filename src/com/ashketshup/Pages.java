package com.ashketshup;

import java.util.List;
import static com.ashketshup.Navigation.getMaxAmountItems;

public class Pages {
    private int currentPage = 0;
    private int pagesAmount;

    public void setPagesAmount(int scrollableListSize) {
        this.pagesAmount = (int) Math.ceil((double) scrollableListSize / (double) getMaxAmountItems());
    }

    public int getPagesAmount(int scrollableListSize) {
        return pagesAmount;
    }

    public void setCurrentIndex(int index) {
        this.currentPage = index;
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
        int nextIndex = getCurrentPage() + 1;

        if (0 <= (nextIndex) && (nextIndex) <= pagesAmount)
            setCurrentIndex(nextIndex);
    }

    public void lastPage() {
        int nextIndex = getCurrentPage() - 1;

        if (0 <= (nextIndex) && (nextIndex) <= pagesAmount)
            setCurrentIndex(nextIndex);
    }

    public <T> List<T> trimContent(List<T> content) {
        int start  = getCurrentPage() * getMaxAmountItems();
        int finish = start + getMaxAmountItems();

        if (finish >= content.size() || finish < 0)
            finish = content.size();

        return content.subList(start, finish);
    }

    public String pageToString() {
        return currentPage + "/" + pagesAmount;
    }
}
