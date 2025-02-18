package lotto.model

class Lotto(numbers: List<Int>) {
    init {
        require(numbers.size == 6) { "[ERROR] 로또는 6개의 번호만 가질 수 있습니다." }
    }
}
