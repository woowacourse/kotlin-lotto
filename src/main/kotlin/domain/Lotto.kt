package domain

class Lotto(val numbers: Set<Int>) {
    init {
        require(numbers.size == 6) { "로또 번호는 6개여야 합니다." }
        require(numbers.all { number -> number in 1..45 } ) { "로또 번호는 1이상 45이하여야 합니다." }
    }
}