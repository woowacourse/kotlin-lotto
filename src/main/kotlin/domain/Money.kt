package domain

class Money(val price: Int?) {

    init {
        require(price != null) { INPUT_MONEY_NULL_ERROR_MESSAGE }
        require(price >= 0) { INPUT_MONEY_NEGATIVE_ERROR_MESSAGE }
        require(price % 1000 == 0) { INPUT_MONEY_NOT_DIVIDE_ERROR_MESSAGE }
    }

    companion object {
        const val INPUT_MONEY_NULL_ERROR_MESSAGE = "금액이 입력되지 않았습니다."
        const val INPUT_MONEY_NEGATIVE_ERROR_MESSAGE = "금액은 양수여야 합니다."
        const val INPUT_MONEY_NOT_DIVIDE_ERROR_MESSAGE = "금액은 1000원으로 나누어떨어져야 합니다."
    }
}