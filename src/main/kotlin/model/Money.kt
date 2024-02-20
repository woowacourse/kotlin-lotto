package model

class Money(val value: Int) {
    init {
        require(value >= 1000) { MINIMUM_VALUE_EXCEPTION_MESSAGE }
    }

    companion object {
        const val MINIMUM_VALUE_EXCEPTION_MESSAGE = "구입금액은 1000원 이상이어야 합니다."
    }
}
