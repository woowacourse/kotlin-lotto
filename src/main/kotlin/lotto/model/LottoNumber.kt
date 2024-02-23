package lotto.model

data class LottoNumber(val number: String) {
    init {
        requireNotNull(number.toIntOrNull()) { "로또 번호는 숫자로 입력해주세요." }
        require(number.toInt() in NUMBER_RANGE) { "로또 번호의 범위는 1~45 사이의 자연수입니다." }
    }

    constructor(inputNumber: Int) : this(inputNumber.toString())

    override fun toString(): String = number

    companion object {
        val NUMBER_RANGE = (1..45)
    }
}
