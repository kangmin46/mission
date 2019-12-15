package com.example.demo.component;

import com.example.demo.utils.LinearRegression;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LinearRegressionTest {

    @Autowired
    private LinearRegression linearRegression;

    @Test
    @DisplayName("선형 회귀알고리즘을 이용하여 API 문서에 적힌 근사치와 비교해보는 테스트")
    void predict_result_test() {
        List<Double> doubles = Arrays.asList(864.0, 416.0, 263.0, 1659.0, 394.0, 2233.0, 1140.0, 2527.0, 3486.0, 2932.0, 3906.0, 5073.0, 3278.0);
        linearRegression.addData(doubles);
        assertThat(linearRegression.predict(doubles, 14)).isCloseTo(4850.0, Offset.offset(71.0));
    }
}
