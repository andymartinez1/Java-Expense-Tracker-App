package com.andymartinez1.expense.mapper;

import com.andymartinez1.expense.dto.CategoryDto;
import com.andymartinez1.expense.entity.Category;

public class CategoryMapper {
    public static Category mapToCategory(CategoryDto categoryDto) {
        return new Category(
                categoryDto.id(),
                categoryDto.name()
        );
    }

    public static CategoryDto mapToCategoryDto(Category category) {
        return new CategoryDto(
                category.getId(),
                category.getName()
        );
    }
}
