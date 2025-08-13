package com.Ecommerce.demo.Components;

import com.Ecommerce.demo.Model.Product.MATERIAL;
import org.springframework.stereotype.Component;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public final class Sorter {


    public Set<MATERIAL> sortMaterialsASC(Set<MATERIAL> materials) {
        return materials.stream()
                .sorted(Comparator.comparing(MATERIAL::name))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
