package test.listing;

import org.testng.annotations.Test;
import test.base.BaseTest;

import static constants.Constant.CatalogNames.FOR_GAMERS;
import static constants.Constant.SortTypes.FROM_CHEAP;
import static constants.Constant.Urls.HOME_PAGE;


public class ListingTest extends BaseTest {


    @Test(priority = 1, description = "Check if elements from all appropriate pages are sorted correct by FROM_CHEAP")
    public void checkSortingCheap() {
        basePage.open(HOME_PAGE);



        homePage.chooseFromCatalog(FOR_GAMERS);
        listingPage.chooseSorting(FROM_CHEAP);
        listingPage.isSortedAsc(
                listingPage.getPricesOfElementsFromAllPages());

    }

}
