package test.listing;

import constants.Constant;
import org.testng.annotations.Test;
import test.base.BaseTest;

import static constants.Constant.CatalogNames;
import static constants.Constant.SortTypes;
import static constants.Constant.Urls.HOME_PAGE;


public class ListingTest extends BaseTest {


    @Test(priority = 1, description = "Check if elements from all appropriate pages are sorted correct by FROM_CHEAP")
    public void checkSortingCheap() {
        basePage.open(HOME_PAGE);

        homePage.chooseFromCatalog(CatalogNames.FOR_GAMERS);
        listingPage.chooseSorting(SortTypes.FROM_CHEAP);
        listingPage.verifySorted(
                listingPage.getPricesOfElementsFromAllPages(),
                Constant.SortDirection.ASCENDING
        );

    }

}
