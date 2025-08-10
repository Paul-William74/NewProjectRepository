package com.Ecommerce.demo.Controller.WaitingList;


import com.Ecommerce.demo.Service.WaitingList.WaitingListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/waiting-lists")
@RequiredArgsConstructor
public final class WaitingListController {

    private final WaitingListService waitingListService;

    @PostMapping("/add/{product_size_id}/{customer_id}/{material}")
    public ResponseEntity<?> addToWaitingList(
            @PathVariable final Long product_size_id,
            @PathVariable final String material,
            @PathVariable final Long customer_id
    ) {
        return this.waitingListService.addToWaitingList(customer_id, product_size_id, material);
    }



}
