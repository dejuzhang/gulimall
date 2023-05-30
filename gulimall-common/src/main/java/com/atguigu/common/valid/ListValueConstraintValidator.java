package com.atguigu.common.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;

/**
 * @Author dejuz
 * @Date 2023/5/20 19:34
 */
public class ListValueConstraintValidator implements ConstraintValidator<ListValue, Integer> {
    private HashSet<Integer> set=new HashSet<>();
    @Override
    public void initialize(ListValue listValue) {
        int[] vals = listValue.vals();
        for (int val : vals) {
            set.add(val);
        }
    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        return set.contains(integer);
    }
}
