package onlinebookstore.dto;

public record BookSearchParameters(String[] titles,
                                   String[] authors,
                                   String[] prices,
                                   String[] descriptions) {
}
