package lotto.model

class Lotto(numbers: List<Int>) {
    init {
        require(numbers.size == 6) { "[ERROR] 로또는 6개의 번호만 가질 수 있습니다." }
        numbers.forEach { number ->
            require(number in 1..45) { "[ERROR] 로또 번호의 범위는 1 이상 45 이하여야 합니다." }
        }
    }
}
