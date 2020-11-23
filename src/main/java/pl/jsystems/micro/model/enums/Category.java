package pl.jsystems.micro.model.enums;

import lombok.Getter;

@Getter
public enum Category {
    IT("IT"),
    DEV_OPS("Dev Ops"),
    TEST("Testing");

    private String categoryName;

    Category(String categoryName) {
        this.categoryName = categoryName;
    }

}
