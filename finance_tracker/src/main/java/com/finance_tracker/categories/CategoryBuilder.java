package com.finance_tracker.categories;

public class CategoryBuilder {
    private Category MainCat;

    public CategoryBuilder(String Name){
        this.MainCat = new Category();
        this.MainCat.setName(Name);
    }

    public CategoryBuilder setEnum(CategoryEnum E){
        this.MainCat.setType(E);
        return this;}

    public Category buildCategory(){
        return this.MainCat;
    }

}
