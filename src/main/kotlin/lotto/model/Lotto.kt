package lotto.model

class Lotto(
    val numbers: List<LottoNumber>,
) {
    init {
        require(numbers.size == numbers.distinct().size) { "중복된 숫자가 존재합니다." }
    }
}
