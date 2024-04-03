package exercise.mapper;

import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.model.Category;
import exercise.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

// BEGIN
@Mapper(
        uses = { JsonNullableMapper.class, ReferenceMapper.class },
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class ProductMapper {
    @Autowired
    private ReferenceMapper referenceMapper;
    @Mapping(target = "category", source = "categoryId")
    public abstract Product map(ProductCreateDTO dto);

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.name", target = "categoryName")
    public abstract ProductDTO map(Product model);

    public Product update(ProductUpdateDTO dto, @MappingTarget Product model) {
        // Извлекаем значение Long из JsonNullable<Long> для категории, если оно не null
        if (dto.getCategoryId() != null) {
            Long categoryId = dto.getCategoryId().get();
            Category updatedCategory = referenceMapper.toEntity(categoryId, Category.class);
            model.setCategory(updatedCategory);
        }

        // Обновляем заголовок продукта, если он не null
        if (dto.getTitle() != null) {
            String title = dto.getTitle().get();
            model.setTitle(title);
        }

        // Обновляем цену продукта, если она не null
        if (dto.getPrice() != null) {
            Integer price = dto.getPrice().get();
            model.setPrice(price);
        }

        return model;
    }
}
// END
