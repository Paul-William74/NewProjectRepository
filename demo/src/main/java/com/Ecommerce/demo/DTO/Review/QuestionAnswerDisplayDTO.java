package com.Ecommerce.demo.DTO.Review;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public final class QuestionAnswerDisplayDTO {

    private Long questionId;
    private String questionAsked;
    private String questionResponse;
}
