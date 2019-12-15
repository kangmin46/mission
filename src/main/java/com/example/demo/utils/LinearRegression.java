package com.example.demo.utils;

import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LinearRegression {
    private SimpleRegression simpleRegression;

    public LinearRegression() {
        simpleRegression = new SimpleRegression();
    }

    public double predict(List<Double> recommendDatas, int predictVariable) {
        addData(recommendDatas);
        return simpleRegression.predict(predictVariable);
    }

    public void addData(List<Double> recommendDatas) {
        recommendDatas.stream()
            .forEach(recommendData ->
                simpleRegression.addData(recommendDatas.indexOf(recommendData), recommendData));
    }
}
