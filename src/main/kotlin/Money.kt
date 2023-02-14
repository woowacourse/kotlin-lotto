class Money(val price: Int?) {

    init {
        require(price != null) { INPUT_MONEY_NULL_ERROR_MESSAGE}
    }

    companion object {
        const val INPUT_MONEY_NULL_ERROR_MESSAGE = "금액이 입력되지 않았습니다."
    }
}