package com.ashketshup;

import java.util.List;
import com.ashketshup.Navigation;

/**
 * The type Pages.
 * Implements a scrolling feature on screens who have a bigger
 * content than the max Amount Of Items.
 */
public class Pages {
    private int maxAmountItems;
    private int currentPage = 0;
    private int pagesAmount;

    /**
     * Instantiates a new Pages.
     *
     * @param contentSize the content size
     */
    public Pages(int contentSize) {
        this.currentPage = 0;
        this.maxAmountItems = Navigation.getMaxAmountItems();

        this.pagesAmount = (contentSize / maxAmountItems);
        if (contentSize % maxAmountItems != 0)
            this.pagesAmount++;
    }

    /**
     * Sets max amount items.
     *
     * @param maxItems the max items
     */
    public void setMaxAmountItems(int maxItems) {
        this.maxAmountItems = maxItems;
    }

    /**
     * Gets max amount items.
     *
     * @return the max amount items
     */
    public int getMaxAmountItems() {
        return maxAmountItems;
    }

    /**
     * Sets pages amount.
     *
     * @param amountPages the amount pages
     */
    public void setPagesAmount(int amountPages) {
        this.pagesAmount = amountPages;
    }

    /**
     * Gets pages amount.
     *
     * @return the pages amount
     */
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

    /**
     * Scrolls to the next page.
     */
    public void nextPage() {
        int nextIndex = currentPage + 1;

        if (0 <= (nextIndex) && (nextIndex) < pagesAmount)
            this.currentPage = nextIndex;
    }

    /**
     * Scrolls to the last page.
     */
    public void lastPage() {
        int nextIndex = currentPage - 1;

        if (0 <= (nextIndex) && (nextIndex) < pagesAmount)
            this.currentPage = nextIndex;
    }

    /**
     * Trim content list.
     *
     * @param <T>     the type parameter
     * @param content the content
     * @return the list
     */
    public <T> List<T> trimContent(List<T> content) {
        int start  = currentPage * maxAmountItems;
        int finish = start + maxAmountItems;

        if (finish >= content.size() || finish <= 0)
            finish = content.size();

        return content.subList(start, finish);
    }

    /**
     * Page to string string.
     *
     * @return the string
     */
    public String pageToString() {
        return (currentPage + 1) + "/" + pagesAmount;
    }
}
