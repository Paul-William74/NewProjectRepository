package com.Ecommerce.demo.Components;

import com.Ecommerce.demo.Model.Product.MATERIAL;
import com.Ecommerce.demo.Model.Product.ProductSize;
import org.springframework.stereotype.Component;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public final class Sorter {

    public List<ProductSize> sortSizesASC(List<ProductSize> productSizeList) {
        productSizeList.sort(Comparator.comparing(ProductSize::getSize));
        return productSizeList;
    }

    public List<ProductSize> sortSizesDESC(List<ProductSize> productSizeList) {
        productSizeList.sort(Comparator.comparing(ProductSize::getSize).reversed());
        return productSizeList;
    }


    public Set<MATERIAL> sortMaterialsASC(Set<MATERIAL> materials) {
        return materials.stream()
                .sorted(Comparator.comparing(MATERIAL::name))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
