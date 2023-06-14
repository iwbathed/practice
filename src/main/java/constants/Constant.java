package constants;

public class Constant {
    public static class TimeOutVariable {
        public static final int IMPLICITLY_WAIT = 4;
        public static final int EXPLICITLY_WAIT = 20;

    }
    public static class Urls {
        public static final String HOME_PAGE = "https://www.3ona51.com/ua/";
    }

    public enum CatalogNames {
        FOR_GAMERS("Товари для геймерів"),
        PC_AND_PERIPHERALS("ПК та периферія"),
        ACOUSTICS("Акустика"),
        GAME_CONSOLES("Ігрові консолі"),
        CHAIRS_AND_TABLES("Крісла і столи"),
        CLOTHES_AND_BACKPACKS("Одяг і рюкзаки"),
        ACCESSORIES_AND_SOUVENIRS("Атрибутика та сувеніри"),
        SALE("Розпродаж"),
        ELECTRIC_TRANSPORT("Електротранспорт"),
        NOVELTY("Новинки"),
        PROMOTIONAL("Акційні");

        private final String value;

        CatalogNames(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
    public enum SortTypes {
        FROM_CHEAP("від дешевих"),
        FROM_EXPENSIVE("від дорогих"),
        BY_NAME("найменуванню"),
        BY_RATING("рейтингу"),
        DEFAULT_VALUE("замовчуванням");

        private final String value;

        SortTypes(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
    public enum SortDirection {
        ASCENDING,
        DESCENDING
    }
    public enum PageDirection {
        NEXT,
        PREVIOUS
    }

}
