package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ItemValidator implements Validator {

    // 파라미터로 넘어오는 object의 검증을 지원하는지 여부
    @Override
    public boolean supports(Class<?> clazz) {
        return Item.class.isAssignableFrom(clazz);
        // item의 child class도 가능하게 하기위해 isAssignableFrom을 사용함
    }

    // 실제 검증
    @Override
    public void validate(Object target, Errors errors) {
        Item item = (Item) target;
        //Errors는 bindingResult의 super class
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "itemName", "required");

        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() >1000000) {
            errors.rejectValue("price", "range", new Object[]{1000, 1000000}, null);
        }
        if (item.getQuantity() == null || item.getQuantity() > 10000) {
            errors.rejectValue("quantity", "max", new Object[]{9999}, null);
        }
        //특정 필드 예외가 아닌 전체 예외
        if (item.getPrice() != null && item.getQuantity() != null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if (resultPrice < 10000) {
                errors.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
            }
        }
    }
}
