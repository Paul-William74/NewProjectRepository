package com.Ecommerce.demo.DTO.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public final class ReviewDisplayContainerDTO {

    private List<ReviewDisplayDTO> reviews;
    private List<QuestionAnswerDisplayDTO> questionAnswers;
}
