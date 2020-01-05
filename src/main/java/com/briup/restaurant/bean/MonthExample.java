package com.briup.restaurant.bean;

import java.util.ArrayList;
import java.util.List;

public class MonthExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MonthExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andMonthIsNull() {
            addCriterion("month is null");
            return (Criteria) this;
        }

        public Criteria andMonthIsNotNull() {
            addCriterion("month is not null");
            return (Criteria) this;
        }

        public Criteria andMonthEqualTo(String value) {
            addCriterion("month =", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthNotEqualTo(String value) {
            addCriterion("month <>", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthGreaterThan(String value) {
            addCriterion("month >", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthGreaterThanOrEqualTo(String value) {
            addCriterion("month >=", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthLessThan(String value) {
            addCriterion("month <", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthLessThanOrEqualTo(String value) {
            addCriterion("month <=", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthLike(String value) {
            addCriterion("month like", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthNotLike(String value) {
            addCriterion("month not like", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthIn(List<String> values) {
            addCriterion("month in", values, "month");
            return (Criteria) this;
        }

        public Criteria andMonthNotIn(List<String> values) {
            addCriterion("month not in", values, "month");
            return (Criteria) this;
        }

        public Criteria andMonthBetween(String value1, String value2) {
            addCriterion("month between", value1, value2, "month");
            return (Criteria) this;
        }

        public Criteria andMonthNotBetween(String value1, String value2) {
            addCriterion("month not between", value1, value2, "month");
            return (Criteria) this;
        }

        public Criteria andTotalPriceIsNull() {
            addCriterion("total_price is null");
            return (Criteria) this;
        }

        public Criteria andTotalPriceIsNotNull() {
            addCriterion("total_price is not null");
            return (Criteria) this;
        }

        public Criteria andTotalPriceEqualTo(Double value) {
            addCriterion("total_price =", value, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceNotEqualTo(Double value) {
            addCriterion("total_price <>", value, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceGreaterThan(Double value) {
            addCriterion("total_price >", value, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("total_price >=", value, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceLessThan(Double value) {
            addCriterion("total_price <", value, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceLessThanOrEqualTo(Double value) {
            addCriterion("total_price <=", value, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceIn(List<Double> values) {
            addCriterion("total_price in", values, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceNotIn(List<Double> values) {
            addCriterion("total_price not in", values, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceBetween(Double value1, Double value2) {
            addCriterion("total_price between", value1, value2, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceNotBetween(Double value1, Double value2) {
            addCriterion("total_price not between", value1, value2, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalOrderIsNull() {
            addCriterion("total_order is null");
            return (Criteria) this;
        }

        public Criteria andTotalOrderIsNotNull() {
            addCriterion("total_order is not null");
            return (Criteria) this;
        }

        public Criteria andTotalOrderEqualTo(Integer value) {
            addCriterion("total_order =", value, "totalOrder");
            return (Criteria) this;
        }

        public Criteria andTotalOrderNotEqualTo(Integer value) {
            addCriterion("total_order <>", value, "totalOrder");
            return (Criteria) this;
        }

        public Criteria andTotalOrderGreaterThan(Integer value) {
            addCriterion("total_order >", value, "totalOrder");
            return (Criteria) this;
        }

        public Criteria andTotalOrderGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_order >=", value, "totalOrder");
            return (Criteria) this;
        }

        public Criteria andTotalOrderLessThan(Integer value) {
            addCriterion("total_order <", value, "totalOrder");
            return (Criteria) this;
        }

        public Criteria andTotalOrderLessThanOrEqualTo(Integer value) {
            addCriterion("total_order <=", value, "totalOrder");
            return (Criteria) this;
        }

        public Criteria andTotalOrderIn(List<Integer> values) {
            addCriterion("total_order in", values, "totalOrder");
            return (Criteria) this;
        }

        public Criteria andTotalOrderNotIn(List<Integer> values) {
            addCriterion("total_order not in", values, "totalOrder");
            return (Criteria) this;
        }

        public Criteria andTotalOrderBetween(Integer value1, Integer value2) {
            addCriterion("total_order between", value1, value2, "totalOrder");
            return (Criteria) this;
        }

        public Criteria andTotalOrderNotBetween(Integer value1, Integer value2) {
            addCriterion("total_order not between", value1, value2, "totalOrder");
            return (Criteria) this;
        }

        public Criteria andFoodOneIsNull() {
            addCriterion("food_one is null");
            return (Criteria) this;
        }

        public Criteria andFoodOneIsNotNull() {
            addCriterion("food_one is not null");
            return (Criteria) this;
        }

        public Criteria andFoodOneEqualTo(Integer value) {
            addCriterion("food_one =", value, "foodOne");
            return (Criteria) this;
        }

        public Criteria andFoodOneNotEqualTo(Integer value) {
            addCriterion("food_one <>", value, "foodOne");
            return (Criteria) this;
        }

        public Criteria andFoodOneGreaterThan(Integer value) {
            addCriterion("food_one >", value, "foodOne");
            return (Criteria) this;
        }

        public Criteria andFoodOneGreaterThanOrEqualTo(Integer value) {
            addCriterion("food_one >=", value, "foodOne");
            return (Criteria) this;
        }

        public Criteria andFoodOneLessThan(Integer value) {
            addCriterion("food_one <", value, "foodOne");
            return (Criteria) this;
        }

        public Criteria andFoodOneLessThanOrEqualTo(Integer value) {
            addCriterion("food_one <=", value, "foodOne");
            return (Criteria) this;
        }

        public Criteria andFoodOneIn(List<Integer> values) {
            addCriterion("food_one in", values, "foodOne");
            return (Criteria) this;
        }

        public Criteria andFoodOneNotIn(List<Integer> values) {
            addCriterion("food_one not in", values, "foodOne");
            return (Criteria) this;
        }

        public Criteria andFoodOneBetween(Integer value1, Integer value2) {
            addCriterion("food_one between", value1, value2, "foodOne");
            return (Criteria) this;
        }

        public Criteria andFoodOneNotBetween(Integer value1, Integer value2) {
            addCriterion("food_one not between", value1, value2, "foodOne");
            return (Criteria) this;
        }

        public Criteria andFoodTwoIsNull() {
            addCriterion("food_two is null");
            return (Criteria) this;
        }

        public Criteria andFoodTwoIsNotNull() {
            addCriterion("food_two is not null");
            return (Criteria) this;
        }

        public Criteria andFoodTwoEqualTo(Integer value) {
            addCriterion("food_two =", value, "foodTwo");
            return (Criteria) this;
        }

        public Criteria andFoodTwoNotEqualTo(Integer value) {
            addCriterion("food_two <>", value, "foodTwo");
            return (Criteria) this;
        }

        public Criteria andFoodTwoGreaterThan(Integer value) {
            addCriterion("food_two >", value, "foodTwo");
            return (Criteria) this;
        }

        public Criteria andFoodTwoGreaterThanOrEqualTo(Integer value) {
            addCriterion("food_two >=", value, "foodTwo");
            return (Criteria) this;
        }

        public Criteria andFoodTwoLessThan(Integer value) {
            addCriterion("food_two <", value, "foodTwo");
            return (Criteria) this;
        }

        public Criteria andFoodTwoLessThanOrEqualTo(Integer value) {
            addCriterion("food_two <=", value, "foodTwo");
            return (Criteria) this;
        }

        public Criteria andFoodTwoIn(List<Integer> values) {
            addCriterion("food_two in", values, "foodTwo");
            return (Criteria) this;
        }

        public Criteria andFoodTwoNotIn(List<Integer> values) {
            addCriterion("food_two not in", values, "foodTwo");
            return (Criteria) this;
        }

        public Criteria andFoodTwoBetween(Integer value1, Integer value2) {
            addCriterion("food_two between", value1, value2, "foodTwo");
            return (Criteria) this;
        }

        public Criteria andFoodTwoNotBetween(Integer value1, Integer value2) {
            addCriterion("food_two not between", value1, value2, "foodTwo");
            return (Criteria) this;
        }

        public Criteria andFoodThreeIsNull() {
            addCriterion("food_three is null");
            return (Criteria) this;
        }

        public Criteria andFoodThreeIsNotNull() {
            addCriterion("food_three is not null");
            return (Criteria) this;
        }

        public Criteria andFoodThreeEqualTo(Integer value) {
            addCriterion("food_three =", value, "foodThree");
            return (Criteria) this;
        }

        public Criteria andFoodThreeNotEqualTo(Integer value) {
            addCriterion("food_three <>", value, "foodThree");
            return (Criteria) this;
        }

        public Criteria andFoodThreeGreaterThan(Integer value) {
            addCriterion("food_three >", value, "foodThree");
            return (Criteria) this;
        }

        public Criteria andFoodThreeGreaterThanOrEqualTo(Integer value) {
            addCriterion("food_three >=", value, "foodThree");
            return (Criteria) this;
        }

        public Criteria andFoodThreeLessThan(Integer value) {
            addCriterion("food_three <", value, "foodThree");
            return (Criteria) this;
        }

        public Criteria andFoodThreeLessThanOrEqualTo(Integer value) {
            addCriterion("food_three <=", value, "foodThree");
            return (Criteria) this;
        }

        public Criteria andFoodThreeIn(List<Integer> values) {
            addCriterion("food_three in", values, "foodThree");
            return (Criteria) this;
        }

        public Criteria andFoodThreeNotIn(List<Integer> values) {
            addCriterion("food_three not in", values, "foodThree");
            return (Criteria) this;
        }

        public Criteria andFoodThreeBetween(Integer value1, Integer value2) {
            addCriterion("food_three between", value1, value2, "foodThree");
            return (Criteria) this;
        }

        public Criteria andFoodThreeNotBetween(Integer value1, Integer value2) {
            addCriterion("food_three not between", value1, value2, "foodThree");
            return (Criteria) this;
        }

        public Criteria andFoodFourIsNull() {
            addCriterion("food_four is null");
            return (Criteria) this;
        }

        public Criteria andFoodFourIsNotNull() {
            addCriterion("food_four is not null");
            return (Criteria) this;
        }

        public Criteria andFoodFourEqualTo(Integer value) {
            addCriterion("food_four =", value, "foodFour");
            return (Criteria) this;
        }

        public Criteria andFoodFourNotEqualTo(Integer value) {
            addCriterion("food_four <>", value, "foodFour");
            return (Criteria) this;
        }

        public Criteria andFoodFourGreaterThan(Integer value) {
            addCriterion("food_four >", value, "foodFour");
            return (Criteria) this;
        }

        public Criteria andFoodFourGreaterThanOrEqualTo(Integer value) {
            addCriterion("food_four >=", value, "foodFour");
            return (Criteria) this;
        }

        public Criteria andFoodFourLessThan(Integer value) {
            addCriterion("food_four <", value, "foodFour");
            return (Criteria) this;
        }

        public Criteria andFoodFourLessThanOrEqualTo(Integer value) {
            addCriterion("food_four <=", value, "foodFour");
            return (Criteria) this;
        }

        public Criteria andFoodFourIn(List<Integer> values) {
            addCriterion("food_four in", values, "foodFour");
            return (Criteria) this;
        }

        public Criteria andFoodFourNotIn(List<Integer> values) {
            addCriterion("food_four not in", values, "foodFour");
            return (Criteria) this;
        }

        public Criteria andFoodFourBetween(Integer value1, Integer value2) {
            addCriterion("food_four between", value1, value2, "foodFour");
            return (Criteria) this;
        }

        public Criteria andFoodFourNotBetween(Integer value1, Integer value2) {
            addCriterion("food_four not between", value1, value2, "foodFour");
            return (Criteria) this;
        }

        public Criteria andFoodFiveIsNull() {
            addCriterion("food_five is null");
            return (Criteria) this;
        }

        public Criteria andFoodFiveIsNotNull() {
            addCriterion("food_five is not null");
            return (Criteria) this;
        }

        public Criteria andFoodFiveEqualTo(Integer value) {
            addCriterion("food_five =", value, "foodFive");
            return (Criteria) this;
        }

        public Criteria andFoodFiveNotEqualTo(Integer value) {
            addCriterion("food_five <>", value, "foodFive");
            return (Criteria) this;
        }

        public Criteria andFoodFiveGreaterThan(Integer value) {
            addCriterion("food_five >", value, "foodFive");
            return (Criteria) this;
        }

        public Criteria andFoodFiveGreaterThanOrEqualTo(Integer value) {
            addCriterion("food_five >=", value, "foodFive");
            return (Criteria) this;
        }

        public Criteria andFoodFiveLessThan(Integer value) {
            addCriterion("food_five <", value, "foodFive");
            return (Criteria) this;
        }

        public Criteria andFoodFiveLessThanOrEqualTo(Integer value) {
            addCriterion("food_five <=", value, "foodFive");
            return (Criteria) this;
        }

        public Criteria andFoodFiveIn(List<Integer> values) {
            addCriterion("food_five in", values, "foodFive");
            return (Criteria) this;
        }

        public Criteria andFoodFiveNotIn(List<Integer> values) {
            addCriterion("food_five not in", values, "foodFive");
            return (Criteria) this;
        }

        public Criteria andFoodFiveBetween(Integer value1, Integer value2) {
            addCriterion("food_five between", value1, value2, "foodFive");
            return (Criteria) this;
        }

        public Criteria andFoodFiveNotBetween(Integer value1, Integer value2) {
            addCriterion("food_five not between", value1, value2, "foodFive");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}